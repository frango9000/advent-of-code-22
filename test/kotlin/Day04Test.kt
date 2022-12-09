import org.junit.jupiter.api.Test

class Day04Test {

    private val testInput = readTestInput("Day04_test")

    @Test
    fun part1() {
        check(Day04.part1(testInput) == 2)
    }

    @Test
    fun part2() {
        check(Day04.part2(testInput) == 4)
    }
}
