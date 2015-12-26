package com.qx.eakay.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 
 * @author Administrator
 * 
 */
@Controller
@RequestMapping("/system/message")
public class MessageController extends BaseController {

	@RequestMapping("success.htm")
	public ModelAndView success(HttpSession session) {
		return new ModelAndView("/message/success");
	}

	@RequestMapping("noRights.htm")
	public ModelAndView noRights(HttpSession session) {
		return new ModelAndView("/message/noRights");
	}

}
