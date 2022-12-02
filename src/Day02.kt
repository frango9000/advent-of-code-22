fun main() {
    fun checkGuessRound(it: String): Int {
        val (a, b) = it.split(" ").map { it.toCharArray().first() }
        val selectionPoints = b.code - 87
        val score = when (selectionPoints == a.code - 64) {
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

        return selectionPoints + (score * 3)
    }

    fun part1(input: List<String>): Int {
        return input.sumOf { checkGuessRound(it) }
    }

    fun checkExpectedRound(it: String): Int {
        val (a, b) = it.split(" ").map { it.toCharArray().first() }
        val score = (b.code - 88)
        val selectionPoints = when (score) {
            0 -> when (a) {
                'A' -> 3
                'B' -> 1
                else -> 2
            }

            1 -> when (a) {
                'A' -> 1
                'B' -> 2
                else -> 3
            }

            2 -> when (a) {
                'A' -> 2
                'B' -> 3
                else -> 1
            }

            else -> 0
        }


        return selectionPoints + (score * 3)
    }

    fun part2(input: List<String>): Int {
        return input.sumOf { checkExpectedRound(it) }
    }

    val testInput = readInput("Day02_test")
    check(part1(testInput) == 15)
    check(part2(testInput) == 12)

    val input = readInput("Day02")
    println(part1(input))
    println(part2(input))
}
