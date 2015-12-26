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
import com.qx.eakay.dao.car.CarDeviceDao;
import com.qx.eakay.login.SessionUserBO;
import com.qx.eakay.model.car.SimPO;
import com.qx.eakay.service.car.CarDeviceService;
import com.qx.eakay.util.MSG;

/**
 * 车载设备
 * 
 * @author jixf
 * @date 2015年7月6日
 */
@Controller
@RequestMapping("/car/device")
public class CarDeviceController extends BaseController {

	@Autowired
	private CarDeviceService carDeviceService;
	@Autowired
	private CarDeviceDao carDeviceDao;

	/**
	 * 进入车载设备页面
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("page.htm")
	public ModelAndView page() throws Exception {
		return new ModelAndView("car/carDevice");
	}

	/**
	 * 修改设备的安装车辆
	 * 
	 * @param id
	 * @param carId
	 * @return
	 */
	@RequestMapping(value = "updateCar.htm")
	@ResponseBody
	public MSG updateCar(Integer id, Integer carId,String carTableName) {
		MSG msg = new MSG();
		boolean bool = carDeviceService.updateCarId(carId,carTableName, id);
		msg.setSuccess(bool);
		return msg;
	}
	
	/**
	 * 修改设备的安装设备卡
	 * 
	 * @param id
	 * @param carId
	 * @return
	 */
	@RequestMapping(value = "updateSim.htm")
	@ResponseBody
	public MSG updateSim(Integer id, Integer deviceId) {
		MSG msg = new MSG();
		msg.setSuccess(carDeviceService.updateSim(id,deviceId));
		return msg;
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

		PageResults pageResults = carDeviceService.findDevicesPage(jsonObject,
				pageHelp.getiDisplayLength(), pageHelp.getiDisplayStart());
		pageResults.setsEcho(pageHelp.getsEcho());
		return pageResults;
	}

	/**
	 * 动态加载车载设备
	 * 
	 * @param paraData
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "ajaxCarDevice.htm")
	@ResponseBody
	public List<Map<String, Object>> ajaxCarDevice(String paraData,
			HttpSession session) {
		JSONObject jsonObject = getJsonPara(paraData, session);
		return carDeviceService.findCarDevice(jsonObject);
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
		carDeviceService.excelExportDevices(response, jsonObject);
		return null;
	}
	

	/**
	 * 进入车载设备卡管理页面
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("sim.htm")
	public ModelAndView sim() throws Exception {
		return new ModelAndView("car/carDeviceSim");
	}
	
	/**
	 * 分页查询车载设备卡信息
	 * 
	 * @param aoData
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "ajaxSimData.htm")
	@ResponseBody
	public PageResults ajaxSimData(String aoData, String paraData,
			HttpSession session) {
		JSONObject jsonObject = getJsonPara(paraData, session);
		
		JSONArray jsonArray = JSONArray.fromObject(aoData);
		PageHelp pageHelp = JSONTools.toPageHelp(jsonArray);
		
		PageResults pageResults = carDeviceService.findSimPage(jsonObject,
				pageHelp.getiDisplayLength(), pageHelp.getiDisplayStart());
		pageResults.setsEcho(pageHelp.getsEcho());
		return pageResults;
	}
	
	/**
	 * 导出车载设备卡信息到Excel表格
	 * 
	 * @param paraData
	 * @param session
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("exportSimToExcel.htm")
	public ModelAndView exportSimToExcel(String paraData, HttpSession session,
			HttpServletResponse response) throws Exception {
		JSONObject jsonObject = getJsonPara(paraData, session);
		carDeviceService.excelExportSim(response, jsonObject);
		return null;
	}

	/**
	 * 进入车载设备卡管理页面
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("add.htm")
	public ModelAndView add() throws Exception {
		return new ModelAndView("car/carDeviceSimAdd");
	}
	
	/**
	 * 保存新增车载设备卡
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("saveAdd.htm")
	@ResponseBody
	public MSG saveAdd(SimPO simPO,HttpSession session) throws Exception {
		MSG msg = new MSG();
		SessionUserBO sessionUser = this.getSessionUser(session);
		simPO.setMerchantId(sessionUser.getMerchantId());
		msg.setSuccess(carDeviceService.saveAdd(simPO));
		return msg;
	}
	
	/**
	 * 检查车载设备卡号是否存在
	 * 
	 * @param simPO
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("checkSimCard.htm")
	@ResponseBody
	public MSG checkSimCard(Integer id,String simCard) throws Exception {
		MSG msg = new MSG();
		msg.setSuccess(carDeviceService.checkSimCard(id,simCard));
		return msg;
	}
	
	/**
	 * 删除车载设备卡
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("deleteSim.htm")
	@ResponseBody
	public MSG deleteSim(Integer id) throws Exception {
		MSG msg = new MSG();
		msg.setSuccess(carDeviceService.deleteSim(id));
		return msg;
	}
	
	/**
	 * 移除车载设备（卡）
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("resetSim.htm")
	@ResponseBody
	public MSG resetSim(Integer id) throws Exception {
		MSG msg = new MSG();
		msg.setSuccess(carDeviceDao.updateSim(id,0));
		return msg;
	}
	
	/**
	 * 进入修改车载设备卡页面
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("edite.htm")
	public ModelAndView edite(Integer id) throws Exception {
		SimPO simPO = carDeviceService.getSim(id);
		return new ModelAndView("car/carDeviceSimEdite","simPO",simPO);
	}
	
	/**
	 * 保存修改车载设备卡
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("saveEdite.htm")
	@ResponseBody
	public MSG saveEdite(SimPO simPO,HttpSession session) throws Exception {
		MSG msg = new MSG();
		SessionUserBO sessionUser = this.getSessionUser(session);
		simPO.setMerchantId(sessionUser.getMerchantId());
		msg.setSuccess(carDeviceService.saveEdite(simPO));
		return msg;
	}
	
	
}
