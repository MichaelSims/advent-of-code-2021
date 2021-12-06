fun main() {
    fun fishForDays(input: List<String>, numDays: Int): Long {
        var fishGen = MutableList(9) { 0L }
        for (fish in input.first().split(",").map(String::toInt)) {
            fishGen[fish]++
        }
        repeat(numDays) {
            val newFishGen = MutableList(9) { 0L }
            newFishGen[8] = fishGen[0]
            newFishGen[6] = fishGen[0]
            for (timerVal in 1 until 9) {
                newFishGen[timerVal - 1] += fishGen[timerVal]
            }
            fishGen = newFishGen
        }
        return fishGen.sum()
    }

    fun part1(input: List<String>): Long = fishForDays(input, 80)
    fun part2(input: List<String>): Long = fishForDays(input, 256)

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day06_test")
    check(part1(testInput) == 5934L)
    check(part2(testInput) == 26984457539L)

    val input = readInput("Day06")
    println(part1(input))
    println(part2(input))
}
