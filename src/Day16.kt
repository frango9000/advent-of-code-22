import kotlin.math.max

fun main() {
    val input = readInput("Day16")
    printTime { print(Day16.part1(input)) }
//    printTime { print(Day16.part2(input)) }
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

            val valvesMap: Map<Valve, Map<Valve, Int>> =
                valves.filter { origin -> origin.rate > 0 || origin.name == "AA" }.associateWith { origin ->
                    valves.filter { it !== origin && it.rate > 0 }.associateWith { target -> bfs(origin, target) }
                }

            val valvesIndexes: Map<Valve, Int> =
                valvesMap.keys.withIndex().associateWith { it.index }.mapKeys { it.key.value }

            return dfs(valves.find { it.name == "AA" }!!, valvesMap, valvesIndexes)
        }


        private fun bfs(from: Valve, target: Valve): Int {
            val queue = ArrayDeque<Pair<Valve, Int>>()
            queue.add(from to 0)
            val visited = mutableSetOf<Valve>()
            while (queue.isNotEmpty()) {
                val next = queue.removeFirst()
                if (visited.contains(next.first)) continue
                visited.add(next.first)
                if (target == next.first) {
                    return next.second
                }
                queue.addAll(next.first.tunnels.map { it to next.second + 1 })
            }
            error("No Path Found")
        }


        private fun dfs(
            start: Valve,
            valvesMap: Map<Valve, Map<Valve, Int>>,
            valvesIndexes: Map<Valve, Int>,
            timeLeft: Int = 30,
            valvesState: Int = 0
        ): Int {
            var maxPressure = 0
            for ((neighbor, distance) in valvesMap[start]!!.entries) {
                val bit = 1 shl valvesIndexes[neighbor]!!
                if (valvesState and bit > 0)
                    continue
                val newTimeLeft = timeLeft - distance - 1
                if (newTimeLeft <= 0)//try 1
                    continue
                maxPressure = max(
                    maxPressure,
                    dfs(
                        neighbor,
                        valvesMap,
                        valvesIndexes,
                        newTimeLeft,
                        valvesState or bit
                    ) + neighbor.rate * newTimeLeft
                )
            }
            return maxPressure
        }

        private fun dfs_v1(path: List<Valve>, valvesMap: Map<Valve, Map<Valve, Int>>, timeLeft: Int = 30): Int {
            if (timeLeft <= 1) {
                return 0
            }
            val current = path.last()
            val timeInCurrentCave = if (current.rate > 0) 1 else 0
            val valveTotalPressure = current.rate * (timeLeft - timeInCurrentCave)
            val openValves = valvesMap[current]!!.filter { !path.contains(it.key) }
            if (openValves.isEmpty()) {
                return valveTotalPressure
            }
            return valveTotalPressure + (openValves.maxOfOrNull { (target, distance) ->
                dfs_v1(listOf(*path.toTypedArray(), target), valvesMap, timeLeft - (timeInCurrentCave + distance))
            } ?: 0)
        }

        fun part2(input: List<String>): Int {
            return 0
        }
    }

    class Valve(val name: String, val rate: Int, val tunnelsTo: Set<String>) {
        val tunnels: MutableSet<Valve> = mutableSetOf()

        override fun toString(): String {
            return "Valve(name='$name', rate=$rate)"
        }

        override fun equals(other: Any?): Boolean {

            return name == (other as Valve).name
        }

        override fun hashCode(): Int {
            return name.hashCode()
        }
    }
}
