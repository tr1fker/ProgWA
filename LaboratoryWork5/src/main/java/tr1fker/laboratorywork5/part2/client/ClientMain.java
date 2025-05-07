package tr1fker.laboratorywork5.part2.client;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Arrays;
import java.util.Scanner;

public class ClientMain {
    private static final int DEFAULT_PORT = 5050;

    private static final Scanner scanner = new Scanner(System.in);

    private static boolean isThreeNumbers(String line){
        String[] parts = line.split(" ");
        try{
            Integer.parseInt(parts[0]);
            Integer.parseInt(parts[1]);
            Integer.parseInt(parts[2]);
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    public static void main(String[] args) {
        byte[] buf = new byte[512];
        try {
            DatagramSocket socket = new DatagramSocket();
            System.out.println("UDPClient: Started");

            DatagramPacket packet = new DatagramPacket(buf, buf.length, InetAddress.getByName("127.0.0.1"), DEFAULT_PORT);
            while (true) {
                System.out.print("Enter three values x, y, z separated by space('quit' to exit):");
                String line = scanner.nextLine().trim();
                Arrays.fill(buf, (byte) 0);
                byte[] data = line.getBytes();
                System.arraycopy(data, 0, buf, 0, data.length);
                packet.setData(buf);
                packet.setLength(data.length);

                if (line.equals("quit")) {
                    socket.send(packet);
                    break;
                }else if (isThreeNumbers(line)) {
                    socket.send(packet);
                    DatagramPacket receivedPacket = new DatagramPacket(buf, buf.length);
                    socket.receive(receivedPacket);
                    String received = new String(receivedPacket.getData(), 0, receivedPacket.getLength()).trim();
                    System.out.println("UDPClient: Received:" + received);
                }else{
                    System.out.println("Incorrect input!");
                }
            }
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
