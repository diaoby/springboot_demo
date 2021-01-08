package com.diaoby.springboot.demo.socket.st03;

import lombok.Cleanup;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

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
        //首先需要计算得知消息的长度
        byte[] sendBytes = msg.getBytes("UTF-8");
        //然后将消息的长度优先发送出去
        outputStream.write(sendBytes.length >>8);
        outputStream.write(sendBytes.length);
        //然后将消息再次发送出去
        outputStream.write(sendBytes);
        outputStream.flush();
        //==========此处重复发送一次，实际项目中为多个命名，此处只为展示用法
        msg = "第二条消息";
        sendBytes = msg.getBytes("UTF-8");
        outputStream.write(sendBytes.length >>8);
        outputStream.write(sendBytes.length);
        outputStream.write(sendBytes);
        outputStream.flush();
        //==========此处重复发送一次，实际项目中为多个命名，此处只为展示用法
        msg = "the third message!";
        sendBytes = msg.getBytes("UTF-8");
        outputStream.write(sendBytes.length >>8);
        outputStream.write(sendBytes.length);
        outputStream.write(sendBytes);
    }
}
