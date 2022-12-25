import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class MaxCounter {
    private static long maxI = 0;
    private final ReentrantLock reentrantLock;
    private final Condition condition;
    MaxCounter(ReentrantLock reentrantLock, Condition condition){
        this.reentrantLock = reentrantLock;
        this.condition = condition;
    }
    public long getMaxI() {
        try {
            reentrantLock.lock();
            while (maxI == 0) {
                condition.await();
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            if (reentrantLock.isHeldByCurrentThread()) {
                condition.signalAll();
                reentrantLock.unlock();
            }
        }
        return maxI;
    }

    public void setMaxI(long maxI) {
        MaxCounter.maxI = maxI;
    }
}
