import kotlin.math.max
import kotlin.math.min

fun main() {

    fun part1(input: List<String>): Int {
        val numLength = input.first().length
        val counts = IntArray(numLength) { 0 }
        for (number in input) {
            for (i in 0 until numLength) {
                if (number[i] == '1') counts[i]++ else counts[i]--
            }
        }
        val gammaStr = counts
            .map { max(min(it, 1), 0) }
            .joinToString("") { it.toString() }
        val gamma = gammaStr.toInt(2)
        val epsilon = gammaStr.toCharArray().map { if (it == '0') '1' else '0' }.joinToString("").toInt(2)
        return gamma * epsilon
    }

    fun part2(input: List<String>): Int {
        tailrec fun findNumber(input: List<String>, filter: (Int, Int) -> Boolean, position: Int = 0): List<String> {
            val ones = input.count { it[position].digitToInt() == 1 }
            val zeroes = input.size - ones
            val mostCommonOrOne = if (ones >= zeroes) 1 else 0
            val filtered = input.filter { filter(it[position].digitToInt(), mostCommonOrOne) }
            return if (filtered.size == 1) filtered else findNumber(filtered, filter, position + 1)
        }

        val oxygen = findNumber(input, { digit, mostCommonOrOne -> digit == mostCommonOrOne }).single().toInt(2)
        val co2 = findNumber(input, { digit, mostCommonOrOne -> digit != mostCommonOrOne }).single().toInt(2)
        return oxygen * co2
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day03_test")
    check(part1(testInput) == 198)
    check(part2(testInput) == 230)

    val input = readInput("Day03")
    println(part1(input))
    println(part2(input))
}
