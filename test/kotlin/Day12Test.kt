import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day12Test {

    private val testInput = listOf(
        "Sabqponm",
        "abcryxxl",
        "accszExk",
        "acctuvwj",
        "abdefghi",
    )

    @Test
    fun part1() {
        assertEquals(31, Day12.part1(testInput))
    }

    @Test
    fun part2() {
        assertEquals(29, Day12.part2(testInput))
    }
}
