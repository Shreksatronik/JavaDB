import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Worker implements Runnable {

    CyclicBarrier cyclicBarrier;
    Department department;

    public Worker(CyclicBarrier cyclicBarrier, Department department) {
        this.cyclicBarrier = cyclicBarrier;
        this.department = department;
    }

    @Override
    public void run() {
        department.performCalculations();
        System.out.println("Department " + department.getIdentifier() + " worked in " + department.getWorkingSeconds() + " seconds.");
        try {
            cyclicBarrier.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            System.out.println("Exception in run method");
        }
    }
}