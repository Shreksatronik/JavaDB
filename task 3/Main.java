public class Main {

    public static void NewThread(int id, String[] text) {
        MyThread runnable = new MyThread(id, text);
        new Thread(runnable).start();
    }

    public static void main(String[] args) throws InterruptedException {
        String[] text = new String[]{"привет", "меня", "зовут", "настя", "как дела"};
        NewThread(1, text);
        NewThread(2, text);
        NewThread(3, text);
        NewThread(4, text);
    }
}