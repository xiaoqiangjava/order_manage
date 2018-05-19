package cn.bsnt.web.shiro.realm;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.annotation.Resource;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import cn.bsnt.web.order.dao.PermissionDao;
import cn.bsnt.web.order.dao.RoleDao;
import cn.bsnt.web.order.dao.UserDao;
import cn.bsnt.web.order.entity.Permission;
import cn.bsnt.web.order.entity.Role;
import cn.bsnt.web.order.entity.User;
/**
 * shiro认证授权的数据源
 * @author xiaoqiang
 *
 */
@Component
public class SecurityRealm extends AuthorizingRealm {
	private static final Logger LOG = LoggerFactory.getLogger(SecurityRealm.class);

	@Resource
	private UserDao userDao;
	
	@Resource 
	private RoleDao roleDao;
	
	@Resource
	private PermissionDao permissionDao;
	
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principal) {
		String username = (String)principal.getPrimaryPrincipal();
		LOG.info("username:" + username);
		//通过username查询该用户对应的角色信息
		List<Role> roles = roleDao.findRolesByUsername(username);
		Collection<String> strPermissions = new ArrayList<String>();
		for(Role role : roles){
			Integer roleId = role.getRoleId();
			//根据roleId查询该角色对应的权限信息
			List<Permission> permissions = permissionDao.findPermissionsByRoleId(roleId);
			for(Permission permission : permissions){
				strPermissions.add(permission.getPermissionName());
			}
			
		}
		
		SimpleAuthorizationInfo  authorizationInfo = new SimpleAuthorizationInfo();
		
//		authorizationInfo.addRole("admin");
//		authorizationInfo.addStringPermission("product:view");
		authorizationInfo.addStringPermissions(strPermissions);
		return authorizationInfo;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		String username = (String) token.getPrincipal();
		User user = userDao.findUserByUsername(username);
//		LOG.debug("用户信息:" + user);
		if(user == null){
			LOG.error("账号不存在!", UnknownAccountException.class);
			throw new UnknownAccountException("账号不存在!");
		}
		SimpleAuthenticationInfo authenticationInfo = 
				new SimpleAuthenticationInfo(user.getName(), user.getPassword(), getName());
		return authenticationInfo;
	}

}
