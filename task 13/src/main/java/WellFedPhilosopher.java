import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.locks.Lock;

public class WellFedPhilosopher implements Runnable {
    private String name;
    private Fork left;
    private Fork right;
    boolean hasEaten = false;

    public WellFedPhilosopher(String name, Fork left, Fork right) {
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
            Fork first = left.getId() < right.getId() ? left : right;
            Fork second = left.getId() > right.getId() ? left : right;
            Fork.forks.lock();
            while (!hasEaten){
            if (first.tryPickUp()) {
                System.out.println("The " + name + " philosopher picked up the first fork");
               if (second.tryPickUp()) {
                    System.out.println("The " + name + " philosopher picked up the second fork");
                    System.out.println("The " + name + " philosopher began the meal");
                    randomSleep();
                    hasEaten = true;
                    System.out.println("The " + name + " philosopher put down the second fork");
                    first.putDown();
                }
                second.putDown();
                System.out.println("The " + name + " philosopher put down the first fork");
                System.out.println("The " + name + " philosopher finished the meal");

            }
                if (hasEaten) {
                    Fork.tryLockFork.signalAll();
                    Fork.forks.unlock();
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