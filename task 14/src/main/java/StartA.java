import java.util.concurrent.Semaphore;

public class StartA {
    private final int counterA;
    private final Thread aParts;
    Semaphore aCompleted;
    public StartA(int counterA){
        this.counterA = counterA;
        this.aCompleted =  new Semaphore(0);
        this.aParts = new Thread(new A(this.counterA, aCompleted));
    }
    public void start() {
        this.aParts.start();
    }

    public Semaphore getaCompleted() {
        return aCompleted;
    }

    public int getCounterA() {
        return counterA;
    }
}
