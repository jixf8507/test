package com.qx.eakay.controller.stake;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.qx.common.base.PageHelp;
import com.qx.common.base.PageResults;
import com.qx.common.tools.JSONTools;
import com.qx.common.tools.StringTools;
import com.qx.eakay.controller.BaseController;
import com.qx.eakay.login.SessionUserBO;
import com.qx.eakay.model.stake.StakeCardInfoPO;
import com.qx.eakay.service.stake.StakeCardService;
import com.qx.eakay.util.MSG;

/**
 * 
 * @author jixf
 * @date 2015年7月25日
 */
@Controller
@RequestMapping("/stake/card")
public class StakeCardContoller extends BaseController {

	@Autowired
	private StakeCardService cardService;

	/**
	 * 进入充电设备页面
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("page.htm")
	public ModelAndView page() throws Exception {
		return new ModelAndView("stake/stakeCard");
	}

	/**
	 * 进入充电卡新增页面
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("add.htm")
	public ModelAndView add() throws Exception {
		return new ModelAndView("stake/stakeCardAdd");
	}

	/**
	 * 通过新增按钮保存充电卡
	 * 
	 * @param deviceNamePO
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("saveAdd.htm")
	@ResponseBody
	public MSG saveAdd(StakeCardInfoPO cardInfoPO, HttpSession session)
			throws Exception {
		cardInfoPO.setMerchantId(this.getSessionUser(session).getMerchantId());
		// 转成大写
		cardInfoPO.setCardID(cardInfoPO.getCardID().toUpperCase());
		return cardService.createCard(cardInfoPO);
	}

	/**
	 * 进入充电卡编辑页面
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("edite.htm")
	public ModelAndView edite(Integer id) throws Exception {
		StakeCardInfoPO cardInfoPO = cardService.getCard(id);
		return new ModelAndView("stake/stakeCardEdite", "cardInfoPO",
				cardInfoPO);
	}

	/**
	 * 保存充电设备卡
	 * 
	 * @param batteryPO
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("saveEdite.htm")
	@ResponseBody
	public MSG saveEdite(StakeCardInfoPO cardInfoPO) throws Exception {
		// 转成大写
		cardInfoPO.setCardID(cardInfoPO.getCardID().toUpperCase());
		return cardService.updateCard(cardInfoPO);
	}

	/**
	 * 删除充电卡
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("delete.htm")
	@ResponseBody
	public MSG delete(Integer id, String status) throws Exception {
		MSG msg = new MSG();
		status = StringTools.decodeMethod(status);
		if ("恢复".equals(status)) {
			status = "正常";
		}
		msg.setSuccess(cardService.deleteCard(id, status));
		return msg;
	}

	/**
	 * 检测充电卡内置卡号是否已存在
	 * 
	 * @param checkFactoryNo
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("checkCardID.htm")
	@ResponseBody
	public MSG checkCardID(String cardID, Integer id, HttpSession session)
			throws Exception {
		MSG msg = new MSG();
		Integer merchantId = this.getSessionUser(session).getMerchantId();
		msg.setSuccess(cardService.cheakCardID(cardID, merchantId, id));
		return msg;
	}

	/**
	 * 检测充电卡外置卡号是否已存在
	 * 
	 * @param checkFactoryNo
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("checkCardNumber.htm")
	@ResponseBody
	public MSG checkCardNumber(String cardNumber, Integer id,
			HttpSession session) throws Exception {
		MSG msg = new MSG();
		Integer merchantId = this.getSessionUser(session).getMerchantId();
		msg.setSuccess(cardService.cheakCardNumber(cardNumber, merchantId, id));
		return msg;
	}

	/**
	 * 分页查询充电设备信息
	 * 
	 * @param aoData
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "ajaxData.htm")
	@ResponseBody
	public PageResults ajaxData(String aoData, String paraData,
			HttpSession session) {
		JSONObject jsonObject = getJsonPara(paraData, session);

		// jquery.datatables 分页查询的参数
		JSONArray jsonArray = JSONArray.fromObject(aoData);
		PageHelp pageHelp = JSONTools.toPageHelp(jsonArray);

		PageResults pageResults = cardService.findCardsPage(jsonObject,
				pageHelp.getiDisplayLength(), pageHelp.getiDisplayStart());
		pageResults.setsEcho(pageHelp.getsEcho());
		return pageResults;
	}

	/**
	 * 得到请求参数条件
	 * 
	 * @param paraData
	 * @param session
	 * @return
	 */
	private JSONObject getJsonPara(String paraData, HttpSession session) {
		paraData = StringTools.decodeMethod(paraData);
		// 动态查询条件参数
		JSONObject jsonObject = JSONObject.fromObject(paraData);
		SessionUserBO sessionUser = this.getSessionUser(session);
		jsonObject.put("merchantId", sessionUser.getMerchantId());

		return jsonObject;
	}

	/**
	 * 导出下载充电设备到Excel表格
	 * 
	 * @param paraData
	 * @param session
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("exportToExcel.htm")
	public ModelAndView exportToExcel(String paraData, HttpSession session,
			HttpServletResponse response) throws Exception {
		JSONObject jsonObject = getJsonPara(paraData, session);
		cardService.excelExportCards(response, jsonObject);
		return null;
	}

	/**
	 * 进入充电卡充值页面
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("rechargePage.htm")
	public ModelAndView rechargePage() throws Exception {
		return new ModelAndView("stake/stakeCardRecharge");
	}

	/**
	 * 根据内置卡号获取客户信息
	 * 
	 * @param cardId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("getCardByCardId.htm")
	@ResponseBody
	public MSG getCardByCardId(String cardID,HttpSession session) throws Exception {
		Integer merchantId = this.getSessionUser(session).getMerchantId();
		return cardService.getCustomerByCardId(cardID,merchantId);
	}

}
