package com.qx.eakay.socket;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.qx.common.bean.SystemConfig;
import com.qx.common.tools.GsonUtils;
import com.qx.eakay.util.ChargeStaticUtil;

public class NServer extends java.lang.Thread {
	private static Logger log = Logger.getLogger(NServer.class);
	private Selector selector = null;
	private Charset charset = Charset.forName("utf-8");
	private ByteBuffer buffer = ByteBuffer.allocate(30000);
	public static Map<String, Map<String, Object>> resultMap = new HashMap<String, Map<String, Object>>();
	ServerSocketChannel server = null;
	public static String testMsg = "<p>{'merId':9999,'devs':{}}</p>";

	public static final int port = SystemConfig.newInstance().getCdzjkPort();

	InetSocketAddress address = new InetSocketAddress(port);

	private String contentMsg = "";

	public void run() {
		try {
			init();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void init() throws IOException {
		selector = Selector.open();
		server = ServerSocketChannel.open();

		server.socket().bind(address);
		server.configureBlocking(false);
		server.register(selector, SelectionKey.OP_ACCEPT);
		while (selector.isOpen()) {
			if (selector.select() > 0) {
				Iterator<SelectionKey> it = selector.selectedKeys().iterator();
				while (it.hasNext()) {
					SelectionKey sk = it.next();
					if (sk.isAcceptable()) {
						SocketChannel socketChannel = server.accept();
						socketChannel.configureBlocking(false);
						socketChannel.register(selector, SelectionKey.OP_READ);
						sk.interestOps(SelectionKey.OP_ACCEPT);
					}

					if (sk.isReadable()) {
						SocketChannel sc = (SocketChannel) sk.channel();//
						String connect = "";

						try {

							while (sc.read(buffer) > 0) {
								buffer.flip();
								connect += charset.decode(buffer);
								log.info("adress:"
										+ sc.socket().getInetAddress()
										+ "  data::::    " + connect);
								savaMsgToMap(connect);
								// sk.interestOps(SelectionKey.OP_READ
								// | SelectionKey.OP_WRITE);

							}
						} catch (Exception e) {
							e.printStackTrace();
							sk.cancel();
							if (sk.channel() != null) {
								sk.channel().close();
							}
						}
						if (connect.length() > 0) {
							sk.attach(connect);
						}
						buffer.clear();
						sc.close();
					}
					// if (sk.isValid() && sk.isWritable()) {
					// SocketChannel sc = (SocketChannel) sk.channel();
					// sc.write(charset.encode(ss));
					// sk.interestOps(SelectionKey.OP_READ);
					// }
					it.remove();
				}
			}
		}
	}

	/**
	 * 把接受到的字符串保存到map中
	 * 
	 * @param connect
	 */
	private void savaMsgToMap(String connect) {
		System.out.println("connect" + connect);
		if (connect.endsWith("</p>")) {
			if (connect.startsWith("<p>")) {
				contentMsg = connect.substring(3, connect.length() - 4);
			} else {
				contentMsg += connect.substring(0, connect.length() - 4);
			}
			if (StringUtils.isNotBlank(contentMsg)) {
				Map<String, Object> res1 = GsonUtils.parseJSON2Map(contentMsg);
				
				Map<String, Object> merchantMap = ChargeStaticUtil.merchantDataMap.get(res1.get("merId")
						.toString()) ;
				if(merchantMap==null){
					merchantMap = new HashMap<String, Object>();
					merchantMap.put("merId", res1.get("merId")
							.toString());
					merchantMap.put("devs", new HashMap<>());
					ChargeStaticUtil.merchantDataMap.put(res1.get("merId")
							.toString(),merchantMap );
				}
				merchantMap.put("time", new Date());
				Map<String,Object> deviceMap= (Map<String, Object>) res1.get("devs") ;
				Map<String,Object> devsMap = (Map<String, Object>) merchantMap.get("devs") ;
				for (String key:deviceMap.keySet()){
					devsMap.put(key, deviceMap.get(key)) ;
				}
				
//				ChargeStaticUtil.merchantDataMap.put(res1.get("merId")
//						.toString(), res1);
			}
		} else {
			if (connect.startsWith("<p>")) {
				contentMsg = connect.substring(3);
			} else {
				contentMsg += connect;
			}
		}

	}

	public void stopServer() {
		if (server != null) {
			try {
				server.socket().close();
				server.close();

				selector.close();
			} catch (IOException e) {
				log.error("colseServerError ," + e);
			}
		}

	}

	public ServerSocketChannel getServer() {
		return server;
	}

	public void setServer(ServerSocketChannel server) {
		this.server = server;
	}

	public Selector getSelector() {
		return selector;
	}

	public void setSelector(Selector selector) {
		this.selector = selector;
	}

}
