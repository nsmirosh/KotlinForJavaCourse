package board

import board.Direction.*

fun createSquareBoard(width: Int): SquareBoard {

}


class MyGameBoard<T>(width: Int) : GameBoard<T> {


    override val width: Int
        get() = TODO("Not yet implemented")

    override fun getCellOrNull(i: Int, j: Int): Cell? {
        TODO("Not yet implemented")
    }

    override fun getCell(i: Int, j: Int): Cell {
        TODO("Not yet implemented")
    }

    override fun getAllCells(): Collection<Cell> {
        TODO("Not yet implemented")
    }

    override fun getRow(i: Int, jRange: IntProgression): List<Cell> {
        TODO("Not yet implemented")
    }

    override fun getColumn(iRange: IntProgression, j: Int): List<Cell> {
        TODO("Not yet implemented")
    }

    override fun Cell.getNeighbour(direction: Direction): Cell? {
        TODO("Not yet implemented")
    }

    override fun get(cell: Cell): T? {
        TODO("Not yet implemented")
    }

    override fun get(row: Int, column: Int): T? {
        TODO("Not yet implemented")
    }

    override fun set(cell: Cell, value: T?) {
        TODO("Not yet implemented")
    }

    override fun set(row: Int, column: Int, value: T?) {
        TODO("Not yet implemented")
    }

    override fun filter(predicate: (T?) -> Boolean): Collection<Cell> {
        TODO("Not yet implemented")
    }

    override fun find(predicate: (T?) -> Boolean): Cell? {
        TODO("Not yet implemented")
    }

    override fun any(predicate: (T?) -> Boolean): Boolean {
        TODO("Not yet implemented")
    }

    override fun all(predicate: (T?) -> Boolean): Boolean {
        TODO("Not yet implemented")
    }
}



fun <T> createGameBoard(width: Int): GameBoard<T>  {

    return MyGameBoard()
}
