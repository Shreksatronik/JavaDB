import java.util.ArrayDeque;
import java.util.Queue;

public class OrderQueue {
    private Queue<Item> queue;

    public OrderQueue() {
        queue = new ArrayDeque<>();
    }

    public void addToQueue(Item item){
        synchronized (queue){
            queue.add(item);
            queue.notifyAll();
        }
    }

    public int size(){
        return queue.size();
    }

    public Item get(){
        synchronized (queue){
            while (queue.isEmpty()){
                try {
                    queue.wait();
                } catch (InterruptedException e) {
                    //e.printStackTrace();
                }
            }
            Item item = queue.poll();
            queue.notifyAll();
            return item;
        }
    }
}

