package com.diaoby.springboot.demo.socket.st03;

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
        // 因为可以复用Socket且能判断长度，所以可以一个Socket用到底
        while(true){
            // 首先读取两个字节表示的长度
            int first = inputStream.read();
            //如果读取的值为-1 说明到了流的末尾，Socket已经被关闭了，此时将不能再去读取
            if(first == -1){
                break;
            }
            int second = inputStream.read();
            int length = (first << 8) + second;
            // 然后构造一个指定长的byte数组
            bytes = new byte[length];
            // 然后读取指定长度的消息即可
            inputStream.read(bytes);
            log.info("get message from client: {}",new String(bytes, "UTF-8"));
        }
    }
}
