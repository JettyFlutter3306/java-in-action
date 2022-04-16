package cn.element.net.inetAddress;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * 获取本机名称和IP地址
 */
public class InetAddressTest {

    public static void main(String[] args) throws UnknownHostException {
        if (args.length > 0) {
            String host = args[0];
            InetAddress[] addresses = InetAddress.getAllByName(host);
            for (InetAddress a : addresses) {
                System.out.println(a);
            }
        } else {
            InetAddress localHost = InetAddress.getLocalHost();
            System.out.println(localHost);
        }
    }

}
