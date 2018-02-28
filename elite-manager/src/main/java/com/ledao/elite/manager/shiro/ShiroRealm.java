package com.ledao.elite.manager.shiro;

import java.util.Collection;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ledao.elite.core.domain.sys.Attas;
import com.ledao.elite.core.domain.sys.SysMenu;
import com.ledao.elite.core.domain.sys.SysUser;
import com.ledao.elite.core.domain.sys.SysUserRole;
import com.ledao.elite.core.framework.constant.GlobalDefinedConstant;
import com.ledao.elite.core.service.sys.SysMenuService;
import com.ledao.elite.core.service.sys.SysUserService;
import com.ledao.elite.core.utils.encry.Digests;
import com.ledao.elite.core.utils.encry.Encodes;
import com.ledao.elite.manager.controller.CaptchaController;

import lombok.extern.slf4j.Slf4j;

/**
 * shiro 安全登录
 * 
 * @author kobe.liu
 */
@Slf4j
@Service
@Transactional
public class ShiroRealm extends AuthorizingRealm {

	private static final boolean useCaptcha = true;// 是否需要验证验证码

	private static final String HASH_ALGORITHM = "MD5";

	@Resource
	private SysUserService sysUserService;

	@Resource
	private SysMenuService sysMenuService;

	@Resource
	private Principal principal;

	public ShiroRealm() {
		setName("shiroRealm");
		setCredentialsMatcher(new SimpleCredentialsMatcher());
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authtoken) throws AuthenticationException {
		CaptchaUsernamePasswordToken token = (CaptchaUsernamePasswordToken) authtoken;
		if (useCaptcha) {
			String parm = token.getCaptcha();
			if (SecurityUtils.getSubject().getSession().getAttribute(CaptchaController.CAPTCHA_KEY) == null) {
				throw new IncorrectCaptchaException("验证码过时,请更换一张");
			} else {
				String c = (String) SecurityUtils.getSubject().getSession().getAttribute(CaptchaController.CAPTCHA_KEY);
				// 忽略大小写
				if (!c.equalsIgnoreCase(parm)) {
					throw new IncorrectCaptchaException("验证码错误");
				}
			}
		}
		SysUser user = sysUserService.findSysUserByLoginName(token.getUsername());
		if (user == null) {
			throw new UnknownAccountException("帐号不存在");
		}
		if (user.getStatus().equals(GlobalDefinedConstant.System_Status.disabled.name())) {
			throw new DisabledAccountException("帐号被冻结");
		} else if (user.getStatus().equals(GlobalDefinedConstant.System_Status.deleted.name())) {
			throw new LockedAccountException("帐号未开通");
		}
		byte[] salt = Encodes.decodeHex(user.getPassSalt());
		String pass = new String(token.getPassword());
		byte[] psalt = pass.getBytes();
		String inpwd = Encodes.encodeHex(Digests.md5(psalt, salt));
		if (!user.getPassword().equals(inpwd)) {
			throw new IncorrectCredentialsException("帐号密码错误");
		}
		Long userId = user.getId();
		principal.setUserId(userId);
		principal.setLoginName(user.getLoginName());
		principal.setUserName(user.getUserName());
		if(user.getUserPhoto()!=null){
			principal.setPhotoId(user.getUserPhoto());
			Attas ap=user.getPhoto();
			principal.setPhotoPath(ap==null?null:ap.getPath());
		}
		List<SysUserRole> roles = user.getRoles();
		Long roleId = roles.isEmpty() ? null : roles.get(0).getRoleId();
		principal.setRoleId(roleId);
		// 登录成功，更新用户最后登录时间
		this.sysUserService.updateUserLastLogin(userId, token.getHost());
		return new SimpleAuthenticationInfo(principal, user.getPassword(), ByteSource.Util.bytes(salt), getName());
	}

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		Principal principal = (Principal) principals.getPrimaryPrincipal();
		List<SysMenu> menus = this.sysMenuService.findSysUserAllMenus(principal.getUserId());
		log.info("拥有权限数量："+(menus.isEmpty()?0:menus.size())+"条");
		for (SysMenu menu : menus) {
			if (menu != null && StringUtils.isNotEmpty(menu.getFlag())) {
				log.info("拥有权限点："+menu.getFlag());
				info.addStringPermission(menu.getFlag());
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
