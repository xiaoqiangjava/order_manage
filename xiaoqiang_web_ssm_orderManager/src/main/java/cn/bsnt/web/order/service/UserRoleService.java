package cn.bsnt.web.order.service;

import java.util.List;

import cn.bsnt.web.order.entity.UserRole;

/**
 * 角色分配业务层接口,封装角色分配中所有的业务层方法
 * @author xiaoqiang
 *
 */
public interface UserRoleService {
	
	public List<UserRole> listAllUserRoles(String userRoleInfo);

	public boolean addUserRole(String userId, String roleName);

	public boolean deleteUserRole(String userId, String roleName); 
}
