package nio.planb;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * @author huangkaifu
 * @date 2018/4/18.
 */
public class NioClientBUG {
    private static SocketChannel socketChannel;
    private final static int port = 12346;
    private static String serverurl = "127.0.0.1";
    private static Selector selector;

    public static void sendMsg(String msg) {
        try {
            selector = Selector.open();
            socketChannel = SocketChannel.open();
            socketChannel.configureBlocking(false);
            System.out.println(socketChannel.connect(new InetSocketAddress(serverurl, port)));
            selector = Selector.open();
            System.out.println(socketChannel.register(selector, SelectionKey.OP_CONNECT));
            boolean isOver = false;

            while(! isOver){
                selector.select();
                Iterator ite = selector.selectedKeys().iterator();
                while(ite.hasNext()){
                    SelectionKey key = (SelectionKey) ite.next();
                    ite.remove();

                    if(key.isConnectable()){
                        if(socketChannel.isConnectionPending()){
                            if(socketChannel.finishConnect()){
                                //只有当连接成功后才能注册OP_READ事件
                                key.interestOps(SelectionKey.OP_READ);

                               // socketChannel.write(msg.getBytes());
                                //sleep();
                            }
                            else{
                                key.cancel();
                            }
                        }
                    }
                    else if(key.isReadable()){
                        ByteBuffer byteBuffer = ByteBuffer.allocate(128);
                        socketChannel.read(byteBuffer);
                        byteBuffer.flip();

                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        sendMsg("hello");
        //start();
    }
}
