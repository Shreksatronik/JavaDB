public class CreatorA implements Runnable{
    private final ItemType itemType = ItemType.A;
    private final int time = 1000;
    private Boolean flag;
    private final OrderQueue queue;

    CreatorA(OrderQueue queue){
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
            queue.addToQueue(new Item(ItemType.A, 1000));
            System.out.println(Thread.currentThread().getName() + " successfully made item A");

        }
    }

}
