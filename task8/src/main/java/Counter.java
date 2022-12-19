public class Counter implements Runnable {
    private State state;
    private int maxI;
    private int currentI;
    private double res;
    private int threadNum;
    private int threadCount;

    Counter(int threadNum, int threadCount) {
        state = State.RUNNING;
        maxI = 0;
        currentI = 0;
        res = 0;
        this.threadNum = threadNum;
        this.threadCount = threadCount;
    }

    public void setFlag(State state) {
        this.state = state;
    }

    @Override
    public void run() {
        int iterator = threadNum;
        while (state == State.RUNNING) {
            res += Math.pow((-1.0), iterator) / (2 * iterator + 1);
            iterator += threadCount;
            currentI++;
            System.out.println(Thread.currentThread().getName() + " " + currentI + " res: " + res + " iterator: " + iterator + " " + Math.pow((-1.0), iterator) / (2 * iterator + 1));
        }
        while (state == State.WAITING) {
            try {
                Thread.sleep(0);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        while (state == State.RECOUNTING) {
            while (currentI < maxI) {
                res += Math.pow((-1.0), iterator) / (2 * iterator + 1);
                iterator += threadCount;
                currentI += 1;
                System.out.println(Thread.currentThread().getName() + " " + currentI + " res: " + res + " iterator " + iterator);
            }
        }
    }

    public void setMaxI(int maxI) {
        this.maxI = maxI;
    }

    public int getCurrentI() {
        return currentI;
    }

    public double getRes() {
        return res;
    }
}
