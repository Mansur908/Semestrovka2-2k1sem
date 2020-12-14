package firstSocketExample;

import game.Board;
import game.Player;

import java.io.*;
import java.net.Socket;

public class ServerSomthing extends Thread {

    private Socket socket;
    private BufferedReader in;
    private BufferedWriter out;
    private Player player;
    private Board board;
    private String sign;
    private String name;
    private int count = 0;

    public ServerSomthing(Socket socket, Board board, String sign) throws IOException {
        this.socket = socket;
        this.board = board;
        this.sign = sign;
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        start();
    }

    @Override
    public void run() {
        String text;
        try {
            player = new Player(board, sign);
            try {
                out.write(sign+ "\n");
                out.flush();
            } catch (IOException ignored) {
            }
            try {
                while (true) {
                    text = in.readLine();
                    if (text.split(":").length < 2) {
                        if (text.equals("connected")){
                            for (ServerSomthing vr : Server.serverList) {
                                if (this.board == vr.getBoard() && this != vr)
                                    vr.send(text);
                            }
                            continue;
                        }
                        player.move(text);
                        count++;
                        for (ServerSomthing vr : Server.serverList) {
                            if (this.board == vr.getBoard() && this != vr)
                                vr.send(text);
                        }
                        if (count == 5 && !player.is_win()){
                            for (ServerSomthing vr : Server.serverList) {
                                if (this.board == vr.getBoard()) {
                                    vr.send("ничья");
                                }
                            }
                        }
                        if (player.is_win()) {
                            for (ServerSomthing vr : Server.serverList) {
                                if (this.board == vr.getBoard()) {
                                    vr.send(sign+" win");
                                }
                            }
                        }
                    }
                    else {
                        for (ServerSomthing vr : Server.serverList) {
                            vr.send(text);
                        }
                    }
                    System.out.println("Echoing: " + text);
                }
            } catch (NullPointerException ignored) {
            }


        } catch (IOException e) {
            this.downService();
        }
    }

    private void send(String msg) {
        try {
            out.write(msg + "\n");
            out.flush();
        } catch (IOException ignored) {
        }

    }

    private void downService() {
        try {
            if (!socket.isClosed()) {
                socket.close();
                in.close();
                out.close();
                Server.serverList.removeIf(vr -> this.board == vr.getBoard());
            }
        } catch (IOException ignored) {
        }
    }

    public Board getBoard() {
        return board;
    }
}