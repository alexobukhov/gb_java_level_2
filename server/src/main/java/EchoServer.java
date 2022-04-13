import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.Scanner;

public class EchoServer {
    private static final int SERVER_PORT = 8186;
    private static DataInputStream in;
    private static DataOutputStream out;

    public static void main(String[] args) {

        try (ServerSocket serverSocket = new ServerSocket(SERVER_PORT);
        ) {
            while (true) {
                System.out.println("Ожидание подключения...");
                Socket clientSocket = serverSocket.accept();
                System.out.println("Соединение установлено!");

                in = new DataInputStream(clientSocket.getInputStream());
                out = new DataOutputStream(clientSocket.getOutputStream());

                Thread t = new Thread(() -> {
                    while (true) {
                        Scanner scanner = new Scanner(System.in);
                        if (scanner.hasNextLine()) {
                            try {
                                out.writeUTF(scanner.nextLine());
                            } catch (Exception e) {
                                e.printStackTrace();
                                System.out.println("Ошибка при отправке сообщения");
                            }
                        }
                    }
                });

                t.start();

                try {
                    while (true) {
                        String message = in.readUTF();

                        if (message.equals("/server-stop")) {
                            System.out.println("Сервер остановлен");
                            System.exit(0);
                        }

                        System.out.println("Клиент: " + message);
                    }

                } catch (SocketException e) {
                    clientSocket.close();
                    System.out.println("Клиент отключился");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();

        }
    }
}
