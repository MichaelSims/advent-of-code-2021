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
        return input.size
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day03_test")
    check(part1(testInput) == 198)
//    check(part2(testInput) == 900)

    val input = readInput("Day03")
    println(part1(input))
//    println(part2(input))
}
