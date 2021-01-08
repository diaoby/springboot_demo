package com.diaoby.springboot.demo.socket.st02;

import lombok.Cleanup;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * @author ：diaoby
 * @date ：Created in 2021/1/8 14:57
 * @description：
 * @modified By：
 */
@Slf4j
public class SocketClient {

    @SneakyThrows
    public static void main(String[] args) {
        String host="127.0.0.1";
        int port = 55333;
        @Cleanup Socket socket = new Socket(host,port);
        @Cleanup OutputStream outputStream = socket.getOutputStream();
        String msg ="hello frist socket online";
        outputStream.write(msg.getBytes("UTF-8"));
        //通过shutdownOutput高速服务器已经发送完数据，后续只能接受数据
        socket.shutdownOutput();

        //接受服务端消息
        @Cleanup InputStream inputStream = socket.getInputStream();
        byte[] bytes = new byte[1024];
        int len;
        StringBuilder sb = new StringBuilder();
        while((len = inputStream.read(bytes)) != -1){
            sb.append(new String(bytes,0,len,"UTF-8"));
        }
        log.info("服务端反馈的消息{}",sb);
    }
}
