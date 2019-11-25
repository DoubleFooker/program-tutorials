package nio.local;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class MappedByteBufferDemo {
    public static void main(String[] args) throws IOException {
        String demo = "text size over original text";
        byte[] bytes = demo.getBytes();

        RandomAccessFile file = new RandomAccessFile("/Users/dbfk/Documents/kaifu/userdata/git/tutorials-parent/io/doc/mappeddemo.txt"
                ,"rw");
        FileChannel channel = file.getChannel();

        MappedByteBuffer mappedByteBuffer = channel.map(FileChannel.MapMode.READ_WRITE, 0, bytes.length);
        for (int i = 0; i < bytes.length; i++) {
            mappedByteBuffer.put(i, (byte) bytes[i]);
        }

        channel.close();
        file.close();

    }
}
