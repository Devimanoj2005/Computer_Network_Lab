
import java.io.*;
import java.net.*;

public class FileServer {
    public static void main(String[] args) throws Exception {
        ServerSocket ss = new ServerSocket(6000);
        long pid = ProcessHandle.current().pid();

        System.out.println("Server started. PID: " + pid);

        while (true) {
            Socket s = ss.accept();

            new Thread(() -> {
                try {
                    BufferedReader in = new BufferedReader(
                        new InputStreamReader(s.getInputStream()));
                    PrintWriter out = new PrintWriter(
                        s.getOutputStream(), true);

                    String name = in.readLine();

                    out.println("PID:" + pid);

                    File f = new File(name);

                    if (f.exists() && f.isFile()) {
                        out.println("FOUND");

                        BufferedReader fr = new BufferedReader(
                            new FileReader(f));

                        String line;
                        while ((line = fr.readLine()) != null)
                            out.println(line);

                        fr.close();
                    } else {
                        out.println("NOTFOUND");
                        out.println("File not found");
                    }

                    out.println("<<END>>");
                    s.close();

                } catch (Exception e) {
                    System.out.println(e);
                }
            }).start();
        }
    }
}
