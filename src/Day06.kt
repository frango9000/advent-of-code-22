fun main() {
    val input = readInput("Day06")
    println(Day06.part1(input))
    println(Day06.part2(input))
}


class Day06 {
    companion object {

        fun part1(input: List<String>): Int {
            return input.uniqueSequenceIndex(4)
        }

        fun part2(input: List<String>): Int {
            return input.uniqueSequenceIndex(14)
        }
    }
}

fun List<String>.uniqueSequenceIndex(count: Int): Int {
    return this[0].windowedSequence(count).indexOfFirst { it.toSet().count() == count } + count
}
