public class Manuf {
    private static final int counterA = 5;
    private static final int counterB = 5;
    private static final int counterC = 5;
    public static void main(String[] args) {
        StartA startA = new StartA(counterA);
        StartB startB = new StartB(counterB);
        StartC startC = new StartC(counterC);
        WidgetProducer widgetProducer = new WidgetProducer(startA,startB,startC);
        widgetProducer.start();
        System.exit(0);
    }
}
