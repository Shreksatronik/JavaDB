import java.util.concurrent.Semaphore;
public class Widget implements Runnable {
    private final Semaphore componentsCompleted;
    private final Semaphore cCompleted;
    private final int widgetsCounter;
    private final Semaphore completedWidgets;

    public Widget(Semaphore componentsCompleted, Semaphore cCompleted, int widgetsCounter, Semaphore completedWidgets) {
        this.componentsCompleted = componentsCompleted;
        this.cCompleted = cCompleted;
        this.widgetsCounter = widgetsCounter;
        this.completedWidgets = completedWidgets;
    }

    @Override
    public void run() {
        for (int i = 0; i < this.widgetsCounter; i++) {
            try {
                this.componentsCompleted.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                this.cCompleted.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(i + "-th widget has completed");
            this.completedWidgets.release();
        }
    }
}
