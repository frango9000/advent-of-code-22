fun main() {
    fun part1(input: List<String>): Int {
        return input.map { it.chunked(it.length / 2) }
            .map { it[0].filter { x -> it[1].contains(x) }.toCharArray().first() }
            .sumOf { it.code - if (it.isUpperCase()) 38 else 96 }
    }

    fun part2(input: List<String>): Int {
        return input.chunked(3).map {
            it[0].filter { x -> it[1].contains(x) && it[2].contains(x) }.toCharArray().first()
        }.sumOf { it.code - if (it.isUpperCase()) 38 else 96 }
    }

    val testInput = readInput("Day03_test")
    check(part1(testInput) == 157)
    check(part2(testInput) == 70)

    val input = readInput("Day03")
    println(part1(input))
    println(part2(input))
}
