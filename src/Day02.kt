fun main() {

    fun parseInput(input: List<String>) = input.map { line ->
        val (first, second) = line.split(" ")
        first to second.toInt()
    }

    fun part1(input: List<String>): Int {
        var position = 0
        var depth = 0
        for ((command, units) in parseInput(input)) {
            when (command) {
                "forward" -> position += units
                "down" -> depth += units
                "up" -> depth -= units
            }
        }
        return position * depth
    }

    fun part2(input: List<String>): Int {
        var position = 0
        var depth = 0
        var aim = 0
        for ((command, units) in parseInput(input)) {
            when (command) {
                "forward" -> {
                    position += units
                    depth += aim * units
                }
                "down" -> aim += units
                "up" -> aim -= units
            }
        }
        return position * depth
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day02_test")
    check(part1(testInput) == 150)
    check(part2(testInput) == 900)

    val input = readInput("Day02")
    println(part1(input))
    println(part2(input))
}
