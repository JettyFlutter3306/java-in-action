package cn.element.net.threaded;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ThreadEchoServer {

    public static void main(String[] args) throws IOException {
        try (ServerSocket server = new ServerSocket(8189)) {
            int i = 1;
            while (true) {
                Socket incoming = server.accept();
                System.out.println("Spawning " + i);
                Runnable r = new ThreadedEchoHandler(incoming);
                Thread t = new Thread(r);
                t.start();
                i++;
            }
        }
    }

}
