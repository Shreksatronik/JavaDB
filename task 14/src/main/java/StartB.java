import java.util.concurrent.Semaphore;

public class StartB {
Semaphore bCompleted;

        private final int counterB;
        private final Thread bParts;
        public StartB(int counterB){
            this.counterB = counterB;
            this.bCompleted = new Semaphore(0);
            this.bParts = new Thread(new B(this.counterB, bCompleted));
        }
        public void start() {
            this.bParts.start();
        }

    public Semaphore getbCompleted() {
        return bCompleted;
    }

    public int getCounterB() {
        return counterB;
    }
}


