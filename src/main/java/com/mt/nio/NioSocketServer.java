package com.mt.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * 以前基于net包进行socket编程时，accept方法会一直阻塞，直到有客户端请求的到来，并返回socket进行相应的处理。整个过程是流水线的，
 * 处理完一个请求，才能去获取并处理后面的请求；当然我们可以把获取socket和处理socket的过程分开，一个线程负责accept，线程池负责处理请求。
 *
 * NIO为我们提供了更好的解决方案，采用选择器（Selector）找出已经准备好读写的socket，并按顺序处理，基于通道（Channel）和缓冲区（Buffer）
 * 来传输和保存数据。
 *
 * Selector是什么？
 * 在养鸡场，有这一个人，每天的工作就是不停检查几个特殊的鸡笼，如果有鸡进来，有鸡出去，有鸡生蛋，有鸡生病等等，就把相应的情况记录下来。
 * 这样，如果负责人想知道鸡场情况，只需要到那个人查询即可，当然前提是，负责得让那个人知道需要记录哪些情况。
 * Selector的作用相当这个人的工作，每个鸡笼相当于一个SocketChannel，单个线程通过Selector可以管理多个SocketChannel
 *
 *  A Thread uses a Selector to handle 3 or more Channels
 * 为了实现Selector管理多个SocketChannel，必须将多个具体的SocketChannel对象注册到Selector对象，并声明需要监听的事件，目前有4种类型的事件：
 *
 * connect：客户端连接服务端事件，对应值为SelectionKey.OP_CONNECT(8)
 * accept：服务端接收客户端连接事件，对应值为SelectionKey.OP_ACCEPT(16)
 * read：读事件，对应值为SelectionKey.OP_READ(1)
 * write：写事件，对应值为SelectionKey.OP_WRITE(4)
 * 当SocketChannel有对应的事件发生时，Selector能够觉察到并进行相应的处理。
 *
 * 服务端连接过程
 * 1、创建ServerSocketChannel实例serverSocketChannel，并bind到指定端口。
 * 2、创建Selector实例selector；
 * 3、将serverSocketChannel注册到selector，并指定事件OP_ACCEPT。
 * 4、while循环执行：
 * 4.1、调用select方法，该方法会阻塞等待，直到有一个或多个通道准备好了I/O操作或等待超时。
 * 4.2、获取选取的键列表；
 * 4.3、循环键集中的每个键：
 * 4.3.a、获取通道，并从键中获取附件（如果添加了附件）；
 * 4.3.b、确定准备就绪的操纵并执行，如果是accept操作，将接收的信道设置为非阻塞模式，并注册到选择器；
 * 4.3.c、如果需要，修改键的兴趣操作集；
 * 4.3.d、从已选键集中移除键
 *
 * 在步骤3中，selector只注册了serverSocketChannel的OP_ACCEPT事件
 *
 * 如果有客户端A连接服务，执行select方法时，可以通过serverSocketChannel获取客户端A的socketChannel，并在selector上注册socketChannel的OP_READ事件。
 * 如果客户端A发送数据，会触发read事件，这样下次轮询调用select方法时，就能通过socketChannel读取数据，同时在selector上注册该socketChannel的OP_WRITE事件，实现服务器往客户端写数据。
 */
public class NioSocketServer {
    public static void main(String[] args) throws IOException {
        ServerSocketChannel serverChannel = ServerSocketChannel.open();
        serverChannel.socket().bind(new InetSocketAddress(9000));
        serverChannel.configureBlocking(false);


        Selector selector = Selector.open();
        serverChannel.register(selector, SelectionKey.OP_ACCEPT);
        System.out.println("-----------------server start up------------------");

        while(true){
            // block method wait event
            int n = selector.select();

//            if (n == 0) continue;

            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator it = selectionKeys.iterator();
            while(it.hasNext()){
                SelectionKey key = (SelectionKey) it.next();

                if (key.isAcceptable()){
                    ServerSocketChannel sc = (ServerSocketChannel) key.channel();
                    SocketChannel clientChannel = sc.accept();
                    clientChannel.configureBlocking(false);
                    //将选择器注册到连接到的客户端信道，
                    //并指定该信道key值的属性为OP_READ，
                    //同时为该信道指定关联的附件
                    clientChannel.register(selector, SelectionKey.OP_READ);
                }
                else if (key.isReadable()){
                    SocketChannel readChannel = (SocketChannel) key.channel();
                    ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                    int read = readChannel.read(byteBuffer);
                    if (read > 0) {
                        System.out.println("-----receive-----: " + new String(byteBuffer.array()));
                    } else if (read == -1) {
                        readChannel.close();
                    }
                }
                else if (key.isWritable() && key.isValid()){
                    handleWrite(key);
                }
                else if (key.isConnectable()){
                    System.out.println("isConnectable = true");
                }
                it.remove(); // in case of duplicate process
            }
        }

    }

    private static void handleWrite(SelectionKey key) {

    }

    private static void handleRead(SelectionKey key) {

    }

}
