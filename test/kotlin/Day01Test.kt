import org.junit.jupiter.api.Test

class Day01Test {

    private val testInput = listOf(
        "1000",
        "2000",
        "3000",
        "",
        "4000",
        "",
        "5000",
        "6000",
        "",
        "7000",
        "8000",
        "9000",
        "",
        "10000",
    )

    @Test
    fun part1() {
        check(Day01.part1(testInput) == 24000)
    }

    @Test
    fun part2() {
        check(Day01.part2(testInput) == 45000)
    }
}
