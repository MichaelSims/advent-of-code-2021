import kotlin.math.abs
import kotlin.time.ExperimentalTime
import kotlin.time.measureTimedValue

@OptIn(ExperimentalTime::class)
fun main() {

    fun part1(input: List<String>): Int {
        val starts = input.first().split(",").map(String::toInt).sorted()
        var cheapest = Int.MAX_VALUE
        for (end in starts.first()..starts.last()) {
            var cost = 0
            for (start in starts) {
                cost += abs(start - end)
                if (cost > cheapest) {
                    return cheapest
                }
            }
            cheapest = cost
        }
        return cheapest
    }

    fun part2(input: List<String>): Int {
        val starts = input.first().split(",").map(String::toInt).sorted()
        var cheapest = Int.MAX_VALUE
        for (end in starts.first()..starts.last()) {
            var cost = 0
            for (start in starts) {
                for (stepCost in abs(start - end) downTo 0) {
                    cost += stepCost
                    if (cost > cheapest) {
                        return cheapest
                    }
                }
            }
            cheapest = cost
        }
        return cheapest
    }

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
