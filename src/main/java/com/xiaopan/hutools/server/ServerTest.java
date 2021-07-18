package com.xiaopan.hutools.server;

import cn.hutool.http.HttpUtil;
import com.sun.net.httpserver.HttpServer;

public class ServerTest {


    public static void main(String[] args) {

        HttpUtil.createServer(8000)
                .addAction("/",(req, resp) -> {
                    String name = req.getParam("name");
                    System.out.println(name);
                })
                .addAction("/get", (req, resp) -> {

                })
                .addAction("/post",(req, resp) -> {

                }).start();
    }
}
