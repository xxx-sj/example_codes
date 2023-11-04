package java_ex.socket_ex.multi_chat;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ConnectException;
import java.net.Socket;
import java.util.Scanner;
import java.util.UUID;

public class TcpIpMultiChatClient {
    public static void main(String[] args) {
//        if(args.length != 1) {
//            System.out.println("USEAGE : JAVA TCPIPMULTICHATCLIENT 대화명" );
//            System.exit(0);
//        }
        String uuid = UUID.randomUUID().toString();


        try {
            String serverIp = "127.0.0.1";
            Socket socket = new Socket(serverIp, 7777);
            Thread sender = new Thread(new ClientSender(socket, uuid));
            Thread receiver = new Thread(new ClientReceiver(socket));

            sender.start();
            receiver.start();
        } catch (ConnectException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static class ClientSender extends Thread {
        Socket socket;
        DataOutputStream out;
        String name;

        ClientSender(Socket socket, String name) {
            this.socket = socket;
            try {
                out = new DataOutputStream(socket.getOutputStream());
                this.name = name;
            } catch (Exception e) {}
        }

        public void run() {
            Scanner scanner = new Scanner(System.in);
            try {
                if (out != null)  {
                    out.writeUTF(name);
                }

                while(out != null) {
                    out.writeUTF("[" + name + "]" + scanner.nextLine());
                }
            } catch (IOException e) {}
        }
    }

    static class ClientReceiver extends Thread {
        Socket socket;
        DataInputStream in;

        ClientReceiver(Socket socket) {
            this.socket = socket;
            try {
                in = new DataInputStream(socket.getInputStream());
            } catch (IOException e) {}
        }

        public void run() {
            while(in != null) {
                try {
                    System.out.println(in.readUTF());
                } catch (IOException e) {}
            }
        }
    }
}
