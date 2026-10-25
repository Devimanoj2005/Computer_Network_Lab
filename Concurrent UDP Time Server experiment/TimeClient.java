
import java.net.*;   // Import networking classes
import java.util.Scanner;  // Import Scanner to read keyboard input

public class TimeClient {
    public static void main(String[] args) throws Exception {

        // Step 1: Start the program

        // Step 2: Create a DatagramSocket for client communication
        DatagramSocket client = new DatagramSocket();

        // Step 3: Create a Scanner to read input from the keyboard
        Scanner sc = new Scanner(System.in);

        // Step 4: Read the server's IP address from the user
        System.out.print("Enter server IP: ");
        String ip = sc.nextLine();

        // Step 5: Convert the IP address into an InetAddress object
        InetAddress address = InetAddress.getByName(ip);

        // Step 6: Convert the TIME_REQUEST message into bytes
        byte[] data = "TIME_REQUEST".getBytes();

        // Step 7: Create a request packet containing the message,
        // server IP address, and port number 9876
        DatagramPacket request = new DatagramPacket(
            data, data.length, address, 9876
        );

        // Step 8: Send the request packet to the server
        client.send(request);

        // Step 9: Create a byte array and packet to receive the response
        byte[] buffer = new byte[1024];
        DatagramPacket response =
            new DatagramPacket(buffer, buffer.length);

        // Step 10: Receive the response from the server
        client.receive(response);

        // Step 11: Convert the received bytes into a string
        String time = new String(
            response.getData(), 0, response.getLength()
        );

        // Step 12: Display the server's date and time
        System.out.println("Server Time: " + time);

        // Step 13: Close the socket and Scanner
        client.close();
        sc.close();

        // Step 14: Stop the program
    }
}
