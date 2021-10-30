package task2;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;

public class Server {
    private static final String COLOR = Main_task2.SERVER_COLOR;
    protected static final String HOST = Main_task2.HOST;
    protected static final int PORT = Main_task2.PORT;

    public static void start() {
        System.out.println(COLOR + "Сервер запускается...");
        try {
            final ServerSocketChannel server = ServerSocketChannel.open();
            server.bind(new InetSocketAddress(HOST, PORT));
            try (final SocketChannel client = server.accept()) {
                final ByteBuffer buf = ByteBuffer.allocate(2 << 5);

                System.out.println(COLOR + "Сервер установил соединение");
                while (client.isConnected()) {
                    System.out.println(COLOR + "Сервер ожидает запроса");
                    int bytes = client.read(buf);
                    if (bytes == -1) break;
                    final String request = new String(buf.array(), 0, bytes, StandardCharsets.UTF_8);
                    buf.clear();
                    System.out.println(COLOR + "Сервер получил запрос: " + request);

                    String result = request.replace(" ", "");
                    System.out.println(COLOR + "Сервер отправляет ответ: " + result);
                    client.write(ByteBuffer.wrap(result.getBytes(StandardCharsets.UTF_8)));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(COLOR + "Сервер завершил работу" + "\033[0m");
    }
}
