import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day18Test {

    private val testInput0 = listOf(
        "1,1,1",
        "2,1,1",
    )

    private val testInput1 = listOf(
        "2,2,2",
        "1,2,2",
        "3,2,2",
        "2,1,2",
        "2,3,2",
        "2,2,1",
        "2,2,3",
        "2,2,4",
        "2,2,6",
        "1,2,5",
        "3,2,5",
        "2,1,5",
        "2,3,5",
    )

    @Test
    fun part1() {
        assertEquals(10, Day18.part1(testInput0))
        assertEquals(64, Day18.part1(testInput1))
        assertEquals(10, Day18.part1Bitwise(testInput0))
        assertEquals(64, Day18.part1Bitwise(testInput1))
    }

    @Test
    fun part2() {
        assertEquals(10, Day18.part2(testInput0))
        assertEquals(58, Day18.part2(testInput1))
    }
}
