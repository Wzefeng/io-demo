package demo.nio.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class NioServerDemo {

    public static void main(String[] args) throws IOException {

        // 创建 selector
        Selector selector = Selector.open();

        // 创建 serverSocketChannel
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        // 设置非阻塞模式
        serverSocketChannel.configureBlocking(false);
        // 绑定 9000 端口
        serverSocketChannel.bind(new InetSocketAddress(9000));

        // 先 selector 注册 serverSocketChanel, interest key 为 ACCEPT
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        while (true) {
            int readyEvent = selector.select(1000);
            if (readyEvent == 0) {
                continue;
            }

            // 处理 ready 的 selectionKeys
            Set<SelectionKey> selectionKeys = selector.selectedKeys();

            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()) {
                SelectionKey selectionKey = iterator.next();

                // 处理 ACCEPTED Event
                if (selectionKey.isAcceptable()) {
                    // 建立 socketChannel
                    SocketChannel socketChannel = serverSocketChannel.accept();
                    socketChannel.configureBlocking(false);

                    // 注册 socketChannel 到 selector 中，interest key 为 READ
                    socketChannel.register(selector, SelectionKey.OP_READ, ByteBuffer.allocate(1024));
                }

                if (selectionKey.isReadable()) {
                    SocketChannel socketChannel = (SocketChannel) selectionKey.channel();

                    ByteBuffer byteBuffer = (ByteBuffer) selectionKey.attachment();

                    socketChannel.read(byteBuffer);

                    System.out.println("服务到接收到信息：" + new String(byteBuffer.array(), 0, byteBuffer.position()));
                }

                iterator.remove();
            }

        }

    }

}
