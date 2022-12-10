fun main() {
    val input = readInput("Day10")

    printTime { println(Day10.part1(input)) }
//    println(Day10.part2(input))
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
                    cycle++
                    x += command.substring(5).toInt()
                    if ((cycle - 20) % 40 == 0) {
                        signalStrength += cycle * x
                    }
                }
            }

            return signalStrength
        }

        fun part2(input: List<String>): Int {
            return input.size
        }
    }
}
