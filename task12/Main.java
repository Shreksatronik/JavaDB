package task12;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.google.common.base.Splitter;

public class Main {
    public static void main(String[] args) throws IOException {
        LL list = new LL();
        Thread thread = new Thread(new Sorter(list));
        thread.start();
        System.out.println("Vvedite chisla");
        Scanner in = new Scanner(System.in);
        String inputData = in.nextLine();
        while (inputData.compareTo("") != 0) {
            if (inputData.length() >= 80) {
                Iterable<String> split = Splitter.fixedLength(80).split(inputData);
                List<String> splitToList = new ArrayList<>();
                split.forEach(splitToList::add);
                for (String s : splitToList) {
                    list.add(s);
                }
            }
                list.add(inputData);
                inputData = in.nextLine();
            }
            list.print();
        }
    }
