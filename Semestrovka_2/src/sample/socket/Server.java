package sample.socket;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

public class Server {

    private List<String> list;

    public Server() throws IOException {
        list = new ArrayList<>();
        list.add("A1");
        list.add("A2");
        list.add("A3");
        list.add("B1");
        list.add("B2");
        list.add("B3");
        list.add("C1");
        list.add("C2");
        list.add("C3");
    }

    public static void main(String[] args) throws IOException {
        try {
            Server server = new Server();
            server.start();
        }
        catch (Exception e){
            System.out.println("end");
        }
    }

    public String answer(){
        int r = (int) (Math.random() * list.size());
        return list.get(r);
    }

    public void start() throws IOException {
        final int PORT = 8082;

        ServerSocket server = new ServerSocket(PORT);
        System.out.println("Starting server on " + PORT);
        System.out.print("Waiting for client...");
        try {
            while (true) {
                Socket socket = server.accept();
                System.out.println("Connected!");
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                String ans = answer();
                list.remove(ans);
                System.out.println(list);
                out.write(  ans + "\n");
                out.flush();
                String text;
                try {
                    while (true) {
                        text = in.readLine();
                        if(text.equals("stop")) {
                            server.close();
                            socket.close();
                            in.close();
                            out.close();
                            break;
                        }
                        list.remove(text);
                        System.out.println("Игрок сходил: " + text);
                        System.out.println(list);
                        try {
                            String ans1 = answer();
                            list.remove(ans1);
                            out.write(ans1 +  "\n");
                            out.flush();
                        } catch (IOException ignored) {}
                    }
                } catch (NullPointerException ignored) {}
            }
        }
        catch (Exception e){
            System.out.println("end");
        }
        finally {
            server.close();
        }
    }
}