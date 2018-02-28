package com.ledao.elite.site.shiro.cas;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cas.CasAuthenticationException;
import org.apache.shiro.cas.CasToken;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.util.CollectionUtils;
import org.apache.shiro.util.StringUtils;
import org.jasig.cas.client.authentication.AttributePrincipal;
import org.jasig.cas.client.validation.AbstractUrlBasedTicketValidator;
import org.jasig.cas.client.validation.Assertion;
import org.jasig.cas.client.validation.Cas20ServiceTicketValidator;
import org.jasig.cas.client.validation.Saml11TicketValidator;
import org.jasig.cas.client.validation.TicketValidationException;
import org.jasig.cas.client.validation.TicketValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.ledao.elite.core.domain.member.MemberPassport;
import com.ledao.elite.site.shiro.Principal;

/**
 * cas权限
 * */
public class CustomCasRealm extends AuthorizingRealm {
	
	// default name of the CAS attribute for remember me authentication (CAS 3.4.10+)
	public static final String DEFAULT_REMEMBER_ME_ATTRIBUTE_NAME = "longTermAuthenticationRequestTokenUsed";
	public static final String DEFAULT_VALIDATION_PROTOCOL = "CAS";

	private static Logger log = LoggerFactory.getLogger("ssoLoginFile");

	// this is the url of the CAS server (example : http://host:port/cas)
	private String casServerUrlPrefix;

	private boolean setHostnameVerifier;

	public boolean isSetHostnameVerifier() {
		return setHostnameVerifier;
	}

	public void setSetHostnameVerifier(boolean setHostnameVerifier) {
		this.setHostnameVerifier = setHostnameVerifier;
	}

	// this is the CAS service url of the application (example : http://host:port/mycontextpath/shiro-cas)
	private String casService;

	/* CAS protocol to use for ticket validation : CAS (default) or SAML :
       - CAS protocol can be used with CAS server version < 3.1 : in this case, no user attributes can be retrieved from the CAS ticket validation response (except if there are some customizations on CAS server side)
       - SAML protocol can be used with CAS server version >= 3.1 : in this case, user attributes can be extracted from the CAS ticket validation response
	 */
	private String validationProtocol = DEFAULT_VALIDATION_PROTOCOL;

	// default name of the CAS attribute for remember me authentication (CAS 3.4.10+)
	private String rememberMeAttributeName = DEFAULT_REMEMBER_ME_ATTRIBUTE_NAME;

	// this class from the CAS client is used to validate a service ticket on CAS server
	private TicketValidator ticketValidator;

	// default roles to applied to authenticated user
	private String defaultRoles;

	// default permissions to applied to authenticated user
	private String defaultPermissions;

	// names of attributes containing roles
	private String roleAttributeNames;

	// names of attributes containing permissions
	private String permissionAttributeNames;

	public CustomCasRealm() {
		setAuthenticationTokenClass(CasToken.class);
	}

	@Override
	protected void onInit() {
		super.onInit();
		ensureTicketValidator();
	}

	protected TicketValidator ensureTicketValidator() {
		if (this.ticketValidator == null) {
			this.ticketValidator = createTicketValidator();
		}
		return this.ticketValidator;
	}

	protected TicketValidator createTicketValidator() {
		String urlPrefix = getCasServerUrlPrefix();
		AbstractUrlBasedTicketValidator validator=null;
		if ("saml".equalsIgnoreCase(getValidationProtocol())) {
			validator=new Saml11TicketValidator(urlPrefix);
		}else{
			validator=new Cas20ServiceTicketValidator(urlPrefix);
		}
		validator.setEncoding("UTF-8");
		if(setHostnameVerifier){
			HostnameVerifier hv = new HostnameVerifier() {
				@Override
				public boolean verify(String arg0, SSLSession arg1) {
					log.debug("Warning: URL Host: " + arg0 + " vs. "+ arg1.getPeerHost());
					return true;
				}
			};

			validator.setHostnameVerifier(hv);
		}
		return validator;
	}

