package com.qx.eakay.controller.stake;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.qx.eakay.model.stake.StakePricePO;
import com.qx.eakay.service.merchant.SiteService;
import com.qx.eakay.service.stake.DeviceService;
import com.qx.eakay.service.stake.StakePriceService;
import com.qx.eakay.util.MSG;

/**
 * 充电套餐
 * 
 */
@Controller
@RequestMapping("stake/price")
public class StakePriceController extends BaseController {

	@Autowired
	private StakePriceService stakePriceService;
	@Autowired
	private SiteService siteService;
	@Autowired
	private DeviceService deviceService;

	/**
	 * 进入充电套餐页面
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("manager.htm")
	public ModelAndView timeCharts() throws Exception {
		return new ModelAndView("stake/stakePrice");
	}

	/**
	 * 分页查询充电套餐信息
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

		PageResults pageResults = stakePriceService.findPricePage(jsonObject,
				pageHelp.getiDisplayLength(), pageHelp.getiDisplayStart());
		pageResults.setsEcho(pageHelp.getsEcho());
		return pageResults;
	}

	/**
	 * 导出充电套餐信息到Excel表格
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
		stakePriceService.excelExportPrice(response, jsonObject);
		return null;
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
		// if (!sessionUser.isManagerUser()) {
		// jsonObject.put("siteId", sessionUser.getMerchantUserPO()
		// .getSiteId());
		// }
		return jsonObject;
	}

	/**
	 * 进入新增充电套餐页面
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("add.htm")
	public ModelAndView add() throws Exception {
		return new ModelAndView("stake/stakePriceAdd");
	}

	/**
	 * 保存新增充电套餐
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "saveAdd.htm")
	@ResponseBody
	public MSG saveAdd(StakePricePO stakePricePO, HttpSession session)
			throws Exception {
		MSG msg = new MSG();
		SessionUserBO sessionUser = this.getSessionUser(session);
		stakePricePO.setMerchantId(sessionUser.getMerchantId());
		msg.setSuccess(stakePriceService.createPrice(stakePricePO));
		return msg;
	}

	/**
	 * 删除充电套餐
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("delete.htm")
	@ResponseBody
	public MSG delete(Integer id) throws Exception {
		MSG msg = new MSG();
		msg.setSuccess(stakePriceService.deletePrice(id));
		return msg;
	}

	/**
	 * 判断充电套餐名称是否唯一
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "checkName.htm")
	@ResponseBody
	public MSG checkOnly(String name, Integer id, HttpSession session) {
		MSG msg = new MSG();
		SessionUserBO sessionUser = this.getSessionUser(session);
		Integer merchantId = sessionUser.getMerchantId();
		msg.setSuccess(stakePriceService.checkName(name, id, merchantId));
		return msg;
	}

	/**
	 * 进入修改充电套餐页面
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("edite.htm")
	public ModelAndView edite(Integer id) throws Exception {
		StakePricePO stakePricePO = stakePriceService.getStakePrice(id);
		return new ModelAndView("stake/stakePriceEdite", "stakePricePO",
				stakePricePO);
	}

	/**
	 * 保存修改充电套餐
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "saveEdite.htm")
	@ResponseBody
	public MSG saveEdite(StakePricePO stakePricePO, HttpSession session)
			throws Exception {
		MSG msg = new MSG();
		SessionUserBO sessionUser = this.getSessionUser(session);
		stakePricePO.setMerchantId(sessionUser.getMerchantId());
		msg.setSuccess(stakePriceService.editePrice(stakePricePO));
		return msg;
	}

	/**
	 * 查找充电收费套餐的充电桩
	 * 
	 * @param id
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("priceTypeStake.htm")
	@ResponseBody
	public Map<String, List<Map<String, Object>>> priceTypeStake(
			HttpSession session, String paraData) throws Exception {
		Map<String, List<Map<String, Object>>> map = new HashMap<String, List<Map<String, Object>>>();
		paraData = StringTools.decodeMethod(paraData);
		// 动态查询条件参数
		JSONObject jsonObject = JSONObject.fromObject(paraData);
		SessionUserBO sessionUser = this.getSessionUser(session);
		jsonObject.put("merchantId", sessionUser.getMerchantId());
		if (!sessionUser.isManagerUser()) {
			jsonObject.put("siteId", sessionUser.getMerchantUserPO()
					.getSiteId());
		}
		map.put("sites", siteService.findSites(jsonObject));
		map.put("stakes", deviceService.findDevices(jsonObject));
		return map;
	}

	/**
	 * 保存充电套餐选择充电桩
	 * 
	 * @param id
	 * @param stakeId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("priceSelectStake.htm")
	@ResponseBody
	public MSG savePriceStake(Integer id, String stakeId, String hid_stakeId)
			throws Exception {
		// 原来
		String[] arr1 = hid_stakeId.split(",");
		List<Integer> sids1 = new ArrayList<>();
		for (String sid1 : arr1) {
			try {
				sids1.add(Integer.parseInt(sid1));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		// 现在
		String[] arr = stakeId.split(",");
		List<Integer> sids = new ArrayList<>();
		for (String sid : arr) {
			try {
				sids.add(Integer.parseInt(sid));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		MSG msg = new MSG();
		boolean bool = stakePriceService.savePriceStake(id, sids, sids1);
		msg.setSuccess(bool);
		return msg;
	}

	/**
	 * 保存充电桩选择充电套餐
	 * 
	 * @param id
	 * @param stakeId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("stakeSelectPrice.htm")
	@ResponseBody
	public MSG saveStakePrice(Integer id, String stakeId, Integer priceId)
			throws Exception {
		MSG msg = new MSG();
		// 充电桩未改变充电套餐的保存
		if (priceId == id) {
			return msg;
		}
		String[] arr = stakeId.split(",");
		List<Integer> sids = new ArrayList<>();
		for (String sid : arr) {
			try {
				sids.add(Integer.parseInt(sid));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		boolean bool = stakePriceService.saveStakePrice(id, sids);
		msg.setSuccess(bool);
		return msg;
	}

	/**
	 * 动态加载充电套餐列表
	 * 
	 * @param carId
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "ajaxStakePrice.htm")
	@ResponseBody
	public List<Map<String, Object>> ajaxStakePrice(String paraData,
			HttpSession session) {
		JSONObject jsonObject = getJsonPara(paraData, session);
		return stakePriceService.findPriceList(jsonObject);
	}

}
