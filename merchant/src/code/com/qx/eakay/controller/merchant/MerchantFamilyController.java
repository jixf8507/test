package com.qx.eakay.controller.merchant;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
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
import com.qx.common.tools.MyDateUtil;
import com.qx.common.tools.StringTools;
import com.qx.eakay.controller.BaseController;
import com.qx.eakay.login.SessionUserBO;
import com.qx.eakay.model.merchant.MerchantFamilyPO;
import com.qx.eakay.model.merchant.MerchantFamilyPO.MerchantFamilyType;
import com.qx.eakay.model.sys.SysFileUrlsPO;
import com.qx.eakay.service.merchant.MerchantFamilyService;
import com.qx.eakay.util.MSG;

@Controller
@RequestMapping("/merchant/family")
public class MerchantFamilyController extends BaseController {
	@Autowired
	private MerchantFamilyService merchantFamilyService;

	
	/**
	 * 跳转到，家庭账户管理界面
	 */
	@RequestMapping("page.htm")
	public ModelAndView page() throws Exception {
		return new ModelAndView("merchant/familyManager");
	}

	/**
	 * 跳转到用户选择页面
	 */
	@RequestMapping("select.htm")
	public ModelAndView select() throws Exception {
		return new ModelAndView("merchant/familySelect");
	}

	/**
	 * 分页查询家庭
	 * 
	 * @param aoData
	 * @param paraData
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

		PageResults pageResults = merchantFamilyService.findMerchantFamilyPage(
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
	 * 增加家庭跳转弹出框页面
	 */
	@RequestMapping("add.htm")
	public ModelAndView add() throws Exception {
		return new ModelAndView("merchant/familyAdd");
	}

	/**
	 * 创建家庭
	 * 
	 * @param aoData
	 * @param paraData
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "saveAdd.htm")
	@ResponseBody
	public MSG saveAdd(MerchantFamilyPO merchantFamilyPO, HttpSession session,
			HttpServletRequest request) {
		MSG msg = new MSG();
		String url = request.getParameter("familyImgHidden");
		String fileName = request.getParameter("idFamilyImgHidden");
		SessionUserBO sessionUserBO = this.getSessionUser(session);
		merchantFamilyPO.setMerchantid(sessionUserBO.getMerchantId());
		msg.setSuccess(merchantFamilyService.createFamily(merchantFamilyPO,
				url, fileName));
		return msg;
	}

	/**
	 * 检查待修改的负责人是否已经是负责人
	 */
	@RequestMapping(value = "checkHouseHolder.htm")
	@ResponseBody
	public MSG checkHouseHolder(String customerid) {
		MSG msg = new MSG();
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("customerId", customerid);
		//jsonObject.put("status", "正常");

		List<Map<String, Object>> list=merchantFamilyService.findMerchantFamilyByJSONObject(jsonObject);
		if (null!=list && list.size()>0) {
			msg.setInfo(list.get(0).get("id")+"");
			msg.setSuccess(false);
		} else {
			msg.setSuccess(true);
		}
		return msg;
	}

	
	/**
	 * 检查用户是否已经有家庭成员关系或者户主
	 */
	@RequestMapping(value="checkMembersAndCustomer.htm")
	@ResponseBody
	public MSG checkMembersAndCustomer(String customerid) {
		MSG msg = new MSG();
		JSONObject jsonObject =new JSONObject();
		jsonObject.put("customerId", customerid);
//		List<Map<String, Object>> list=merchantFamilyService.findMerchantFamilyCustomerByJSONObject(jsonObject);
//		if (null!=list && list.size()>0) {
//			msg.setInfo(list.get(0).get("familyId")+"");
//			msg.setSuccess(false);
//		}else{
//			msg.setSuccess(true);
//		}
		String result=merchantFamilyService.checkCustomer(jsonObject);
		if (null!=result) {
			msg.setInfo(result);
			msg.setSuccess(false);
		}else{
			msg.setSuccess(true);
		}
		return msg;
	}
	
