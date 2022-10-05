import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Printer {

    final int capacity = 1;
    Queue<Integer> queue = new LinkedList<>();
    Lock lock = new ReentrantLock();
    Condition condition = lock.newCondition();

    public void printMain() throws InterruptedException {
        for (int i = 1; i <= 5; i++) {
            lock.lock();
            try {
                while (queue.size() == capacity) {
                    condition.await();
                }
                queue.add(i);
                System.out.println("Main: line " + i + " !");
                condition.signal();
            } finally {
                lock.unlock();
            }
        }
    }
    public void printThread2() throws InterruptedException {
        for (int i = 1; i <= 5; i++) {
            lock.lock();
            try{
                while (queue.size() < 1) {
                    condition.await();
                }
                System.out.println("Child: " + queue.remove() + " !");
                condition.signal();
            }finally{
            lock.unlock();
        }
    }
}}