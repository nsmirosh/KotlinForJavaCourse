package board

import board.Direction.*

class MyGameBoard<T>(override val width: Int) : GameBoard<T> {

    @JvmField
    val cellMap = mutableMapOf<Cell, T?>()

    init {
        var row = 1
        var column = 1
        repeat (width) {
            repeat(width) {
                cellMap.put(Cell(row, column), null)
                column++
            }
            column = 1
            row++
        }
    }

    override fun getCellOrNull(i: Int, j: Int): Cell? {
        TODO("Not yet implemented")
    }

    override fun getCell(i: Int, j: Int): Cell {
       return cellMap.filterKeys { it == Cell(i, j) }.keys.first()
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

    override fun get(row: Int, column: Int): T? =
         cellMap[Cell(row, column)]

    override fun set(cell: Cell, value: T?) {
        TODO("Not yet implemented")
    }

    override fun set(row: Int, column: Int, value: T?) {
        cellMap[Cell(row, column)] = value
    }

    override fun filter(predicate: (T?) -> Boolean): Collection<Cell> {
        return cellMap.filterValues(predicate).keys
    }

    override fun find(predicate: (T?) -> Boolean): Cell? {
        return cellMap.filterValues(predicate).keys.toList().first()
    }

    override fun any(predicate: (T?) -> Boolean): Boolean {
        return cellMap.values.any(predicate)
    }

    override fun all(predicate: (T?) -> Boolean): Boolean {
        return cellMap.values.all(predicate)
    }
}

class MySquareBoard(override val width: Int) : SquareBoard {

    @JvmField
    val cellSet = mutableSetOf<Cell>()

    init {
        var row = 1
        var column = 1
        repeat (width) {
            repeat(width) {
                cellSet.add(Cell(row, column))
                column++
            }
            column = 1
            row++
        }
    }

    override fun getCellOrNull(i: Int, j: Int): Cell? {
        return cellSet.find { it == Cell(i, j)}
    }

    override fun getCell(i: Int, j: Int): Cell {
        TODO("Not yet implemented")
    }

    override fun getAllCells(): Collection<Cell> {
        return cellSet
    }

    override fun getRow(i: Int, jRange: IntProgression): List<Cell> {

        val filtered = cellSet.filter {it.i == i && it.j in jRange}

        if (jRange.first < jRange.last) {
            return filtered
        }
        return filtered.sortedByDescending { it.j }
    }

    override fun getColumn(iRange: IntProgression, j: Int): List<Cell> {
        TODO("Not yet implemented")
    }

    override fun Cell.getNeighbour(direction: Direction): Cell? {
        TODO("Not yet implemented")
    }
}




fun <T> createGameBoard(width: Int): GameBoard<T>  {

    return MyGameBoard(2)
}



fun createSquareBoard(width: Int): SquareBoard {

    return MySquareBoard(2)
}

