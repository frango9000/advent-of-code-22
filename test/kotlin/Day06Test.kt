import org.junit.jupiter.api.Test

class Day06Test {

    private val testInput = listOf("mjqjpqmgbljsphdztnvjfqwrcgsmlb")
    private val testInput2 = listOf("bvwbjplbgvbhsrlpgdmjqwftvncz")
    private val testInput3 = listOf("nppdvjthqldpwncqszvftbrmjlhg")
    private val testInput4 = listOf("nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg")
    private val testInput5 = listOf("zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw")

    @Test
    fun part1() {
        check(Day06.part1(testInput) == 7)
        check(Day06.part1(testInput2) == 5)
        check(Day06.part1(testInput3) == 6)
        check(Day06.part1(testInput4) == 10)
        check(Day06.part1(testInput5) == 11)
    }

    @Test
    fun part2() {
        check(Day06.part2(testInput) == 19)
        check(Day06.part2(testInput2) == 23)
        check(Day06.part2(testInput3) == 23)
        check(Day06.part2(testInput4) == 29)
        check(Day06.part2(testInput5) == 26)
    }
}
