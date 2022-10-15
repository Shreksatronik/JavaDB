import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Fork {

    int id;
    Lock forkLocker;
    static Lock forks = new ReentrantLock();
    static Condition tryLockFork = forks.newCondition();

    public synchronized boolean tryPickUp() {
        return forkLocker.tryLock();
    }
    public synchronized void putDown() {
        forkLocker.unlock();
    }
    public Fork(int id) {
        this.id = id;
        this.forkLocker = new ReentrantLock();
    }

    public int getId() {
        return this.id;
    }

}