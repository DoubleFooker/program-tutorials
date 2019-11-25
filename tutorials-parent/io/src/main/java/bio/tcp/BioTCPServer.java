package bio.tcp;

import bio.MsgHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 网络编程的基本模型是C/S模型，即两个进程间的通信。
 * 服务端提供IP和监听端口，客户端通过连接操作想服务端监听的地址发起连接请求，通过三次握手连接，如果连接成功建立，双方就可以通过套接字进行通信。
 * 传统的同步阻塞模型开发中，ServerSocket负责绑定IP地址，启动监听端口；Socket负责发起连接操作。连接成功后，双方通过输入和输出流进行同步阻塞式通信。
 * BIO的服务端通信模型：采用BIO通信模型的服务端，通常由一个独立的Acceptor线程负责监听客户端的连接，它接收到客户端连接请求之后为每个客户端创建一个新的线程进行链路处理没处理完成后，通过输出流返回应答给客户端，线程销毁。即典型的一请求一应答通信模型。
 * 通信是同步阻塞的，一发一线程。
 * 使用ServerSocket穿甲server，无线循环监听server.accept()，有socket返回，即有连接产生则新建线程处理请求。
 * 缺点：
 * 并发能力差，高并发下，服务器线程数对应客户端访问数1:1，线程过多导致，系统性能降低。
 *
 * @author huangkaifu
 * @date 2018/4/18.
 */
public final class BioTCPServer {
    //默认的端口号
    private static int DEFAULT_PORT = 12345;
    //单例的ServerSocket
    private static ServerSocket server;

    //根据传入参数设置监听端口，如果没有参数调用以下方法并使用默认值
    public static void start() throws IOException {
        //使用默认值
        start(DEFAULT_PORT);
    }

    //这个方法不会被大量并发访问，不太需要考虑效率，直接进行方法同步就行了
    public synchronized static void start(int port) throws IOException {
        if (server != null) {
            return;
        }
        try {
            //通过构造函数创建ServerSocket
            //如果端口合法且空闲，服务端就监听成功
            server = new ServerSocket(port);
            System.out.println("Socket服务器已启动，端口号：" + port);
            //通过无线循环监听客户端连接
            //如果没有客户端接入，将阻塞在accept操作上。
            while (true) {
                Socket socket = server.accept();
                //当有新的客户端接入时，会执行下面的代码
                //然后创建一个新的线程处理这条Socket链路
                new Thread(new MsgHandler(socket)).start();
            }
        } finally {
            //一些必要的清理工作
            if (server != null) {
                System.out.println("服务器已关闭。");
                server.close();
                server = null;
            }
        }
    }

    public static void main(String[] args) {
        //启动服务器
        try {
            BioTCPServer.start();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("服务器启动异常");
        }
    }
}
