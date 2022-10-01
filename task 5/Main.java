import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicReference;

public class Main {
    public static void main(String[] args) {
        AtomicReference<Thread> reference = new AtomicReference<>();
        Timer myTimer = new Timer();
        myTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                Thread thread = new Thread(() -> {
                    while (!reference.get().isInterrupted()) {
                        System.out.println("Поток работает");
                    }
                    System.out.println("Поток был прерван");
                });
                reference.set(thread);
                thread.start();
            }
        }, 0);
        myTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                reference.get().interrupt();
                myTimer.cancel();
            }
        },2000);
    }
}