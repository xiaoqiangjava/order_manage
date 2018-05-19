package cn.bsnt.web.order.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.bsnt.web.order.dao.PermissionDao;
import cn.bsnt.web.order.dao.RolePermissionDao;
import cn.bsnt.web.order.entity.Permission;
import cn.bsnt.web.order.entity.RolePermission;
/**
 * 封装了权限分配中的业务层方法
 * @author xiaoqiang
 *
 */
@Service("rolePermissionService")
public class RolePermissionServiceImpl implements RolePermissionService {

	private static final Logger LOG = LoggerFactory.getLogger(RolePermissionServiceImpl.class);
	@Resource
	private RolePermissionDao rolePermissionDao;
	
	@Resource
	private PermissionDao permissionDao;
	/**
	 * 查询所有的角色和权限的关系
	 * @return 返回角色和权限关系列表
	 */
	@Transactional(readOnly=true)
	public List<RolePermission> listAllRolePermissions(String rolePermissionInfo) {
		List<RolePermission> rolePermissions;
		if("请输入关键字...".equals(rolePermissionInfo) || rolePermissionInfo.trim().isEmpty()){
			rolePermissions = rolePermissionDao.findAllRolePermissions(new HashMap<String, Object>());			
		}else{
			rolePermissionInfo = "%" + rolePermissionInfo + "%";
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("rolePermissionInfo", rolePermissionInfo);
			rolePermissions = rolePermissionDao.findAllRolePermissions(params);
		}
//		LOG.debug("角色和权限的关系列表: " + rolePermissions);
		return rolePermissions;
	}
	
	/**
	 * 给角色分配权限
	 * @param roleId 角色id
	 * @param permissionName 权限名称
 	 * @return 是否成功
	 */
	@Transactional
	public boolean createRolePermission(String roleId, String permissionName) {
		LOG.debug("给用户分配权限时的角色Id: " + roleId);
		Map<String, Object> params = new HashMap<String, Object>();	
		if(roleId!=null && !roleId.trim().isEmpty()){
			params.put("roleId", roleId);
		}else{
			throw new RuntimeException("给角色分配权限时权限Id不能为空!");
		}
		if(permissionName!=null && !permissionName.trim().isEmpty()){
			Permission permission = permissionDao.findPermissionByPermissionName(permissionName);
			if(permission==null){
				throw new RuntimeException("该权限不存在,不能分配给该角色");
			}
			params.put("permissionId", permission.getPermissionId());
			Integer permissionId = rolePermissionDao.findRolePermissionId(params);
			if(permissionId!=null){
				throw new RuntimeException("该角色已经拥有该权限,不需要重复分配权限!");
			}
		}else{
			throw new RuntimeException("请输入需要分配给该用户的权限标识符!");
		}
		int n = rolePermissionDao.createRolePermission(params);
		return n==1;
	}

	/**
	 * 限定角色所拥有的权限
	 * @param roleId 角色Id
	 * @param permissionName 权限名称
	 * @return 是否成功
	 */
	@Transactional
	public boolean deleteRolePermission(String roleId, String permissionName) {
		LOG.debug("限定权限时的角色Id: " + roleId);
		Map<String, Object> params = new HashMap<String, Object>();
		if(roleId!=null && !roleId.trim().isEmpty()){
			params.put("roleId", roleId);
		}else{
			throw new RuntimeException("限定权限时角色Id不能为空!");
		}
		if(permissionName!=null && !permissionName.trim().isEmpty()){
			Permission permission = permissionDao.findPermissionByPermissionName(permissionName);
			LOG.debug("权限信息: " + permission);
			if(permission==null){
				throw new RuntimeException("该权限不存在,不需要删除!");
			}
			params.put("permissionId", permission.getPermissionId());
			Integer permissionId = rolePermissionDao.findRolePermissionId(params);
			if(permissionId==null){
				throw new RuntimeException("该角色不拥有该权限,不需要限定其权限!");
			}
		}else{
			throw new RuntimeException("请输入需要限定的权限标识符!");
		}
		int n = rolePermissionDao.deleteRolePermission(params);
		return n==1;
	}

}
