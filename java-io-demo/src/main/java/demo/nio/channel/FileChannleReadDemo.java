package demo.nio.channel;


import java.io.FileInputStream;
import java.io.IOException;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * {@link  FileChannel} 读取 {@link Buffer} 示例
 *
 * @author wangzefeng
 */
public class FileChannleReadDemo {

    public static void main(String[] args) {
        String path = FileChannelWriteDemo.class.getClassLoader().getResource("").getPath();
        String fileName = path + "/nio/hello.txt";

        try (FileInputStream fis = new FileInputStream(fileName)) {
            FileChannel channel = fis.getChannel();

            StringBuilder sb = new StringBuilder();

            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

            while (channel.read(byteBuffer) != -1) {
                byteBuffer.flip();
                sb.append(new String(byteBuffer.array()));
                byteBuffer.clear();
            }

            System.out.println(sb);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
