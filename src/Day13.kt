import kotlin.math.max

fun main() {
    val input = readInput("Day13")
    printTime { println(Day13.part1(input)) }
//    println(Day13.part2(input))
}

class Day13 {
    companion object {
        fun part1(input: List<String>): Int {
            val packetPairs = input.partitionOnElement("").map { it.map { parsePacket(it.substring(1, it.lastIndex)) } }
            val packetComparisons: List<Int> = packetPairs.map { (first, second) -> comparePackets(first, second) }
            return packetComparisons.withIndex().filter { it.value <= 0 }.sumOf { it.index + 1 }
        }

        private fun comparePackets(first: Any, second: Any): Int {
            if (first is List<*> && second is List<*>) {
                for (index in 0..max(first.lastIndex, second.lastIndex)) {
                    if (first.lastIndex < index) return -1
                    if (second.lastIndex < index) return 1
                    val packetComparison = comparePackets(first[index]!!, second[index]!!)
                    if (packetComparison == 0) continue
                    else return packetComparison
                }
                return 0
            }

            if (first is List<*>) return comparePackets(first, listOf(second))
            if (second is List<*>) return comparePackets(listOf(first), second)
            return (first as Int) - (second as Int)
        }

        private fun parsePacket(substring: String): List<Any> {
            val root = mutableListOf<Any>()
            val stack: ArrayDeque<MutableList<Any>> = ArrayDeque()
            stack.add(root)
            var index = 0

            while (index < substring.length) {
                when (substring[index]) {
                    '[' -> {
                        val newStack = mutableListOf<Any>()
                        stack.last().add(newStack)
                        stack.addLast(newStack)
                        index++
                    }

                    ']' -> {
                        stack.removeLast()
                        index++
                    }

                    ',' -> index++
                    else -> {
                        val fromNumber = substring.substring(index)
                        var indexOfNextClose = fromNumber.indexOfFirst { it == ']' || it == ',' }
                        if (indexOfNextClose < 0) indexOfNextClose = fromNumber.length
                        val number = fromNumber.substring(0, indexOfNextClose).toInt()
                        stack.last().add(number)
                        index += indexOfNextClose
                    }
                }
            }
            return root
        }

        fun part2(input: List<String>): Int {
            return 0
        }
    }
}
