import kotlin.math.max

fun main() {
    val input = readInput("Day01")
    println(Day01.part1(input))
    println(Day01.part2(input))
}

class Day01 {
    companion object {
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
    }
}
