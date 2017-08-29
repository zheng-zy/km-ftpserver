package com.shiyi.km.ftpserver.util;

import org.apache.log4j.Logger;

import java.io.File;

/**
 * 
 * @fileName FileUtil.java
 * @author chenkaideng
 * @date 2015年8月11日
 * @describe 文件的工具类
 */
public class FileUtil {
	private static final Logger logger = Logger.getLogger(FileUtil.class);
	
	/**
	 * 文件夹下的文件大小是否超过配额
	 * @return true:超过|false:未超过
	 */
	public static boolean overSizeLimit(int fileSizeLimit, File file){
		
		//计数用户目录中所有文件的总size
		double file_size=0;
		File[] t = file.listFiles();
		for(int i=0;i<t.length;i++){
            //判断文件列表中的对象是否为文件夹对象，如果是则执行tree递归，直到把此文件夹中所有文件输出为止
            if(t[i].isFile()){
//                logger.info(String.format("目前文件下的文件名称为：%s,大小为：%d", t[i].getName(),t[i].length()));
                file_size +=  t[i].length();
            }
            else{
            	logger.info(String.format("[%s]不是一个文件!",t[i].getName()));
            }
        }
		
		//目录下的文件总size
		logger.info(String.format("用户目录中的文件总数为：[%d]个,文件总大小为:[%f]MB",t.length ,file_size/(1024*1024)));
		
		//判断是否超过分配的限额
		if(file_size>fileSizeLimit*1024*1024){
        	return true;
        }
		
		return false;
	}
}
