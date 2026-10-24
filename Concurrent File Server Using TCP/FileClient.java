
import java.io.*;
import java.net.*;
import java.util.Scanner;

public class FileClient {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter server IP: ");
        String ip = sc.nextLine();

        Socket s = new Socket(ip, 6000);

        BufferedReader in = new BufferedReader(
            new InputStreamReader(s.getInputStream()));
        PrintWriter out = new PrintWriter(
            s.getOutputStream(), true);

        System.out.print("Enter file name: ");
        out.println(sc.nextLine());

        String pid = in.readLine();
        String status = in.readLine();

        System.out.println("Server " + pid);

        String line;
        while (!(line = in.readLine()).equals("<<END>>")) {
            System.out.println(line);
        }

        s.close();
        sc.close();
    }
}
