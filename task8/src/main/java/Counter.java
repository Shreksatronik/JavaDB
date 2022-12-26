import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicLong;

public class Counter implements Runnable {
    public volatile boolean caughtSignal;
    //    private long maxI;
    public static final AtomicLong maxI = new AtomicLong(-1);

    private long currentI;
    private double res;
    private final int threadNum;
    private final int threadCount;
    private final CyclicBarrier cyclicBarrier;
    //private final MaxCounter maxCounter;


    Counter(int threadNum, int threadCount, CyclicBarrier cyclicBarrier) {
        caughtSignal = false;
//        maxI = 0L;
        currentI = 0;
        res = 0;
        this.threadNum = threadNum;
        this.threadCount = threadCount;
        //this.maxCounter = maxCounter;
        this.cyclicBarrier = cyclicBarrier;

    }


    @Override
    public void run() {
        long iterator = threadNum;
        while (!caughtSignal) {
            res += Math.pow((-1.0), iterator) / (2 * iterator + 1);
            iterator += threadCount;
            currentI++;
            System.out.println(Thread.currentThread().getName()
                    + " " + currentI + " res: " + res + " iterator: "
                    + iterator + " " + Math.pow((-1.0), iterator) / (2 * iterator + 1)
                    + " caught signal: " + caughtSignal);
        }
        System.out.println("Caught signal");

        synchronized (maxI) {
            if (maxI.get() < currentI) {
                maxI.set(currentI);
            }
        }

        try {
            cyclicBarrier.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        System.out.println("after cyclic barrier");
        while (currentI < maxI.get()) {
            res += Math.pow((-1.0), iterator) / (2 * iterator + 1);
            iterator += threadCount;
            currentI += 1;
            System.out.println(Thread.currentThread().getName() + " " + currentI + " res: " + res + " iterator " + iterator + " caught signal:  " + caughtSignal);
        }
        System.out.println(Thread.currentThread().getName() + " Work is done. current i " + currentI + " max i " + maxI.get());

        try {
            System.out.println(Thread.currentThread().getName() + " is awaiting");
            cyclicBarrier.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            throw new RuntimeException(e);
        }
    }
    public double getRes() {
        return res;
    }
}