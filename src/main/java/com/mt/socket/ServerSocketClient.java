package com.mt.socket;

import java.io.BufferedOutputStream;
import java.io.OutputStream;
import java.net.Socket;

public class ServerSocketClient {
    public static void main(String[] args) throws Exception {
        Socket socket = new Socket("localhost", 8080);
        OutputStream outputStream = socket.getOutputStream();
        BufferedOutputStream buffer = new BufferedOutputStream(outputStream);
        String note = "hello nice to meet you server!";
        byte[] bytes = note.getBytes();
        buffer.write(bytes);
        buffer.close();
        outputStream.close();
        socket.close();

    }
}
