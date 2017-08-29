package com.shiyi.km.ftpserver.control;

import com.shiyi.km.ftpserver.util.ConfigUtil;
import com.shiyi.km.ftpserver.util.FileUtil;
import org.apache.ftpserver.ftplet.*;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;

/**
 * 
 * @fileName MyFtplet.java
 * @author chenkaideng
 * @date 2015年8月11日
 * @describe 监控事件
 */
public class MyFtplet extends DefaultFtplet {
	private static final Logger logger = Logger.getLogger(MyFtplet.class);
	
	
	@Override
	public FtpletResult onUploadStart(FtpSession session, FtpRequest request)
			throws FtpException, IOException {
		//获取用户信息
		User user = session.getUser();
		logger.info(String.format("用户信息，用户名【%s】，用户工作目录【%s】", user.getName(),user.getHomeDirectory()));
		File file=new File(user.getHomeDirectory());
		
		//判断传入对象是否为一个文件夹对象
        if(!file.isDirectory()){
            logger.info("用户的HomeDirectory不是一个文件夹，请检查路径是否有误！！");
        }
        else{
        	ConfigUtil configUtil = ConfigUtil.getInstance();
            if(FileUtil.overSizeLimit(configUtil.getQuota(), file)){
            	logger.error(String.format("目前用户[%s]目录下的文件总大小超过配额！！！", user.getName()));
            	session.write(new DefaultFtpReply(228, "The files under the directory over quota"));
            	return FtpletResult.DISCONNECT;
            }
        }
		return super.onUploadStart(session, request);
	}
	
	
	
}
