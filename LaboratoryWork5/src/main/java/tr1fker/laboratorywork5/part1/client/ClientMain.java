package tr1fker.laboratorywork5.part1.client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Random;

public class ClientMain {

    private static final int minSizeMatrix = 5;
    private static final int maxSizeMatrix = 10;

    private static final int minElement = -9;
    private static final int maxElement = 9;

    private static final Random random = new Random();

    private static final int DEFAULT_PORT = 5050;

    public static void main(String[] args) {
        System.out.println("Server connecting...");

        try {
            Socket clientSocket = new Socket("127.0.0.1", DEFAULT_PORT);
            System.out.println("Connection established...");

            BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
            ObjectOutputStream coos = new ObjectOutputStream(clientSocket.getOutputStream());
            ObjectInputStream cois = new ObjectInputStream(clientSocket.getInputStream());

            while (true) {
                System.out.print("'1' - Create a new matrix, otherwise - exit:");
                String clientMessage = stdin.readLine();

                if (clientMessage.equals("1")) {
                    int size = random.nextInt(minSizeMatrix, maxSizeMatrix + 1);
                    System.out.println("Current matrix size:" + size);
                    clientMessage = "" +  size;
                    int countEl = size*size;

                    System.out.print("Current matrix:");
                    for (int _el = 0; _el < countEl; ++_el){
                        int newEl = random.nextInt(minElement, maxElement + 1);
                        clientMessage += " " + newEl;
                        System.out.printf((_el % size == 0 ? "\n" : "") + "%3d", newEl);
                    }
                    coos.writeObject(clientMessage);
                    System.out.println("\nServer response(attitude):" + cois.readObject());
                }else {
                    coos.writeObject("exit");
                    break;
                }
            }

            coos.writeObject("exit");
            coos.close();
            cois.close();
            clientSocket.close();
            stdin.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}