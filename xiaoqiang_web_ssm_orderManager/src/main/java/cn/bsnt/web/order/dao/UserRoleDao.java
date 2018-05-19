package cn.bsnt.web.order.dao;

import java.util.List;
import java.util.Map;

import cn.bsnt.web.order.entity.UserRole;

public interface UserRoleDao {
	//角色分配
	List<UserRole> findAllUserRoles(Map<String, Object> params);

	int createUserRole(Map<String, Object> params);

	int deleteUserRole(Map<String, Object> params);

	Integer findUserRoleId(Map<String, Object> params);

	int insertUserRole(Map<String, Object> params);
}
