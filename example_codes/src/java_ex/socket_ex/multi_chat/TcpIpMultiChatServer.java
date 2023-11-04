package java_ex.socket_ex.multi_chat;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;

public class TcpIpMultiChatServer {
    HashMap clients;

    TcpIpMultiChatServer() {
        clients = new HashMap();
        Collections.synchronizedMap(clients);
    }

    public void start() {
        ServerSocket serverSocket = null;
        Socket socket = null;

        try {
            serverSocket = new ServerSocket(7777);
            System.out.println("server start");

            while (true) {
                socket = serverSocket.accept();
                System.out.println("[" + socket.getInetAddress() + ":" + socket.getPort() +"]" + "에서 접속");

                ServerReceiver thread = new ServerReceiver(socket);
                thread.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void sendToAll(String msg) {
        //name list
        Iterator it = clients.keySet().iterator();

        while(it.hasNext()) {
            try {
                //get -> DataOutputStream
                DataOutputStream out = (DataOutputStream) clients.get(it.next());
                out.writeUTF(msg);
            } catch (IOException e) {}
        }
    }

    public static void main(String[] args) {
        new TcpIpMultiChatServer().start();
    }

    class ServerReceiver extends Thread {
        Socket socket;
        DataInputStream in;
        DataOutputStream out;

        ServerReceiver(Socket socket) {
            this.socket = socket;
            try {
                in = new DataInputStream(socket.getInputStream());
                out = new DataOutputStream(socket.getOutputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void run() {
            String name = "";
            try {
                name = in.readUTF();
                sendToAll("#" + name + "님이 들어오셨습니다.");

                clients.put(name, out);
                System.out.println("현재 접속자 수는 " + clients.size() + "입니다.|.");
                while (in != null) {
                    sendToAll(in.readUTF());
                }
            } catch (IOException e) {

            }finally {
                sendToAll("#" + name + "님이 나가셨스빈다.");
                clients.remove(name);
                System.out.println("[" + socket.getInetAddress() + ":" + socket.getPort() + "에서 접속을 종료하셨습니다.");
                System.out.println("현재 서버 접속자 수는 " + clients.size() + "입니다.");
            }
        }
    }
}
