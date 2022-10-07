package task12;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;

public class LL{
    LinkedList<String> list;
    boolean isReady = false;

    public  LL() {
        this.list = new LinkedList<>();
    }


    public synchronized void sort() {
        int size = this.list.size();
        if (size != 0) {
            for (int i = 0; i < size; i++) {
                for (int j = i; j < size; j++) {
                    String first = this.list.get(i);
                    String second = this.list.get(j);
                    if (second.compareTo(first) < 0) {
                        this.list.set(i, second);
                        this.list.set(j, first);
                    }
                }
            }
        }
    }

    public synchronized void print() {
        isReady = true;
        for (String string : list) {
            System.out.println(string);
        }
        list.clear();
    }

    public synchronized void add(String elem) {
        list.offerFirst(elem);
    }
}