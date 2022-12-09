import org.junit.jupiter.api.Test

class Day04Test {

    private val testInput = listOf(
        "2-4,6-8",
        "2-3,4-5",
        "5-7,7-9",
        "2-8,3-7",
        "6-6,4-6",
        "2-6,4-8",
    )

    @Test
    fun part1() {
        check(Day04.part1(testInput) == 2)
    }

    @Test
    fun part2() {
        check(Day04.part2(testInput) == 4)
    }
}
