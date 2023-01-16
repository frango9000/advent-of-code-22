import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day17Test {

    private val testInput = listOf(
        ">>><<><>><<<>><>>><<<>>><<<><<<>><>><<>>",
    )

    @Test
    fun part1() {
        assertEquals(3068, Day17.part1(testInput))
    }

    @Test
    fun part2() {
        assertEquals(1514285714288, Day17.part2(testInput))
    }
}
