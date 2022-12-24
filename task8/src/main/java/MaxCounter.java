import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class MaxCounter {
    private static long maxI = 0;

    public long getMaxI(ReentrantLock reentrantLock, Condition condition) {
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
