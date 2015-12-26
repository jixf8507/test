package com.qx.eakay.service.stake;

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
import com.qx.eakay.dao.customer.CustomerDao;
import com.qx.eakay.dao.stake.StakeCardInfoDao;
import com.qx.eakay.export.stake.StakeCardExport;
import com.qx.eakay.model.customer.CustomerPO;
import com.qx.eakay.model.stake.StakeCardInfoPO;
import com.qx.eakay.service.BaseService;
import com.qx.eakay.util.MSG;

/**
 * 
 * @author jixf
 * @date 2015年7月25日
 */
@Service
public class StakeCardService extends BaseService {

	@Autowired
	private StakeCardInfoDao cardInfoDao;
	@Autowired
	private CustomerDao customerDao;

	/**
	 * 分页查找充电卡
	 * 
	 * @param jsonObject
	 * @param pageSize
	 * @param iDisplayStart
	 * @return
	 */
	public PageResults findCardsPage(JSONObject jsonObject, int pageSize,
			int iDisplayStart) {
		return cardInfoDao.findCardsPage(jsonObject, pageSize, iDisplayStart);
	}

	/**
	 * 导出充电设备列表
	 * 
	 * @param response
	 * @param jsonObject
	 */
	public void excelExportCards(HttpServletResponse response,
			JSONObject jsonObject) {
		BaseExport export = new StakeCardExport();
		List<Map<String, Object>> list = findCards(jsonObject);
		try {
			export.toExcel(response, list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 条件搜索充电卡
	 * 
	 * @param jsonObject
	 * @return
	 */
	public List<Map<String, Object>> findCards(JSONObject jsonObject) {
		return cardInfoDao.findCards(jsonObject);
	}

	/**
	 * 创建充电卡
	 * 
	 * @param cardInfoPO
	 * @return
	 */
	@Transactional
	public MSG createCard(StakeCardInfoPO cardInfoPO) {
		MSG msg = new MSG();
		boolean flag = cheakCardID(cardInfoPO.getCardID(),
				cardInfoPO.getMerchantId(), cardInfoPO.getId());
		if (flag) {
			cardInfoDao.create(cardInfoPO);
			return msg;
		} else {
			msg.setInfo("(内置卡号已重复)");
			msg.setSuccess(flag);
		}
		return msg;
	}

	/**
	 * 获取充电卡
	 * 
	 * @param id
	 * @return
	 */
	public StakeCardInfoPO getCard(Integer id) {
		return cardInfoDao.get(id);
	}

	/**
	 * 修改充电卡
	 * 
	 * @param cardInfoPO
	 * @return
	 */
	@Transactional
	public MSG updateCard(StakeCardInfoPO cardInfoPO) {
		MSG msg = new MSG();
		boolean flag = cheakCardID(cardInfoPO.getCardID(),
				cardInfoPO.getMerchantId(), cardInfoPO.getId());
		if (flag) {
			cardInfoDao.update(cardInfoPO);
			return msg;
		} else {
			msg.setInfo("(内置卡号已重复)");
			msg.setSuccess(flag);
		}
		return msg;
	}

	/**
	 * 删除充电卡
	 * 
	 * @param id
	 * @return
	 */
	@Transactional
	public boolean deleteCard(Integer id, String status) {
		return cardInfoDao.delete(id, status);
	}

	/**
	 * 检测内置卡号是否已存在
	 * 
	 * @param cardID
	 * @param id
	 * @return
	 */
	public boolean cheakCardID(String cardID, Integer merchantId, Integer id) {
		StakeCardInfoPO cardInfoPO = cardInfoDao.findByCardID(cardID,merchantId);
		if (cardInfoPO == null) {
			return true;
		}
		if (cardInfoPO.getId().equals(id)) {
			return true;
		}
		return false;
	}

	/**
	 * 检测外置卡号是否已经存在
	 * 
	 * @param cardNumber
	 * @param id
	 * @return
	 */
	public boolean cheakCardNumber(String cardNumber, Integer merchantId,
			Integer id) {
		StakeCardInfoPO cardInfoPO = cardInfoDao.findByCardNumber(cardNumber);
		if (cardInfoPO == null) {
			return true;
		}
		if (cardInfoPO.getId().equals(id)) {
			return true;
		}
		// 不同商家
		if (!cardInfoPO.getMerchantId().equals(merchantId)) {
			return true;
		}
		return false;
	}

	/**
	 * 根据内置卡号获取客户信息
	 * 
	 * @param cardID
	 * @param merchantId
	 * @return
	 */
	@Transactional
	public MSG getCustomerByCardId(String cardID, Integer merchantId) {
		MSG msg = new MSG();
		StakeCardInfoPO cardInfoPO = cardInfoDao.findByCardID(cardID,merchantId);
		if (cardInfoPO == null) {
			return new MSG("未找到该充电卡对应的客户信息", 1);
		}
		if ("删除".equals(cardInfoPO.getIsAnnul())) {
			return new MSG("该充电卡已丢失", 1);
		}
		Integer customerId = cardInfoPO.getCustomerPO().getId();
		CustomerPO customerPO = customerDao.getCustomerAccount(customerId);
		if (customerPO == null) {
			return new MSG("未找到该充电卡对应的客户信息", 1);
		}
		msg.setInfo(customerId + "," + customerPO.getName() + ","
				+ customerPO.getPhone() + ","
				+ customerPO.getAccountPO().getId() + ","
				+ customerPO.getAccountPO().getBalance());
		return msg;
	}

	/**
	 * 保存充电设备卡
	 * 
	 * @param cardInfoPO
	 * @return
	 */
	@Transactional
	public MSG createStakeCard(StakeCardInfoPO cardInfoPO) {
		MSG msg = new MSG();
		boolean flag = cheakCardID(cardInfoPO.getCardID(),
				cardInfoPO.getMerchantId(), cardInfoPO.getId());
		boolean flag1 = cheakCardNumber(cardInfoPO.getCardNumber(),
				cardInfoPO.getMerchantId(), cardInfoPO.getId());

		if (!flag) {
			return new MSG("内置卡号已存在", 1);
		}
		if (!flag1) {
			return new MSG("外置卡号已存在", 1);
		}

		Integer id = cardInfoDao.create(cardInfoPO);
		if (id > 0) {
			msg.setInfo("保存成功");
		} else {
			return new MSG("保存失败", 1);
		}
		return msg;
	}

	/**
	 * 根据内置卡号获取客户信息（接口）
	 * 
	 * @param cardID
	 *            内置卡号
	 * @param merchantId
	 *            商家ID
	 * @return
	 */
	@Transactional
	public HashMap<String, Object> getApiCustomerByCardId(String cardID,
			String merchantId) {
		MSG msg = new MSG();
		HashMap<String, Object> result = new HashMap<>();

		if (cardID == null || "".equals(cardID)) {
			msg.setInfo("内置卡号为空");
			msg.setSuccess(false);
		} else if (merchantId == null || "".equals(merchantId)) {
			msg.setInfo("商家ID为空");
			msg.setSuccess(false);
		} else {
			StakeCardInfoPO cardInfoPO = cardInfoDao.findByCardID(cardID,Integer.parseInt(merchantId));
			if (cardInfoPO == null) {
				msg.setInfo("未找到该充电卡对应的客户信息");
				msg.setSuccess(false);
			} else {
				Integer customerId = cardInfoPO.getCustomerPO().getId();
				CustomerPO customerPO = customerDao
						.getCustomerAccount(customerId);
				if (customerPO == null) {
					msg.setInfo("未找到该充电卡对应的客户信息");
					msg.setSuccess(false);
				}else if("删除".equals(cardInfoPO.getIsAnnul())){
					msg.setInfo("该充电卡已丢失");
					msg.setSuccess(false);
				}else {
					Map<String, Object> map = new HashMap<String, Object>();
					// 客户信息
					map.put("id", customerId);
					map.put("idCard", customerPO.getIdCard());
					map.put("name", customerPO.getName());
					map.put("phone", customerPO.getPhone());
					map.put("sex", customerPO.getSex());
					map.put("address", customerPO.getAddress());
					map.put("status", customerPO.getStatus());
					// 账户信息
					map.put("accountStatus", customerPO.getAccountPO()
							.getStatus());
					map.put("accountId", customerPO.getAccountPO().getId());
					map.put("balance", customerPO.getAccountPO().getBalance()
							+ "");
					msg.setSuccess(true);
					msg.setInfo("获取客户信息成功");
					result.put("customer", map);
				}
			}
		}
		result.put("msg", msg);
		return result;
	}

}
