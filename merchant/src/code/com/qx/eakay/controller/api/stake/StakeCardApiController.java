package com.qx.eakay.controller.api.stake;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qx.eakay.controller.BaseController;
import com.qx.eakay.model.stake.StakeCardInfoPO;
import com.qx.eakay.service.stake.StakeCardService;
import com.qx.eakay.util.MSG;

/**
 * 充电设备卡接口
 * 
 * @author sdf
 * @date 2015年12月4日
 */
@Controller
@RequestMapping("/api/stakeCard")
public class StakeCardApiController extends BaseController {

	@Autowired
	private StakeCardService cardService;

	/**
	 * 验证充电设备卡是否存在
	 * 
	 * @param request
	 * @param merchantId
	 *            商家ID
	 * @param cardID
	 *            内置卡号
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("check.htm")
	@ResponseBody
	public HashMap<String, Object> checkStakeCard(HttpServletRequest request) throws Exception {
		MSG msg = new MSG();
		HashMap<String, Object> map = new HashMap<String, Object>();
		String merchantId = request.getParameter("merchantId");
		String cardID = request.getParameter("cardID");
		if (merchantId == null || "".equals(merchantId)) {
			msg.setSuccess(false);
			msg.setInfo("商家ID为空");
		}else if (cardID == null || "".equals(cardID)) {
			msg.setSuccess(false);
			msg.setInfo("内置卡号为空");
		}else{
			boolean flag = cardService.cheakCardID(cardID.toUpperCase(),
					Integer.parseInt(merchantId), -1);
			
			if (!flag) {
				msg.setSuccess(true);
				msg.setInfo("充电卡已存在");
			} else {
				msg.setSuccess(false);
				msg.setInfo("充电卡不存在");
			}
		}
		map.put("msg", msg);
		return map;
	}

	/**
	 * 保存充电设备卡
	 * 
	 * @param request
	 * @param cardID
	 *            内置卡号
	 * @param cardNumber
	 *            外置卡号
	 * @param customerId
	 *            客户ID
	 * @param merchantId
	 *            商家ID
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("create.htm")
	@ResponseBody
	public MSG createStakeCard(HttpServletRequest request) throws Exception {

		String cardID = request.getParameter("cardID");
		String cardNumber = request.getParameter("cardNumber");
		String customerId = request.getParameter("customerId");
		String merchantId = request.getParameter("merchantId");

		if (cardID == null || "".equals(cardID)) {
			return new MSG("内置卡号为空", 1);
		}
		if (cardNumber == null || "".equals(cardNumber)) {
			return new MSG("外置卡号为空", 1);
		}
		if (customerId == null || "".equals(customerId)) {
			return new MSG("客户ID为空", 1);
		}
		if (merchantId == null || "".equals(merchantId)) {
			return new MSG("商家ID为空", 1);
		}

		StakeCardInfoPO cardInfoPO = new StakeCardInfoPO();
		cardInfoPO.setCardID(cardID.toUpperCase());// 转成大写
		cardInfoPO.setCardNumber(cardNumber);
		cardInfoPO.setCustomerId(Integer.parseInt(customerId));
		cardInfoPO.setMerchantId(Integer.parseInt(merchantId));
		cardInfoPO.setCarTableName("");

		return cardService.createStakeCard(cardInfoPO);

	}

	/**
	 * 根据内置卡号获取客户信息
	 * 
	 * @param request
	 * @param cardId
	 *            内置卡号
	 * @param merchantId
	 *            商家ID
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("getCustomerByCardId.htm")
	@ResponseBody
	public HashMap<String, Object> getCustomerByCardId(
			HttpServletRequest request) throws Exception {

		String cardID = request.getParameter("cardID");
		String merchantId = request.getParameter("merchantId");

		return cardService.getApiCustomerByCardId(cardID,merchantId);
	}

}
