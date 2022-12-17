public class CreatorC implements Runnable{
    private final ItemType itemType = ItemType.C;
    private final int time = 3000;
    private Boolean flag;
    private final OrderQueue queue;

    CreatorC(OrderQueue queue){
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
            queue.addToQueue(new Item(ItemType.C, 3000));
            System.out.println(Thread.currentThread().getName() + " successfully made item C");
        }

    }

}
