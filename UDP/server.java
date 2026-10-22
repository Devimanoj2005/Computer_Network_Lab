
import java.net.*;

public class UDPServer {
    public static void main(String args[]) throws Exception {

        // Create a UDP socket on port 9876
        DatagramSocket socket = new DatagramSocket(9876);

        // Create a byte array to receive client data
        byte[] receiveData = new byte[1024];

        // Display server status
        System.out.println("Server Started...");

        // Create a packet to receive data
        DatagramPacket receivePacket =
                new DatagramPacket(receiveData, receiveData.length);

        // Wait for the client message
        socket.receive(receivePacket);

        // Convert received bytes into a String
        String sentence = new String(
                receivePacket.getData(),
                0,
                receivePacket.getLength()
        );

        // Display the received sentence
        System.out.println("Received: " + sentence);

        // Expand chat abbreviations
        sentence = sentence.replace("tbh", "to be honest");
        sentence = sentence.replace("ig", "I guess");
        sentence = sentence.replace("tbf", "to be fair");
        sentence = sentence.replace("atm", "at the moment");
        sentence = sentence.replace("irl", "in real life");
        sentence = sentence.replace("lol", "laughing out loud");
        sentence = sentence.replace("asap", "as soon as possible");
        sentence = sentence.replace("omg", "oh my God");
        sentence = sentence.replace("ttyl", "talk to you later");
        sentence = sentence.replace("idk", "I don't know");
        sentence = sentence.replace("nvm", "never mind");
        sentence = sentence.replace("idc", "I don't care");

        // Convert the translated sentence into bytes
        byte[] sendData = sentence.getBytes();

        // Create a packet to send the response to the client
        DatagramPacket sendPacket =
                new DatagramPacket(
                        sendData,
                        sendData.length,
                        receivePacket.getAddress(),
                        receivePacket.getPort()
                );

        // Send the translated sentence
        socket.send(sendPacket);

        // Display confirmation
        System.out.println("Translation Sent.");

        // Close the socket
        socket.close();
    }
}
