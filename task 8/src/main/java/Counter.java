import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Counter implements Runnable {
    int count;
    int threadNum;
    boolean flag;
    double Sum ;
    CyclicBarrier cyclicBarrier;
    int i = 0;
    public Counter(int count, int threadNum,CyclicBarrier cyclicBarrier) {
        this.count = count;
        this.threadNum = threadNum;
        this.cyclicBarrier = cyclicBarrier;
        Sum = 0.0;
        flag = true;
    }

    public double getSum() {
        return Sum;
    }

    @Override
    public void run() {
        int iterator = threadNum;
        while(flag){
            Sum += Math.pow((-1.0), iterator)/(2*iterator+1);
            iterator += count;
            i++;
            if (i % 100 == 0){
                try{
                    cyclicBarrier.await();
                } catch (BrokenBarrierException | InterruptedException e) {
                    e.printStackTrace();
                }
            }
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public void stop(){
        flag = false;
    }
}