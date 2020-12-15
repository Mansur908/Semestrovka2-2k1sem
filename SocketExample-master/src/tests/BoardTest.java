package tests;

import game.Board;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class BoardTest {

    @Test
    void getList() {
        Board board = new Board();
        List<String> list = new ArrayList<>();
        list.add("A1");
        list.add("A2");
        list.add("A3");
        list.add("B1");
        list.add("B2");
        list.add("B3");
        list.add("C1");
        list.add("C2");
        list.add("C3");
        Assert.assertEquals(list, board.getList());
    }

    @Test
    void setA1(){
        Board board = new Board();
        board.setA1("O");
        Assert.assertEquals(board.getA1(), "O");
    }
}