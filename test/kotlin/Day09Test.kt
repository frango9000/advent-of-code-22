import org.junit.jupiter.api.Test

class Day09Test {

    private val testInput = readTestInput("Day09_test")

    @Test
    fun part1() {
        check(Day09.part1(testInput) == 13)
    }

    @Test
    fun part2() {
        check(Day09.part2(testInput) == 36)
    }
}
