import java.util.Scanner;
public class Main {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Please write the number of threads");
        Scanner in = new Scanner(System.in);
        int Count = in.nextInt();
        int N = 10_000_000;//количество итераций
        PiThread[] threads = new PiThread[Count];
        for (int i = 0; i < Count; i++) {
            threads[i] = new PiThread(Count, i, N);
            threads[i].start();
        }
        for (int i = 0; i < Count; i++) {
            threads[i].join();
        }
        double pi = 0;

        for (int i = 0; i < Count; i++) {
            pi += threads[i].getSum();
        }
        System.out.print("PI = " + (pi * 4 - Math.PI));

    }

}