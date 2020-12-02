package sample.game;

import java.io.*;
import java.net.Socket;

public class Tic_tac_toe {

    private Socket socket = new Socket("localhost",8082);
    private Board board = new Board();
    private BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    private BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

    public Tic_tac_toe() throws IOException {
    }

    public static void main (String[] args) throws IOException {

        Tic_tac_toe tic_tac_toe = new Tic_tac_toe();
        tic_tac_toe.start();
    }

    private String getStr() throws IOException {
        String str;
        try {
            str = in.readLine();
            return str;

        } catch (IOException e) {
            socket.close();
            in.close();
            out.close();
        }
        return null;
    }

    private void start() throws IOException {

        Player serv = new Player(board,"X");
        Player player = new Player(board,"O");


        while (true){
            String h1 = getStr();
            serv.move(h1);
            System.out.println(board);
            if (serv.is_win()){
                System.out.println("Вы проиграли");
                break;
            }
            if (board.getList().isEmpty()){
                System.out.println("Ничья");
                break;
            }
            System.out.println("Ваш ход");
            BufferedReader sc1 = new BufferedReader(new InputStreamReader(System.in));
            String s1 = sc1.readLine();
            while (!player.move(s1)) {
                System.out.println("Ваш ход");
                s1 = sc1.readLine();
            }
            out.write( s1 +   "\n");
            out.flush();
//            System.out.println(board);
            if (player.is_win()){
                System.out.println("Вы выиграли");
                break;
            }
            if (board.getList().isEmpty()){
                System.out.println("Ничья");
                break;
            }
        }
    }



















}