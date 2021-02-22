package board

import board.Direction.*

class MyGameBoard<T>(override val width: Int) : MySquareBoard(width), GameBoard<T> {

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

    override fun getCell(i: Int, j: Int): Cell {
       return cellMap.filterKeys { it == Cell(i, j) }.keys.first()
    }

    override fun get(cell: Cell): T? {
        return cellMap[cell]
    }

    override fun set(cell: Cell, value: T?) {
        cellMap[cell] = value
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

open class MySquareBoard(override val width: Int) : SquareBoard {

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
        return cellSet.find { it == Cell(i, j)}!!
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
        val filtered =  cellSet.filter {it.j == j && it.i in iRange}


        if (iRange.first < iRange.last) {
            return filtered
        }
        return filtered.sortedByDescending { it.i }
    }

    override fun Cell.getNeighbour(direction: Direction): Cell? {
        return when (direction) {
            UP -> getCellOrNull(i - 1, j)
            DOWN -> getCellOrNull(i + 1, j)
            LEFT -> getCellOrNull(i, j - 1)
            RIGHT -> getCellOrNull(i, j + 1)
        }
    }
}

fun <T> createGameBoard(width: Int): GameBoard<T>  {
    return MyGameBoard(width)
}

fun createSquareBoard(width: Int): SquareBoard {
    return MySquareBoard(width)
}

