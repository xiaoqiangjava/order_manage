package cn.bsnt.web.order.dao;

import java.util.List;
import java.util.Map;

import cn.bsnt.web.order.entity.Permission;

public interface PermissionDao {
	//通过角色id查询该角色拥有的所有权限
	public List<Permission> findPermissionsByRoleId(Integer roleId);
	
	//查询所有的权限信息
	List<Map<String, Object>> findAllPermissions();

	public int updatePermission(Map<String, Object> params);

	public int createPermission(Map<String, Object> params);

	public Permission findPermissionByPermissionName(String permissionName);

	public int deletePermissionById(String permissionId);

	public List<Map<String, Object>> findPermissionsByPermissionInfo(String permissionInfo);
}
