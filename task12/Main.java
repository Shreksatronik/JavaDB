package task12;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;



public class Main {
    public static void main(String[] args) {
        LL list = new LL();
        Thread thread = new Thread(new Sorter(list));
        thread.start();
        System.out.println("Vvedite chisla");
        Scanner in = new Scanner(System.in);
        String inputData = in.nextLine();
        while (inputData.compareTo("") != 0) {
            if (inputData.length() >= 80) {
                String[] lines = inputData.split("(?<=\\G.{" + 80 + "})");
               for (String line : lines){
                    list.add(line);
                }
            }
                list.add(inputData);
                inputData = in.nextLine();
            }
            list.print();
        }
    }
