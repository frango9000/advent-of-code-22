fun main() {
    fun part1(input: List<String>): Int {
        return input.filter { it: String ->
            val (a1, a2, b1, b2) = it.split(",")
                .map { it.split("-").map { point -> point.toInt() } }.flatten()
            a1 >= b1 && a2 <= b2 || b1 >= a1 && b2 <= a2
        }.size
    }

    fun part2(input: List<String>): Int {
        return 0
    }

    val testInput = readInput("Day04_test")
    check(part1(testInput) == 2)
//    check(part2(testInput) == 0)

    val input = readInput("Day04")
    println(part1(input))
//    println(part2(input))
}
