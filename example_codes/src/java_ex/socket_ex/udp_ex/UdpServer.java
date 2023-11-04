package java_ex.socket_ex.udp_ex;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UdpServer {
    public void start() throws IOException {
        //소켓을 생성한다.
        DatagramSocket socket = new DatagramSocket(7777);
        DatagramPacket inPacket, outPacket;

        byte[] inMsg = new byte[10];
        byte[] outMsg;

        while(true) {
            //데이터를 수신하기 위한 패킷생성
            inPacket = new DatagramPacket(inMsg, inMsg.length);

            //패킷을 통해 데이터를 수신(receive) 한다
            socket.receive(inPacket);
            System.out.println(inPacket.getData().length);
            //수신한 패ㅣㅅ으로 부터 client의 IP주소와 port를 얻는다.
            InetAddress address = inPacket.getAddress();
            int port = inPacket.getPort();

            //서버의 현재 시간을 시분초 형태로 반환
            SimpleDateFormat sdf = new SimpleDateFormat("[hh:mm:ss]");
            String time = sdf.format(new Date());
            outMsg = time.getBytes(); //time을 byte 배열로 반환

            //패킷을 생성해서 client에게 전송한다
            outPacket = new DatagramPacket(outMsg, outMsg.length, address, port);
            socket.send(outPacket);
        }
    }

    public static void main(String[] args) {
        try {
            new UdpServer().start();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}
