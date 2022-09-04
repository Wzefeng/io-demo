package demo.io;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class FileInputStreamDemo {

    public static void main(String[] args) {
        try (InputStream in = new FileInputStream("E:\\dev\\code\\idea_workspace\\io-demo\\java-io-demo\\src\\main\\resources\\in.txt")) {
            StringBuilder str = new StringBuilder();
            byte[] data = new byte[1024];
            int n;
            while ((n = in.read(data)) != -1) {
                str.append(new String(data, 0, n));
            }

            System.out.println(str);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
