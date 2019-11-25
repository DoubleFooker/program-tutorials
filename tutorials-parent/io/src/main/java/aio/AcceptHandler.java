package aio;

import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;

/**
 * @author huangkaifu
 * @date 2018/4/18.
 */
public class AcceptHandler implements CompletionHandler<AsynchronousSocketChannel, AioAsyncMsgHandle> {
    @Override
    public void completed(AsynchronousSocketChannel channel,AioAsyncMsgHandle serverHandler) {
        //继续接受其他客户端的请求
        AioServer.clientCount++;
        System.out.println("连接的客户端数：" + AioServer.clientCount);
        serverHandler.channel.accept(serverHandler, this);
        //创建新的Buffer
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        //异步读  第三个参数为接收消息回调的业务Handler
        channel.read(buffer, buffer, new ReadHandler(channel));
    }
    @Override
    public void failed(Throwable exc, AioAsyncMsgHandle serverHandler) {
        exc.printStackTrace();
        serverHandler.latch.countDown();
    }
}
