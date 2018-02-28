package com.ledao.elite.site.shiro;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ledao.elite.core.domain.member.MemberBasic;
import com.ledao.elite.core.domain.member.MemberElite;
import com.ledao.elite.core.domain.member.MemberIdentity;
import com.ledao.elite.core.domain.member.MemberIdentity.MemberIdentity_Type;
import com.ledao.elite.core.domain.member.MemberPassport;
import com.ledao.elite.core.domain.member.MemberPassport.Member_Channel;
import com.ledao.elite.core.domain.member.MemberPassport.Member_Status;
import com.ledao.elite.core.framework.shiro.sso.ITokenUserManage;
import com.ledao.elite.core.framework.shiro.sso.SSOToken;
import com.ledao.elite.core.framework.shiro.sso.TokenGenerateFactory;
import com.ledao.elite.core.domain.member.MemberRole;
import com.ledao.elite.core.domain.member.SitePower;
import com.ledao.elite.core.domain.member.SiteRole;
import com.ledao.elite.core.domain.member.SiteRolePower;
import com.ledao.elite.core.service.member.MemberBasicService;
import com.ledao.elite.core.service.member.MemberEliteService;
import com.ledao.elite.core.service.member.MemberIdentityService;
import com.ledao.elite.core.service.member.MemberPassportService;
import com.ledao.elite.core.utils.encry.Digests;
import com.ledao.elite.core.utils.encry.Encodes;

/**
 * shiro 安全登录
 * 
 * @author kobe.liu
 * */
@Service
@Transactional
public class ShiroRealm extends AuthorizingRealm {
	
	private static final String HASH_ALGORITHM="MD5";
	
	@Resource
	private MemberPassportService memberPassportService;
	@Resource
	private MemberIdentityService memberIdentityService;
	@Resource
	private MemberBasicService memberBasicService;
	@Resource
	private MemberEliteService memberEliteService;
	
	@Resource(name="tokenUserRAMManage")
	private ITokenUserManage tokenUserManage;
	
	@Resource
	private Principal principal;

	public ShiroRealm() {
		setName("shiroRealm");
		setCredentialsMatcher(new SimpleCredentialsMatcher());
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authtoken) throws AuthenticationException {
		UsernamePasswordToken token = (UsernamePasswordToken) authtoken;
		MemberPassport user = memberPassportService.findMemberPassportByAccount(token.getUsername());
		if (user == null) {
			throw new UnknownAccountException("帐号不存在");
		}
		if (user.getStatus().equals(Member_Status.disabled)) {
			throw new DisabledAccountException("帐号被冻结");
		} else if (user.getStatus().equals(Member_Status.deleted)) {
			throw new LockedAccountException("帐号未开通");
		}
		byte[] salt = Encodes.decodeHex(user.getPassSalt());
		String pass = new String(token.getPassword());
		byte[] psalt = pass.getBytes();
		String inpwd = Encodes.encodeHex(Digests.md5(psalt, salt));
		if (!user.getPassword().equals(inpwd)) {
			throw new IncorrectCredentialsException("帐号密码错误");
		}
		Long memberId=user.getId();
		//存储会员登录token
		Map<String,String> userMap = new HashMap<String, String>();
    	userMap.put("channel", Member_Channel.PC.name());
    	userMap.put("account", user.getAccount());
    	String accesToken = TokenGenerateFactory.genToken(userMap);
    	SSOToken ssoToken=new SSOToken(memberId, accesToken, new Date());
		tokenUserManage.persistenceUser(accesToken, ssoToken);
		//存储登录会员session信息
		MemberBasic basic=this.memberBasicService.findMemberBasicByMemberId(memberId);
		if(user.getCurrentType().equals(MemberIdentity_Type.cto.name()) || user.getCurrentType().equals(MemberIdentity_Type.elite.name())){
			MemberElite elite = memberEliteService.findMemberEliteByMemberId(memberId);
			principal.setStatus(elite.getStatus());
		}
		principal.setMemberId(memberId);
		principal.setCurrentType(user.getCurrentType());;
		principal.setMemberNum(user.getMemberNum());
		principal.setNickName(user.getNickName());
		if(basic!=null&&basic.getPhotoId()!=null){
			principal.setHeader(basic.getMemberPhoto().getPath());
		}
		principal.setToken(accesToken);
		List<MemberIdentity> identitys=memberIdentityService.findMemberIdentityListByMemberId(user.getId());
		Map<String,String> identityMap=identitys.stream().collect(Collectors.toMap(MemberIdentity::getType,MemberIdentity::getName));
		principal.setIdentityMap(identityMap);
		//更新会员最后登录时间
		this.memberPassportService.updateMemberLastLogin(memberId,user.getAccount(),token.getHost());
		return new SimpleAuthenticationInfo(principal, user.getPassword(), ByteSource.Util.bytes(salt), getName());
	}
	

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		Principal principal = (Principal) principals.getPrimaryPrincipal();
		MemberPassport user = this.memberPassportService.findMemberPassportById(principal.getMemberId());
		List<MemberRole> roles = user.getRoles();
		for (MemberRole memberRole : roles) {
			SiteRole role = memberRole.getSiteRole();
			info.addRole(role.getName());
			List<SiteRolePower> rolePower = role.getPowers();
			for (SiteRolePower srp : rolePower) {
				SitePower power = srp.getSitePower();
				if (power != null && StringUtils.isNotEmpty(power.getFlag())) {
					info.addStringPermission(power.getFlag());
				}
			}
		}
		return info;
	}
	
	/**
     * 设定Password校验的Hash算法与迭代次数.
     */
    @PostConstruct
    public void initCredentialsMatcher() {
        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher(HASH_ALGORITHM);
        matcher.setHashIterations(1);
        setCredentialsMatcher(matcher);
    }

	@Override
	public boolean isPermitted(PrincipalCollection principals, String permission) {
		if (principals == null)
			return true;
		AuthorizationInfo info = this.getAuthorizationInfo(principals);
		Collection<String> pers = info.getStringPermissions();
		for (String per : pers) {
			if (per.equals(permission))
				return true;
		}
		return false;
	}
}
