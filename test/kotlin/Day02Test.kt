import org.junit.jupiter.api.Test

class Day02Test {

    private val testInput = readTestInput("Day02_test")

    @Test
    fun part1() {
        check(Day02.part1(testInput) == 15)
    }

    @Test
    fun part2() {
        check(Day02.part2(testInput) == 12)
    }
}
