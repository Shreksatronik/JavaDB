import java.util.concurrent.Semaphore;
public class C implements Runnable {

    private final int partsCounter;
    private final Semaphore completedParts;

    public C(int partsCounter, Semaphore completedParts) {
        this.partsCounter = partsCounter;
        this.completedParts = completedParts;
    }

    @Override
    public void run() {
        for (int i = 0; i < this.partsCounter; i++) {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println(i + "-th part C has completed");
                this.completedParts.release();
            }
        }
    }
}
