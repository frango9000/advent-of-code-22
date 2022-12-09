import org.junit.jupiter.api.Test

class Day07Test {

    private val testInput = readTestInput("Day07_test")

    @Test
    fun part1() {
        check(Day07.part1(testInput) == 95437)
    }

    @Test
    fun part2() {
        check(Day07.part2(testInput) == 24933642)
    }
}
