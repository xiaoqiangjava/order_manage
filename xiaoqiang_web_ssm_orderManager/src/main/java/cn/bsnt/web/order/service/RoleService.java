package cn.bsnt.web.order.service;

import java.util.List;
import java.util.Map;
/**
 * 角色信息业务层接口,为所有角色相关操作提供统一的访问接口
 * @author xiaoqiang
 *
 */
public interface RoleService {

	//列出所有的角色信息
	public List<Map<String, Object>> listAllRoles();

	public boolean updateRoleInfo(String roleId, String roleName, String roleDesc);

	public boolean createRole(String roleName, String roleDesc);

	public boolean deleteRole(String roleId);

	public List<Map<String, Object>> findRoles(String roleInfo);
}
