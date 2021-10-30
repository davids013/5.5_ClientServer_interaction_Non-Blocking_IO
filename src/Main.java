public class Main {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("""
                                
                \tМодуль 5. Многопоточное и функциональное программирование
                \tЛекция 5. Клиент-серверное взаимодействие. Blocking и Non-Blocking IO""");

        task1.Main_task1.main(null);
        task2.Main_task2.main(null);
        System.out.println("Все потоки завершены");
    }
}
