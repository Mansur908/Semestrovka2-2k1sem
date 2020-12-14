package game;

import java.util.ArrayList;
import java.util.List;

public class Board {

    private String a1;
    private String a2;
    private String a3;
    private String b1;
    private String b2;
    private String b3;
    private String c1;
    private String c2;
    private String c3;
    private List<String> list;

    public Board() {
        this.a1 = " ";
        this.a2 = " ";
        this.a3 = " ";
        this.b1 = " ";
        this.b2 = " ";
        this.b3 = " ";
        this.c1 = " ";
        this.c2 = " ";
        this.c3 = " ";
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

    public List<String> getList() {
        return list;
    }

    public void setA1(String a1) {
        this.a1 = a1;
    }

    public void setA2(String a2) {
        this.a2 = a2;
    }

    public void setA3(String a3) {
        this.a3 = a3;
    }

    public void setB1(String b1) {
        this.b1 = b1;
    }

    public void setB2(String b2) {
        this.b2 = b2;
    }

    public void setB3(String b3) {
        this.b3 = b3;
    }

    public void setC1(String c1) {
        this.c1 = c1;
    }

    public void setC2(String c2) {
        this.c2 = c2;
    }

    public void setC3(String c3) {
        this.c3 = c3;
    }
}
