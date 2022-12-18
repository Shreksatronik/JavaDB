public class CreatorB implements Runnable{
    private final ItemType itemType = ItemType.B;
    private final int time = 2000;
    private Boolean flag;
    private final OrderQueue queue;

    CreatorB(OrderQueue queue){
        flag = true;
        this.queue = queue;
    }

    public void stop(){
        flag = false;
    }

    @Override
    public void run() {
        while (flag){
            try {
                Thread.sleep(time);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            queue.addToQueue(new Item(ItemType.B, 2000));
            System.out.println(Thread.currentThread().getName() + " successfully made item B");
        }
    }
}
