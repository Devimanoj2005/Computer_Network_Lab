
import java.io.*;       // Import input and output classes
import java.net.*;      // Import networking classes
import java.util.*;     // Import Scanner and Random classes

public class Client {
    public static void main(String[] args) throws Exception {

        // Connect to the server running on localhost at port 9002
        Socket socket = new Socket("localhost", 9002);

        // Create Scanner to read user input
        Scanner sc = new Scanner(System.in);

        // Create Random object to generate random numbers
        Random rand = new Random();

        // Ask the user to enter the matrix order
        System.out.print("Enter order of matrix: ");
        int n = sc.nextInt();

        // Create an n x n integer matrix
        int[][] matrix = new int[n][n];

        // Fill the matrix with random numbers from 1 to 50
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = rand.nextInt(50) + 1;
            }
        }

        // Randomly select one of four matrix types: 0, 1, 2, or 3
        int type = rand.nextInt(4);

        // Modify the matrix according to the selected type
        switch (type) {

            case 0:
                // Create an upper triangular matrix
                // Set all elements below the main diagonal to zero
                for (int i = 0; i < n; i++)
                    for (int j = 0; j < n; j++)
                        if (i > j)
                            matrix[i][j] = 0;
                break;

            case 1:
                // Create a lower triangular matrix
                // Set all elements above the main diagonal to zero
                for (int i = 0; i < n; i++)
                    for (int j = 0; j < n; j++)
                        if (i < j)
                            matrix[i][j] = 0;
                break;

            case 2:
                // Create a diagonal matrix
                // Set every non-diagonal element to zero
                for (int i = 0; i < n; i++)
                    for (int j = 0; j < n; j++)
                        if (i != j)
                            matrix[i][j] = 0;
                break;

            default:
                // Keep all random values to create a normal matrix
                break;
        }

        // Display the generated matrix
        System.out.println("Generated Matrix:");

        // Loop through each row
        for (int i = 0; i < n; i++) {

            // Loop through each column
            for (int j = 0; j < n; j++) {
                System.out.print(matrix[i][j] + " ");
            }

            // Move to the next line after printing a row
            System.out.println();
        }

        // Create a stream to send the matrix and integer data
        ObjectOutputStream out =
            new ObjectOutputStream(socket.getOutputStream());

        // Send the matrix order to the server
        out.writeInt(n);

        // Send the complete matrix object to the server
        out.writeObject(matrix);

        // Ensure the data is sent immediately
        out.flush();

        // Create a stream to receive the server's response
        DataInputStream in =
            new DataInputStream(socket.getInputStream());

        // Read the matrix type returned by the server
        String result = in.readUTF();

        // Display the result received from the server
        System.out.println("Matrix Type: " + result);

        // Close the client socket
        socket.close();

        // Close the Scanner
        sc.close();
    }
}
