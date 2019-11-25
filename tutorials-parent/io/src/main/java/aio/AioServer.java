package aio;

/**
 * NIO 2.0引入了新的异步通道的概念，并提供了异步文件通道和异步套接字通道的实现。
 * 异步的套接字通道时真正的异步非阻塞I/O，对应于UNIX网络编程中的事件驱动I/O（AIO）。
 * 不需要过多的Selector对注册的通道进行轮询即可实现异步读写，从而简化了NIO的编程模型。
 * @author huangkaifu
 * @date 2018/4/18.
 */
public class AioServer {
    private static int DEFAULT_PORT = 12347;
    private static AioAsyncMsgHandle serverHandle;
    public volatile static long clientCount = 0;
    public static void start(){
        start(DEFAULT_PORT);
    }
    public static synchronized void start(int port){
        if(serverHandle!=null)
        {
            return;
        }
        serverHandle = new AioAsyncMsgHandle(port);
        new Thread(serverHandle,"Server").start();
    }
    public static void main(String[] args){
        AioServer.start();
    }
}
