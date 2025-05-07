package tr1fker.laboratorywork5.part3.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerMain {

    private static final int port = 6666;
    private static final String[] words = {
            "ноль", "один", "два", "три", "четыре",
            "пять", "шесть", "семь", "восемь", "девять", "десять"
    };

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(port);
        System.out.println("Сервер запущен на порту " + port);
        Socket clientSocket = serverSocket.accept();
        System.out.println("Подключен клиент " + clientSocket.getInetAddress().getHostAddress() + ":" + clientSocket.getPort());
        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
        while(true) {
            String input = in.readLine();
            if (input == null) break;
            try {
                int number = Integer.parseInt(input);
                System.out.println("Клиент: " + number);
                out.println(number >= 0 && number <= 10 ? words[number] : "Ошибка: Число вне диапазона");
            } catch (Exception e) {
                out.println("Ошибка: Введите число от 0 до 10");
            }
        }
        clientSocket.close();
    }
}
