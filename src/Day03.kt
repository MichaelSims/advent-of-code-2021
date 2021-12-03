import kotlin.math.max
import kotlin.math.min

fun main() {

    fun part1(input: List<String>): Int {
        val bitLength = input.first().length
        val counts = IntArray(bitLength) { 0 }
        for (byte in input.map { it.toBigInteger(2) }) {
            for (i in 0 until bitLength) {
                val mask = (1 shl i).toBigInteger()
                if (byte and mask == mask) counts[i]++ else counts[i]--
            }
        }
        val gamma = counts
            .reversed()
            .map { max(min(it, 1), 0) }
            .joinToString("") { it.toString() }
            .toBigInteger(2)
        val epsilon = gamma.inv() and ((1 shl bitLength) - 1).toBigInteger()

        return (gamma * epsilon).toInt()
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
