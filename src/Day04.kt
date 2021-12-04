typealias Row = MutableList<String?>
typealias Board = MutableList<Row>
const val numColumns = 5

fun main() {

    fun parseInput(input: List<String>): Pair<List<String>, List<Board>> {
        val lines = input.iterator()
        val numbers = lines.next().split(",")

        val boardList = mutableListOf<Board>()
        while (lines.hasNext()) {
            lines.next() // Skip blank line
            boardList.add(mutableListOf<Row>().apply {
                repeat(numColumns) {
                    add(lines.next().trim().split("\\s+".toRegex()).toMutableList())
                }
            })
        }
        return numbers to boardList
    }

    fun checkVictory(board: Board): Int? {
        fun rowWins(row: Row) = row.all { it == null }
        fun colWins(colIndex: Int) = board.all { it[colIndex] == null }
        return if (board.any(::rowWins) || (0 until numColumns).any(::colWins)) {
            board.flatten().filterNotNull().sumOf(String::toInt)
        } else {
            null
        }
    }

    fun getWinningScores(input: List<String>): List<Int> {
        val (numbers, boardList) = parseInput(input)

        val remainingBoards = boardList.toMutableList()
        val winningScores = mutableListOf<Int>()
        for (number in numbers) {
            val boardIterator = remainingBoards.iterator()
            while (boardIterator.hasNext()) {
                val board = boardIterator.next()
                for (row in board) {
                    for ((i, boardNum) in row.withIndex()) {
                        if (boardNum == number) {
                            row[i] = null
                            val victorySum = checkVictory(board)
                            if (victorySum != null) {
                                winningScores.add(victorySum * number.toInt())
                                boardIterator.remove()
                            }
                        }
                    }
                }
            }
        }
        return winningScores
    }

    fun part1(input: List<String>): Int = getWinningScores(input).first()
    fun part2(input: List<String>): Int = getWinningScores(input).last()

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day04_test")
    check(part1(testInput) == 4512)
    check(part2(testInput) == 1924)

    val input = readInput("Day04")
    println(part1(input))
    println(part2(input))
}
