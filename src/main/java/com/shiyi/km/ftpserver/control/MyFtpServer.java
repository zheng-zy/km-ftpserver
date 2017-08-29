package com.shiyi.km.ftpserver.control;

import org.apache.ftpserver.FtpServer;
import org.apache.ftpserver.ftplet.FtpException;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 
 * @fileName MyFtpServer.java
 * @author chenkaideng
 * @date 2015年8月11日
 * @describe ftpserver实现类（启动，关闭，连接用户表，监控）
 */
public class MyFtpServer {
	
	private static final Logger logger = Logger.getLogger(MyFtpServer.class);
	private static String xmlPath = "my-ftpd-full.xml";
	
	public MyFtpServer() throws FtpException {
		
		//读取my-ftpd-full.xml，连接数据库和监控配置,然后来启动server
//		FileSystemXmlApplicationContext ctx = new FileSystemXmlApplicationContext(xmlPath);
		ApplicationContext ctx = new ClassPathXmlApplicationContext(xmlPath);
		FtpServer server = null;
		if (ctx.containsBean("server")) {
	        server = (FtpServer)ctx.getBean("server");
	     } else {
	        String[] beanNames = ctx.getBeanNamesForType(FtpServer.class);
	        if (beanNames.length == 1) {
	        	server = (FtpServer)ctx.getBean(beanNames[0]);
	        } else if (beanNames.length > 1) {
	        	logger.info("Using the first server defined in the configuration, named " + beanNames[0]);
	        	server = (FtpServer)ctx.getBean(beanNames[0]);
	        } else {
	        	logger.info("XML configuration does not contain a server configuration");
	        }
	    }
		
		//ftp服务器启动
		server.start();
		
		//在jvm关闭的时候，清理函数
	    addShutdownHook(server);
	}
	
	/**
	 * 清理的垃圾的钩子函数
	 * @param engine
	 */
	private void addShutdownHook(final FtpServer engine)
	  {
	    Runnable shutdownHook = new Runnable() {
	      public void run() {
	       logger.info("Stopping server...");
	        engine.stop();
	      }
	    };
	    Runtime runtime = Runtime.getRuntime();
	    runtime.addShutdownHook(new Thread(shutdownHook));
	  }
	
}
