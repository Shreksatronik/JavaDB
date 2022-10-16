import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.locks.ReentrantLock;

public class WellFedPhilosopher implements Runnable {
    private String name;
    private ReentrantLock left;
    private ReentrantLock right;
    boolean hasEaten = false;

    public WellFedPhilosopher(String name, ReentrantLock left, ReentrantLock right) {
        this.name = name;
        this.left = left;
        this.right = right;
    }



    private void randomSleep() {
        long millis = ThreadLocalRandom.current().nextLong(1000, 2000);
        try {
            Thread.sleep(millis);
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (true) {
            randomSleep();
            Fork.forkLocker.lock();
            while (!hasEaten){
            if (left.tryLock()) {
                System.out.println("The " + name + " philosopher picked up the first fork");
               if (right.tryLock()) {
                   Fork.forkLocker.unlock();
                    System.out.println("The " + name + " philosopher picked up the second fork");
                    System.out.println("The " + name + " philosopher began the meal");
                    randomSleep();
                    hasEaten = true;
                    randomSleep();
                    System.out.println("The " + name + " philosopher put down the second fork");
                    right.unlock();
                }
                left.unlock();
                System.out.println("The " + name + " philosopher put down the first fork");
                System.out.println("The " + name + " philosopher finished the meal");
            }
                if (hasEaten) {
                    Fork.forkLocker.lock();
                    Fork.tryLockFork.signalAll();
                    Fork.forkLocker.unlock();
                    break;
                } else {
                    try {
                        Fork.tryLockFork.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            hasEaten = false;
        }
    }
}