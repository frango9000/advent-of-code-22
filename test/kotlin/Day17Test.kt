import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day17Test {

    private val testInput = listOf(
        ">>><<><>><<<>><>>><<<>>><<<><<<>><>><<>>",
    )

    @Test
    fun part1() {
        assertEquals(3068, Day17.part1(testInput))
//        val heightMap = listOf(
//            "####...",
//            "##.#..",
//            "....#..",
//            "....#..",
//            "#####..",
//            "......."
//        );
//
//        println(Day17.Rock.E.canMoveLeft(heightMap, 5, 2))
//        println(Day17.Rock.E.canMoveRight(heightMap, 5, 2))
//        println(Day17.Rock.E.canDrop(heightMap, 2, 2))

    }

    @Test
    fun part2() {
        assertEquals(1707, Day17.part2(testInput))
    }
}
