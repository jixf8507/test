package com.qx.eakay.controller.price;

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
import com.qx.eakay.model.price.WhHoursPriceTypePO;
import com.qx.eakay.service.price.PriceTypeService;
import com.qx.eakay.util.MSG;

/**
 * 
 * @author Administrator
 * 
 */
@Controller
@RequestMapping("/price/priceType")
public class PriceTypeController extends BaseController {

	@Autowired
	private PriceTypeService priceTypeService;

	/**
	 * 进入收费套餐页面
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("page.htm")
	public ModelAndView page() throws Exception {
		return new ModelAndView("price/priceType");
	}

	/**
	 * 进入收费套餐管理界面
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("manager.htm")
	public ModelAndView manager() throws Exception {
		return new ModelAndView("price/priceTypeManager");
	}

	/**
	 * 进入新增收费套餐界面
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("add.htm")
	public ModelAndView add() throws Exception {
		return new ModelAndView("price/priceTypeAdd");
	}

	@RequestMapping("ajaxTimes.htm")
	@ResponseBody
	public List<Map<String, Object>> ajaxTimes(Integer id) throws Exception {
		List<Map<String, Object>> times = new ArrayList<>();
		for (int i = 0; i <= 9; i++) {
			Map<String, Object> map1 = new HashMap<String, Object>();
			map1.put("name", "0" + i + ":00");
			map1.put("value", "2001-01-01 0" + i + ":00");
			Map<String, Object> map2 = new HashMap<String, Object>();
			map2.put("name", "0" + i + ":30");
			map2.put("value", "2001-01-01 0" + i + ":30");
			times.add(map1);
			times.add(map2);
		}
		for (int i = 10; i <= 23; i++) {
			Map<String, Object> map1 = new HashMap<String, Object>();
			map1.put("name", i + ":00");
			map1.put("value", "2001-01-01 " + i + ":00");
			Map<String, Object> map2 = new HashMap<String, Object>();
			map2.put("name", i + ":30");
			map2.put("value", "2001-01-01 " + i + ":30");
			times.add(map1);
			times.add(map2);
		}
		return times;
	}

	/**
	 * 通过按钮方式增加租赁套餐
	 * @param hoursPriceTypePO
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("saveAdd.htm")
	@ResponseBody
	public MSG saveAdd(WhHoursPriceTypePO hoursPriceTypePO,
			HttpSession session) throws Exception {
		MSG msg = new MSG();
		hoursPriceTypePO.setMerchantId(this.getSessionUser(session)
				.getMerchantId());
		msg.setSuccess(priceTypeService.createPriceType(hoursPriceTypePO));
		return msg;
	}
	
	

	// /**
	// * 查看详细收费规则套餐
	// *
	// * @param id
	// * @return
	// * @throws Exception
	// */
	// @RequestMapping("edite.htm")
	// public ModelAndView edite(Integer id) throws Exception {
	// WhHoursPriceTypePO priceTypePO = priceTypeService.getPriceType(id);
	// return new ModelAndView("price/priceTypeEdite", "hoursPriceTypePO",
	// priceTypePO);
	// }

	// @RequestMapping("saveEdite.htm")
	// @ResponseBody
	// public MSG saveEdite(WhHoursPriceTypePO hoursPriceTypePO) throws
	// Exception {
	// MSG msg = new MSG() ;
	// priceTypeService.u
	// return msg;
	// }

	@RequestMapping("delete.htm")
	@ResponseBody
	public MSG delete(Integer id) throws Exception {
		MSG msg = new MSG();
		boolean bool = priceTypeService.deletePriceType(id);
		msg.setSuccess(bool);
		return msg;
	}

	/**
	 * 查看详细收费规则套餐
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("detail.htm")
	public ModelAndView detail(Integer id) throws Exception {
		WhHoursPriceTypePO priceTypePO = priceTypeService.getPriceType(id);
		return new ModelAndView("price/priceTypeDetail", "hoursPriceTypePO",
				priceTypePO);
	}

	/**
	 * 分页查询车辆状态信息
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

		PageResults pageResults = priceTypeService.findTypesPage(jsonObject,
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
	 * 导出下载车辆信息到Excel表格
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
		priceTypeService.excelExportTypes(response, jsonObject);
		return null;
	}
	
}
