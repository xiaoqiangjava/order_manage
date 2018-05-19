package cn.bsnt.web.order.controller;

import java.util.List;

import javax.annotation.Resource;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.bsnt.web.order.entity.UserRole;
import cn.bsnt.web.order.service.UserRoleService;
import cn.bsnt.web.order.util.JsonResult;

/**
 * 角色分配控制器类,为角色分配中的所有操作提供了统一的访问接口
 * @author xiaoqiang
 *
 */
@Controller
@RequestMapping("/userRole")
public class UserRoleController extends AbstractController {

	@Resource
	private UserRoleService userRoleService;
	
	/**
	 * 列出所有的用户和角色信息
	 * @return
	 */
	@RequiresPermissions(value="userRole:view")
	@RequestMapping("/listUserRole.bsnt")
	@ResponseBody
	public JsonResult listUserRoles(String userRoleInfo){
		List<UserRole> userRoles = userRoleService.listAllUserRoles(userRoleInfo);
		return new JsonResult(0, userRoles);
	}
	
	/**
	 * 给用户分配角色
	 * @param userId 用户Id
	 * @param roleName 角色名称
	 * @return 是否成功
	 */
	@RequiresPermissions(value="userRole:create")
	@RequestMapping("/addUserRole.bsnt")
	@ResponseBody
	public JsonResult addUserRole(String userId, String roleName){
		boolean success = userRoleService.addUserRole(userId, roleName);
		if(success){
			return new JsonResult(0, success);
		}
		return new JsonResult(1, success);
	}
	
	/**
	 * 限制用户所拥有的角色
	 * @param userId 用户的id
	 * @param roleName 需要限制的角色名称
	 * @return 是否成功
	 */
	@RequiresPermissions(value="userRole:delete")
	@RequestMapping("/deleteUserRole.bsnt")
	@ResponseBody
	public JsonResult deleteUserRole(String userId, String roleName){
		boolean success = userRoleService.deleteUserRole(userId, roleName);
		if(success){
			return new JsonResult(0, success);
		}
		return new JsonResult(1, success);
	}
}
