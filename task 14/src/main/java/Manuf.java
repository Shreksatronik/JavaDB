public class Manuf {
    private static final int COUNTER = 5;
    public static void main(String[] args) {
        WidgetProducer WidgetProducer = new WidgetProducer(COUNTER);
        WidgetProducer.start();
        System.exit(0);
    }
}
