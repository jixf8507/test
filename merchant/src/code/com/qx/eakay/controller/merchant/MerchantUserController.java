package com.qx.eakay.controller.merchant;

import java.util.ArrayList;
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
import com.qx.eakay.model.merchant.MerchantUserPO;
import com.qx.eakay.model.merchant.MerchantUserSignPO;
import com.qx.eakay.service.merchant.MerchantUserService;
import com.qx.eakay.util.MSG;

/**
 * 
 * @author jixf
 * @date 2015年7月11日
 */
@Controller
@RequestMapping("/merchant/user")
public class MerchantUserController extends BaseController {

	@Autowired
	private MerchantUserService merchantUserService;

	/**
	 * 进入商家用户管理页面
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("page.htm")
	public ModelAndView page() throws Exception {
		return new ModelAndView("merchant/user");
	}

	/**
	 * 提交保存新增商家工作人员
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("saveAdd.htm")
	public ModelAndView saveAdd(MerchantUserPO merchantUserPO,
			HttpSession session) throws Exception {
		merchantUserPO.setMerchantId(this.getSessionUser(session)
				.getMerchantId());
		merchantUserService.createUser(merchantUserPO);
		return new ModelAndView("redirect:/system/message/success.htm");
	}
	
	
	
	/**
	 * 进入员工添加页面
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("add.htm")
	public ModelAndView add() throws Exception {
		return new ModelAndView("merchant/userAdd");
	}

	/**
	 * 进入商家工作人员编辑页面
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("edite.htm")
	public ModelAndView edite(Integer id) throws Exception {
		MerchantUserPO merchantUserPO = merchantUserService.getMerchant(id);
		return new ModelAndView("merchant/userEdite", "merchantUserPO",
				merchantUserPO);
	}
	
	/**
	 * 通过"新增"按钮的方式提交保存新增商家工作人员,返回一个json
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("saveAdd2.htm")
	@ResponseBody
	public MSG saveAdd2(MerchantUserPO merchantUserPO,
			HttpSession session) throws Exception {
		merchantUserPO.setMerchantId(this.getSessionUser(session).getMerchantId());
		MSG msg = new MSG();
		msg.setSuccess(merchantUserService.createUser(merchantUserPO));
		return msg;
	}

	/**
	 * 提交保存编辑商家工作人员
	 * 
	 * @param sitePO
	 * @return
	 */
	@RequestMapping(value = "saveEdite.htm")
	@ResponseBody
	public MSG saveEdite(MerchantUserPO merchantUserPO) {
		MSG msg = new MSG();
		msg.setSuccess(merchantUserService.updateUser(merchantUserPO));
		return msg;
	}

	/**
	 * 删除商家工作人员
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "delete.htm")
	@ResponseBody
	public MSG delete(Integer id) {
		MSG msg = new MSG();
		msg.setSuccess(merchantUserService.delete(id));
		return msg;
	}

	/**
	 * 
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("rights.htm")
	@ResponseBody
	public List<Map<String, Object>> rights(Integer id, HttpSession session)
			throws Exception {
		return merchantUserService.findMerchantMoudles(id,
				this.getSessionUser(session).getSystemName());
	}

	/**
	 * 保存用户权限设置
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "saveRights.htm")
	@ResponseBody
	public MSG saveRights(Integer id, String modelIds) {
		MSG msg = new MSG();
		String[] ids = modelIds.split(",");
		List<Integer> moudlesIds = new ArrayList<>();
		for (String mid : ids) {
			try {
				moudlesIds.add(Integer.parseInt(mid));
			} catch (Exception e) {

			}
		}
		msg.setSuccess(merchantUserService.saveUserRigths(id, moudlesIds));
		return msg;
	}

	/**
	 * 验证登录邮箱是否已经存在
	 * 
	 * @param siteName
	 * @param id
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "checkEmail.htm")
	@ResponseBody
	public MSG checkEmail(String email, Integer id) {
		MSG msg = new MSG();
		msg.setSuccess(merchantUserService.checkEmail(email, id));
		return msg;
	}

	@RequestMapping(value = "ajaxData.htm")
	@ResponseBody
	public PageResults ajaxData(String aoData, String paraData,
			HttpSession session) {
		JSONObject jsonObject = getJsonPara(paraData, session);

		// jquery.datatables 分页查询的参数
		JSONArray jsonArray = JSONArray.fromObject(aoData);
		PageHelp pageHelp = JSONTools.toPageHelp(jsonArray);

		PageResults pageResults = merchantUserService.findUserPage(jsonObject,
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
//		if (!sessionUser.isManagerUser()) {
//			jsonObject.put("siteId", sessionUser.getMerchantUserPO()
//					.getSiteId());
//		}
		return jsonObject;
	}


	/**
	 * 导出下载租赁点到Excel表格
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
		merchantUserService.excelExportUsers(response, jsonObject);
		return null;
	}
	
	/**
	 * 进入员工签到点地图页面
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("signPoint.htm")
	public ModelAndView signPoint(Integer id) throws Exception {
		MerchantUserSignPO userSignPO = merchantUserService.getMerchantUserSign(id);
		return new ModelAndView("merchant/userSignPoint","userSignPO",userSignPO);
	}
	
	/**
	 * 进入员工签到查询页面
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("sign.htm")
	public ModelAndView sign() throws Exception {
		return new ModelAndView("merchant/userSign");
	}
	
	/**
	 * 员工签到列表
	 * 
	 * @param aoData
	 * @param paraData
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "ajaxSignData.htm")
	@ResponseBody
	public PageResults ajaxSignData(String aoData, String paraData,
			HttpSession session) {
		JSONObject jsonObject = getJsonPara(paraData, session);

		JSONArray jsonArray = JSONArray.fromObject(aoData);
		PageHelp pageHelp = JSONTools.toPageHelp(jsonArray);

		PageResults pageResults = merchantUserService.findUserSignPage(jsonObject,
				pageHelp.getiDisplayLength(), pageHelp.getiDisplayStart());
		pageResults.setsEcho(pageHelp.getsEcho());
		return pageResults;
	}
	
	/**
	 * 导出员工签到列表到Excel表格
	 * 
	 * @param paraData
	 * @param session
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("exportSignToExcel.htm")
	public ModelAndView exportSignToExcel(String paraData, HttpSession session,
			HttpServletResponse response) throws Exception {
		JSONObject jsonObject = getJsonPara(paraData, session);
		merchantUserService.excelExportUserSign(response, jsonObject);
		return null;
	}
}
