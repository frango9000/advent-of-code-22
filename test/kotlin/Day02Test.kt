import org.junit.jupiter.api.Test

class Day02Test {

    private val testInput = listOf(
        "A Y",
        "B X",
        "C Z",
    )

    @Test
    fun part1() {
        check(Day02.part1(testInput) == 15)
    }

    @Test
    fun part2() {
        check(Day02.part2(testInput) == 12)
    }
}
