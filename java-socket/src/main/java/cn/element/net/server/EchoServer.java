package cn.element.net.server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class EchoServer {

    public static void main(String[] args) throws IOException {
        try (ServerSocket server = new ServerSocket(8189)) {
            try (Socket incoming = server.accept()) {
                InputStream is = incoming.getInputStream();
                OutputStream os = incoming.getOutputStream();

                try (Scanner sc = new Scanner(is, StandardCharsets.UTF_8)) {
                    PrintWriter out = new PrintWriter(new OutputStreamWriter(os, StandardCharsets.UTF_8));
                    out.println("Hello! Enter Bye to exit.");
                    
                    // echo client input
                    boolean done = false;
                    while (!done && sc.hasNextLine()) {
                        String line = sc.nextLine();
                        out.println("Echo: " + line);
                        if (line.trim().equals("Bye")) {
                            done = true;
                        }
                    }
                }
            }
        }
    }

}
