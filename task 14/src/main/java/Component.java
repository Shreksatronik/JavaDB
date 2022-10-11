import java.util.concurrent.Semaphore;
public class Component implements Runnable {
    private final Semaphore aCompleted;
    private final Semaphore bCompleted;
    private final int componentsCounter;
    private final Semaphore completedComponents;

    public Component(Semaphore aCompleted, Semaphore bCompleted, int componentsCounter, Semaphore completedComponents) {
        this.aCompleted = aCompleted;
        this.bCompleted = bCompleted;
        this.componentsCounter = componentsCounter;
        this.completedComponents = completedComponents;
    }

    @Override
    public void run() {
        for (int i = 0; i < this.componentsCounter; i++) {
            try {
                this.aCompleted.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                this.bCompleted.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(i + "-th component has completed");
            this.completedComponents.release();
        }
    }
}