import org.junit.jupiter.api.Test

class Day08Test {

    private val testInput = readTestInput("Day08_test")

    @Test
    fun part1() {
        check(Day08.part1(testInput) == 21)
    }

    @Test
    fun part2() {
        check(Day08.part2(testInput) == 8)
    }
}
