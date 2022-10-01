public class PiThread extends Thread {
    private final int Count;
    private final int Remainder;
    private final int N;
    private double sum = 0;

    public PiThread(int threadCount, int Remainder, int n) {
        this.Count = threadCount;
        this.Remainder = Remainder;//number thread
        N = n;
    }

    @Override
    public void run() {
        for (int i = Remainder; i <= N; i += Count) {
            sum += Math.pow(-1, i) / (2 * i + 1);
        }
    }

    public double getSum() {
        return sum;
    }
}

