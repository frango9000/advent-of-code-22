import org.junit.jupiter.api.Test

class Day09Test {

    private val testInput = listOf(
        "R 4",
        "U 4",
        "L 3",
        "D 1",
        "R 4",
        "D 1",
        "L 5",
        "R 2",
    )

    private val testInput2 = listOf(
        "R 5",
        "U 8",
        "L 8",
        "D 3",
        "R 17",
        "D 10",
        "L 25",
        "U 20",
    )

    @Test
    fun part1() {
        check(Day09.part1(testInput) == 13)
    }

    @Test
    fun part2() {
        check(Day09.part2(testInput) == 1)
        check(Day09.part2(testInput2) == 36)
    }
}
