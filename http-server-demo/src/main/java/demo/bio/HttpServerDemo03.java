package demo.bio;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * HttpServer demo
 */
public class HttpServerDemo03 {

    public static void main(String[] args) throws IOException {
        ExecutorService executorService = Executors.newFixedThreadPool(40);

        // 绑定端口
        ServerSocket serverSocket = new ServerSocket(8083);

        while (true) {
            try {
                Socket socket = serverSocket.accept();

                executorService.submit(() -> doService(socket));

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void doService(Socket socket) {
        PrintWriter printWriter = null;
        try {
            // sleep 模拟处理业务时间
            Thread.sleep(20);

            printWriter = new PrintWriter(socket.getOutputStream(), true);

            // 模拟输出 HTTP 报文头
            printWriter.println("HTTP/1.1 200 OK");
            printWriter.println("Content-Type:text/html;charset=utf-8");
            printWriter.println();
            printWriter.println("Hello Thread Pool Http Server");

            printWriter.close();

        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        } finally {
            if (printWriter != null) {
                printWriter.close();
            }
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


}
