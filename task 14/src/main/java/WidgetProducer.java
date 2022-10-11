import java.util.concurrent.Semaphore;
public class WidgetProducer {
    private final int counter;
    private final Thread aParts;
    private final Thread bParts;
    private final Thread cParts;
    private final Thread components;
    private final Thread widgets;

    public WidgetProducer(int counter) {
        Semaphore aCompleted = new Semaphore(0);
        Semaphore bCompleted = new Semaphore(0);
        Semaphore cCompleted = new Semaphore(0);
        Semaphore componentsCompleted = new Semaphore(0);
        Semaphore widgetsCompleted = new Semaphore(0);
        this.counter = counter;
        this.aParts = new Thread(new A(this.counter, aCompleted));
        this.bParts = new Thread(new B(this.counter, bCompleted));
        this.cParts = new Thread(new C(this.counter, cCompleted));
        this.components = new Thread(new Component(aCompleted, bCompleted, this.counter, componentsCompleted));
        this.widgets = new Thread(new Widget(componentsCompleted, cCompleted, this.counter, widgetsCompleted));
    }

    public void start() {
        this.aParts.start();
        this.bParts.start();
        this.cParts.start();
        this.components.start();
        this.widgets.start();
        try {
            this.widgets.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println("Done");
        }
    }
}
