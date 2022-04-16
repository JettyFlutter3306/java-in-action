package cn.element.net.threaded;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class ThreadedEchoHandler implements Runnable {
    
    private final Socket incoming;

    public ThreadedEchoHandler(Socket incoming) {
        this.incoming = incoming;
    }

    @Override
    public void run() {
        try (InputStream is = incoming.getInputStream();
             OutputStream os = incoming.getOutputStream();
             Scanner sc = new Scanner(is, StandardCharsets.UTF_8);
             PrintWriter out = new PrintWriter(new OutputStreamWriter(os, StandardCharsets.UTF_8), true)
        ) {
            out.println("Hello! Enter Bye to exit.");
            
            boolean done = false;
            while (!done && sc.hasNextLine()) {
                String line = sc.nextLine();
                out.println("Echo: " + line);
                
                if (line.trim().equals("Bye")) {
                    done = true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
