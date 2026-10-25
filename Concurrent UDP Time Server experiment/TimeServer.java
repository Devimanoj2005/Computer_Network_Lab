
import java.net.*;   // Import networking classes
import java.util.Date;  // Import Date class to get current date and time

public class TimeServer {
    public static void main(String[] args) throws Exception {

        // Step 1: Start the program

        // Step 2: Create a DatagramSocket on port number 9876
        DatagramSocket server = new DatagramSocket(9876);

        // Step 3: Display the server started message
        System.out.println("Time Server Started");

        // Step 4: Repeat continuously to handle client requests
        while (true) {

            // Step 4.1: Create a byte array to receive the client's request
            byte[] buffer = new byte[1024];

            // Step 4.2: Create a DatagramPacket to store the request
            DatagramPacket request =
                new DatagramPacket(buffer, buffer.length);

            // Step 4.3: Receive the request from the client
            server.receive(request);

            // Step 4.4: Create a new thread to handle the request
            Thread t = new Thread(() -> {
                try {
                    // Step 4.5: Get the current date and time
                    String time = new Date().toString();

                    // Step 4.6: Convert the date and time into bytes
                    byte[] data = time.getBytes();

                    // Step 4.7: Create a response packet containing
                    // the time, client's IP address, and port number
                    DatagramPacket response = new DatagramPacket(
                        data, data.length,
                        request.getAddress(), request.getPort()
                    );

                    // Step 4.8: Send the response packet to the client
                    server.send(response);

                } catch (Exception e) {
                    e.printStackTrace(); // Display any error
                }
            });

            // Step 4.9: Start the thread
            t.start();
        }

        // Step 5: Stop (the loop runs continuously until interrupted)
    }
}
