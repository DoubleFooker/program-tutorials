package nio;

/**
 * JDK 1.4中的java.nio.*包中引入新的Java I/O库。
 * NIO：Non-block I/O，即非阻塞I/O
 * NIO与BIO中的Socket和ServerSocket相对应的SocketChannel和ServerSocketChannel实现。
 * 新增的着两种通道都支持阻塞和非阻塞两种模式。
 * 对于低负载、低并发的应用程序，可以使用同步阻塞I/O来提升开发速率和更好的维护性；
 * 对于高负载、高并发的（网络）应用，应使用NIO的非阻塞模式来开发。
 * NIO三个关键部分
 * channel：
 * 数据的读取和写入要通过Channel。相当于操作数据的通道。
 * buffer：
 * Buffer是一个对象，包含一些要写入或者读出的数据。
 * 在NIO库中，所有数据都是用缓冲区处理的。即数据存放的地方。
 * 有position，limit,capacity三个关键属性
 * Selector：
 * Selector会不断轮询注册在其上的Channel，如果某个Channel上面发生读或者写事件，这个Channel就处于就绪状态，会被Selector轮询出来，然后通过SelectionKey可以获取就绪Channel的集合，进行后续的I/O操作。
 * JDK使用了epoll()代替传统的select实现，所以没有最大连接句柄1024/2048的限制
 *
 * 启动一个线程无限循环监听所有就绪事件。
 * @author huangkaifu
 * @date 2018/4/18.
 */
public class NioServer {
    private static int DEFAULT_PORT = 12346;
    private static NioServerSelectorHandle serverHandle;
    public static void start(){
        start(DEFAULT_PORT);
    }
    public static synchronized void start(int port){
        if(serverHandle!=null){
            serverHandle.stop();
        }
        serverHandle = new NioServerSelectorHandle(port);
        new Thread(serverHandle,"Server").start();
    }
    public static void main(String[] args){
        start();
    }
}
