fun main() {
    val input = readInput("Day16")
    printTime { print(Day16.part1(input)) }
    printTime { print(Day16.part2(input)) }
}

class Day16 {
    companion object {
        fun part1(input: List<String>): Int {
            val valves = input.map {
                it.split(
                    "Valve ", " has flow rate=", "; tunnel leads to valve ", "; tunnels lead to valves "
                ).drop(1)
            }.map { Valve(it[0], it[1].toInt(), it[2].split(", ").toSet()) }
            for (valve in valves) {
                valve.tunnels.addAll(valve.tunnelsTo.map { to -> valves.find { it.name == to }!! })
            }

            return getMaxPressureAfter(mutableListOf(valves.first() to false), 30)
        }

        private fun getMaxPressureAfter(
            path: MutableList<Pair<Valve, Boolean>>,
            steps: Int,
            pressure: Int = 0
        ): Int {
            if (steps <= 1) {
                return 0
            }
            val current = path.last().first
            val isOpen = current.rate > 0 && path.any { it.second && it.first == current }
            val posibleSteps = mutableListOf<Int>()
            posibleSteps.addAll(current.tunnels.map {
                getMaxPressureAfter(
                    path.toMutableList().apply { add(it to false) }, steps - 1, pressure
                )
            })
            if (!isOpen) {
                posibleSteps.addAll(current.tunnels.map {
                    getMaxPressureAfter(
                        path.toMutableList().apply { add(current to true); add(it to false) },
                        steps - 2,
                        pressure + ((steps - 2) * current.rate)
                    )
                })
            }
            return posibleSteps.max()
        }

        fun part2(input: List<String>): Int {
            return 0
        }
    }

    class Valve(val name: String, val rate: Int, val tunnelsTo: Set<String>) {
        val tunnels: MutableSet<Valve> = mutableSetOf()

        override fun toString(): String {
            return "Valve(name='$name')"
        }


    }

}
