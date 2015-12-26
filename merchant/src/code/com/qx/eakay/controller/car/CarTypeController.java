package com.qx.eakay.controller.car;

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
import com.qx.eakay.model.car.CarTypeDetailPO;
import com.qx.eakay.model.car.CarTypePO;
import com.qx.eakay.service.car.CarService;
import com.qx.eakay.service.car.CarTypeService;
import com.qx.eakay.util.MSG;

/**
 * 
 * @author Administrator
 * 
 */
@Controller
@RequestMapping("/car/type")
public class CarTypeController extends BaseController {

	@Autowired
	private CarTypeService carTypeService;

	@Autowired
	private CarService carService;

	/**
	 * 进入车辆型号界面
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("page.htm")
	public ModelAndView page() throws Exception {
		return new ModelAndView("car/carType");
	}

	@RequestMapping(value = "ajaxData.htm")
	@ResponseBody
	public PageResults ajaxData(String aoData, String paraData,
			HttpSession session) {
		JSONObject jsonObject = getJsonPara(paraData, session);

		// jquery.datatables 分页查询的参数
		JSONArray jsonArray = JSONArray.fromObject(aoData);
		PageHelp pageHelp = JSONTools.toPageHelp(jsonArray);

		PageResults pageResults = carTypeService.findCarTypePage(jsonObject,
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
	 * 导出下载车辆供应商到Excel表格
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
		carTypeService.excelExportCarType(response, jsonObject);
		return null;
	}

	/**
	 * 动态获取商家的车辆型号
	 * 
	 * @param paraData
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "ajaxTypes.htm")
	@ResponseBody
	public List<Map<String, Object>> ajaxTypes(String paraData,
			HttpSession session) {
		paraData = StringTools.decodeMethod(paraData);
		// 动态查询条件参数
		JSONObject jsonObject = JSONObject.fromObject(paraData);
		SessionUserBO sessionUser = this.getSessionUser(session);
		jsonObject.put("merchantId", sessionUser.getMerchantId());
		return carTypeService.findCarTypes(jsonObject);
	}

	/**
	 * 获取车辆型号下的颜色
	 * 
	 * @param paraData
	 * @return
	 */
	@RequestMapping(value = "ajaxTypeColors.htm")
	@ResponseBody
	public List<Map<String, Object>> ajaxTypeColors(String paraData) {
		paraData = StringTools.decodeMethod(paraData);
		// 动态查询条件参数
		JSONObject jsonObject = JSONObject.fromObject(paraData);
		Object obj = jsonObject.get("carTypeId");
		Integer typeId = null;
		try {
			typeId = Integer.parseInt(obj + "");
		} catch (Exception e) {

		}
		return carTypeService.findTypeColors(typeId);
	}
	

	/**
	 * 获取车辆的状态
	 * 目前值给予一个状态，因为是在前台点击“其他”按钮，车辆状态修改成“其他”，并没有别的状态，后期可以添加动态状态。
	 * 
	 * @param paraData
	 * @return
	 */
	@RequestMapping(value = "ajaxTypeStatus.htm")
	@ResponseBody
	public List<Map<String, Object>> ajaxTypeStatus(String paraData) {
		/**
		 * 此处传递的paraData值是空的，所以typeId也是空的没有用到，以后如果用的化可以根据paraData得到
		 */
		paraData = StringTools.decodeMethod(paraData);
		// 动态查询条件参数
		JSONObject jsonObject = JSONObject.fromObject(paraData);
		Object obj = jsonObject.get("carTypeId");
		Integer typeId = null;
		try {
			typeId = Integer.parseInt(obj + "");
		} catch (Exception e) {

		}
		//此处返回一个List<Map<String,Object>>类型，会自动转换成json以后返回。
		return carTypeService.findTypeStatus(typeId);
	}

	/**
	 * 进入车辆供应商添加界面
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("add.htm")
	public ModelAndView add() throws Exception {
		return new ModelAndView("car/carTypeAdd");
	}

	/**
	 * 保存新增车辆
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("saveAdd.htm")
	public ModelAndView saveAdd(CarTypePO typePO, String[] components,
			String[] statuss, String[] describes, HttpSession session)
			throws Exception {
		if (components != null) {
			for (int i = 0; i < components.length; i++) {
				CarTypeDetailPO detailPO = new CarTypeDetailPO();
				detailPO.setComponent(components[i]);
				detailPO.setDescribe(describes[i]);
				detailPO.setStatus(statuss[i]);
				typePO.getDetailPOs().add(detailPO);
			}
		}
		carTypeService.createCarType(typePO);
		return new ModelAndView("redirect:/system/message/success.htm");
	}

	/**
	 * 进入车辆供应商编辑界面
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("edite.htm")
	public ModelAndView edite(Integer id) throws Exception {
		CarTypePO typePO = carTypeService.getCarType(id);
		return new ModelAndView("car/carTypeEdite", "typePO", typePO);
	}

	@RequestMapping("saveEdite.htm")
	@ResponseBody
	public MSG saveEdite(CarTypePO typePO, String[] components,
			String[] statuss, String[] describes) throws Exception {
		MSG msg = new MSG();
		if (components != null) {
			for (int i = 0; i < components.length; i++) {
				CarTypeDetailPO detailPO = new CarTypeDetailPO();
				detailPO.setComponent(components[i]);
				detailPO.setDescribe(describes[i]);
				detailPO.setStatus(statuss[i]);
				typePO.getDetailPOs().add(detailPO);
			}
		}
		boolean bool = carTypeService.updateCarType(typePO);
		msg.setSuccess(bool);
		return msg;
	}

	@RequestMapping("delete.htm")
	@ResponseBody
	public MSG delete(Integer id) throws Exception {
		MSG msg = new MSG();
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("carTypeId", id);
		List<Map<String, Object>> types = carService.findCars(jsonObject);
		if (types.size() > 0) {
			msg.setSuccess(false);
			msg.setInfo("请先移除该型号下的车辆！");
			return msg;
		}
		boolean bool = carTypeService.deleteCarType(id);
		if (!bool) {
			msg.setInfo("系统异常！");
		}
		msg.setSuccess(bool);
		return msg;
	}

}
