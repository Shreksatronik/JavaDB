public class Main {
    public static void main(String[] args) throws InterruptedException {
        Printer printer = new Printer();
        new Thread2(printer);
        printer.printMain();

    }
}