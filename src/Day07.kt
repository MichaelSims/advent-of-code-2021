import kotlin.math.abs
import kotlin.time.ExperimentalTime
import kotlin.time.measureTime
import kotlin.time.measureTimedValue

@OptIn(ExperimentalTime::class)
fun main() {
    fun getCostToBestDestination(input: List<String>, getFuelCost: (start: Int, end: Int) -> Int): Int {
        val starts = input.first().split(",").map(String::toInt).sorted()
        return (starts.first()..starts.last()).minOf { end -> starts.sumOf { start -> getFuelCost(start, end) } }
    }

    fun triangular(n: Int): Int = (n downTo 0).reduce(Int::plus)

    fun part1(input: List<String>): Int = getCostToBestDestination(input) { start, end -> abs(start - end) }
    fun part2(input: List<String>): Int = getCostToBestDestination(input) { start, end -> triangular(abs(start - end)) }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day07_test")
    check(part1(testInput) == 37)
    check(part2(testInput) == 168)

    val input = readInput("Day07")
    val (part1, part1time) = measureTimedValue { part1(input) }
    println("Part 1 [$part1time]: $part1")
    val (part2, part2time) = measureTimedValue { part2(input) }
    println("Part 2 [$part2time]: $part2")
}
