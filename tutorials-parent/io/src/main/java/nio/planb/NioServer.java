package nio.planb;

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
 * Selector 的基本使用流程
 * 通过 Selector.open() 打开一个 Selector.
 * 将 Channel 注册到 Selector 中, 并设置需要监听的事件(interest set)
 * 不断重复:
 * 调用 select() 方法
 * 调用 selector.selectedKeys() 获取 selected keys
 * 迭代每个 selected key:
 * 从 selected key 中获取 对应的 Channel 和附加信息(如果有的话)
 * 判断是哪些 IO 事件已经就绪了, 然后处理它们. 如果是 OP_ACCEPT 事件, 则调用
 * "SocketChannel clientChannel = ((ServerSocketChannel) key.channel()).accept()"
 * 获取 SocketChannel, 并将它设置为 非阻塞的, 然后将这个 Channel 注册到 Selector 中.
 * 根据需要更改 selected key 的监听事件.
 * 将已经处理过的 key 从 selected keys 集合中删除.
 *
 * @author huangkaifu
 * @date 2018/4/18.
 */
public class NioServer {
    private static ServerSocketChannel serverSocketChannel;
    private static Selector selector;

    NioServer(Integer port) {
        try {
            selector = Selector.open();
            serverSocketChannel = ServerSocketChannel.open();
            //开启非阻塞模式
            serverSocketChannel.configureBlocking(false);
            serverSocketChannel.bind(new InetSocketAddress(port));
            //监听连接事件
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
            System.out.println("NIO服务器端启动，端口：" + port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void start() {
        while (true) {
            try {
                selector.select(1000);
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> selectionKeyIterator = selectionKeys.iterator();
                SelectionKey key = null;
                while (selectionKeyIterator.hasNext()) {
                    key = selectionKeyIterator.next();
                    selectionKeyIterator.remove();
                    if (!key.isValid()) {
                        continue;
                    }
                    try {
                        handleSelectionKey(key);
                    } catch (IOException e) {
                        e.printStackTrace();
                        if (key != null) {
                            key.cancel();
                            if (key.channel() != null) {
                                key.channel().close();
                            }
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void handleSelectionKey(SelectionKey key) throws IOException {

        if (key.isAcceptable()) {
            //完成了tcp、握手请求
            SocketChannel socketChannel = ((ServerSocketChannel) key.channel()).accept();
            socketChannel.configureBlocking(false);
            socketChannel.register(selector, SelectionKey.OP_READ);
        }
        int readBytes = 0;
        SocketChannel socketChannel = (SocketChannel) key.channel();
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        //获取客户端发送过来的信息,返回int 判断是否有数据
        readBytes = socketChannel.read(buffer);
        String msg = "";
        if (readBytes > 0) {
            buffer.flip();
            //根据缓冲区可读字节数创建字节数组,remaining：limit-position获取数据大小
            byte[] bytes = new byte[buffer.remaining()];
            buffer.get(bytes);
            msg = new String(bytes, "utf-8");
            System.out.println("服务器接收到客户端消息：" + msg);
        }
    }

    public static void main(String[] args) throws IOException {
        start();
    }
}
