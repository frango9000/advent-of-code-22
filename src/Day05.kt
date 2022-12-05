fun main() {
    fun part1(input: List<String>): String {
        val (initial, rawActions) = input.partitionOnElement("")

        val numberOfStacks = initial.maxOf { it.length } / 3
        val stacks: List<ArrayDeque<Char>> = (1..numberOfStacks).toList().map { ArrayDeque<Char>(initial.size) }
        val initialReversed = initial.dropLast(1).reversed()
        for (level in initialReversed) {
            for (i in 1..numberOfStacks) {
                val value = level.getOrNull((4 * i) - 3)
                if (value != null && value != ' ') {
                    stacks[i - 1].addLast(value)
                }
            }
        }

        val actions = rawActions.map { it.split("move ", " from ", " to ").subList(1, 4).map { v -> v.toInt() } }
            .map { (size, from, to) -> Move(size, from, to) }


        for (action in actions) {
            for (i in 0 until action.size) {
                stacks[action.to - 1].addLast(stacks[action.from - 1].removeLast())
            }
        }

        return stacks.mapNotNull { it.removeLastOrNull() }.joinToString("")
    }

    fun part2(input: List<String>): String {
        return ""
    }

    val testInput = readInput("Day05_test")
    check(part1(testInput) == "CMZ")
//    check(part2(testInput) == "")

    val input = readInput("Day05")
    println(part1(input))
//    println(part2(input))
}

data class Move(val size: Int, val from: Int, val to: Int)
