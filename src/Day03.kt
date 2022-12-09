fun main() {
    val input = readInput("Day03")
    println(Day03.part1(input))
    println(Day03.part2(input))
}

class Day03 {
    companion object {
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
    }
}
