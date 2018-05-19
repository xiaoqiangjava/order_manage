package cn.bsnt.web.order.service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.bsnt.web.order.dao.PermissionDao;
import cn.bsnt.web.order.entity.Permission;
/**
 * 该类封装权限相关的所有业务层方法
 * @author xiaoqiang
 *
 */
@Service("permissionService")
public class PermissionServiceImpl implements PermissionService {

	@Resource
	private PermissionDao permissionDao;
	
	/**
	 * 列出所有的权限信息
	 * @return 返回权限信息列表
	 */
	@Transactional(readOnly=true)
	public List<Map<String, Object>> listAllPermissions() {
		List<Map<String, Object>> permissions = permissionDao.findAllPermissions();
		return permissions;
	}

	/**
	 * 修改权限信息
	 * @param permissionId 必须参数,权限的id
	 * @param permissionName 权限名称
	 * @param permissionDesc 权限描述
	 * @return 是否修改权限信息成功
	 */
	@Transactional
	public boolean updatePermission(String permissionId, String permissionName, String permissionDesc) {
		Map<String, Object> params = new HashMap<String, Object>();
		if(permissionId!=null && !permissionId.trim().isEmpty()){
			params.put("permissionId", permissionId);
		}else{
			throw new RuntimeException("修改权限时id不能为空!");
		}
		if(permissionName!=null && !permissionName.trim().isEmpty()){
			params.put("permissionName", permissionName);
		}
		if(permissionDesc!=null && !permissionDesc.trim().isEmpty()){
			params.put("permissionDesc", permissionDesc);
		}
		Timestamp updateTime = new Timestamp(new Date().getTime());
		params.put("updateTime", updateTime);
		int n = permissionDao.updatePermission(params);
		return n==1;
	}

	/**
	 * 创建权限信息,该方法的调用会修改创建新的权限操作符,要使新创建的权限操作符生效需要在代码中使用该权限操作符,需要更新后台管理系统,
	 * 所以该方法只供超级管理员使用.
	 * @param permissionName 权限名称
	 * @param permissionDesc 权限描述
	 * @return 返回创建结果
	 */
	@Transactional
	public boolean createPermission(String permissionName, String permissionDesc) {
		Map<String, Object> params = new HashMap<String, Object>();
		if(permissionName!=null && !permissionName.trim().isEmpty()){
			Permission permission = permissionDao.findPermissionByPermissionName(permissionName);
			if(permission!=null){
				throw new RuntimeException("该权限控制符已经存在,不能重复添加!");
			}
		}else{
			throw new RuntimeException("请输入权限名称!");
		}
		params.put("permissionName", permissionName);
		if(permissionDesc!=null && !permissionDesc.trim().isEmpty()){
			params.put("permissionDesc", permissionDesc);
		}else{
			throw new RuntimeException("请输入权限描述!");
		}
		Timestamp createTime = new Timestamp(new Date().getTime());
		params.put("createTime", createTime);
		int n = permissionDao.createPermission(params);
		return n==1;
	}

	/**
	 * 删除权限信息
	 * @param permissionId 权限id
	 * @return 是否删除
	 */
	@Transactional
	public boolean deletePermission(String permissionId) {
		if(permissionId!=null && !permissionId.trim().isEmpty()){
			int n = permissionDao.deletePermissionById(permissionId);
			return n==1;
		}else{
			throw new RuntimeException("需要删除的权限id不能为空!");
		}
	}

	/**
	 * 根据关键字查询权限信息
	 * @param permissionInfo 关键字
	 * @return 返回查询结果集
	 */
	public List<Map<String, Object>> findPermissions(String permissionInfo) {
		List<Map<String, Object>> permissions ;
		if("请输入关键字...".equals(permissionInfo) || permissionInfo.trim().isEmpty()){
			permissions = permissionDao.findAllPermissions();
		}else{
			permissionInfo = "%" + permissionInfo + "%";
			permissions = permissionDao.findPermissionsByPermissionInfo(permissionInfo);
		}
		return permissions;
	}

}
