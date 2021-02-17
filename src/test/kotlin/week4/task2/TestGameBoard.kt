package week4.task2

import board.createGameBoard
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4


class TestGameBoard {

    @Test
    fun settingCharacterWorks() {

        val gameBoard = createGameBoard<Char>(2)
        gameBoard[1, 1] = 'a'
        Assert.assertEquals('a', gameBoard[1, 1])
    }


    @Test
    fun test02Filter() {
        val gameBoard = createGameBoard<Char>(2)
        gameBoard[1, 1] = 'a'
        gameBoard[1, 2] = 'b'
        val cells = gameBoard.filter { it == 'a' }
        Assert.assertEquals(1, cells.size)
        val cell = cells.first()
        Assert.assertEquals(1, cell.i)
        Assert.assertEquals(1, cell.j)
    }

    @Test
    fun test03All() {
        val gameBoard = createGameBoard<Char>(2)
        gameBoard[1, 1] = 'a'
        gameBoard[1, 2] = 'a'
        Assert.assertFalse(gameBoard.all { it == 'a' })
        gameBoard[2, 1] = 'a'
        gameBoard[2, 2] = 'a'
        Assert.assertTrue(gameBoard.all { it == 'a' })
    }


}