import java.awt.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.CyclicBarrier;
import java.util.List;
public class Main {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Please write the number of threads");
        Scanner in = new Scanner(System.in);
        int Count = in.nextInt();
        List<Counter> counters;
        counters = new ArrayList<>(Count);


        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                System.out.println("Shutdown hook ran!");
                new Barriers(counters).run();
            }

        });
        for (int i = 0; i < Count; i++) {
            counters.add(i, new Counter(i, Count));
        }
        CyclicBarrier cyclicBarrier = new CyclicBarrier(Count, new Barriers(counters));
        for (Counter counter : counters) {
            new Thread(new PiThread(counter, cyclicBarrier)).start();


        }


    }
}