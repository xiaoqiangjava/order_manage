package cn.bsnt.web.order.dao;

import java.util.List;
import java.util.Map;

import cn.bsnt.web.order.entity.Role;

public interface RoleDao {
	//通过用户名查询其对应的角色
	public List<Role> findRolesByUsername(String username);
	
	//查询所有的角色信息
	List<Map<String, Object>> findAllRoles();

	public int updateRoleByParams(Map<String, Object> params);

	public int createRole(Map<String, Object> params);

	public int deleteRoleByRoleId(String roleId);

	public Role findRoleByRoleName(String roleName);

	public List<Map<String, Object>> findRolesByRoleInfo(String roleInfo);
}
