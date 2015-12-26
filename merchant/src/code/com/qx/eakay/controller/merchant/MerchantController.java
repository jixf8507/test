package com.qx.eakay.controller.merchant;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.qx.eakay.controller.BaseController;
import com.qx.eakay.login.SessionUserBO;
import com.qx.eakay.model.merchant.MerchantPO;
import com.qx.eakay.service.merchant.MerchantService;

/**
 * 
 * @author jixf
 * @date 2015年7月10日
 */
@Controller
@RequestMapping("/merchant/merchant")
public class MerchantController extends BaseController {
	@Autowired
	private MerchantService merchantService;

	/**
	 * 进入商家账户信息页面
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("info.htm")
	public ModelAndView info(HttpSession session) throws Exception {
		SessionUserBO sessionUserBO = this.getSessionUser(session);
		MerchantPO merchantPO = merchantService.getMerchant(sessionUserBO
				.getMerchantId());
		return new ModelAndView("merchant/merchantInfo", "merchantPO",
				merchantPO);
	}
	
	/**
	 * 进入商家账户信息修改页面
	 *  
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("edit.htm")
	public ModelAndView edit(HttpSession session) throws Exception {
		SessionUserBO sessionUserBO = this.getSessionUser(session);
		MerchantPO merchantPO = merchantService.getMerchant(sessionUserBO
				.getMerchantId());
		return new ModelAndView("merchant/merchantEdit", "merchantPO",
				merchantPO);
	}
	/**
	 * 保存商家账户信息
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("saveEdit.htm")
	public ModelAndView saveEdit(HttpSession session,MerchantPO merchantPO) throws Exception {
		 merchantPO.setId(this.getSessionUser(session).getMerchantId()); 
		 merchantService.saveEdit(merchantPO);
		 return new ModelAndView("redirect:/system/message/success.htm");
	}
	

}
