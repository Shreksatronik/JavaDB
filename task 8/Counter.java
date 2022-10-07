public class Counter {
    private static final int N = 10_000_000;
    int Remainder;
    int threadCount;
   public double sum = 0;
    public Counter(int Remainder, int threadCount) {
        this.Remainder = Remainder;
        this.threadCount = threadCount;
    }
    public double getSum() {
        return sum;
    }

    public void count() {
            for (int i = Remainder; i <= N; i += threadCount) {
                sum += Math.pow(-1, i) / (2 * i + 1);
            }
        }
}