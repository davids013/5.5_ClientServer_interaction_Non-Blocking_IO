package task2;

public class Main_task2 {
    protected static final String CLIENT_COLOR = "\033[31m";
    protected static final String SERVER_COLOR = "\033[34m";
    protected static final String HOST = "localhost";
    protected static final int PORT = 13954;

    public static void main(String[] args) throws InterruptedException {
        System.out.println("\n\tЗадача 2. Уничтожитель пробелов");

        Thread server = new Thread(Server::start);
        Thread client = new Thread(Client::start);

        server.start();
        client.start();

        client.join();
        server.join();
    }
}
