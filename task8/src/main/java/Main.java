import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Counter> counters = new ArrayList<>();
        ArrayList<Thread> threads = new ArrayList<>();
        int threadsAmount = scanner.nextInt();
        for (int i = 0; i < threadsAmount; i++) {
            counters.add(new Counter(i, threadsAmount));
            threads.add(new Thread(counters.get(i)));
        }
        for (Thread thread : threads) {
            thread.start();
        }
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            ArrayList<Integer> countersI = new ArrayList<>();
            System.out.println("\nStopping threads\n");
            int max = 0;
            for (Counter counter:counters) {
                counter.setFlag(State.WAITING);
                countersI.add(counter.getCurrentI());
            }
            for (int i = 0; i < counters.size(); i++) {
                if (max<countersI.get(i)){
                    max = countersI.get(i);
                }
            }
            System.out.println(max);
            for (Counter counter:counters) {
                counter.setMaxI(max);
                counter.setFlag(State.RECOUNTING);
            }
            for (Counter counter:counters) {
                counter.setFlag(State.STOPPED);
            }
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            double res = 0;
            for (Counter counter:counters) {
                res += counter.getRes();
            }
            System.out.println(res * 4);
        }));
    }
}
