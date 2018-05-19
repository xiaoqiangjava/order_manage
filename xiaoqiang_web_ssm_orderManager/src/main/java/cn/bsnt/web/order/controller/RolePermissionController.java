package cn.bsnt.web.order.controller;

import java.util.List;

import javax.annotation.Resource;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.bsnt.web.order.entity.RolePermission;
import cn.bsnt.web.order.service.RolePermissionService;
import cn.bsnt.web.order.util.JsonResult;

/**
 * 权限分配控制器方法,为权限分配中的所有操作提供统一的入口
 * @author xiaoqiang
 *
 */
@Controller
@RequestMapping("rolePermission")
public class RolePermissionController extends AbstractController {

	@Resource
	private RolePermissionService rolePermissionService;
	
	/**
	 * 列出所有的角色和权限的关系
	 * @return 角色和权限关系列表
	 */
	@RequiresPermissions("rolePermission:view")
	@RequestMapping("listRolePermission.bsnt")
	@ResponseBody
	public JsonResult listRolePermissions(String rolePermissionInfo){
		List<RolePermission> rolePermissions = rolePermissionService.listAllRolePermissions(rolePermissionInfo);
		return new JsonResult(0, rolePermissions);
	}
	
	/**
	 * 给角色分配权限
	 * @param roleId 角色id
	 * @param permissionName 权限名称
 	 * @return 是否成功
	 */
	@RequiresPermissions("rolePermission:create")
	@RequestMapping("addRolePermission.bsnt")
	@ResponseBody
	public JsonResult createRolePermission(String roleId, String permissionName){
		boolean success = rolePermissionService.createRolePermission(roleId, permissionName);
		if(success){
			return new JsonResult(0, success);
		}
		return new JsonResult(1, success);
	}
	
	/**
	 * 限定角色所拥有的权限
	 * @param roleId 角色Id
	 * @param permissionName 权限名称
	 * @return 是否成功
	 */
	@RequiresPermissions(value="rolePermission:delete")
	@RequestMapping("deleteRolePermission.bsnt")
	@ResponseBody
	public JsonResult deleteRolePermission(String roleId, String permissionName){
		boolean success = rolePermissionService.deleteRolePermission(roleId, permissionName);
		if(success){
			return new JsonResult(0, success);
		}
		return new JsonResult(1, success);
	}
}
