package task12;
public class Sorter implements Runnable {

    LL list;

    public Sorter(LL list) {
        this.list = list;
    }

    @Override
    public void run() {
        while (!list.isReady) {
            list.sort();
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                System.out.println("Interrupted sorter!");
            }
        }
    }
}