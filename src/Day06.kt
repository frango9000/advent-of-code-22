fun main() {
    fun part1(input: List<String>): Int {
        val (dataStream) = input
        for (i in 3 until dataStream.length) {
            if (setOf(dataStream[i - 3], dataStream[i - 2], dataStream[i - 1], dataStream[i]).size == 4) {
                return i + 1
            }
        }
        return 0
    }

    fun part2(input: List<String>): Int {
        return 0
    }

    val testInput = readInput("Day06_test")
    check(part1(testInput) == 7)
    check(part1(listOf("bvwbjplbgvbhsrlpgdmjqwftvncz")) == 5)
    check(part1(listOf("nppdvjthqldpwncqszvftbrmjlhg")) == 6)
    check(part1(listOf("nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg")) == 10)
    check(part1(listOf("zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw")) == 11)
//    check(part2(testInput) == 0)

    val input = readInput("Day06")
    println(part1(input))
//    println(part2(input))
}
