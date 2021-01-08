package com.diaoby.springboot.demo.socket.st04;

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
        for(int count = 0;count<10;count++){
            @Cleanup Socket socket = new Socket(host,port);
            @Cleanup OutputStream outputStream = socket.getOutputStream();
            String msg ="hello frist socket online "+count;
            outputStream.write(msg.getBytes("UTF-8"));
        }
    }
}
