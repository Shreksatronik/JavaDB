public class Main{
    public static void main(String[] args) {
        //Создание потока
        Thread newThread = new Thread(new Runnable() {
            @Override
            public void run(){ //Этот метод будет выполняться в побочном потоке
                for (int i = 0; i < 5; i++) {
                    System.out.println("привет");
                }
            }
        }
        );
        newThread.start();    //Запуск потока
            for (int i = 0; i < 5; i++) {
                System.out.println("пока");
            }
        }
    }


