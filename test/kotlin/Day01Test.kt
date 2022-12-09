import org.junit.jupiter.api.Test

class Day01Test {

    private val testInput = readTestInput("Day01_test")

    @Test
    fun part1() {
        check(Day01.part1(testInput) == 24000)
    }

    @Test
    fun part2() {
        check(Day01.part2(testInput) == 45000)
    }
}
