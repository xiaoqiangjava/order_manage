package cn.bsnt.web.order.service;

import java.util.List;

import cn.bsnt.web.order.entity.RolePermission;

/**
 * 权限分配业务层接口,为权限分配的所有业务层方法提供了统一的访问接口
 * @author xiaoqiang
 *
 */
public interface RolePermissionService {

	public List<RolePermission> listAllRolePermissions(String rolePermissionInfo);

	public boolean createRolePermission(String roleId, String permissionName);

	public boolean deleteRolePermission(String roleId, String permissionName);
}
