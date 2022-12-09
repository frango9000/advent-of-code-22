fun main() {
    val input = readInput("Day04")
    println(Day04.part1(input))
    println(Day04.part2(input))
}

class Day04 {
    companion object {
        fun part1(input: List<String>): Int {
            return input.filter { it: String ->
                val (a1, a2, b1, b2) = it.split(",")
                    .map { it.split("-").map { point -> point.toInt() } }.flatten()
                a1 in b1..b2 || b2 in a1..a2
            }.size
        }

        fun part2(input: List<String>): Int {
            return input.filter { it: String ->
                val (a1, a2, b1, b2) = it.split(",")
                    .map { it.split("-").map { point -> point.toInt() } }.flatten()
                a1 in b1..b2 || b1 in a1..a2

            }.size
        }

    }
}
