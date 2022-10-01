public class Main {
    public static void main(String[] args) {
        Printer printer = new Printer();
        new Thread2(printer);
        for (int i = 1; i <= 5; i++) {
            printer.printMain(i);
        }
    }
}