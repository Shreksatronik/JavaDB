import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;
import java.util.concurrent.Semaphore;


public class Main {

    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(0);
        System.out.println("Please write the number of threads");
        Scanner in = new Scanner(System.in);
        int Count = in.nextInt();
        List<Counter> counters = new ArrayList<>();
        List<Thread> threads = new ArrayList<>();
        for(int i = 0;i<Count;i++){
            counters.add(new Counter(Count, i));
            threads.add(new Thread(counters.get(i)));
        }
        for (Thread thread : threads) {
            thread.start();
        }
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                System.out.println("Is working");
                for (int i = 0; i < Count; i++) {
                    semaphore.release();
                    counters.get(i).stop();
                }
                try {
                    semaphore.acquire();
                } catch (InterruptedException e) {
                    e.printStackTrace();

                }
                double sum = 0;
                for (int i = 0; i < Count; i++) {
                    sum += counters.get(i).getSum();
                    System.out.println(counters.get(i).getSum());
                }
                System.out.println(sum * 4);
            }

        });

        }
    }
