import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CyclicBarrier;


public final class Founder {
    private final List<Worker> workers;
    CyclicBarrier cyclicBarrier;

    public Founder(final Company company) {
        this.workers = new ArrayList<>(company.getDepartmentsCount());
        this.cyclicBarrier = new CyclicBarrier(company.getDepartmentsCount(), new Bariers(company));
        for (int i = 0; i < company.getDepartmentsCount(); i++) {
            Worker newWorker = new Worker(cyclicBarrier, company.getFreeDepartment(i));
            workers.add(i,newWorker);
        }
    }

    public void start() {
        for (final Runnable worker : workers) {
            new Thread(worker).start();
        }

    }
}
