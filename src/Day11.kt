fun main() {
    val input = readInput("Day11")
    printTime { println(Day11.part1(input)) }
    printTime { println(Day11.part2(input)) }
}

class Day11 {
    companion object {
        fun part1(input: List<String>): Int {
            val monkeys = input.toMonkeys()
            monkeys.playKeepAway()
            return monkeys.map { it.inspections }.sortedDescending().subList(0, 2).reduce { acc, i -> acc * i }
        }

        fun part2(input: List<String>): Int {
            return 0
        }
    }
}


data class Monkey(
    val divideBy: Int,
    val trueTo: Int,
    val falseTo: Int,
    val items: MutableList<Int> = mutableListOf(),
    val operation: (Int) -> Int
) {
    var inspections = 0
}


fun Int.doOperation(charOperator: Char, x: Int) = when (charOperator) {
    '+' -> this + x
    '-' -> this - x
    '/' -> this / x
    '*' -> this * x
    else -> throw IllegalArgumentException("Not supported")
}


fun List<String>.toMonkeys(): List<Monkey> {
    return this.chunked(7).map {
        val items: MutableList<Int> = it[1].substring(18).split(", ").map { item -> item.toInt() }.toMutableList()
        val (operator, operand) = it[2].substring(23).split(" ")
        val operation: (Int) -> Int = { old: Int ->
            old.doOperation(
                operator.toCharArray().first(), if (operand == "old") old else operand.toInt()
            )
        }
        val divideBy = it[3].substring(21).toInt()
        val trueTo = it[4].substring(29).toInt()
        val falseTo = it[5].substring(30).toInt()

        Monkey(divideBy, trueTo, falseTo, items, operation)
    }
}


fun List<Monkey>.playKeepAway(rounds: Int = 20, worryReduction: Int = 3) {
    for (round in 1..rounds) {
        for (monkey in this) {
            for (item in monkey.items) {
                monkey.inspections++
                val worry = monkey.operation(item) / worryReduction
                val toMonkey = if (worry % monkey.divideBy == 0) monkey.trueTo else monkey.falseTo
                this[toMonkey].items.add(worry)
            }
            monkey.items.clear()
        }
    }
}
