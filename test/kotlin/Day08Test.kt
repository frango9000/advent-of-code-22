import org.junit.jupiter.api.Test

class Day08Test {

    private val testInput = listOf(
        "30373",
        "25512",
        "65332",
        "33549",
        "35390",
    )

    @Test
    fun part1() {
        check(Day08.part1(testInput) == 21)
    }

    @Test
    fun part2() {
        check(Day08.part2(testInput) == 8)
    }
}
