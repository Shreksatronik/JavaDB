import java.util.concurrent.Semaphore;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Semaphore SemaphoreA = new Semaphore(0);
        Semaphore SemaphoreB = new Semaphore(0);
        Semaphore SemaphoreC = new Semaphore(0);

        new Thread(new WidgetProducer(SemaphoreA, 1000, "A")).start();
        new Thread(new WidgetProducer(SemaphoreB, 2000, "B")).start();
        new Thread(new WidgetProducer(SemaphoreC, 3000, "C")).start();

        while (true) {
            SemaphoreA.acquire();
            SemaphoreB.acquire();
            SemaphoreC.acquire();
            System.out.println("Product received");
        }
    }
}