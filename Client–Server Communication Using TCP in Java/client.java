
import java.net.*;
import java.util.Scanner;

public class TimeClient {
    public static void main(String[] args) throws Exception {
        DatagramSocket client = new DatagramSocket();
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter server IP: ");
        String ip = sc.nextLine();

        System.out.print("Enter port: ");
        int port = sc.nextInt();

        InetAddress address = InetAddress.getByName(ip);

        byte[] data = "TIME".getBytes();

        DatagramPacket request = new DatagramPacket(
            data, data.length, address, port
        );

        client.send(request);

        byte[] buffer = new byte[1024];

        DatagramPacket reply =
            new DatagramPacket(buffer, buffer.length);

        client.receive(reply);

        String time = new String(
            reply.getData(), 0, reply.getLength()
        );

        System.out.println("Server Time: " + time);

        client.close();
    }
}
