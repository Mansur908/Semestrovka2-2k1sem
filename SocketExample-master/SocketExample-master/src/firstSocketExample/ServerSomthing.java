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
            name = in.readLine();
            player = new Player(board, sign);
            try {
                out.write("Enter position" + "\n");
                out.flush();
            } catch (IOException ignored) {
            }
            try {
                while (true) {
                    text = in.readLine();
                    if (text.equals("stop")) {
                        this.downService(); // харакири
                        break; // если пришла пустая строка - выходим из цикла прослушки
                    }
                    player.move(text);
                    for (ServerSomthing vr : Server.serverList) {
                        if (this.board == vr.getBoard())
                            vr.send(board.toString()); // отослать принятое сообщение с привязанного клиента всем остальным влючая его
                    }
                    if (player.is_win()) {
                        for (ServerSomthing vr : Server.serverList) {
                            if (this.board == vr.getBoard()) {
                                vr.send(name + " win"); // отослать принятое сообщение с привязанного клиента всем остальным влючая его
                                vr.send("stop");
                            }
                        }
//                        for (ServerSomthing vr : Server.serverList) {
//                            if (this.board == vr.getBoard()){
//                                vr.send("stop"); // отослать принятое сообщение с привязанного клиента всем остальным влючая его
//
//                            }
//                        }
                    }
                    System.out.println("Echoing: " + text);
//                    for (ServerSomthing vr : Server.serverList) {
//                        vr.send(text); // отослать принятое сообщение с привязанного клиента всем остальным влючая его
//                    }
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
