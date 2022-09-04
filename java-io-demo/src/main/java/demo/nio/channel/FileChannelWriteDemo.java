package demo.nio.channel;


import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * {@link  FileChannel} 写入 buffer 示例
 * 使用 FileChannel 将 "Hello NIO" 写入到 "/resources/nio/hello.txt" 文件中
 *
 * @author wangzefeng
 */
public class FileChannelWriteDemo {

    public static void main(String[] args) throws IOException {

        String path = FileChannelWriteDemo.class.getClassLoader().getResource("").getPath();
        String fileName = path + "/nio/hello.txt";
        String text = "Hello NIO";

        FileChannel fileChannel;
        try (FileOutputStream fos = new FileOutputStream(fileName)) {

            fileChannel = fos.getChannel();

            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            byteBuffer.put(text.getBytes());

            byteBuffer.flip();

            fileChannel.write(byteBuffer);
        }


    }

}
