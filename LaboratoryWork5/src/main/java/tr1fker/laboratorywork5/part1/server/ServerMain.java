package tr1fker.laboratorywork5.part1.server;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerMain {
    private static final int DEFAULT_PORT = 5050;

    public static void main(String[] args) {
        System.out.println("Server starting...");
        try {
            ServerSocket serverSocket = new ServerSocket(DEFAULT_PORT);
            Socket clientAccepted = serverSocket.accept();
            System.out.println("Connection established...");
            ObjectOutputStream soos = new ObjectOutputStream(clientAccepted.getOutputStream());
            ObjectInputStream sois = new ObjectInputStream(clientAccepted.getInputStream());
            String clientMessageReceived = (String)sois.readObject();;

            while(!clientMessageReceived.equals("exit")) {
                String[] arrStr = clientMessageReceived.split(" ");
                int length = Integer.parseInt(arrStr[0]), countEl = length * length;
                int[] arr = new int[length * length];
                for (int i = 1; i < length * length; i++)
                    arr[i] = Integer.parseInt(arrStr[i]);
                System.out.println("Message received:" + clientMessageReceived);

                float mainDiagonal = 0f, secondaryDiagonal = 0f;
                for (int _si = 0; _si < length; _si++) {
                    mainDiagonal += arr[_si * length + _si];
                    secondaryDiagonal += arr[_si * length + length - _si - 1];
                }

                float attitude = mainDiagonal / secondaryDiagonal;
                System.out.printf("Sum of main diagonal:%.2f\nSum of secondary diagonal:%.2f\nResult(attitude):%.2f\n", mainDiagonal, secondaryDiagonal, attitude);
                soos.writeObject(attitude);
                clientMessageReceived = (String)sois.readObject();
            }
            soos.close();
            sois.close();
            clientAccepted.close();
            serverSocket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}