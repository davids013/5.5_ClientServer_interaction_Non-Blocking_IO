package task1;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private static final String COLOR = Main_task1.CLIENT_COLOR;

    public static void start() {
        System.out.println(COLOR + "Клиент запускается...");
        final Scanner sc = new Scanner(System.in);
        try (final Socket socket = new Socket(Server.HOST, Server.PORT);
             final BufferedReader in = new BufferedReader(
                     new InputStreamReader(socket.getInputStream()));
             final PrintWriter out = new PrintWriter(
                     new OutputStreamWriter(socket.getOutputStream()), true)) {
            String request;
            Thread.sleep(1); // Костыль для правильной очередности печати в консоль
            while (true) {
                System.out.print(COLOR + "Введите номер элемента последовательности Фибоначчи:\n>> ");
                request = sc.nextLine();
                try {
                    int num = Integer.parseInt(request.replaceAll("[&&[^0-9]]", ""));
                    if (num > 0 && num <= 500_000) {
                        System.out.println(COLOR + "Клиент отправляет запрос: " + num);
                        out.println(num);
                        System.out.println(COLOR + "Ответ сервера получен: " + in.readLine());
                        break;
                    } else System.out.println(COLOR + "\tYou really don't want to do that...");
                } catch (NumberFormatException | IOException e) {
                    System.out.println(COLOR + "Некорректный ввод");
                }
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        } finally {
//            sc.close();
            System.out.println(COLOR + "Клиент завершил работу" + "\033[0m");
        }
    }
}
