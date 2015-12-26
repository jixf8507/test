package com.qx.eakay.controller.car;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.qx.common.base.PageHelp;
import com.qx.common.base.PageResults;
import com.qx.common.tools.JSONTools;
import com.qx.common.tools.StringTools;
import com.qx.eakay.controller.BaseController;
import com.qx.eakay.dao.order.OrderDao;
import com.qx.eakay.login.SessionUserBO;
import com.qx.eakay.model.car.CarPO;
import com.qx.eakay.model.car.CarPO.CarStatus;
import com.qx.eakay.service.car.CarService;
import com.qx.eakay.service.merchant.SiteService;
import com.qx.eakay.util.MSG;

/**
 * 
 * @author Administrator
 * 
 */
@Controller
@RequestMapping("/car/car")
public class CarController extends BaseController {

	@Autowired
	private CarService carService;
	@Autowired
	private SiteService siteService;
	@Autowired
	private OrderDao orderDao;

	/**
	 * 进入车辆信息页面
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("page.htm")
	public ModelAndView page() throws Exception {
		return new ModelAndView("car/car");
	}

	/**
	 * 进入车辆信息管理页面
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("manager.htm")
	public ModelAndView manager() throws Exception {
		return new ModelAndView("car/carManager");
	}

	/**
	 * 进入车辆状态页面
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("status.htm")
	public ModelAndView status() throws Exception {
		return new ModelAndView("car/status");
	}

	/**
	 * 进入车辆故障页面
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("fault.htm")
	public ModelAndView fault() throws Exception {
		return new ModelAndView("car/fault");
	}

	/**
	 * 进入添加车辆页面
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("add.htm")
	public ModelAndView add() throws Exception {
		return new ModelAndView("car/carAdd");
	}

	/**
	 * 新增车辆
	 * 
	 * @param carPO
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("saveAdd.htm")
	public ModelAndView saveAdd(CarPO carPO, HttpSession session)
			throws Exception {
		carPO.setMerchantId(this.getSessionUser(session).getMerchantId());
		carPO.setBigImgs(carPO.getBigIcon());
		carPO.setLittleIcon(carPO.getBigIcon());
		carPO.setLittleImgs(carPO.getBigIcon());
		carService.createCar(carPO);
		return new ModelAndView("redirect:/system/message/success.htm");
	}

	/**
	 * 进入车辆编辑界面
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("edite.htm")
	public ModelAndView edite(Integer id) throws Exception {
		CarPO carPO = carService.getCar(id);
		return new ModelAndView("car/carEdite", "carPO", carPO);
	}

	/**
	 * 进入车辆状态编辑界面
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("editeStatus.htm")
	public ModelAndView editeStatus(Integer id) throws Exception {
		CarPO carPO = carService.getCar(id);
		return new ModelAndView("car/carEditeStatus", "carPO", carPO);
	}

	@RequestMapping("saveEdite.htm")
	@ResponseBody
	public MSG saveEdite(CarPO carPO) throws Exception {
		carPO.setBigImgs(carPO.getBigIcon());
		carPO.setLittleIcon(carPO.getBigIcon());
		carPO.setLittleImgs(carPO.getBigIcon());
		MSG msg = new MSG();
		boolean bool = carService.updateCar(carPO);
		msg.setSuccess(bool);
		return msg;
	}

	/**
	 * 删除车辆
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "delete.htm")
	@ResponseBody
	public MSG delete(Integer id) {
		MSG msg = new MSG();
		boolean bool = carService.deleteCar(id);
		msg.setSuccess(bool);
		return msg;
	}

	@RequestMapping(value = "publish.htm")
	@ResponseBody
	public MSG publish(String cids) {
		String[] ids = cids.split(",");
		List<Integer> carIds = new ArrayList<>();
		for (String id : ids) {
			carIds.add(Integer.parseInt(id));
		}
		MSG msg = new MSG();
		boolean bool = carService.batchPublish(carIds);
		msg.setSuccess(bool);
		return msg;
	}

	/**
	 * 分页查询车辆状态信息（关联order表）
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

		PageResults pageResults = carService.findCarsPage(jsonObject,
				pageHelp.getiDisplayLength(), pageHelp.getiDisplayStart());
		pageResults.setsEcho(pageHelp.getsEcho());
		return pageResults;
	}
	
	/**
	 * 分页查询车辆状态信息
	 * 
	 * @param aoData
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "ajaxCarData.htm")
	@ResponseBody
	public PageResults ajaxCarData(String aoData, String paraData,
			HttpSession session) {
		JSONObject jsonObject = getJsonPara(paraData, session);
		
		// jquery.datatables 分页查询的参数
		JSONArray jsonArray = JSONArray.fromObject(aoData);
		PageHelp pageHelp = JSONTools.toPageHelp(jsonArray);
		
		PageResults pageResults = carService.findOnlyCarsPage(jsonObject,
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
		if (!sessionUser.isManagerUser()) {
			jsonObject.put("curSiteId", sessionUser.getMerchantUserPO()
					.getSiteId());
		}
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
		carService.excelExportCars(response, jsonObject);
		return null;
	}

	/**
	 * 检查车牌号码是否重复
	 * 
	 * @param carNumber
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "checkCarNumber.htm")
	@ResponseBody
	public MSG checkCarNumber(String carNumber, Integer id) {
		MSG msg = new MSG();
		boolean bool = carService.checkCarNumber(carNumber, id);
		msg.setSuccess(bool);
		return msg;
	}

	/**
	 * 检查车牌号码是否重复
	 * 
	 * @param carNumber
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "checkCarVin.htm")
	@ResponseBody
	public MSG checkCarVin(String vin, Integer id) {
		MSG msg = new MSG();
		boolean bool = carService.checkCarVin(vin, id);
		msg.setSuccess(bool);
		return msg;
	}

	/**
	 * 修改车辆的状态
	 * 
	 * @param status
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "updateStatus.htm")
	@ResponseBody
	public MSG updateStatus(CarStatus status, Integer id) {
		MSG msg = new MSG();
		if (status == null) {
			msg.setSuccess(false);
		} else {
			boolean bool = carService.updateStatus(status, id);
			msg.setSuccess(bool);
		}
		return msg;
	}

	/**
	 * 修改状态和描述
	 * 
	 * @param carPO
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("saveStatusDescribe.htm")
	@ResponseBody
	public MSG updateStatusDescribe(CarPO carPO) throws Exception {
		MSG msg = new MSG();
		Integer id = carPO.getId();
		boolean bool = carService.updateStatusAndDescribe(carPO, id);
		msg.setSuccess(bool);
		return msg;
	}

	/**
	 * 获取未安装设备的车辆
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("noDeviceCars.htm")
	@ResponseBody
	public Map<String, List<Map<String, Object>>> noDeviceCars(Integer id,
			HttpSession session) throws Exception {
		Map<String, List<Map<String, Object>>> map = new HashMap<String, List<Map<String, Object>>>();

		JSONObject jsonObject = new JSONObject();
		SessionUserBO sessionUser = this.getSessionUser(session);
		jsonObject.put("merchantId", sessionUser.getMerchantId());
		if (!sessionUser.isManagerUser()) {
			jsonObject.put("siteId", sessionUser.getMerchantUserPO()
					.getSiteId());
		}
		map.put("sites", siteService.findSites(jsonObject));
		jsonObject.put("deviceId", id);
		map.put("cars", carService.findNoDeviceCars(jsonObject));
		return map;
	}

	/**
	 * 查找租赁收费套餐的车辆
	 * 
	 * @param id
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("priceTypeCars.htm")
	@ResponseBody
	public Map<String, List<Map<String, Object>>> priceTypeCars(Integer id,
			String tableName, HttpSession session) throws Exception {
		Map<String, List<Map<String, Object>>> map = new HashMap<String, List<Map<String, Object>>>();

		JSONObject jsonObject = new JSONObject();
		SessionUserBO sessionUser = this.getSessionUser(session);
		jsonObject.put("merchantId", sessionUser.getMerchantId());
		if (!sessionUser.isManagerUser()) {
			jsonObject.put("siteId", sessionUser.getMerchantUserPO()
					.getSiteId());
		}
		jsonObject.put("tableName", tableName);

		map.put("sites", siteService.findSites(jsonObject));
		jsonObject.put("priceTypeId", id);
		map.put("cars", carService.findPriceTypeCars(jsonObject));
		return map;
	}

	/**
	 * 查找车辆坐标
	 * 
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("ajaxCarPoints.htm")
	@ResponseBody
	public List<Map<String, Object>> ajaxCarPoints(HttpSession session,String carNumber,Integer carId)
			throws Exception {
		JSONObject jsonObject = new JSONObject();
		SessionUserBO sessionUser = this.getSessionUser(session);
		jsonObject.put("merchantId", sessionUser.getMerchantId());
		if(carNumber!=""&&carNumber!=null){
			jsonObject.put("carNumber", "%"+carNumber+"%");
		}
		if(carId!=null){
			jsonObject.put("carId", carId);
		}
		if (!sessionUser.isManagerUser()) {
			jsonObject.put("siteId", sessionUser.getMerchantUserPO()
					.getSiteId());
		}
		return carService.findCarPoints(jsonObject);
	}

	/**
	 * 查找根据车辆的站点查找车辆坐标
	 * 
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("ajaxCarPointsBySite.htm")
	@ResponseBody
	public List<Map<String, Object>> ajaxCarPointsBySite(
			HttpServletRequest request) throws Exception {
		JSONObject jsonObject = new JSONObject();
		// 从session中取得siteId的request作用域中的值
		String siteId = request.getParameter("siteId");
		jsonObject.put("siteId", siteId);
		return carService.findCarPoints(jsonObject);
	}

	/**
	 * 进入车辆状态弹出层页面
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("statusWin.htm")
	public ModelAndView statusWin(Integer siteId, String status)
			throws Exception {
		ModelMap modelMap = new ModelMap();
		status = StringTools.decodeMethod(status);
		modelMap.put("status", status);
		return new ModelAndView("car/statusWin", "modelMap", modelMap);
	}

	/**
	 * 分页查询车辆状态信息
	 * 
	 * @param aoData
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "ajaxOtherOrderData.htm")
	@ResponseBody
	public PageResults ajaxOtherOrderData(String aoData, String paraData,
			HttpSession session) {
		JSONObject jsonObject = getJsonPara(paraData, session);

		JSONArray jsonArray = JSONArray.fromObject(aoData);
		PageHelp pageHelp = JSONTools.toPageHelp(jsonArray);

		PageResults pageResults = orderDao.otherMonitorSiteSQL(jsonObject,
				pageHelp.getiDisplayLength(), pageHelp.getiDisplayStart());
		pageResults.setsEcho(pageHelp.getsEcho());
		return pageResults;
	}

	/**
	 * 进入车辆图片管理页面
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("carPicture.htm")
	public ModelAndView carPicture(Integer carId) throws Exception {
		CarPO carPO = carService.getCar(carId);
		return new ModelAndView("car/carPicture","carPO",carPO);
	}
}
