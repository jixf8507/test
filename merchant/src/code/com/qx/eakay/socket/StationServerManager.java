package com.qx.eakay.socket;

import org.apache.log4j.Logger;


public class StationServerManager {
	private static Logger log = Logger.getLogger(StationServerManager.class);
	private static NServer nServer  ; 
	
//	private static StationServerManager myServer =null ;
//	
//	static {
//		if(myServer==null){
//			myServer = new StationServerManager();
//		}
//	}
	
	public static NServer getNServer(){
		return nServer ;
	}
	
	private StationServerManager(){
		nServer = new NServer() ;
	}
	
	/**
	 * 停止NSERVER
	 */
	public static void stopServer(){
		log.info("NServer 停止服务开始" ) ;
		nServer.stopServer();
		startServer();
		log.info("NServer 停止服务结束") ;
	}
	
	/**
	 * 重启NSERVER
	 */
	public static void restartServer() {
		log.info("NServer 重新启动开始" ) ;
		stopServer();
		startServer();
		log.info("NServer 重新启动结束") ;
	}

	/**
	 * 启动NSERVER
	 */
	public static void startServer() {
		log.info("NServer 启动服务开始" ) ;
		nServer = new NServer() ;
		nServer.start() ;
		log.info("NServer 启动启动结束") ;
	}
	
	
	
	
}
