import kotlin.math.max

fun main() {
    fun part1(input: List<String>): Int {
        return partitionByLine(input).map { it ->
            it.map { it.toInt() }.fold(0) { acc, calories -> acc + calories }
        }
            .reduce { acc, curr -> max(acc, curr) }
    }

    fun part2(input: List<String>): Int {
        return input.size
    }

    val testInput = readInput("Day01_test")
    check(part1(readInput("Day01_test")) == 24000)
//    check(part2(testInput) == 2)

    val input = readInput("Day01")
    println(part1(input))
//    println(part2(input))
}
