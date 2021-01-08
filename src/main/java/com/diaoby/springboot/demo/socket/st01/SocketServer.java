package com.diaoby.springboot.demo.socket.st01;

import lombok.Cleanup;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author ：diaoby
 * @date ：Created in 2021/1/8 14:39
 * @description：
 * @modified By：
 */
@Slf4j
public class SocketServer {
    @SneakyThrows
    public static void main(String[] args) {
        int port = 55333;
        @Cleanup ServerSocket server = new ServerSocket(port);
        log.info("server 等带连接中......");
        @Cleanup Socket socket = server.accept();
        //建立连接后，从socket中获取输入流 并建立缓存经行读取
        @Cleanup InputStream inputStream = socket.getInputStream();
        byte[] bytes = new byte[1024];
        int len;
        StringBuilder sb = new StringBuilder();
        while((len = inputStream.read(bytes)) != -1){
            //注意指定编码格式，发送方和接收方一定要统一，建议使用UTF-8
            sb.append(new String(bytes,0,len,"UTF-8"));
        }
        log.info("客户端获取的信息是:{}",sb);
    }
}
