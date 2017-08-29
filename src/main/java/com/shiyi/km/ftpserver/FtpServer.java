package com.shiyi.km.ftpserver;

import com.shiyi.km.ftpserver.control.MyFtpServer;
import org.apache.ftpserver.ftplet.FtpException;

/**
 * Hello world!
 */
public class FtpServer {
    public static void main(String[] args) {
        try {
            new MyFtpServer();
        } catch (FtpException e) {
            e.printStackTrace();
        }
    }
}
