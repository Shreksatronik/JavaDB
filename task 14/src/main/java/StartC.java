import java.util.concurrent.Semaphore;

public class StartC{

    Semaphore cCompleted;
    private final int counterC;
    private final Thread cParts;
    public StartC(int counterC){
        this.counterC = counterC;
        this.cCompleted = new Semaphore(0);
        this.cParts = new Thread(new C(this.counterC, cCompleted));
    }
    public void start() {
        this.cParts.start();
    }

    public int getCounterC() {
        return counterC;
    }

    public Semaphore getcCompleted() {
        return cCompleted;
    }
}
