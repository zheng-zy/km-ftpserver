package com.shiyi.km.ftpserver.util;

import org.apache.log4j.Logger;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * 
 * @fileName ConfigUtil.java
 * @author chenkaideng
 * @date 2015年8月11日
 * @describe 配置文件读取工具类
 */
public class ConfigUtil {
	private static final Logger logger = Logger.getLogger(ConfigUtil.class);
	//配额默认为100MB
	private int quota=100;
	
	private static final ConfigUtil configUtil = new ConfigUtil();
	
	public static ConfigUtil getInstance(){
		return configUtil;
	}
	
	private ConfigUtil(){
		//读取配置文件
		Properties properties=new Properties();
		try {
			properties.load(new FileInputStream("resources/config.properties"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String quotaString=properties.getProperty("quota");
		if(("").equals(quotaString)||properties.getProperty("quota")==null){
			logger.info("配置文件未设置配额大小，则用默认配额");
		}else {
			if(quotaString.matches("^[0-9]*$")){
				quota = Integer.parseInt(quotaString);
			}else {
				logger.info("配置文件配置配额格式不正确，则用默认配额");
			}
		}
	}

	public int getQuota() {
		logger.info(String.format("每个ftp用户的配额为：%dMB", quota));
		return quota;
	}
	
	
/*	public static void main(String[] args) throws FileNotFoundException, IOException{
		ConfigUtil.getInstance().getQuota();
	}*/
}
