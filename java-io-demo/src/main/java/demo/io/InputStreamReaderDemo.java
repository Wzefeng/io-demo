package demo.io;


import java.io.FileReader;
import java.io.IOException;

public class InputStreamReaderDemo {

    public static void main(String[] args) {
        try (FileReader reader = new FileReader("E:\\dev\\code\\idea_workspace\\io-demo\\java-io-demo\\src\\main\\resources\\in.txt")) {

            StringBuilder str = new StringBuilder();
            char[] buf = new char[128];
            int n;
            while ((n = reader.read(buf)) != -1) {
                str.append(new String(buf, 0, n));
            }

            System.out.println(str);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
