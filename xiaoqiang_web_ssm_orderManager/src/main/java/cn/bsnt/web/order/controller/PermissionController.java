package cn.bsnt.web.order.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.bsnt.web.order.service.PermissionService;
import cn.bsnt.web.order.util.JsonResult;

/**
 * 该类是所有权限操作的控制器类,为权限操作提供统一的访问接口
 * @author xiaoqiang
 *
 */
@Controller
@RequestMapping("permission")
public class PermissionController extends AbstractController {

	@Resource
	private PermissionService permissionService;
	
	/**
	 * 列出所有的权限信息
	 * @return
	 */
	@RequiresPermissions(value="permission:view")
	@RequestMapping("listPermission.bsnt")
	@ResponseBody
	public JsonResult listPermission(){
		List<Map<String, Object>> permissions = permissionService.listAllPermissions();
		return new JsonResult(0, permissions);
	}
	
	/**
	 * 修改权限信息
	 * @param permissionId 必须参数,权限的id
	 * @param permissionName 权限名称
	 * @param permissionDesc 权限描述
	 * @return 是否修改权限信息成功
	 */
	@RequiresPermissions(value="permission:update")
	@RequestMapping("updatePermission.bsnt")
	@ResponseBody
	public JsonResult updatePermission(String permissionId, String permissionName, String permissionDesc){
		boolean success = permissionService.updatePermission(permissionId, permissionName, permissionDesc);
		if(success){
			return new JsonResult(0, success);			
		}else{
			return new JsonResult(1, success);
		}
	}
	
	/**
	 * 创建权限信息,该方法的调用会修改创建新的权限操作符,要使新创建的权限操作符生效需要在代码中使用该权限操作符,需要更新后台管理系统,
	 * 所以该方法只供超级管理员使用.
	 * @param permissionName 权限名称
	 * @param permissionDesc 权限描述
	 * @return 返回创建结果
	 */
	@RequiresPermissions(value="permission:create")
	@RequestMapping("createPermission.bsnt")
	@ResponseBody
	public JsonResult createPermission(String permissionName, String permissionDesc){
		boolean success = permissionService.createPermission(permissionName, permissionDesc);
		if(success){
			return new JsonResult(0, success);			
		}else{
			return new JsonResult(1, success);
		}
	}
	
	/**
	 * 删除权限信息
	 * @param permissionId 权限id
	 * @return 是否删除
	 */
	@RequiresPermissions(value="permission:delete")
	@RequestMapping("deletePermission.bsnt")
	@ResponseBody
	public JsonResult deletePermission(String permissionId){
		boolean success = permissionService.deletePermission(permissionId);
		if(success){
			return new JsonResult(0, success);			
		}else{
			return new JsonResult(1, success);
		}
	}
	
	/**
	 * 根据关键字查询权限信息
	 * @param permissionInfo 关键字
	 * @return 返回查询结果集
	 */
	@RequiresPermissions(value="permission:view")
	@RequestMapping("findPermission.bsnt")
	@ResponseBody
	public JsonResult findPermission(String permissionInfo){
		List<Map<String, Object>> permissions = permissionService.findPermissions(permissionInfo);
		return new JsonResult(0, permissions);
	}
}
