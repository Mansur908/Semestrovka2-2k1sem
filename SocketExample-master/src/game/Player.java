package game;

public class Player {

    private String a1;
    private String a2;
    private String a3;
    private String b1;
    private String b2;
    private String b3;
    private String c1;
    private String c2;
    private String c3;
    private Board board;
    private String sign;

    public Player(Board board,String sign) {
        this.a1 = "";
        this.a2 = "";
        this.a3 = "";
        this.b1 = "";
        this.b2 = "";
        this.b3 = "";
        this.c1 = "";
        this.c2 = "";
        this.c3 = "";
        this.board = board;
        this.sign = sign;
    }

    public boolean is_win() {
        return this.a1 == "1" && this.a2 == "1" && this.a3 == "1" ||
                this.b1 == "1" && this.b2 == "1" && this.b3 == "1" ||
                this.c1 == "1" && this.c2 == "1" && this.c3 == "1" ||
                this.a1 == "1" && this.b1 == "1" && this.c1 == "1" ||
                this.a2 == "1" && this.b2 == "1" && this.c2 == "1" ||
                this.a3 == "1" && this.b3 == "1" && this.c3 == "1" ||
                this.a1 == "1" && this.b2 == "1" && this.c3 == "1" ||
                this.a3 == "1" && this.b2 == "1" && this.c1 == "1";
    }

    public boolean move(String position){
        if (board.getList().contains(position)) {
            switch (position) {
                case ("A1"):
                    if (this.sign.equals("X"))
                        board.setA1("X");
                    else
                        board.setA1("O");
                    this.a1 = "1";
                    board.getList().remove(position);
                    return true;
                case ("A2"):
                    if (this.sign.equals("X"))
                        board.setA2("X");
                    else
                        board.setA2("O");
                    this.a2 = "1";
                    board.getList().remove(position);
                    return true;
                case ("A3"):
                    if (this.sign.equals("X"))
                        board.setA3("X");
                    else
                        board.setA3("O");
                    this.a3 = "1";
                    board.getList().remove(position);
                    return true;
                case ("B1"):
                    if (this.sign.equals("X"))
                        board.setB1("X");
                    else
                        board.setB1("O");
                    this.b1 = "1";
                    board.getList().remove(position);
                    return true;
                case ("B2"):
                    if (this.sign.equals("X"))
                        board.setB2("X");
                    else
                        board.setB2("O");
                    this.b2 = "1";
                    board.getList().remove(position);
                    return true;
                case ("B3"):
                    if (this.sign.equals("X"))
                        board.setB3("X");
                    else
                        board.setB3("O");
                    this.b3 = "1";
                    board.getList().remove(position);
                    return true;
                case ("C1"):
                    if (this.sign.equals("X"))
                        board.setC1("X");
                    else
                        board.setC1("O");
                    this.c1 = "1";
                    board.getList().remove(position);
                    return true;
                case ("C2"):
                    if (this.sign.equals("X"))
                        board.setC2("X");
                    else
                        board.setC2("O");
                    this.c2 = "1";
                    board.getList().remove(position);
                    return true;
                case ("C3"):
                    if (this.sign.equals("X"))
                        board.setC3("X");
                    else
                        board.setC3("O");
                    this.c3 = "1";
                    board.getList().remove(position);
                    return true;
                default:
                    return false;
            }
        }
        else
            return false;
    }
}