	/**
	 * 只检查是否有成员关系。
	 */
	@RequestMapping(value="checkMembers.htm")
	@ResponseBody
	public MSG checkMembers(String customerid) {
		MSG msg = new MSG();
		JSONObject jsonObject =new JSONObject();
		jsonObject.put("customerId", customerid);
		String result =merchantFamilyService.checkCustomer2(jsonObject);
		if (null!=result) {
			msg.setInfo(result);
			msg.setSuccess(false);
		}else{
			msg.setSuccess(true);
		}
		return msg;
	}
	
	
	
	
	
	/**
	 * 跳转到家庭的成员添加页面
	 */
	@RequestMapping("editAddMember.htm")
	public ModelAndView editAddMember(Integer id) throws Exception {
		MerchantFamilyPO merchantFamilyPO=merchantFamilyService.getMerchantFamilyPO(id);
		return new ModelAndView("merchant/familyAddMember","merchantFamilyPO",merchantFamilyPO);
	}	
	
	/**
	 * 批量创建家庭成员关系
	 * @param idsStr
	 * @param familyId
	 * @return
	 */
	@RequestMapping("saveAddMember.htm")
	@ResponseBody
	public MSG createFamilyCustomerByIds(String idsStr,String familyId) {
		MSG msg = new MSG();
		msg.setSuccess(merchantFamilyService.createFamilyCustomerByIds(idsStr, familyId));
		return msg;
	}
	
	/**
	 * 跳转到编辑家庭页面，可以编辑家庭信息，可以指定查看家庭的相关证件
	 */
	@RequestMapping("editMember.htm")
	public ModelAndView editMember(Integer id) throws Exception {
		//查找出当前家庭
		//MerchantFamilyPO merchantFamilyPO=merchantFamilyService.getMerchantFamilyPO(id);
		JSONObject jsonObject =new JSONObject();
		jsonObject.put("id", id);
		List<Map<String, Object>> list=merchantFamilyService.findMerchantFamilyByJSONObject(jsonObject);
		//因为根据id去找只能找到一条记录，所以取得list中的第一个值
		Map<String, Object> rsMap =list.get(0);
		MerchantFamilyPO merchantFamilyPO=new MerchantFamilyPO();
		merchantFamilyPO.setHomeaddress(rsMap.get("homeAddress")+"");
		merchantFamilyPO.setHomephone(rsMap.get("homePhone")+"");
		merchantFamilyPO.setHouseholder(rsMap.get("householder")+"");
		merchantFamilyPO.setId(id);
		merchantFamilyPO.setCreatedtime(MyDateUtil.stringToDate(rsMap.get("createdTime")+""));
		merchantFamilyPO.setCustomerid(Integer.parseInt(rsMap.get("customerId")+""));
		merchantFamilyPO.setStatus(rsMap.get("status")+"");
		merchantFamilyPO.setMerchantid(Integer.parseInt(rsMap.get("merchantId")+""));
		//得到家庭账户的保证金
		String moneyOfAssure =rsMap.get("moneyOfassure")+"";
		merchantFamilyPO.setMoneyOfAssure(moneyOfAssure);
		//查找出当前家庭的照片
		SysFileUrlsPO sysFileUrlsPO=merchantFamilyService.findFamilyImg(id);
		merchantFamilyPO.setSysFileUrlsPO(sysFileUrlsPO);
		
		return new ModelAndView("merchant/editFamily","merchantFamilyPO",merchantFamilyPO);
	}	
	


