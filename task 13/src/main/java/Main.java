import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

public class Main {
    public static void main(String args[]) {

        for (int i = 0; i < 5; ++i) {
            Fork.forks[i] = new ReentrantLock();
        }

        String[] names = new String[]{"Socrates", "Plato", "Aristotle", "Thales", "Pythagoras"};

        List<WellFedPhilosopher> philosophers = new ArrayList<>();
        for (int i = 0; i < 5; ++i) {
            philosophers.add(new WellFedPhilosopher(names[i], Fork.forks[i], i == 4 ? Fork.forks[0] : Fork.forks[i + 1]));
        }
        philosophers.forEach(philosopher -> new Thread(philosopher).start());
    }
    }

