package nio.local;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 直接缓存区写入demo
 * 可以实现零拷贝，不经过jvm缓冲区 直接使用系统的缓冲区
 */
public class BufferDirectDemo {

    public static void main(String[] args) throws IOException {
        FileInputStream file = new FileInputStream("/Users/dbfk/Documents/kaifu/userdata/git/tutorials-parent/io/doc/demo.txt");
        FileChannel channel = file.getChannel();

        FileOutputStream fileOutputStream = new FileOutputStream("/Users/dbfk/Documents/kaifu/userdata/git/tutorials-parent/io/doc/demoCopy.txt");
        FileChannel outChannel = fileOutputStream.getChannel();
        // 直接缓存区使用 ByteBuffer.allocateDirect 实现ZERO拷贝
        ByteBuffer buffer = ByteBuffer.allocateDirect(10);
        while (true) {
            buffer.clear();
            int read = channel.read(buffer);
            if (read == -1) {
                break;
            }
            buffer.flip();
            outChannel.write(buffer);
        }
        channel.close();
        outChannel.close();
        file.close();
        fileOutputStream.close();
    }
}
