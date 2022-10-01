public class MyThread implements Runnable {

    private final int id;
    private final String[] text;

    public MyThread(int id, String[] text) {
        this.text = text;
        this.id = id;
    }

    @Override
    public void run() {
        for (String strings : text) {
            System.out.println(id + " : " + strings);

        }
    }
}