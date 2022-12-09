import org.junit.jupiter.api.Test

class Day06Test {

    private val testInput = readTestInput("Day06_test")

    @Test
    fun part1() {
        check(Day06.part1(testInput) == 7)
        check(Day06.part1(listOf("bvwbjplbgvbhsrlpgdmjqwftvncz")) == 5)
        check(Day06.part1(listOf("nppdvjthqldpwncqszvftbrmjlhg")) == 6)
        check(Day06.part1(listOf("nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg")) == 10)
        check(Day06.part1(listOf("zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw")) == 11)
    }

    @Test
    fun part2() {
        check(Day06.part2(testInput) == 19)
        check(Day06.part2(listOf("bvwbjplbgvbhsrlpgdmjqwftvncz")) == 23)
        check(Day06.part2(listOf("nppdvjthqldpwncqszvftbrmjlhg")) == 23)
        check(Day06.part2(listOf("nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg")) == 29)
        check(Day06.part2(listOf("zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw")) == 26)
    }
}
