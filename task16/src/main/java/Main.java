import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.URL;
import java.util.Scanner;
import java.util.concurrent.LinkedBlockingQueue;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        URL url = new URL(args[0]);
        LinkedBlockingQueue<String> linesQueue = new LinkedBlockingQueue<>();
        Scanner scanner = new Scanner(System.in);
        Thread thread = new Thread(() -> {
            int port = 443;
            if (url.getPort() != -1) {
                port = url.getPort();
            }
            try {
                SSLSocketFactory factory = (SSLSocketFactory) SSLSocketFactory.getDefault();
                SSLSocket socket = (SSLSocket) factory.createSocket(url.getHost(), port);
                PrintStream printStream = new PrintStream(socket.getOutputStream());
                printStream.print("GET " + url.getPath() + " HTTP/1.1\r\n");
                printStream.print("Host: " + url.getHost() + "\r\n");
                printStream.print("\r\n");
                printStream.flush();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                bufferedReader.lines().forEach(linesQueue::add);
                socket.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        thread.start();

        while (true){
            for (int i = 0; i < 25; i++) {
                String line = linesQueue.take();
                System.out.println(line);
                if (line.equals("0")){
                    return;
                }

            }
            System.out.println("=======================================");
            String inp = "not null";
            System.out.println("Нажмите enter чтобы продолжить читать");
            while (inp.length() != 0){
                inp = scanner.nextLine();
            }
        }
    }

}
