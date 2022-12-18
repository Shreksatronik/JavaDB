import java.util.concurrent.Semaphore;

public class CreatorWidget implements Runnable{
    private final Semaphore componentCompleted;
    private final Semaphore cCompleted;
    private final OrderQueue componentQueue;
    private final OrderQueue cQueue;
    private Boolean flag;

    public CreatorWidget(Semaphore componentCompleted, Semaphore cCompleted, OrderQueue componentQueue, OrderQueue cQueue) {
        this.componentCompleted = componentCompleted;
        this.cCompleted = cCompleted;
        this.componentQueue = componentQueue;
        this.cQueue = cQueue;
        flag = true;
    }

    public void stop(){
        flag = false;
    }

    @Override
    public void run() {
        while (flag){
            try {
                cQueue.get();
                cCompleted.acquire();
                componentQueue.get();
                componentCompleted.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            cCompleted.release();
            componentCompleted.release();
            System.out.println(Thread.currentThread().getName() + " successfully created widget");
        }
    }
}
