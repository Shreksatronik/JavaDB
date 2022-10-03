import java.util.concurrent.Semaphore;

public class WidgetProducer implements Runnable {
        Semaphore semaphore;
        int time;
        String Name;

        public WidgetProducer(Semaphore semaphore, int time, String Name) {
            this.semaphore = semaphore;
            this.time = time;
            this.Name = Name;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    Thread.sleep(time);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                semaphore.release();
                System.out.println("Detail " + Name + " is produced");
            }
        }
    }



