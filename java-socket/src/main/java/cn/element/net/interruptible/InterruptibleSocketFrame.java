package cn.element.net.interruptible;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class InterruptibleSocketFrame extends JFrame {
    
    private Scanner in;
    private final JButton interruptibleButton;
    private final JButton blockingButton;
    private JButton cancelButton;
    private final JTextArea messages;
    private final TestServer server;
    private Thread connectThread;
    
    public InterruptibleSocketFrame() {
        JPanel northPanel = new JPanel();
        add(northPanel, BorderLayout.NORTH);
        
        final int TEXT_ROWS = 20;
        final int TEXT_COLUMNS = 60;
        messages = new JTextArea(TEXT_ROWS, TEXT_COLUMNS);
        add(new JScrollPane(messages));
        
        interruptibleButton = new JButton("Interruptible");
        blockingButton = new JButton("Blocking");
        
        northPanel.add(interruptibleButton);
        northPanel.add(blockingButton);
        
        interruptibleButton.addActionListener(e -> {
            interruptibleButton.setEnabled(false);
            blockingButton.setEnabled(false);
            cancelButton.setEnabled(true);
            connectThread = new Thread(() -> {
                try {
                    connectInterruptibly();
                } catch (IOException ioException) {
                    messages.append("\nInterruptibleSocketTest.connectInterruptibly: " + e);
                }
            });
            
            connectThread.start();
        });
        
        blockingButton.addActionListener(e -> {
            interruptibleButton.setEnabled(false);
            blockingButton.setEnabled(false);
            cancelButton.setEnabled(true);
            connectThread = new Thread(() -> {
                try {
                    connectBlocking();
                } catch (IOException ex) {
                    messages.append("\nInterruptibleSocketTest.connectBlocking: " + ex);
                }
            });
            
            connectThread.start();
        });
        
        cancelButton = new JButton("Cancel");
        cancelButton.setEnabled(false);
        northPanel.add(cancelButton);
        cancelButton.addActionListener(e -> {
            connectThread.interrupt();
            cancelButton.setEnabled(false);
        });
        
        server = new TestServer();
        new Thread(server).start();
        pack();
    }
    
    public void connectInterruptibly() throws IOException {
        messages.append("Interruptible:\n");
        try (SocketChannel channel = SocketChannel.open(new InetSocketAddress("localhost", 8189))) {
            in = new Scanner(channel, StandardCharsets.UTF_8);
            while (!Thread.currentThread().isInterrupted()) {
                messages.append("Reading ");
                if (in.hasNextLine()) {
                    String line = in.nextLine();
                    messages.append(line);
                    messages.append("\n");
                }
            }
        } finally {
            EventQueue.invokeLater(() -> {
                messages.append("Channel Closed\n");
                interruptibleButton.setEnabled(true);
                blockingButton.setEnabled(true);
            });
        }
    }
    
    public void connectBlocking() throws IOException {
        messages.append("Blocking:\n");
        try (Socket socket = new Socket("localhost", 8189)) {
            in = new Scanner(socket.getInputStream(), StandardCharsets.UTF_8);
            while (!Thread.currentThread().isInterrupted()) {
                messages.append("Reading: ");
                if (in.hasNextLine()) {
                    String line = in.nextLine();
                    messages.append(line);
                    messages.append("\n");
                }
            }
        } finally {
            EventQueue.invokeLater(() -> {
                messages.append("Socket Closed\n");
                interruptibleButton.setEnabled(true);
                blockingButton.setEnabled(true);
            });
        }
    }


    public class TestServerHandler implements Runnable {
    
        private Socket incoming;
        
        private int counter;
    
        public TestServerHandler(Socket incoming) {
            this.incoming = incoming;
        }
    
        @Override
        public void run() {
            try {
                try {
                    OutputStream os = incoming.getOutputStream();
                    PrintWriter writer = new PrintWriter(
                            new OutputStreamWriter(os, StandardCharsets.UTF_8), true);
                    
                    while (counter < 100) {
                        counter++;
                        if (counter <= 10) {
                            writer.println(counter);
                        }
                        Thread.sleep(100);
                    }
                } finally {
                    incoming.close();
                    messages.append("Closing Server\n");
                }
            } catch (Exception e) {
                messages.append("\nTestServerHandler.run: " + e);
            }
        }
    }

    public class TestServer implements Runnable {
    
        @Override
        public void run() {
            try (ServerSocket s = new ServerSocket(8189)) {
                while (true) {
                    Socket incoming = s.accept();
                    Runnable r = new TestServerHandler(incoming);
                    new Thread(r).start();
                }
            } catch (IOException e) {
                messages.append("\nTestServer.run: " + e);
            }
        }
    }
}
