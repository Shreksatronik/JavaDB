import java.util.concurrent.Semaphore;
public class CreatorComponent implements Runnable {
    private final Semaphore aCompleted;
    private final Semaphore bCompleted;
    private final OrderQueue componentQueue;
    private final OrderQueue aQueue;
    private final OrderQueue bQueue;
    private Boolean flag;

    public CreatorComponent(Semaphore aCompleted, Semaphore bCompleted, OrderQueue componentQueue, OrderQueue a, OrderQueue b) {
        this.aCompleted = aCompleted;
        this.bCompleted = bCompleted;
        this.componentQueue = componentQueue;
        this.aQueue = a;
        this.bQueue = b;
        flag = true;
    }

    public void stop(){
        flag = false;
    }
    @Override
    public void run() {
        while (flag) {
            try {
                aQueue.get();
                aCompleted.acquire();
                bQueue.get();
                bCompleted.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            aCompleted.release();
            bCompleted.release();
            componentQueue.addToQueue(new Item(ItemType.COMPONENT, 0));
            System.out.println(Thread.currentThread().getName() + " successfully made component");
        }

    }
}