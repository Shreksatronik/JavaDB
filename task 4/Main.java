public class Main implements Runnable{

    public void run(){


            System.out.println("Jules: The path of the righteous man is beset on all sides by the iniquities " +
                    "of the selfish and the tyranny of evil men. Blessed is he who, in the name of " +
                    "charity and good will, shepherds the weak through the valley of darkness, " +
                    "for he is truly his brother's keeper and the finder of lost children." +
                    "And I will strike down upon thee with great vengeance and furious anger " +
                    "those who attempt to poison and destroy my brothers. And you will know my name is " +
                    "The Lord when I lay my vengeance upon thee.");


    }


    public static void main(String[] args) throws InterruptedException {
        Main main = new Main();
        Thread printThread = new Thread(main);
        printThread.start();
        Thread.sleep(2000);
        printThread.interrupt();
        System.out.println("пока");
    }
}


