import org.junit.jupiter.api.Test

class Day05Test {

    private val testInput = readTestInput("Day05_test")

    @Test
    fun part1() {
        check(Day05.part1(testInput) == "CMZ")
    }

    @Test
    fun part2() {
        check(Day05.part2(testInput) == "MCD")
    }
}
