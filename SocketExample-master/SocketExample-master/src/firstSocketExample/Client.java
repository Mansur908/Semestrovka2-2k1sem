package firstSocketExample;

public class Client {

    public static String Addr = "localhost";
    public static int port = 8082;

    public static void main(String[] args) {
        new ClientSomthing(Addr, port);
    }
}