package demo.nio.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class NioClientDemo {

    public static void main(String[] args) throws IOException {

        // 创建 socketChannel
        SocketChannel socketChannel = SocketChannel.open();
        // 设置非阻塞
        socketChannel.configureBlocking(false);
        // 设置连接的服务端主机地址
        InetSocketAddress address = new InetSocketAddress("127.0.0.1", 9000);

        // 建立与服务端之间的连接
        if (!socketChannel.connect(address)) {
            while (!socketChannel.finishConnect()) {
                System.out.println("建立连接中...");
            }
        }
        // 往服务端发送消息
        String message = "Hello, NIO Server";
        ByteBuffer byteBuffer = ByteBuffer.wrap(message.getBytes());
        socketChannel.write(byteBuffer);

        System.in.read();
    }
}
