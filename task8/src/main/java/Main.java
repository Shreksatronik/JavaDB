import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Counter> counters = new ArrayList<>();
        ArrayList<Thread> threads = new ArrayList<>();
        int threadsAmount = scanner.nextInt();
        CyclicBarrier cyclicBarrier = new CyclicBarrier(threadsAmount);
        ReentrantLock reentrantLock = new ReentrantLock();
        Condition condition = reentrantLock.newCondition();
        MaxCounter maxCounter = new MaxCounter(reentrantLock, condition);
        for (int i = 0; i < threadsAmount; i++) {
            Counter counter = new Counter(i, threadsAmount, cyclicBarrier, maxCounter);
            counters.add(counter);
            threads.add(new Thread(counter));
        }
        threads.forEach(Thread::start);


        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("Останавливаем подсчет...");
            counters.forEach(counter -> counter.caughtSignal = true);
            long maxI = 0;
            for (Counter counter : counters) {
                if (maxI < counter.getCurrentI()) {
                    maxI = counter.getCurrentI();
                }
            }
            maxCounter.setMaxI(maxI);
            double result = 0.0;
            for (Thread thread : threads) {
                try {
                    thread.join();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            for (Counter counter : counters) {
                result += counter.getRes();
            }
            System.out.println(result * 4);
        }));
    }
}
