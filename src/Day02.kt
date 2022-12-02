fun main() {
    fun checkRound(it: String): Int {
        val (a, b) = it.split(" ").map { it.toCharArray().first() }
        val selectionPoints = b.code - 87
        val score2 = when (selectionPoints == a.code - 64) {
            true -> 1
            false -> when (b) {
                'X' -> when (a) {
                    'C' -> 2
                    else -> 0
                }

                'Y' -> when (a) {
                    'A' -> 2
                    else -> 0
                }

                'Z' -> when (a) {
                    'B' -> 2
                    else -> 0
                }

                else -> 0
            }
        }

        return selectionPoints + (score2 * 3)
    }

    fun part1(input: List<String>): Int {
        return input.sumOf { checkRound(it) }
    }

    fun part2(input: List<String>): Int {
        return 0
    }

    val testInput = readInput("Day02_test")
    check(part1(testInput) == 15)
//    check(part2(testInput) == 45000)

    val input = readInput("Day02")
    println(part1(input))
//    println(part2(input))
}
