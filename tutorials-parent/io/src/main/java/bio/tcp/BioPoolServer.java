package bio.tcp;

import bio.MsgHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 同样是BIO，引入线程池概念。解决BIO一个请求创建一个线程问题。线程创建交给线程池管理。
 * 缺点：
 * 通过线程池限制了线程数量，如果发生大量并发请求，超过最大数量的线程，或者线程执行时间不合理过长，就只能等待，直到线程池中的有空闲的线程可以被复用。
 * 而对Socket的输入流进行读取时，会一直阻塞，直到发生：
 * 有数据可读
 * 可用数据以及读取完毕
 * 发生空指针或I/O异常
 * 所以在读取数据较慢时（比如数据量大、网络传输慢等），大量并发的情况下，其他接入的消息，只能一直等待，这就是最大的弊端
 * @author huangkaifu
 * @date 2018/4/18.
 */
public final class BioPoolServer {
    //默认的端口号
    private static int DEFAULT_PORT = 12345;
    //单例的ServerSocket
    private static ServerSocket server;
    //线程池 懒汉式的单例
    //通过ThreadPoolExwcutor创建线程池
    private static ExecutorService executorService =new  ThreadPoolExecutor(6, 10, 5, TimeUnit.SECONDS, new SynchronousQueue<Runnable>());
    //根据传入参数设置监听端口，如果没有参数调用以下方法并使用默认值
    public static void start() throws IOException{
        //使用默认值
        start(DEFAULT_PORT);
    }
    //这个方法不会被大量并发访问，不太需要考虑效率，直接进行方法同步就行了
    public synchronized static void start(int port) throws IOException{
        if(server != null) {
            return;
        }
        try{
            //通过构造函数创建ServerSocket
            //如果端口合法且空闲，服务端就监听成功
            server = new ServerSocket(port);
            System.out.println("线程池Socket服务器已启动，端口号：" + port);
            //通过无线循环监听客户端连接
            //如果没有客户端接入，将阻塞在accept操作上。
            while(true){
                Socket socket = server.accept();
                //当有新的客户端接入时，会执行下面的代码
                //然后创建一个新的线程处理这条Socket链路
                executorService.execute(new MsgHandler(socket));
            }
        }finally{
            //一些必要的清理工作
            if(server != null){
                System.out.println("服务器已关闭。");
                server.close();
                server = null;
            }
        }
    }
    public static void main(String[] args) {
        //启动服务器
        try {
            BioPoolServer.start();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("服务器启动异常");
        }
    }
}
