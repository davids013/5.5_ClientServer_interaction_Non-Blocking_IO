package task1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private static final String COLOR = Main_task1.SERVER_COLOR;
    protected static final String HOST = Main_task1.HOST;
    protected static final int PORT = Main_task1.PORT;

    public static void start() {
        System.out.println(COLOR + "Сервер запускается...");
        try {
            final ServerSocket server = new ServerSocket(PORT);
            try (Socket socket = server.accept();
                 final PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                 final BufferedReader in = new BufferedReader(
                         new InputStreamReader(socket.getInputStream()))) {

                System.out.println(COLOR + "Сервер установил соединение");
                String request;
                while ((request = in.readLine()) != null) {
                    System.out.println(COLOR + "Сервер принял запрос: " + request);
                    int target;
                    try {
                        target = Integer.parseInt(request);
                        System.out.println(COLOR +
                                "Сервер производит вычисление элемента " + target + "...");
                        if (target > 0) {
                            String result = String.valueOf(getFibElement(target));
                            System.out.println(COLOR + "Сервер отправляет ответ клиенту: " + result);
                            out.println(result);
                        } else out.println(COLOR + "Некорректный запрос");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(COLOR + "Сервер завершил работу" + "\033[0m");
    }

    private static BigInteger getFibElement(int target) {
        BigInteger first = new BigInteger("0");
        BigInteger second = new BigInteger("1");
        BigInteger current = new BigInteger("1");
        return switch (target) {
            case 1 -> first;
            case 2 -> second;
            default -> {
                for (int i = 3; i <= target; i++) {
                    current = first.add(second);
                    first = second;
                    second = current;
                }
                yield current;
            }
        };
    }
}
