
import java.io.*;
import java.net.*;
import java.util.*;

public class ChatServer {
    static Vector<ClientHandler> clients = new Vector<>();

    public static void main(String[] args) throws Exception {
        ServerSocket server = new ServerSocket(5000);
        System.out.println("Chat Server Started");

        while (true) {
            Socket socket = server.accept();
            ClientHandler client = new ClientHandler(socket);
            clients.add(client);
            client.start();
        }
    }
}

class ClientHandler extends Thread {
    Socket socket;
    BufferedReader in;
    PrintWriter out;
    String name;

    ClientHandler(Socket socket) throws IOException {
        this.socket = socket;
        in = new BufferedReader(
            new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);
    }

    public void run() {
        try {
            name = in.readLine();
            broadcast(name + " joined the chat");

            String msg;
            while ((msg = in.readLine()) != null) {
                broadcast(name + ": " + msg);
            }
        } catch (Exception e) {
            // Client disconnected
        } finally {
            clients.remove(this);
            broadcast(name + " left the chat");
            try {
                socket.close();
            } catch (IOException e) {
                // Ignore closing error
            }
        }
    }

    void broadcast(String msg) {
        for (ClientHandler c : clients) {
            if (c != this) {
                c.out.println(msg);
            }
        }
    }
}
