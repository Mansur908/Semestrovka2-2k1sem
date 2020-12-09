package firstSocketExample;

import game.Board;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;

public class Server {
    public static final int PORT = 8082;
    public static LinkedList<ServerSomthing> serverList = new LinkedList<>();

    public static void main (String[] args) throws IOException {
        ServerSocket server = new ServerSocket(PORT);
        System.out.println("Server Started");
//        Board board = new Board();
        try {
            while (true) {
                Board board = new Board();
                Socket socket = server.accept();
                try {
                    serverList.add(new ServerSomthing(socket,board,"X"));
                } catch (IOException e) {
                    socket.close();
                }
                Socket socket1 = server.accept();
                try {
                    serverList.add(new ServerSomthing(socket1,board, "O"));
                } catch (IOException e) {
                    socket.close();
                }
            }
        } finally {
            server.close();
        }
    }
}
