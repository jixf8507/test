package com.qx.eakay.controller.stake;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
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
import com.qx.eakay.model.stake.StakeDeviceNamePO;
import com.qx.eakay.service.stake.DeviceService;
import com.qx.eakay.util.MSG;

/**
 * 
 * @author jixf
 * @date 2015年7月15日
 */
@Controller
@RequestMapping("/stake/device")
public class DeviceController extends BaseController {

	@Autowired
	private DeviceService deviceService;

	/**
	 * 进入充电设备页面
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("page.htm")
	public ModelAndView page() throws Exception {
		return new ModelAndView("stake/device");
	}

	/**
	 * 进入充电设备管理页面
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("manager.htm")
	public ModelAndView manager() throws Exception {
		return new ModelAndView("stake/deviceManager");
	}

	/**
	 * 进入充电设备新增页面
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("add.htm")
	public ModelAndView add() throws Exception {
		return new ModelAndView("stake/deviceAdd");
	}

	/**
	 * 保存充电设备新增
	 * 
	 * @param deviceNamePO
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("saveAdd.htm")
	@ResponseBody
	public MSG saveAdd(StakeDeviceNamePO deviceNamePO, HttpSession session)
			throws Exception {
		deviceNamePO
				.setMerchantId(this.getSessionUser(session).getMerchantId());
		MSG msg = new MSG();
		msg.setSuccess(deviceService.createDevice(deviceNamePO));
		return msg;
	}

	/**
	 * 进入充电设备编辑页面
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("edite.htm")
	public ModelAndView edite(Integer id) throws Exception {
		StakeDeviceNamePO deviceNamePO = deviceService.getDevice(id);
		return new ModelAndView("stake/deviceEdite", "deviceNamePO",
				deviceNamePO);
	}

	/**
	 * 保存充电设备修改
	 * 
	 * @param batteryPO
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("saveEdite.htm")
	@ResponseBody
	public MSG saveEdite(StakeDeviceNamePO deviceNamePO, HttpSession session)
			throws Exception {
		deviceNamePO
				.setMerchantId(this.getSessionUser(session).getMerchantId());
		MSG msg = new MSG();
		msg.setSuccess(deviceService.updateDevice(deviceNamePO));
		return msg;
	}

	/**
	 * 删除充电设备
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("delete.htm")
	@ResponseBody
	public MSG delete(Integer id) throws Exception {
		MSG msg = new MSG();
		msg.setSuccess(deviceService.deleteDevice(id));
		return msg;
	}

	/**
	 * 检测充电设备编号是否已存在
	 * 
	 * @param factoryNo
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("checkFactoryNo.htm")
	@ResponseBody
	public MSG checkFactoryNo(String factoryNo, Integer id) throws Exception {
		MSG msg = new MSG();
		msg.setSuccess(deviceService.cheakFactoryNo(factoryNo, id));
		return msg;
	}

	/**
	 * 检测充电设备铭牌是否已存在
	 * 
	 * @param nameplate
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("checkNameplate.htm")
	@ResponseBody
	public MSG checkNameplate(String nameplate, Integer id) throws Exception {
		MSG msg = new MSG();
		msg.setSuccess(deviceService.checkNameplate(nameplate, id));
		return msg;
	}

	/**
	 * 检测充电设备名称是否已存在
	 * 
	 * @param deviceNo
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("checkDeviceNo.htm")
	@ResponseBody
	public MSG checkDeviceNo(String deviceNo, Integer id) throws Exception {
		MSG msg = new MSG();
		msg.setSuccess(deviceService.checkDeviceNo(deviceNo, id));
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

		PageResults pageResults = deviceService.findDevicesPage(jsonObject,
				pageHelp.getiDisplayLength(), pageHelp.getiDisplayStart());
		pageResults.setsEcho(pageHelp.getsEcho());
		return pageResults;
	}

	/**
	 * 加载充电设备列表
	 * 
	 * @param carId
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "ajaxStakeData.htm")
	@ResponseBody
	public MSG ajaxStakeData(Integer id) {
		StakeDeviceNamePO deviceNamePO = deviceService.getDevice(id);
		MSG msg = new MSG();
		Integer priceId = deviceNamePO.getPriceid();
		msg.setInfo(priceId + "");
		return msg;
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
			jsonObject.put("siteId", sessionUser.getMerchantUserPO()
					.getSiteId());
		}
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
		deviceService.excelExportDevices(response, jsonObject);
		return null;
	}

	/**
	 * 查找充电端口分类
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("portTypes.htm")
	@ResponseBody
	public List<Map<String, Object>> portTypes(String paraData)
			throws Exception {
		paraData = StringTools.decodeMethod(paraData);
		// 动态查询条件参数
		JSONObject jsonObject = JSONObject.fromObject(paraData);

		return deviceService.findPortTypes(jsonObject);
	}

	/**
	 * 查找充电站分区
	 * 
	 * @param siteId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("ajaxArea.htm")
	@ResponseBody
	public List<Map<String, Object>> ajaxArea(String paraData) throws Exception {
		paraData = StringTools.decodeMethod(paraData);
		JSONObject jsonObject = JSONObject.fromObject(paraData);
		return deviceService.findAreas(jsonObject.get("siteCode") + "");
	}

	/**
	 * 查找充电站分区
	 * 
	 * @param siteId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("ajaxDevice.htm")
	@ResponseBody
	public List<Map<String, Object>> ajaxDevice(String paraData,
			HttpSession session) throws Exception {
		paraData = StringTools.decodeMethod(paraData);
		JSONObject jsonObject = JSONObject.fromObject(paraData);
		if (StringUtils.isBlank(jsonObject.get("siteId") + "")) {
			return new ArrayList<>();
		}
		SessionUserBO sessionUser = this.getSessionUser(session);
		jsonObject.put("merchantId", sessionUser.getMerchantId());
		if (!sessionUser.isManagerUser()) {
			jsonObject.put("siteId", sessionUser.getMerchantUserPO()
					.getSiteId());
		}
		return deviceService.findDevices(jsonObject);
	}

	/**
	 * 动态加载设备的充电口
	 * 
	 * @param paraData
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("ajaxDevicePorts.htm")
	@ResponseBody
	public List<Map<String, Object>> ajaxDevicePorts(String paraData,
			HttpSession session) throws Exception {
		paraData = StringTools.decodeMethod(paraData);
		JSONObject jsonObject = JSONObject.fromObject(paraData);
		if (StringUtils.isBlank(jsonObject.get("factoryNo") + "")
				|| StringUtils.isBlank(jsonObject.get("pos") + "")) {
			return new ArrayList<>();
		}
		return deviceService.findDevicePorts(jsonObject);
	}

	@RequestMapping("ajaxMerchantPorts.htm")
	@ResponseBody
	public List<Map<String, Object>> ajaxMerchantPorts(String paraData,
			HttpSession session) throws Exception {
		paraData = StringTools.decodeMethod(paraData);
		JSONObject jsonObject = JSONObject.fromObject(paraData);
		jsonObject.put("merchantId", this.getSessionUser(session)
				.getMerchantId());
		return deviceService.findDevicePorts(jsonObject);
	}

	/**
	 * 查找充电桩
	 * 
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("ajaxDevicePoints.htm")
	@ResponseBody
	public List<Map<String, Object>> ajaxDevicePoints(HttpSession session,
			String paraData) throws Exception {
		paraData = StringTools.decodeMethod(paraData);
		JSONObject jsonObject = JSONObject.fromObject(paraData);
		SessionUserBO sessionUser = this.getSessionUser(session);
		jsonObject.put("merchantId", sessionUser.getMerchantId());
		return deviceService.findDevices(jsonObject);
	}

	/**
	 * 进入充电设备编辑页面(1托30 主桩子)
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("parentEdite.htm")
	public ModelAndView parentEdite(Integer id) throws Exception {
		StakeDeviceNamePO deviceNamePO = deviceService.getDevice(id);
		return new ModelAndView("stake/deviceParentEdite", "deviceNamePO",
				deviceNamePO);
	}

	/**
	 * 进入充电设备编辑页面(1托30 子桩子)
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("childEdite.htm")
	public ModelAndView childEdite(Integer id) throws Exception {
		StakeDeviceNamePO deviceNamePO = deviceService.getDeviceChild(id);
		return new ModelAndView("stake/deviceChildEdite", "deviceNamePO",
				deviceNamePO);
	}
}
