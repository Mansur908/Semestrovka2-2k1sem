package sample.game;

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

    @Override
    public String toString() {
        return ("    1   2   3\n"+
                "A   "+ a1 +" |"+ " "+ a2 +" |"+ " "+ a3 +" \n"+
                "   ------------\n"+
                "B   "+ b1 +" |"+ " "+ b2 +" |"+ " "+ b3 +" \n"+
                "   ------------\n"+
                "C   "+ c1 +" |"+ " "+ c2 +" |"+ " "+ c3 +"");
    }

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    public String getA1() {
        return a1;
    }

    public void setA1(String a1) {
        this.a1 = a1;
    }

    public String getA2() {
        return a2;
    }

    public void setA2(String a2) {
        this.a2 = a2;
    }

    public String getA3() {
        return a3;
    }

    public void setA3(String a3) {
        this.a3 = a3;
    }

    public String getB1() {
        return b1;
    }

    public void setB1(String b1) {
        this.b1 = b1;
    }

    public String getB2() {
        return b2;
    }

    public void setB2(String b2) {
        this.b2 = b2;
    }

    public String getB3() {
        return b3;
    }

    public void setB3(String b3) {
        this.b3 = b3;
    }

    public String getC1() {
        return c1;
    }

    public void setC1(String c1) {
        this.c1 = c1;
    }

    public String getC2() {
        return c2;
    }

    public void setC2(String c2) {
        this.c2 = c2;
    }

    public String getC3() {
        return c3;
    }

    public void setC3(String c3) {
        this.c3 = c3;
    }
}
