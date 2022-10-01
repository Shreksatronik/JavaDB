import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Printer {

    static Semaphore semChild = new Semaphore(0);
    static Semaphore semParent = new Semaphore(1);
    int count;

    void printThread2() {

        try {
            semChild.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Child: " + count);
        semParent.release();
    }

    void printMain(int count) {
        try {
            semParent.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.count = count;
        System.out.println("Main: " + count);
        semChild.release();
    }
    }
