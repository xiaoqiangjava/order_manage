package cn.bsnt.web.order.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.bsnt.web.order.service.RoleService;
import cn.bsnt.web.order.util.JsonResult;

/**
 * 该类是角色信息相关的控制器方法,为所有的业务层提供统一的访问接口
 * @author xiaoqiang
 *
 */
@Controller
@RequestMapping("/role")
public class RoleController extends AbstractController {

	private static final Logger LOG = LoggerFactory.getLogger(RoleController.class);
	@Resource
	private RoleService roleService;
	
	/**
	 * 列出多有的角色信息
	 * @return
	 */
	@RequiresPermissions(value="role:view")
	@RequestMapping("listRole.bsnt")
	@ResponseBody
	public JsonResult listRole(){
		List<Map<String, Object>> roles = roleService.listAllRoles();
		return new JsonResult(0,roles);
	}
	
	/**
	 * 修改角色信息
	 * @param roleId 角色标识
	 * @param roleName 角色名称
	 * @param roleDesc 角色描述
	 * @return 角色信息是否修改成功
	 */
	@RequiresPermissions(value="role:update")
	@RequestMapping("updateRole.bsnt")
	@ResponseBody
	public JsonResult updateRole(String roleId, String roleName, String roleDesc){
		boolean success = roleService.updateRoleInfo(roleId, roleName, roleDesc);
		if(success){
			return new JsonResult(0, success);			
		}else{
			return new JsonResult(1, success);
		}
	}
	
	/**
	 * 添加角色信息
	 * @param roleName 角色名称
	 * @param roleDesc 角色描述
	 * @return 是否成功添加角色信息
	 */
	@RequiresPermissions(value="role:create")
	@RequestMapping("createRole.bsnt")
	@ResponseBody
	public JsonResult createRole(String roleName, String roleDesc){
		boolean success = roleService.createRole(roleName, roleDesc);
		if(success){
			return new JsonResult(0, success);			
		}else{
			return new JsonResult(1, success);
		}
	}
	
	/**
	 * 删除角色信息
	 * @param roleId 必须参数角色id
	 * @return 是否删除成功
	 */
	@RequiresPermissions(value="role:delete")
	@RequestMapping("deleteRole.bsnt")
	@ResponseBody
	public JsonResult deleteRole(String roleId){
		boolean success = roleService.deleteRole(roleId);
		if(success){
			return new JsonResult(0, success);			
		}else{
			return new JsonResult(1, success);
		}
	}
	
	/**
	 * 根据关键字查询角色信息
	 * @param roleInfo 关键字
	 * @return 返回查询结果集
	 */
	@RequiresPermissions(value="role:view")
	@RequestMapping("findRole.bsnt")
	@ResponseBody
	public JsonResult findRoles(String roleInfo){
		LOG.debug("角色查询关键字: " + roleInfo);
		List<Map<String, Object>> roles = roleService.findRoles(roleInfo);
		return new JsonResult(0, roles);
	}
}
