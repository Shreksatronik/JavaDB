public class Thread2 implements Runnable {
        Printer printer;
    Thread2(Printer printer) {
        this.printer = printer;
        new Thread(this).start();
    }

    @Override
    public void run() {
        for (int i = 1; i <= 5; i++) {
            printer.printThread2();
        }

    }

}
