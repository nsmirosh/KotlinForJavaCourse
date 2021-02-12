package week4.task2

import board.createGameBoard
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4


class TestGameBoard {

    @Test
    fun balls() {

        val gameBoard = createGameBoard<Char>(2)
        gameBoard[1, 1] = 'a'
        Assert.assertEquals('a', gameBoard[1, 1])
    }


}