package cn.element.net.socket;

import java.io.IOException;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * 获取本初子午线当前时间
 */
public class SocketTest {

    public static void main(String[] args) {
        try (
                Socket s = new Socket("time-a.nist.gov", 13);
                Scanner sc = new Scanner(s.getInputStream(), StandardCharsets.UTF_8)
        ) {
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
