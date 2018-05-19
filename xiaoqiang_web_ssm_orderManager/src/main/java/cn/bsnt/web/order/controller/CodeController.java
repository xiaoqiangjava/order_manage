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

import cn.bsnt.web.order.service.CodeService;
import cn.bsnt.web.order.util.JsonResult;

/**
 * 该类是账号管理的控制器
 * @author xiaoqiang
 *
 */
@Controller
@RequestMapping("code")
public class CodeController extends AbstractController {
	
	private static final Logger LOG = LoggerFactory.getLogger(CodeController.class);
	
	@Resource
	private CodeService codeService;
	/**
	 * 列出所有的账号信息
	 * @return
	 */
	@RequiresPermissions(value="user:view")
	@RequestMapping("listCode.bsnt")
	@ResponseBody
	public JsonResult listCode(){
		//System.out.println("flag:"+flag);
		List<Map<String,Object>> codes = codeService.listAllCodes();
//		LOG.info("codes: " + codes);
		return new JsonResult(0,codes);
	}
	/**
	 * 根据关键字查询账号信息
	 * @param codeInfo 关键字
	 * @return 查询结果
	 */
	@RequiresPermissions(value="user:view")
	@RequestMapping("findCode.bsnt")
	@ResponseBody
	public JsonResult findCode(String codeInfo){
		//System.out.println(codeInfo);
		LOG.debug("关键字: " + codeInfo);
		List<Map<String,Object>> codes = codeService.findCode(codeInfo);
		return new JsonResult(0,codes);
	}
	/**
	 * 删除账号信息
	 * @param codeId
	 * @return
	 */
	@RequiresPermissions(value="user:delete")
	@RequestMapping("deleteCode.bsnt")
	@ResponseBody
	public JsonResult deleteCode(String codeId){
		boolean success = codeService.deleteCode(codeId);
		if(success){
			return new JsonResult(0,success);
		}else{
			return new JsonResult(1,success);
		}
	}
	/**
	 * 修改账号信息
	 * @param codeId 账号id(必须)
	 * @param codeNick 会员昵称
	 * @return 修改是否成功
	 */
	@RequiresPermissions(value="user:update")
	@RequestMapping("updateCode.bsnt")
	@ResponseBody
	public JsonResult updateCode(String codeId, String codeNick){
		boolean success = codeService.updateCode(codeId, codeNick);
		if(success){
			return new JsonResult(0,success);
		}
		return new JsonResult(1,success);
	}
}
