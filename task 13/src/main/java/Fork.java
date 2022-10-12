
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Fork {

    int id;
    Lock forkLocker;
    static Lock forks = new ReentrantLock();
    static Condition tryLockFork = forks.newCondition();


    public Fork(int id) {
        this.id = id;
        this.forkLocker = new ReentrantLock();
    }

    public int getId() {
        return this.id;
    }

}