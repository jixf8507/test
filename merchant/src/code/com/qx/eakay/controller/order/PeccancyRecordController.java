package com.qx.eakay.controller.order;

import java.io.InputStream;
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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.qx.eakay.export.order.PaccancyRecordImport;
import com.qx.common.base.PageHelp;
import com.qx.common.base.PageResults;
import com.qx.common.tools.JSONTools;
import com.qx.common.tools.StringTools;
import com.qx.eakay.controller.BaseController;
import com.qx.eakay.login.SessionUserBO;
import com.qx.eakay.model.order.OrderPeccancyRecordPO;
import com.qx.eakay.service.order.PeccancyRecordService;
import com.qx.eakay.util.MSG;

@Controller
@RequestMapping("/order/peccancyRecord")
public class PeccancyRecordController extends BaseController {

	@Autowired
	private PeccancyRecordService peccancyRecordService;

	/**
	 * 进入违章记录列表
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("page.htm")
	public ModelAndView page() throws Exception {
		return new ModelAndView("order/peccancyRecord");
	}

	/**
	 * 进入新增违章记录界面
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("add.htm")
	public ModelAndView add() throws Exception {
		return new ModelAndView("order/peccancyRecordAdd");
	}

	/**
	 * 保存新增违章记录
	 * 
	 * @param peccancyRecordPO
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("saveAdd.htm")
	public ModelAndView saveAdd(OrderPeccancyRecordPO peccancyRecordPO,
			HttpSession session) throws Exception {
		SessionUserBO sessionUser = this.getSessionUser(session);
		peccancyRecordPO.setMerchantId(sessionUser.getMerchantId());
		peccancyRecordPO.setCheckMan(sessionUser.getName());
		peccancyRecordService.createPeccancyRecord(peccancyRecordPO);
		return new ModelAndView("redirect:/system/message/success.htm");
	}

	/**
	 * 通过按钮保存新增违章记录
	 * 
	 * @param peccancyRecordPO
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("saveAdd2.htm")
	@ResponseBody
	public MSG saveAdd2(OrderPeccancyRecordPO peccancyRecordPO,
			HttpSession session) throws Exception {
		MSG msg = new MSG();
		SessionUserBO sessionUser = this.getSessionUser(session);
		peccancyRecordPO.setMerchantId(sessionUser.getMerchantId());
		peccancyRecordPO.setCheckMan(sessionUser.getName());
		msg.setSuccess(peccancyRecordService
				.createPeccancyRecord(peccancyRecordPO));
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

		PageResults pageResults = peccancyRecordService.findPeccancyRecordPage(
				jsonObject, pageHelp.getiDisplayLength(),
				pageHelp.getiDisplayStart());
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
		peccancyRecordService.excelExportPeccancyRecord(response, jsonObject);
		return null;
	}

	/**
	 * 进入处理违章记录界面
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("edit.htm")
	public ModelAndView edit(Integer id) throws Exception {

		OrderPeccancyRecordPO peccancyRecord = peccancyRecordService
				.getById(id);
		return new ModelAndView("order/peccancyRecordEdit", "peccancyRecord",
				peccancyRecord);
	}

	/**
	 * 保存修改违章记录
	 * 
	 * @param peccancyRecordPO
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "saveEdit.htm")
	@ResponseBody
	public MSG saveEdit(OrderPeccancyRecordPO peccancyRecordPO,
			HttpSession session) {
		MSG msg = new MSG();
		SessionUserBO sessionUser = this.getSessionUser(session);
		peccancyRecordPO.setMerchantId(sessionUser.getMerchantId());
		peccancyRecordPO.setCheckMan(sessionUser.getName());

		boolean bool = peccancyRecordService
				.editPeccancyRecord(peccancyRecordPO);
		msg.setSuccess(bool);
		return msg;
	}

	@RequestMapping("loadFile.htm")
	public ModelAndView loadFIle() throws Exception {
		return new ModelAndView("order/loadFile");
	}

	/**
	 * 读取客户传入的文件
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "importToExcel.htm")
	public ModelAndView importToExcel(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		InputStream in = null;
		List<List<Object>> listob = null;
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		MultipartFile file = multipartRequest.getFile("loadFile");
		if (file.isEmpty()) {
			throw new Exception("文件不存在");
		}
		in = file.getInputStream();
		listob = PaccancyRecordImport.getBankListByExcel(in,
				file.getOriginalFilename());
		List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
		
			if (listob.size() > 0) {
				for (List<Object> list1 : listob) {
					Map<String, Object> map = new HashMap<>();
					map.put("carNumber", list1.get(0));
					map.put("time", list1.get(1));
					map.put("address", list1.get(2));
					map.put("info", list1.get(3));
					resultList.add(map);
				}
				list = peccancyRecordService.selectOrder(resultList);
			} else {
				return new ModelAndView("order/importResult", "list", list);
			}
		
		return new ModelAndView("order/importResult", "list", list);
		

	}

	/**
	 * 根据用户上传的文件更新数据库
	 * 
	 * @param carPO
	 * @param session
	 * @throws Exception
	 */
	@RequestMapping("save_insert.htm")
	@ResponseBody
	@Transactional
	public MSG save_insert(String[] orderId, String[] prccancyRecordTime,
			String[] address, String[] info, HttpServletRequest request,
			HttpSession session) throws Exception {
		MSG msg = new MSG();
		OrderPeccancyRecordPO peccancyRecordPO = new OrderPeccancyRecordPO();
		SessionUserBO sessionUser = this.getSessionUser(session);
		request.setCharacterEncoding("UTF-8");
		orderId = request.getParameterValues("orderId");
		prccancyRecordTime = request.getParameterValues("prccancyRecordTime");
		address = request.getParameterValues("address");
		info = request.getParameterValues("info");
		boolean flag = true;
		for (int i = 0; i < orderId.length; i++) {
			peccancyRecordPO.setOrderId(Integer.parseInt(orderId[i]));
			peccancyRecordPO.setPeccancyTime(prccancyRecordTime[i]);
			peccancyRecordPO.setAddress(address[i]);
			peccancyRecordPO.setInfo(info[i]);
			peccancyRecordPO.setImg("");
			peccancyRecordPO.setPayStatus("未处理");
			peccancyRecordPO.setCheckMan(sessionUser.getName());
			peccancyRecordPO.setPayCost(new java.math.BigDecimal(0.0));
			if (peccancyRecordService.save_insert(peccancyRecordPO)) {
			} else
				flag = false;
		}
		msg.setSuccess(flag);
		return msg;

	}
}
