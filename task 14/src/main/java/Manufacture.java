import java.awt.*;
import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.Semaphore;

public class Manufacture {
    public static void main(String[] args) {
        OrderQueue aQueue = new OrderQueue();
        OrderQueue bQueue = new OrderQueue();
        OrderQueue cQueue = new OrderQueue();
        OrderQueue componentQueue = new OrderQueue();
        int aCreators = 1;
        int bCreators = 1;
        int cCreators = 2;
        int componentCreator = 1;
        int widgetCreator = 1;
        ArrayList<CreatorA> aWorkers = new ArrayList<CreatorA>();
        ArrayList<CreatorB> bWorkers = new ArrayList<CreatorB>();
        ArrayList<CreatorC> cWorkers = new ArrayList<CreatorC>();
        ArrayList<CreatorComponent> componentWorkers = new ArrayList<CreatorComponent>();
        ArrayList<CreatorWidget> widgetWorkers = new ArrayList<CreatorWidget>();
        Semaphore a = new Semaphore(1);
        Semaphore b = new Semaphore(1);
        Semaphore c = new Semaphore(1);
        Semaphore component = new Semaphore(1);
        Thread[] aThreads = new Thread[aCreators];
        Thread[] bThreads = new Thread[bCreators];
        Thread[] cThreads = new Thread[cCreators];
        Thread[] componentThreads = new Thread[componentCreator];
        Thread[] widgetThreads = new Thread[widgetCreator];

        for (int i = 0; i < aCreators; i++) {
            aWorkers.add(new CreatorA(aQueue));
            aThreads[i] = new Thread(aWorkers.get(i));
        }
        for (int i = 0; i < bCreators; i++) {
            bWorkers.add(new CreatorB(bQueue));
            bThreads[i] = new Thread(bWorkers.get(i));
        }
        for (int i = 0; i < cCreators; i++) {
            cWorkers.add(new CreatorC(cQueue));
            cThreads[i] = new Thread(cWorkers.get(i));
        }
        for (int i = 0; i < componentCreator; i++) {
            componentWorkers.add(new CreatorComponent(a, b, componentQueue, aQueue, bQueue));
            componentThreads[i] = new Thread(componentWorkers.get(i));
        }
        for (int i = 0; i < widgetCreator; i++) {
            widgetWorkers.add(new CreatorWidget(component, c, componentQueue, cQueue));
            widgetThreads[i] = new Thread(widgetWorkers.get(i));
        }
        for (Thread thread:aThreads) {
            thread.start();
        }
        for (Thread thread:bThreads) {
            thread.start();
        }
        for (Thread thread:cThreads) {
            thread.start();
        }
        for (Thread thread:componentThreads) {
            thread.start();
        }
        for (Thread thread:widgetThreads) {
            thread.start();
        }
    }
}
