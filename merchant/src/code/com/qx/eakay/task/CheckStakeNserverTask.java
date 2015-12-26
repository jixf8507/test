package com.qx.eakay.task;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Date;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.qx.common.bean.SystemConfig;
import com.qx.eakay.socket.NServer;
import com.qx.eakay.socket.StationServerManager;
import com.qx.eakay.util.ChargeStaticUtil;

/**
 * 检测充电监控的设备通信服务的定时任务
 * 
 * @author jixf
 * @date 2015年7月22日
 */
@Component
public class CheckStakeNserverTask {

	private static Logger logger = Logger
			.getLogger(CheckStakeNserverTask.class);

	private SocketChannel client;
	private SocketAddress address;

	/**
	 * 检查充电监控Server是否已被启动
	 */
	@Scheduled(cron = "0 30/1 * * * *?")
	public void checkServerIsStart() {
		logger.info("CheckStakeNserverTask:checkServerIsStart begin");
		try {
			address = new InetSocketAddress("localhost", NServer.port);
			client = SocketChannel.open(address);
			logger.info("client 测试连接成功" + client.isConnected());
		} catch (Exception e) {
			logger.error("client 测试连接失败" + e);
			StationServerManager.startServer();
			try {				
				client = SocketChannel.open(address);
				sendMsg();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}

		try {
			if (!client.isConnected() || !checkMsg()) {
				StationServerManager.restartServer();
			}
			sendMsg();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (client != null) {
				try {
					client.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}

	// 发送测试消息
	private void sendMsg() throws IOException {
		// client = SocketChannel.open(address);
		ByteBuffer buffer = ByteBuffer.allocate(NServer.testMsg.length());
		client.configureBlocking(false);
		buffer.put(NServer.testMsg.getBytes());
		buffer.clear();
		client.write(buffer);
		// client.close();
	}

	// 检测测试消息
	private static boolean checkMsg() {
		int outTime = SystemConfig.newInstance().getOutTime();
		Date nowdate = new Date();
		Map<String, Map<String, Object>> merchantDataMap = ChargeStaticUtil.merchantDataMap;
		Map<String, Object> siteResultMap = merchantDataMap.get("9999");
		if (siteResultMap == null) {
			return false;
		}
		Date d = (Date) siteResultMap.get("time");
		logger.info("checkMsg 测试连接最后一次接受时间：" + d);
		long l = nowdate.getTime() - d.getTime();
		if (l > outTime * 2) {
			logger.info("验证消息超时：" + d);
			return false;
		}
		return true;
	}

}
