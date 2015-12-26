package com.qx.eakay.service.merchant;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qx.common.base.BaseExport;
import com.qx.common.base.PageResults;
import com.qx.eakay.dao.merchant.MerchantUserDao;
import com.qx.eakay.dao.merchant.MerchantUserModelsDao;
import com.qx.eakay.export.merhcant.MerchantUserExport;
import com.qx.eakay.export.merhcant.MerchantUserSignExport;
import com.qx.eakay.export.statistics.MerchantUserDayWorkExport;
import com.qx.eakay.model.merchant.MerchantUserPO;
import com.qx.eakay.model.merchant.MerchantUserSignPO;
import com.qx.eakay.service.BaseService;

/**
 * 
 * @author jixf
 * @date 2015年7月11日
 */
@Service
public class MerchantUserService extends BaseService {

	@Autowired
	private MerchantUserDao merchantUserDao;
	@Autowired
	private MerchantUserModelsDao merchantUserModelsDao;

	/**
	 * 分页查询商家工作人员
	 * 
	 * @param jsonObject
	 * @param pageSize
	 * @param iDisplayStart
	 * @return
	 */
	public PageResults findUserPage(JSONObject jsonObject, int pageSize,
			int iDisplayStart) {
		return merchantUserDao
				.findUserPage(jsonObject, pageSize, iDisplayStart);
	}

	/**
	 * 条件搜索商家工作人员
	 * 
	 * @param jsonObject
	 * @return
	 */
	public List<Map<String, Object>> findUsers(JSONObject jsonObject) {
		return merchantUserDao.findUsers(jsonObject);
	}

	/**
	 * 导出商家工作人员列表
	 * 
	 * @param response
	 * @param jsonObject
	 */
	public void excelExportUsers(HttpServletResponse response,
			JSONObject jsonObject) {
		BaseExport export = new MerchantUserExport();
		List<Map<String, Object>> list = findUsers(jsonObject);
		try {
			export.toExcel(response, list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 新增商家工作人员
	 * 
	 * @param merchantUserPO
	 * @return
	 */
	public boolean createUser(MerchantUserPO merchantUserPO) {
		return merchantUserDao.create(merchantUserPO) > 0;
	}

	/**
	 * 根据ID获取商家工作人员对象
	 * 
	 * @param id
	 * @return
	 */
	public MerchantUserPO getMerchant(Integer id) {
		return merchantUserDao.get(id);
	}

	/**
	 * 修改商家工作人员
	 * 
	 * @param merchantUserPO
	 * @return
	 */
	public boolean updateUser(MerchantUserPO merchantUserPO) {
		return merchantUserDao.update(merchantUserPO);
	}

	/**
	 * 删除商家工作人员
	 * 
	 * @param id
	 * @return
	 */
	@Transactional
	public boolean delete(Integer id) {
		// 批量删除用户菜单关系
		merchantUserModelsDao.batchDelete(id);
		// 删除用户
		return merchantUserDao.delete(id);
	}

	@Transactional
	public boolean saveUserRigths(Integer id, List<Integer> moudlesIds) {
		// 删除用户原有菜单权限
		merchantUserModelsDao.batchDelete(id);
		// 新增用户菜单权限
		return merchantUserModelsDao.batchCreate(id, moudlesIds);
	}

	/**
	 * 检测商家工作人员的EMIAL是否已经存在
	 * 
	 * @param email
	 * @param id
	 * @return
	 */
	public boolean checkEmail(String email, Integer id) {
		MerchantUserPO merchantUserPO = merchantUserDao.findByEmail(email);
		if (merchantUserPO == null) {
			return true;
		}
		if (merchantUserPO.getId().equals(id)) {
			return true;
		}
		return false;
	}

	public List<Map<String, Object>> findMerchantMoudles(Integer userId,
			String systemName) {
		return this.merchantUserDao.findMerchantMoudles(userId, systemName);
	}

	/**
	 * 员工签到列表
	 * 
	 * @param jsonObject
	 * @param pageSize
	 * @param iDisplayStart
	 * @return
	 */
	public PageResults findUserSignPage(JSONObject jsonObject, int pageSize,
			int iDisplayStart) {
		return merchantUserDao.findUserSignPage(jsonObject, pageSize,
				iDisplayStart);
	}

	/**
	 * 条件搜索商家工作人员签到列表
	 * 
	 * @param jsonObject
	 * @return
	 */
	public List<Map<String, Object>> findUserSign(JSONObject jsonObject) {
		return merchantUserDao.findUserSign(jsonObject);
	}

	/**
	 * 员工签到列表导出
	 * 
	 * @param response
	 * @param jsonObject
	 */
	public void excelExportUserSign(HttpServletResponse response,
			JSONObject jsonObject) {
		BaseExport export = new MerchantUserSignExport();
		List<Map<String, Object>> list = findUserSign(jsonObject);
		try {
			export.toExcel(response, list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取签到信息
	 * 
	 * @param id
	 * @return
	 */
	public MerchantUserSignPO getMerchantUserSign(Integer id) {
		return merchantUserDao.getMerchantUserSign(id);
	}

	/**
	 * 取车操作记录
	 * 
	 * @param jsonObject
	 * @return
	 */
	public List<Map<String, Object>> findGetCarList(JSONObject jsonObject) {
		return merchantUserDao.findGetCarList(jsonObject);
	}

	/**
	 * 还车操作记录
	 * 
	 * @param jsonObject
	 * @return
	 */
	public List<Map<String, Object>> findReturnCarList(JSONObject jsonObject) {
		return merchantUserDao.findReturnCarList(jsonObject);
	}

	/**
	 * 开户操作记录
	 * 
	 * @param jsonObject
	 * @return
	 */
	public List<Map<String, Object>> findCreateCustomerList(
			JSONObject jsonObject) {
		return merchantUserDao.findCreateCustomerList(jsonObject);
	}

	/**
	 * 导出充电设备列表
	 * 
	 * @param response
	 * @param jsonObject
	 */
	public void excelExportDay(HttpServletResponse response,
			JSONObject jsonObject) {
		MerchantUserDayWorkExport export = new MerchantUserDayWorkExport();
		List<Map<String, Object>> getCarList = findGetCarList(jsonObject);
		List<Map<String, Object>> returnCarList = findReturnCarList(jsonObject);
		List<Map<String, Object>> createCustomerList = findCreateCustomerList(jsonObject);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("getCarList", getCarList);
		map.put("returnCarList", returnCarList);
		map.put("createCustomerList", createCustomerList);
		try {
			export.toExcel(response, map);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
