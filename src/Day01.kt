import kotlin.math.max

fun main() {
    fun part1(input: List<String>): Int {
        return input.partitionOnElement("").map { it ->
            it.sumOf { it.toInt() }
        }
            .reduce { acc, curr -> max(acc, curr) }
    }

    fun part2(input: List<String>): Int {
        return input.partitionOnElement("").map { it ->
            it.sumOf { it.toInt() }
        }
            .sortedDescending().take(3).sum()
    }

    val testInput = readInput("Day01_test")
    check(part1(testInput) == 24000)
    check(part2(testInput) == 45000)

    val input = readInput("Day01")
    println(part1(input))
    println(part2(input))
}
