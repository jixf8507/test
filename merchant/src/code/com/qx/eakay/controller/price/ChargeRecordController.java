package com.qx.eakay.controller.price;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.qx.eakay.controller.BaseController;

/**
 * 
 * @author jixf
 * @date 2015年7月7日
 */
@Controller
@RequestMapping("/price/chargeRecord")
public class ChargeRecordController extends BaseController {

	/**
	 * 进入收费套餐页面
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("page.htm")
	public ModelAndView page() throws Exception {
		return new ModelAndView("price/chargeRecord");
	}

}
