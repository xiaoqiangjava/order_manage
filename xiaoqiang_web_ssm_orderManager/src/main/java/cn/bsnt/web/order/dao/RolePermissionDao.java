package cn.bsnt.web.order.dao;

import java.util.List;
import java.util.Map;

import cn.bsnt.web.order.entity.RolePermission;

public interface RolePermissionDao {
	//权限分配
	List<RolePermission> findAllRolePermissions(Map<String, Object> params);

	int createRolePermission(Map<String, Object> params);

	int deleteRolePermission(Map<String, Object> params);

	Integer findRolePermissionId(Map<String, Object> params);

	int insertRolePermission(Map<String, Object> param);
}
