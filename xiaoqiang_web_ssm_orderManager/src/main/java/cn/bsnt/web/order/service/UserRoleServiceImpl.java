package cn.bsnt.web.order.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.bsnt.web.order.dao.RoleDao;
import cn.bsnt.web.order.dao.UserRoleDao;
import cn.bsnt.web.order.entity.Role;
import cn.bsnt.web.order.entity.UserRole;

/**
 * 角色分配业务层方法
 * @author xiaoqiang
 *
 */
@Service("userRoleService")
public class UserRoleServiceImpl implements UserRoleService {
	private static final Logger LOG = LoggerFactory.getLogger(UserRoleServiceImpl.class);

	@Resource
	private UserRoleDao userRoleDao;
	
	@Resource
	private RoleDao roleDao;

	/**
	 * 列出所有的用户和角色的关系信息
	 * @return 返回用户和角色信息实例
	 */
	@Transactional(readOnly=true)
	public List<UserRole> listAllUserRoles(String userRoleInfo) {
		List<UserRole> userRoles;
		if("请输入关键字...".equals(userRoleInfo) || userRoleInfo.trim().isEmpty()){
			userRoles = userRoleDao.findAllUserRoles(new HashMap<String, Object>());			
		}else{
			userRoleInfo = "%" + userRoleInfo + "%";
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("userRoleInfo", userRoleInfo);
			userRoles = userRoleDao.findAllUserRoles(params);
		}
//		LOG.debug("角色分配信息列表:" + userRoles);
		return userRoles;
	}

	/**
	 * 给用户分配角色
	 * @param userId 用户Id
	 * @param roleName 角色名称
	 * @return 是否成功
	 */
	@Transactional
	public boolean addUserRole(String userId, String roleName) {
		LOG.debug("分配角色中的用户Id: " + userId);
		Map<String, Object> params = new HashMap<String, Object>();
		if(userId!=null && !userId.trim().isEmpty()){
			params.put("userId", userId);
		}else{
			throw new RuntimeException("给用户分配角色时需要指明用户!");
		}
		if(roleName!=null && !roleName.trim().isEmpty()){
			Role role = roleDao.findRoleByRoleName(roleName);
			if(role==null){
				throw new RuntimeException("该角色不存在,不能分配给用户!");
			}
			Integer roleId = role.getRoleId();
			params.put("roleId", roleId);
			Integer userRoleId = userRoleDao.findUserRoleId(params);
			if(userRoleId!=null){
				throw new RuntimeException("该用户已经拥有该角色,不需要重新分配!");
			}
		}else{
			throw new RuntimeException("请您输入需要分配的角色名称!");
		}
		int n = userRoleDao.createUserRole(params);
		return n==1;
	}

	/**
	 * 限制用户所拥有的角色
	 * @param userId 用户的id
	 * @param roleName 需要限制的角色名称
	 * @return 是否成功
	 */
	@Transactional
	public boolean deleteUserRole(String userId, String roleName) {
		LOG.debug("限制用户角色时的用户Id: " + userId);
		Map<String, Object> params = new HashMap<String, Object>();
		if(userId!=null && !userId.trim().isEmpty()){
			params.put("userId", userId);
		}else{
			throw new RuntimeException("限制用户所拥有的角色时用户Id不能为空!");
		}
		if(roleName!=null && !roleName.trim().isEmpty()){
			Role role = roleDao.findRoleByRoleName(roleName);
			if(role==null){
				throw new RuntimeException("该用户不拥有该角色!");
			}
			params.put("roleId", role.getRoleId());
			Integer userRoleId = userRoleDao.findUserRoleId(params);
			if(userRoleId==null){
				throw new RuntimeException("该用户不拥有该角色,不需要限定其角色!");
			}
		}else{
			throw new RuntimeException("请您输入需要限制的角色名称!");
		}
		int n = userRoleDao.deleteUserRole(params);
		return n==1;
	}
	
	
}
