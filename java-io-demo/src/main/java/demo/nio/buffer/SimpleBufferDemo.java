package demo.nio.buffer;

import java.nio.IntBuffer;

public class SimpleBufferDemo {

    public static void main(String[] args) {

        // 创建一个容量为 5 的 IntBuffer
        IntBuffer intBuffer = IntBuffer.allocate(5);

        // 往 IntBuffer 写入数据
        for (int i = 0; i < intBuffer.capacity(); i++) {
            intBuffer.put(i * 2);
        }

        // 切换 IntBuffer 读写模式
        intBuffer.flip();

        // 读取 IntBuffer
        while (intBuffer.hasRemaining()) {
            int num = intBuffer.get();
            System.out.println(num);
        }
    }

}
