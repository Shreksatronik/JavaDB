import java.util.concurrent.Semaphore;
public class A implements Runnable {
        private final int partsCounter;
        private final Semaphore completedParts;
        public A(int partsCounter, Semaphore completedParts) {
            this.partsCounter = partsCounter;
            this.completedParts = completedParts;
        }
        @Override
        public void run() {
            for (int i = 0; i < this.partsCounter; i++) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    System.out.println(i + "-th part A has completed");
                    this.completedParts.release();
                }
            }
        }
    }

