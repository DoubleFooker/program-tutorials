package nio;

/**
 * @author huangkaifu
 * @date 2018/4/18.
 */
public class NioClient {
    private static String DEFAULT_HOST = "127.0.0.1";
    private static int DEFAULT_PORT = 12347;
    private static NioClientHandle clientHandle;
    public static void start(){
        start(DEFAULT_HOST,DEFAULT_PORT);
    }
    public static synchronized void start(String ip,int port){
        if(clientHandle!=null){
            clientHandle.stop();
        }
        clientHandle = new NioClientHandle(ip,port);
        new Thread(clientHandle,"Client").start();
    }
    //向服务器发送消息
    public static boolean sendMsg(String msg) throws Exception{
        clientHandle.sendMsg(msg);
        return true;
    }
    public static void main(String[] args) throws Exception {
        start();
        Thread.sleep(3000);
        sendMsg("hello");

    }
}
