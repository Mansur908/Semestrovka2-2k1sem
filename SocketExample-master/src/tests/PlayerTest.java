package tests;

import game.Board;
import game.Player;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

class PlayerTest {

    Board board = new Board();
    Player player = new Player(board,"X");

    @Test
    void is_win(){
        player.move("A1");
        player.move("A2");
        player.move("A3");
        Assert.assertTrue(player.is_win());
    }

    @Test
    void is_win_second(){
        Assert.assertFalse(player.is_win());
    }

    @Test
    void move(){
        player.move("A1");
        Assert.assertFalse(player.move("A1"));
    }

}
