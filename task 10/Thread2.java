public class Thread2 implements Runnable {
    Printer printer;

    public Thread2(Printer printer) {
        this.printer = printer;
        new Thread(this).start();
    }

    @Override
    public void run() {
        try {
            printer.printThread2();
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }
}
