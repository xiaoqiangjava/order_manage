package cn.bsnt.web.order.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.bsnt.web.order.dao.CodeDao;
/**
 * 账号列表业务层方法
 * @author xiaoqiang
 *
 */
@Service("codeService")
public class CodeServiceImpl implements CodeService {
	@Resource
	private CodeDao codeDao;
	/**
	 * 查询所有的账号信息
	 * @return 返回查询结果
	 */
	@Transactional(readOnly=true)
	public List<Map<String, Object>> listAllCodes() {
		List<Map<String,Object>> codes = codeDao.findAllCodes();
		return codes;
	}
	
	/**
	 * 根据关键字查询账号信息
	 * @return 返回查询结果
	 */
	@Transactional(readOnly=true)
	public List<Map<String, Object>> findCode(String codeInfo) {
		List<Map<String,Object>> codes = null;
		if("请输入关键字...".equals(codeInfo) || codeInfo.trim().isEmpty()){
			codes = codeDao.findAllCodes();
		}else{
			codeInfo = "%"+codeInfo+"%";
			codes = codeDao.findCodeByCodeInfo(codeInfo);			
		}
		return codes;
	}
	
	/**
	 * 根据账号id删除账号信息
	 * @return 是否删除成功
	 */
	@Transactional
	public boolean deleteCode(String codeId) {
		if(codeId==null || codeId.trim().isEmpty()){
			throw new RuntimeException("账号ID不能为空!");
		}
		boolean success = codeDao.deleteCodeByCodeId(codeId);
		return success;
	}
	
	/**
	 * 动态参数更新用户表中的数据
	 * @param codeId 账号id
	 * @param flag 审核状态
	 */
	@Transactional
	public boolean updateCode(String codeId, String codeNick) {
		Map<String,Object> params = new HashMap<String,Object>();
		if(codeId!=null && !codeId.trim().isEmpty()){
			params.put("codeId", codeId);
		}else{
			throw new RuntimeException("需要修改的账号ID不能为空!");
		}
		if(codeNick!=null && !codeNick.trim().isEmpty()){
			params.put("codeNick", codeNick);
		}else{
			throw new RuntimeException("没有需要修改的信息!");
		}
		int n = codeDao.updateCodeByParams(params);
		return n==1;
	}

}
