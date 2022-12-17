import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day14Test {

    private val testInput = listOf(
        "498,4 -> 498,6 -> 496,6",
        "503,4 -> 502,4 -> 502,9 -> 494,9",
    )

    @Test
    fun part1() {
        assertEquals(24, Day14.part1(testInput))
    }

//    @Test
//    fun part2() {
//        assertEquals(-1, Day14.part2(testInput))
//    }
}
