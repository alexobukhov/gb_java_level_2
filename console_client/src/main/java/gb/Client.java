package gb;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        final String host = "localhost";
        final int port = 8186;
        Socket socket;
        DataInputStream in;
        DataOutputStream out;

        Network network = new Network(host, port);
        network.connect();

        Thread t = new Thread(() -> {
            try {
                network.waitMessage();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        t.start();

        while (true) {
            Scanner scanner = new Scanner(System.in);
            if (scanner.hasNextLine()) {
                try {
                    network.sendMessage(scanner.nextLine());
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("Ошибка при отправке сообщения");
                }
            }
        }
    }
}
