public class Main{
    public static void main(String[] args) throws InterruptedException {
        //Создание потока
            Thread newThread = new Thread(new Runnable() {
                public void run(){
                    for (int i = 0; i < 5; i++){
                        System.out.println("привет");
                    }
                }
            }
            );
        newThread.start();    //Запуск потока
        newThread.join();
            for (int i = 0; i < 5; i++) {
                System.out.println("пока");
            }
        }
    }


