fun main() {
    val input = readInput("Day10")
    printTime { println(Day10.part1(input)) }
    printTime { println(Day10.part2(input)) }
}

class Day10 {
    companion object {
        fun part1(input: List<String>): Int {
            var signalStrength = 0

            var x = 1
            var cycle = 1

            for (command in input) {
                cycle++
                if ((cycle - 20) % 40 == 0) {
                    signalStrength += cycle * x
                }
                if (command != "noop") {
                    x += command.substring(5).toInt()
                    cycle++
                    if ((cycle - 20) % 40 == 0) {
                        signalStrength += cycle * x
                    }
                }
            }

            return signalStrength
        }

        fun part2(input: List<String>): String {
            var crt = ""

            var x = 1
            var cycle = 1

            for (command in input) {
                val lineOffset = (cycle / 40) * 40
                crt += if (cycle - lineOffset - 1 in (x + -1..x + 1)) "▓" else "░"
                cycle++

                if (command != "noop") {
                    crt += if (cycle - lineOffset - 1 in (x - 1..x + 1)) "▓" else "░"
                    x += command.substring(5).toInt()
                    cycle++
                }
            }

            return crt.chunked(40).joinToString("\n")
        }
    }
}