	/**
	 * Authenticates a user and retrieves its information.
	 * 
	 * @param token the authentication token
	 * @throws org.apache.shiro.authc.AuthenticationException if there is an error during authentication.
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		CasToken casToken = (CasToken) token;
		if (token == null) {
			return null;
		}
		String ticket = (String)casToken.getCredentials();
		if (!StringUtils.hasText(ticket)) {
			return null;
		}

		TicketValidator ticketValidator = ensureTicketValidator();
		try {
			// contact CAS server to validate service ticket
			Assertion casAssertion = ticketValidator.validate(ticket, getCasService());
			// get principal, user id and attributes
			AttributePrincipal casPrincipal = casAssertion.getPrincipal();
			log.info("casPrincipal = "+casPrincipal.toString());
			String userId = casPrincipal.getName();
			log.debug("Validate ticket : {} in CAS server : {} to retrieve user : {}", new Object[]{
					ticket, getCasServerUrlPrefix(), userId
			});

			Map<String, Object> attributes = casPrincipal.getAttributes();
			// refresh authentication token (user id + remember me)
			casToken.setUserId(userId);
			String rememberMeAttributeName = getRememberMeAttributeName();
			String rememberMeStringValue = (String)attributes.get(rememberMeAttributeName);
			boolean isRemembered = rememberMeStringValue != null && Boolean.parseBoolean(rememberMeStringValue);
			if (isRemembered) {
				casToken.setRememberMe(true);
			}
			// create simple authentication info
			SimpleAuthenticationInfo info = new SimpleAuthenticationInfo();
			MemberPassport shiroUser =null;
			//getUserInfo(Long.parseLong(userId));
			List<Object> principals = CollectionUtils.asList(shiroUser, attributes);
			PrincipalCollection principalCollection = new SimplePrincipalCollection(principals, getName());
			info.setCredentials(ticket);
			info.setPrincipals(principalCollection);
			log.info("登录用户:" + JSON.toJSONString(shiroUser));
			return info;
		} catch (TicketValidationException e) { 
			log.error("验证ticket出错", e);
			throw new CasAuthenticationException("Unable to validate ticket [" + ticket + "]", e);
		} catch(Exception e){
			log.error("验证ticket出错", e);
			throw new CasAuthenticationException("Unable to validate ticket [" + ticket + "]", e);
		}finally{

		}
	}

	/**
	 * Retrieves the AuthorizationInfo for the given principals (the CAS previously authenticated user : id + attributes).
	 * 
	 * @param principals the primary identifying principals of the AuthorizationInfo that should be retrieved.
	 * @return the AuthorizationInfo associated with this principals.
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		Principal principal = (Principal) principals.getPrimaryPrincipal();
		log.info("principal = "+principal.toString());
		return info;
	}

	public MemberPassport getUserInfo(Long userId){
		MemberPassport user =new MemberPassport();
		return user;
	}



	@Override
	public boolean isPermitted(PrincipalCollection principals, String permission) {
		if(principals==null) return true;

		AuthorizationInfo info = this.getAuthorizationInfo(principals);
		Collection<String> pers = info.getStringPermissions();
		for (String per : pers) {
			if(per.equals(permission)){
				return true;
			}
		}
		return false;
	}

	public String getCasServerUrlPrefix() {
		return casServerUrlPrefix;
	}

	public void setCasServerUrlPrefix(String casServerUrlPrefix) {
		this.casServerUrlPrefix = casServerUrlPrefix;
	}

	public String getCasService() {
		return casService;
	}

	public void setCasService(String casService) {
		this.casService = casService;
	}

	public String getValidationProtocol() {
		return validationProtocol;
	}

	public void setValidationProtocol(String validationProtocol) {
		this.validationProtocol = validationProtocol;
	}

	public String getRememberMeAttributeName() {
		return rememberMeAttributeName;
	}

	public void setRememberMeAttributeName(String rememberMeAttributeName) {
		this.rememberMeAttributeName = rememberMeAttributeName;
	}

	public String getDefaultRoles() {
		return defaultRoles;
	}

	public void setDefaultRoles(String defaultRoles) {
		this.defaultRoles = defaultRoles;
	}

	public String getDefaultPermissions() {
		return defaultPermissions;
	}

	public void setDefaultPermissions(String defaultPermissions) {
		this.defaultPermissions = defaultPermissions;
	}

	public String getRoleAttributeNames() {
		return roleAttributeNames;
	}

	public void setRoleAttributeNames(String roleAttributeNames) {
		this.roleAttributeNames = roleAttributeNames;
	}

	public String getPermissionAttributeNames() {
		return permissionAttributeNames;
	}

	public void setPermissionAttributeNames(String permissionAttributeNames) {
		this.permissionAttributeNames = permissionAttributeNames;
	}

} 
