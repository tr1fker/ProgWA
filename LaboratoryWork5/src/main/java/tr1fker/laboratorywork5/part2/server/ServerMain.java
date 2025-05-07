package tr1fker.laboratorywork5.part2.server;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class ServerMain {
    private static final int DEFAULT_PORT = 5050;

    private static int[] isThreeNumbers(String line){
        String[] parts = line.split(" ");
        if (parts.length != 3)
            return null;
        try{
            int[] numbers = new int[3];
            for (int i = 0; i < 3; i++)
                numbers[i] = Integer.parseInt(parts[i]);
            return numbers;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    private static double calculate(int[] numbers){
        return 6f + Math.exp(numbers[0] - Math.sin(numbers[1])) * (1 + Math.pow(Math.pow(Math.atan(numbers[2] / 100f), 7), Math.sqrt(Math.abs(numbers[1] + 3f)))) / (numbers[1] + Math.atan(numbers[0] * numbers[0]) / (numbers[1] + Math.pow(numbers[0], 7) / numbers[2]));
    }

    public static void main(String[] args) {
        boolean stopFlag = false;
        byte[] buf = new byte[512];
        try {
            DatagramSocket socket = new DatagramSocket(DEFAULT_PORT);
            System.out.println("UDPServer: Started on " + socket.getLocalAddress() + ":" + socket.getLocalPort());

            while (!stopFlag) {
                DatagramPacket packet = new DatagramPacket(buf, buf.length);
                socket.receive(packet);
                String cmd = new String(packet.getData(), 0, packet.getLength()).trim();
                System.out.println("UDPServer: Received:" + cmd);

                if (cmd.equals("quit"))
                    break;

                int[] numbers = isThreeNumbers(cmd);
                String message = (numbers != null) ? String.valueOf(calculate(numbers)) : "Incorrect input!";
                byte[] responseData = message.getBytes();
                System.out.println("UDPServer: Sending:" + message);
                DatagramPacket response = new DatagramPacket(responseData, responseData.length, packet.getAddress(), packet.getPort());
                socket.send(response);
            }
            System.out.println("UDPServer: Stopped");
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
