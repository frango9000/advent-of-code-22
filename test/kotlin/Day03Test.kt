import org.junit.jupiter.api.Test

class Day03Test {

    private val testInput = readTestInput("Day03_test")

    @Test
    fun part1() {
        check(Day03.part1(testInput) == 157)
    }

    @Test
    fun part2() {
        check(Day03.part2(testInput) == 70)
    }
}
