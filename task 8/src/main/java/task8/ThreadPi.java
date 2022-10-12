package task8;

import java.util.concurrent.atomic.AtomicBoolean;

public class ThreadPi extends Thread {
    private final int Count;
    private final int Remainder;
    private volatile double sum = 0;

    private volatile long n;
    private volatile long i;

    public AtomicBoolean getWaitLonger() {
        return waitLonger;
    }

    private final AtomicBoolean waitLonger = new AtomicBoolean();
    public final Object lock = new Object ();
    public ThreadPi(int threadCount, int Remainder, long n) {
        this.Count = threadCount;
        this.Remainder = Remainder;
        this.n = n;
    }

    @Override
    public void run() {
        i = Remainder;
        while (n == 0 || i < n) {
            synchronized (lock) {
                while (waitLonger.get()) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        System.out.println(e.getCause().toString());
                    }
                }
            }
            sum += Math.pow(-1, i) / (2 * i + 1);
            i += Count;
        }
    }

    public double getSum() {
        return sum;
    }

    public long getN() {
        return i;
    }

    public void setN(long n) {
        this.n = n;
    }

    public Object getLock() {
        return lock;
    }
}

