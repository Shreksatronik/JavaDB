
public class Counter implements Runnable {
    int count;
    int threadNum;
    volatile boolean flag;
    double Sum ;
    int i = 0;
    public Counter(int count, int threadNum) {
        this.count = count;
        this.threadNum = threadNum;
        Sum = 0.0;
        flag = true;

    }

    public double getSum() {
        return Sum;
    }

    @Override
    public void run() {
        long iterator = threadNum;
        double Max = 0.0;
        while(flag){
            Sum += Math.pow((-1.0), iterator)/(2*iterator+1);
            iterator += count;
            i++;
            if (i > Max){
                    Max = i;
            }

        }
        if(!flag){
            for(long j = iterator;j<Max;j++){
                Sum += Math.pow((-1.0), iterator)/(2*iterator+1);
                iterator += count;
                i++;
            }
        }
    }
    public void stop(){
        flag = false;
        }
}