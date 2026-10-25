
import java.io.*;       // Import input and output classes
import java.net.*;      // Import networking classes

public class Server {
    public static void main(String[] args) throws Exception {

        // Create a server socket on port 9002
        ServerSocket server = new ServerSocket(9002);

        // Display a message while waiting for a client
        System.out.println("Server waiting for connection...");

        // Accept the client connection
        Socket socket = server.accept();
        System.out.println("Client connected.");

        // Create a stream to receive objects and primitive data
        ObjectInputStream in =
            new ObjectInputStream(socket.getInputStream());

        // Read the order of the matrix sent by the client
        int n = in.readInt();

        // Receive the matrix object and convert it to int[][]
        int[][] matrix = (int[][]) in.readObject();

        // Display the received matrix
        System.out.println("Received Matrix:");

        // Loop through each row
        for (int i = 0; i < n; i++) {

            // Loop through each column
            for (int j = 0; j < n; j++) {
                System.out.print(matrix[i][j] + " ");
            }

            // Move to the next line after printing a row
            System.out.println();
        }

        // Initially assume the matrix has all three properties
        boolean upper = true;
        boolean lower = true;
        boolean diagonal = true;

        // Check every element in the matrix
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {

                // If an element below the main diagonal is non-zero,
                // the matrix is not upper triangular
                if (i > j && matrix[i][j] != 0)
                    upper = false;

                // If an element above the main diagonal is non-zero,
                // the matrix is not lower triangular
                if (i < j && matrix[i][j] != 0)
                    lower = false;

                // If any non-diagonal element is non-zero,
                // the matrix is not diagonal
                if (i != j && matrix[i][j] != 0)
                    diagonal = false;
            }
        }

        // Variable to store the matrix classification
        String result;

        // Check the matrix type and assign the result
        if (diagonal)
            result = "Diagonal Matrix";

        // Otherwise, check whether it is upper triangular
        else if (upper)
            result = "Upper Triangular Matrix";

        // Otherwise, check whether it is lower triangular
        else if (lower)
            result = "Lower Triangular Matrix";

        // If none of the above conditions is satisfied
        else
            result = "Normal Matrix";

        // Display the identified matrix type
        System.out.println("Matrix Type: " + result);

        // Create a stream to send the result to the client
        DataOutputStream out =
            new DataOutputStream(socket.getOutputStream());

        // Send the result as a string
        out.writeUTF(result);

        // Ensure the result is sent immediately
        out.flush();

        // Close the client connection
        socket.close();

        // Close the server socket
        server.close();
    }
}
