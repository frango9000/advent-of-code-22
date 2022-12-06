fun main() {

    fun part1(input: List<String>): Int {
        return uniqueSequenceIndex(input, 4)
    }

    fun part2(input: List<String>): Int {
        return uniqueSequenceIndex(input, 14)
    }

    val testInput = readInput("Day06_test")
    check(part1(testInput) == 7)
    check(part1(listOf("bvwbjplbgvbhsrlpgdmjqwftvncz")) == 5)
    check(part1(listOf("nppdvjthqldpwncqszvftbrmjlhg")) == 6)
    check(part1(listOf("nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg")) == 10)
    check(part1(listOf("zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw")) == 11)
    check(part2(testInput) == 19)
    check(part2(listOf("bvwbjplbgvbhsrlpgdmjqwftvncz")) == 23)
    check(part2(listOf("nppdvjthqldpwncqszvftbrmjlhg")) == 23)
    check(part2(listOf("nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg")) == 29)
    check(part2(listOf("zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw")) == 26)

    val input = readInput("Day06")
    println(part1(input))
    println(part2(input))
}

fun uniqueSequenceIndex(input: List<String>, count: Int): Int {
    val (dataStream) = input
    for (i in count - 1 until dataStream.length) {
        if ((i - count + 1..i).toList().map { dataStream[it] }.toSet().size == count) {
            return i + 1
        }
    }
    return 0
}
