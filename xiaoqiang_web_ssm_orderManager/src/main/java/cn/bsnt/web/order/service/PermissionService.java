package cn.bsnt.web.order.service;

import java.util.List;
import java.util.Map;
/**
 * 权限列表业务层接口,封装所有权限相关的业务层方法
 * @author xiaoqiang
 *
 */
public interface PermissionService {

	public List<Map<String, Object>> listAllPermissions();

	public boolean updatePermission(String permissionId, String permissionName, String permissionDesc);

	public boolean createPermission(String permissionName, String permissionDesc);

	public boolean deletePermission(String permissionId);

	public List<Map<String, Object>> findPermissions(String permissionInfo);
}
