import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

public class PiThread implements Runnable {

CyclicBarrier cyclicBarrier;
Counter counter;
    public PiThread(Counter counter, CyclicBarrier cyclicBarrier) {
    this.counter = counter;
    this.cyclicBarrier = cyclicBarrier;
    }


    @Override
    public void run() {
       counter.count();
            try {
                cyclicBarrier.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }

        }

}

