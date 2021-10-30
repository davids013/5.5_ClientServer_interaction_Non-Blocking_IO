package task2;

import org.w3c.dom.ls.LSOutput;
import task1.Main_task1;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Client {
    private static final String COLOR = Main_task2.CLIENT_COLOR;
    private static final int WORK_DELAY = 2_000;

    public static void start() {
        System.out.println(COLOR + "Клиент запускается...");
        final InetSocketAddress address = new InetSocketAddress(Server.HOST, Server.PORT);
        try (final SocketChannel socketChannel = SocketChannel.open();
             final Scanner scanner = new Scanner(System.in)) {

            socketChannel.connect(address);
            final ByteBuffer inputBuffer = ByteBuffer.allocate(2 << 20);

            String request;
            Thread.sleep(1); // Костыль для правильной очередности печати в консоль
            while (true) {
                System.out.print(COLOR +
                        "Введите сообщение для очистки от пробелов сервером (`end` для выхода):\n>> ");
                request = scanner.nextLine();
                if ("end".equalsIgnoreCase(request.trim()) || "утв".equalsIgnoreCase(request.trim()))
                    break;
                System.out.println(COLOR + "Клиент отсылает запрос: " + request);
                socketChannel.write(ByteBuffer.wrap(request.getBytes(StandardCharsets.UTF_8)));
//                System.out.println(COLOR + "Клиент занимается своими делами...");
//                Thread.sleep(WORK_DELAY);
                int bytesCount = socketChannel.read(inputBuffer);
                System.out.println(COLOR + "Ответ сервера получен: " +
                        new String(inputBuffer.array(), 0, bytesCount, StandardCharsets.UTF_8).trim());
                inputBuffer.clear();
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(COLOR + "Клиент завершил работу" + "\033[0m");
    }
}
