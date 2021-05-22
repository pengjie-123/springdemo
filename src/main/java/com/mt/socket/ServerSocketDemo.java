package com.mt.socket;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerSocketDemo {
    public static void main(String[] args) throws Exception{
        System.out.println("server is start up");
        ServerSocket socket = new ServerSocket(8080);
        Socket accept = null;
        while (true) {
            accept = socket.accept();
            BufferedReader reader = new BufferedReader(new InputStreamReader(accept.getInputStream()));
            String s = "";
            while (reader.readLine() != null) {
                s = s + reader.readLine() + "\n";
            }
            System.out.println("server start receive message-----------");
            System.out.println(s);
        }

    }
}
