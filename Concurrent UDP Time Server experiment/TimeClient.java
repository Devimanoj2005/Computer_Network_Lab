
import java.net.*;
import java.util.Scanner;

public class TimeClient {
    public static void main(String[] args) throws Exception {
        DatagramSocket client = new DatagramSocket();
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter server IP: ");
        String ip = sc.nextLine();

        InetAddress address = InetAddress.getByName(ip);

        byte[] data = "TIME_REQUEST".getBytes();

        DatagramPacket request = new DatagramPacket(
            data, data.length, address, 9876
        );

        client.send(request);

        byte[] buffer = new byte[1024];
        DatagramPacket response =
            new DatagramPacket(buffer, buffer.length);

        client.receive(response);

        String time = new String(
            response.getData(), 0, response.getLength()
        );

        System.out.println("Server Time: " + time);

        client.close();
        sc.close();
    }
}
