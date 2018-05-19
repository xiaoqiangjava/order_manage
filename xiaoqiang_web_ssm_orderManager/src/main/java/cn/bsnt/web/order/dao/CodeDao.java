package cn.bsnt.web.order.dao;

import java.util.List;
import java.util.Map;
/**
 * 账号管理模块DAO接口
 * @author xiaoqiang
 *
 */
public interface CodeDao {
	List<Map<String,Object>> findAllCodes();

	List<Map<String, Object>> findCodeByCodeInfo(String codeInfo);

	boolean deleteCodeByCodeId(String codeId);

	int updateCodeByParams(Map<String, Object> params);
	
	
}
