package java_ex.socket_ex.udp_ex;

import java.io.IOException;
import java.net.*;

public class UdpClient {
    public void start() throws IOException {
        DatagramSocket datagramSocket = new DatagramSocket();
        InetAddress serverAddress = InetAddress.getByName("127.0.0.1");

        //데이터가 저장될 공간으로 byte배열 생성
        byte[] msg = new byte[10];
        DatagramPacket outPacket = new DatagramPacket(msg, 1, serverAddress, 7777);
        DatagramPacket inPacket = new DatagramPacket(msg, msg.length);

        datagramSocket.send(outPacket); //전송
        datagramSocket.receive(inPacket); //수신

        System.out.println("ccurent server time : " + new String(inPacket.getData()));

        datagramSocket.close();
    }

    public static void main(String[] args) {
        try {
            new UdpClient().start();
        } catch ( Exception e) {
            e.printStackTrace();
        }
    }
}
