
import java.net.*;
import java.util.Scanner;

public class UDPClient {
    public static void main(String args[]) throws Exception {

        // Create Scanner to read keyboard input
        Scanner sc = new Scanner(System.in);

        // Create a UDP client socket
        DatagramSocket socket = new DatagramSocket();

        // Get the server IP address
        InetAddress ip = InetAddress.getByName("localhost");

        // Ask the user to enter a sentence
        System.out.print("Enter a sentence: ");

        // Read the complete sentence
        String sentence = sc.nextLine();

        // Convert the sentence into bytes
        byte[] sendData = sentence.getBytes();

        // Create the packet to send to the server
        DatagramPacket sendPacket =
                new DatagramPacket(sendData, sendData.length, ip, 9876);

        // Send the packet
        socket.send(sendPacket);

        // Create a buffer to receive the translated sentence
        byte[] receiveData = new byte[1024];

        // Create the receiving packet
        DatagramPacket receivePacket =
                new DatagramPacket(receiveData, receiveData.length);

        // Receive the server response
        socket.receive(receivePacket);

        // Convert the received bytes into a String
        String translated = new String(
                receivePacket.getData(),
                0,
                receivePacket.getLength()
        );

        // Display the translated sentence
        System.out.println("\nTranslated Sentence:");
        System.out.println(translated);

        // Close the socket
        socket.close();

        // Close Scanner
        sc.close();
    }
}
