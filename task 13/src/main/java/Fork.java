import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Fork {

    int id;
    static ReentrantLock forkLocker = new ReentrantLock();
    static ReentrantLock[] forks = new ReentrantLock[5];
    static Condition tryLockFork = forkLocker.newCondition();

    public Fork(int id) {
        this.id = id;
    }


}