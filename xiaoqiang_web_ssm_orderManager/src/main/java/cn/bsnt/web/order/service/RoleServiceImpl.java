package cn.bsnt.web.order.service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.bsnt.web.order.dao.RoleDao;
import cn.bsnt.web.order.dao.RolePermissionDao;
import cn.bsnt.web.order.entity.Role;
/**
 * 该类封装角色相关的所有业务层方法
 * @author xiaoqiang
 *
 */
@Service("roleService")
public class RoleServiceImpl implements RoleService {

	private final static Logger LOG = LoggerFactory.getLogger(RoleServiceImpl.class);
	@Resource
	private RoleDao roleDao;
	
	@Resource
	private RolePermissionDao rolePermissionDao;
	/**
	 * 列出所有的角色信息
	 * @return 返回角色列表
	 */
	@Transactional(readOnly=true)
	public List<Map<String, Object>> listAllRoles() {
		List<Map<String, Object>> roles = roleDao.findAllRoles();
		return roles;
	}
	
	/**
	 * 修改角色信息
	 * @param roleId 角色标识
	 * @param roleName 角色名称
	 * @param roleDesc 角色描述
	 * @return 角色信息是否修改成功
	 */
	@Transactional
	public boolean updateRoleInfo(String roleId, String roleName, String roleDesc) {
		Map<String, Object> params = new HashMap<String, Object>();
		if(roleId==null || roleId.trim().isEmpty()){
			throw new RuntimeException("角色编号不能为空!");
		}else{
			params.put("roleId", roleId);
		}
		if(roleName!=null && !roleName.trim().isEmpty()){
			params.put("roleName", roleName);
		}
		if(roleDesc!=null && !roleDesc.trim().isEmpty()){
			params.put("roleDesc", roleDesc);
		}
		Timestamp updateTime = new Timestamp(new Date().getTime());
		params.put("updateTime", updateTime);
		int n = roleDao.updateRoleByParams(params);
		return n==1;
	}

	/**
	 * 添加角色信息
	 * @param roleName 角色名称
	 * @param roleDesc 角色描述
	 * @return 是否成功添加角色信息
	 */
	@Transactional
	public boolean createRole(String roleName, String roleDesc) {
		Map<String, Object> params = new HashMap<String, Object>();
		if(roleName!=null && !roleName.trim().isEmpty()){
			Role role = roleDao.findRoleByRoleName(roleName);
			if(role!=null){
				throw new RuntimeException("该角色已经存在,不能重复添加!");
			}
		}else{
			throw new RuntimeException("角色名不能为空!");
		}
		params.put("roleName", roleName);
		if(roleDesc!=null && !roleDesc.trim().isEmpty()){
			params.put("roleDesc", roleDesc);
		}else{
			throw new RuntimeException("角色描述不能为空!");
		}
		Timestamp createTime = new Timestamp(new Date().getTime());
		LOG.debug("角色创建时间: " + createTime.toString());
		params.put("createTime", createTime);
		int n = roleDao.createRole(params);
		Role role = roleDao.findRoleByRoleName(roleName);
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("roleId", role.getRoleId());
		param.put("permissionId", 33);
		rolePermissionDao.insertRolePermission(param);
		return n==1;
	}
	
	/**
	 * 删除角色信息
	 * @param roleId 必须参数角色id
	 * @return 是否删除成功
	 */
	public boolean deleteRole(String roleId) {
		if(roleId==null || roleId.trim().isEmpty()){
			throw new RuntimeException("删除角色信息时的id不能为空!");
		}
		int n = roleDao.deleteRoleByRoleId(roleId);
		return n==1;
	}

	/**
	 * 根据关键字查询角色信息
	 * @param roleInfo 关键字
	 * @return 返回查询结果集
	 */
	@Transactional(readOnly=true)
	public List<Map<String, Object>> findRoles(String roleInfo) {
		List<Map<String, Object>> roles;
		if("请输入关键字...".equals(roleInfo) || roleInfo.trim().isEmpty()){
			roles = roleDao.findAllRoles();
		}else{
			roleInfo = "%" + roleInfo + "%";
			roles = roleDao.findRolesByRoleInfo(roleInfo);
		}
		LOG.debug("关键字查询结果集: " + roles);
		return roles;
	}

}
