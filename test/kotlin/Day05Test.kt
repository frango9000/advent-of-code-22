import org.junit.jupiter.api.Test

class Day05Test {

    private val testInput = listOf(
        "    [D]    ",
        "[N] [C]    ",
        "[Z] [M] [P]",
        " 1   2   3 ",
        "",
        "move 1 from 2 to 1",
        "move 3 from 1 to 3",
        "move 2 from 2 to 1",
        "move 1 from 1 to 2"
    )

    @Test
    fun part1() {
        check(Day05.part1(testInput) == "CMZ")
    }

    @Test
    fun part2() {
        check(Day05.part2(testInput) == "MCD")
    }
}
