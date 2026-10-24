
import java.net.*;
import java.util.Date;

public class TimeServer {
    public static void main(String[] args) throws Exception {
        DatagramSocket server = new DatagramSocket(9876);
        System.out.println("Time Server Started");

        while (true) {
            byte[] buffer = new byte[1024];
            DatagramPacket request =
                new DatagramPacket(buffer, buffer.length);

            server.receive(request);

            Thread t = new Thread(() -> {
                try {
                    String time = new Date().toString();
                    byte[] data = time.getBytes();

                    DatagramPacket response = new DatagramPacket(
                        data, data.length,
                        request.getAddress(), request.getPort()
                    );

                    server.send(response);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });

            t.start();
        }
    }
}
