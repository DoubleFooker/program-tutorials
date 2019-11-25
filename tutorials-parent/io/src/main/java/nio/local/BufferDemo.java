package nio.local;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.Channel;
import java.nio.channels.FileChannel;

public class BufferDemo {
    public static void main(String[] args) throws IOException {
        String a="xxx";
        String c="";
        String b="xxx"+c;
        System.out.println(a==b);

        FileInputStream file = new FileInputStream("/Users/dbfk/Documents/kaifu/userdata/git/tutorials-parent/io/doc/demo.txt");
        FileChannel channel = file.getChannel();
        // 直接缓存区使用 ByteBuffer.allocateDirect 实现ZERO拷贝
        ByteBuffer buffer = ByteBuffer.allocate(100);
        // 初始化
        System.out.println("初始化");
        output(buffer);
        //写入buffer
        System.out.println("写入");

        channel.read(buffer);
        output(buffer);
        // 准备完成 锁定操作
        System.out.println("锁定");

        buffer.flip();
        output(buffer);
        System.out.println("读取");

        while (buffer.remaining()>0){
            //读取buffer
            buffer.get();
            output(buffer);
        }
        // 清空
        System.out.println("清空");

        buffer.clear();
        output(buffer);
        channel.close();


    }

    private static void output(Buffer buffer) {
        System.out.println("capacity:" + buffer.capacity() + ",limit:" + buffer.limit() + ",position:" + buffer.position());
    }
}
