package task8;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
public class Main {
    private static final List<ThreadPi> threads = Collections.synchronizedList(new ArrayList<>());;
    private static int count;
    private static double res;
    public static void main(String[] args) throws InterruptedException {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            res = 0;
            long max = 1;
            synchronized (threads) {
                    for (ThreadPi thread : threads) {
                        synchronized (thread.getLock()) {
                            thread.getWaitLonger().set(true);
                        }
                    }
            }
            synchronized (threads) {
                for (ThreadPi thread : threads) {
                    if (thread.getN() > max) {
                        max = thread.getN();
                    }
                }
            }
            synchronized (threads) {
                for (ThreadPi thread : threads) {
                    if (thread.getN() <= max) {
                        thread.setN(max);
                    }
                    synchronized (thread.getLock()) {
                        thread.getWaitLonger().set(false);
                        thread.getLock().notify();
                    }
                }
            }
            synchronized (threads) {
                for (ThreadPi thread : threads) {
                    try {
                        thread.join();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    res += thread.getSum();
                }
            }
            System.out.println("Catched SIGINT");
            System.out.print("PI = " + res * 4);
        }));
        Scanner in = new Scanner(System.in);
        System.out.println("Ведите количество потоков");
        count = in.nextInt();

        for (int i = 0; i < count; i++) {
            synchronized (threads) {
                threads.add(new ThreadPi(count, i, 0));
                threads.get(i).start();
            }
        }
    }
}