	/**
	 * 分页查询家庭的用户
	 * 
	 * @param aoData
	 * @param paraData
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "ajaxCustomerData.htm")
	@ResponseBody
	public PageResults ajaxCustomerData(String aoData, String paraData,
			HttpSession session) {
		JSONObject jsonObject = getJsonPara(paraData, session);
		// jquery.datatables 分页查询的参数
		JSONArray jsonArray = JSONArray.fromObject(aoData);
		PageHelp pageHelp = JSONTools.toPageHelp(jsonArray);

		PageResults pageResults = merchantFamilyService
				.findMerchantFamilyCustomerPage(jsonObject,
						pageHelp.getiDisplayLength(),
						pageHelp.getiDisplayStart());
		pageResults.setsEcho(pageHelp.getsEcho());
		return pageResults;
	}
	
	/**
	 * 分页查询家庭的用户无重复记录
	 * 
	 * @param aoData
	 * @param paraData
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "ajaxCustomerData2.htm")
	@ResponseBody
	public PageResults ajaxCustomerData2(String aoData, String paraData,
			HttpSession session) {
		JSONObject jsonObject = getJsonPara(paraData, session);
		// jquery.datatables 分页查询的参数
		JSONArray jsonArray = JSONArray.fromObject(aoData);
		PageHelp pageHelp = JSONTools.toPageHelp(jsonArray);

		PageResults pageResults = merchantFamilyService
				.findFamilyCustomerByPage(jsonObject,
						pageHelp.getiDisplayLength(),
						pageHelp.getiDisplayStart());
		pageResults.setsEcho(pageHelp.getsEcho());
		return pageResults;
	}
	
	
	
	
	/**
	 * 加载家庭成员列表
	 */
	@RequestMapping(value="memberAjaxData.htm")
	@ResponseBody
	public PageResults memberAjaxData(String aoData, String paraData,
			HttpSession session){
		JSONObject jsonObject = getJsonPara(paraData, session);
		// jquery.datatables 分页查询的参数
		JSONArray jsonArray = JSONArray.fromObject(aoData);
		PageHelp pageHelp = JSONTools.toPageHelp(jsonArray);
		PageResults pageResults=merchantFamilyService.findMerchantFamilyMemberPage(jsonObject,pageHelp.getiDisplayLength(), pageHelp.getiDisplayStart());
		pageResults.setsEcho(pageHelp.getsEcho());
		return pageResults;
	}
	
	
	/**
	 * 保存编辑的家庭信息。
	 */
	@RequestMapping(value="updateFamilyInfo.htm")
	@ResponseBody
	public MSG updateFamilyInfo(MerchantFamilyPO merchantFamilyPO,String newCustomerid,HttpSession session,HttpServletRequest request){
		MSG msg = new MSG();
		String url=request.getParameter("familyImgHidden");
		String fileName=request.getParameter("idFamilyImgHidden");
		String fileId=request.getParameter("familyImgId");
		SessionUserBO sessionUserBO = this.getSessionUser(session);
		merchantFamilyPO.setMerchantid(sessionUserBO.getMerchantId());
		msg.setSuccess(merchantFamilyService.updateFamily(merchantFamilyPO,newCustomerid, url, fileName,fileId));
		return msg;
	}
	
	
	/**
	 * 批量删除家庭成员关系saveEditMenber.htm
	 */
	
	@RequestMapping(value="saveEditMenber.htm")
	@ResponseBody
	public MSG saveEditMenber(String idsStr,String familyId){
		MSG msg = new MSG();
		String[] ids = idsStr.split(",");
		msg.setSuccess( merchantFamilyService.bachDeleteFamilyMember(ids, familyId));
		return msg;
	}
	
	
	/**
	 * 冻结家庭
	 */
	@RequestMapping("freezenFamily.htm")
	@ResponseBody
	public MSG freezenFamily(Integer id){
		MSG msg =new MSG();
		//调用service层方法更新状态
		MerchantFamilyPO merchantFamilyPO = new MerchantFamilyPO();
		merchantFamilyPO.setId(id);
		merchantFamilyPO.setStatus(MerchantFamilyType.冻结+"");
		try {
			msg.setSuccess(merchantFamilyService.updateFamilyStatus(merchantFamilyPO));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return msg;
	}
	
	/**
	 * 解冻家庭
	 */
	@RequestMapping("unFreezenFamily.htm")
	@ResponseBody
	public MSG unFreezenFamily(Integer id){
		MSG msg =new MSG();
		//调用service层方法跟新状态
		MerchantFamilyPO merchantFamilyPO = new MerchantFamilyPO();
		merchantFamilyPO.setId(id);
		merchantFamilyPO.setStatus(MerchantFamilyType.正常+"");
		try {
			msg.setSuccess(merchantFamilyService.updateFamilyStatus(merchantFamilyPO));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return msg;
	}
	
	/**
	 * 删除家庭
	 */
	@RequestMapping("deleteFamily.htm")
	@ResponseBody
	public MSG deleteFamily(Integer id){
		MSG msg =new MSG();
		msg.setSuccess(merchantFamilyService.deleteFamily(id));
		return msg;
	}
	
	
	
}
