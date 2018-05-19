package cn.bsnt.web.order.service;

import java.util.List;
import java.util.Map;

/**
 * 账号管理业务层接口
 * @author xiaoqiang
 *
 */
public interface CodeService {
	public List<Map<String,Object>> listAllCodes();

	public List<Map<String, Object>> findCode(String codeInfo);

	public boolean deleteCode(String codeId);

	public boolean updateCode(String codeId, String codeNick);

}
