fun main() {
    val input = readInput("Day11")
    printTime { println(Day11.part1(input)) }
    printTime { println(Day11.part2(input)) }
}

class Day11 {
    companion object {
        fun part1(input: List<String>): Long {
            val monkeys = input.toMonkeys()
            monkeys.playKeepAway { it.floorDiv(3) }
            return monkeys.getMonkeyBusiness()
        }

        fun part2(input: List<String>): Long {
            val monkeys = input.toMonkeys()
            val commonMod = monkeys.map { it.divideBy }.lcm().toInt()
            monkeys.playKeepAway(10000) { it % commonMod }
            return monkeys.getMonkeyBusiness()
        }
    }
}


data class Monkey(
    val divideBy: Long,
    val trueTo: Int,
    val falseTo: Int,
    val items: MutableList<Long> = mutableListOf(),
    val operation: (Long) -> Long
) {
    var inspections = 0L
}


fun Long.doOperation(charOperator: Char, x: Long) = when (charOperator) {
    '+' -> this + x
    '*' -> this * x
    else -> throw IllegalArgumentException("Not supported")
}


fun List<String>.toMonkeys(): List<Monkey> {
    return this.chunked(7).map {
        val items: MutableList<Long> = it[1].substring(18).split(", ").map { item -> item.toLong() }.toMutableList()
        val (operator, operand) = it[2].substring(23).split(" ")
        val operation: (Long) -> Long = { old: Long ->
            old.doOperation(
                operator.toCharArray().first(), if (operand == "old") old else operand.toLong()
            )
        }
        val divideBy = it[3].substring(21).toLong()
        val trueTo = it[4].substring(29).toInt()
        val falseTo = it[5].substring(30).toInt()

        Monkey(divideBy, trueTo, falseTo, items, operation)
    }
}


fun List<Monkey>.playKeepAway(rounds: Int = 20, escalationPrevention: (Long) -> Long) {
    repeat(rounds) {
        for (monkey in this) {
            for (item in monkey.items) {
                monkey.inspections++
                val worry = escalationPrevention(monkey.operation(item))
                val toMonkey = if (worry isDivisibleBy monkey.divideBy) monkey.trueTo else monkey.falseTo
                this[toMonkey].items.add(worry)
            }
            monkey.items.clear()
        }
    }
}

fun List<Monkey>.getMonkeyBusiness() = this.map { it.inspections }.sortedDescending().take(2).product()
