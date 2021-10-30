package task1;

public class Main_task1 {
    protected static final String CLIENT_COLOR = "\033[31m";
    protected static final String SERVER_COLOR = "\033[34m";
    protected static final String HOST = "192.168.1.172";
    protected static final int PORT = 22749;

    public static void main(String[] args) throws InterruptedException {
        System.out.println("\n\tЗадача 1. Тяжелые вычисления");

        Thread server = new Thread(Server::start);
        Thread client = new Thread(Client::start);

        server.start();
        client.start();

        client.join();
        server.join();
    }
}
