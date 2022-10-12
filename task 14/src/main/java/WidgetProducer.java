import java.util.concurrent.Semaphore;
public class WidgetProducer {

    private final Thread components;
    private final Thread widgets;
    StartA startA;
   StartB startB;
   StartC startC;
    public WidgetProducer(StartA startA, StartB startB, StartC startC) {
        this.startA = startA;
        this.startB = startB;
        this.startC = startC;
        Semaphore componentsCompleted = new Semaphore(0);
        Semaphore widgetsCompleted = new Semaphore(0);
        this.components = new Thread(new Component(startA.getaCompleted(), startB.getbCompleted(), startA.getCounterA(), componentsCompleted));
        this.widgets = new Thread(new Widget(componentsCompleted, startC.getcCompleted(), startC.getCounterC(), widgetsCompleted));
    }

    public void start() {
        startA.start();
        startB.start();
        startC.start();
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
