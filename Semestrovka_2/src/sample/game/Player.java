package sample.game;

import sun.util.resources.cldr.en.TimeZoneNames_en_ZA;

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
        Cross cross = new Cross();
        Zero zero = new Zero();
        if (board.getList().contains(position)) {
            switch (position) {
                case ("A1"):
                    if (this.sign == "X")
                        board.setA1(cross.toString());
                    else
                        board.setA1(zero.toString());
                    this.a1 = "1";
                    board.getList().remove(position);
                    return true;
                case ("A2"):
                    if (this.sign == "X")
                        board.setA2(cross.toString());
                    else
                        board.setA2(zero.toString());
                    this.a2 = "1";
                    board.getList().remove(position);
                    return true;
                case ("A3"):
                    if (this.sign == "X")
                        board.setA3(cross.toString());
                    else
                        board.setA3(zero.toString());
                    this.a3 = "1";
                    board.getList().remove(position);
                    return true;
                case ("B1"):
                    if (this.sign == "X")
                        board.setB1(cross.toString());
                    else
                        board.setB1(zero.toString());
                    this.b1 = "1";
                    board.getList().remove(position);
                    return true;
                case ("B2"):
                    if (this.sign == "X")
                        board.setB2(cross.toString());
                    else
                        board.setB2(zero.toString());
                    this.b2 = "1";
                    board.getList().remove(position);
                    return true;
                case ("B3"):
                    if (this.sign == "X")
                        board.setB3(cross.toString());
                    else
                        board.setB3(zero.toString());
                    this.b3 = "1";
                    board.getList().remove(position);
                    return true;
                case ("C1"):
                    if (this.sign == "X")
                        board.setC1(cross.toString());
                    else
                        board.setC1(zero.toString());
                    this.c1 = "1";
                    board.getList().remove(position);
                    return true;
                case ("C2"):
                    if (this.sign == "X")
                        board.setC2(cross.toString());
                    else
                        board.setC2(zero.toString());
                    this.c2 = "1";
                    board.getList().remove(position);
                    return true;
                case ("C3"):
                    if (this.sign == "X")
                        board.setC3(cross.toString());
                    else
                        board.setC3(zero.toString());
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
