import java.util.List;
public class Barriers implements Runnable{
    List<Counter> counters;
    public Barriers(List<Counter> counters) {
        this.counters = counters;
    }

    @Override
    public void run() {
        double sum = counters.stream().map(Counter::getSum).reduce(Double::sum).orElse(0.0);
        System.out.println("SIGINT");
        System.out.println("RESULT: " + sum * 4);
    }
}