package com.qx.eakay.task;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.qx.eakay.service.order.PeccancyRecordService;
import com.qx.eakay.service.stake.StakeRechargeCostService;

/**
 * 发送违章短信定时任务
 * 
 * @author lc
 * 
 * @date 2015年12月3日
 */
@Component
public class PeccancyRecordSendMessageTask {
	private static Logger logger = Logger
			.getLogger(PeccancyRecordSendMessageTask.class);

	@Autowired
	private PeccancyRecordService peccancyRecordservice;

	/**
	 * 两次违章记录
	 */
	@Scheduled(cron = "0 0/1 * * * *?")
	public void cancelChargeOrder() {
		// logger.info("===================>>开始检测用户是否有两条违章记录！！");
		//
		// boolean info=peccancyRecordservice.peccancyRecordSendMessage();
		//
		// logger.info("===================>>检测完毕，违章冻结短信发送状态为"+info);
	}

}
