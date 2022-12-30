fun main() {
    val input = readInput("Day16g")
    printTime { print(Day16.part1(input)) } //x < 2014 < 2153
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

            val valvesMap =
                valves.filterIndexed { index, origin -> origin.rate > 0 || index == 0 }.associateWith { origin ->
                    valves.filter { it.rate > 0 }.associateWith { target -> bfs(origin, target) }
                }

//            println(valvesMap)
            return dfs(valves.subList(0, 1), valvesMap)
        }


        private fun bfs(from: Valve, target: Valve): Int {
            val queue = ArrayDeque<Pair<Valve, Int>>()
            queue.add(from to 0)
            while (queue.isNotEmpty()) {
                val next = queue.removeFirst()
                if (target == next.first) {
                    return next.second
                }
                queue.addAll(next.first.tunnels.filter { queue.none { pair -> pair.first == it } }
                    .map { it to next.second + 1 })
            }
            error("No Path Found")
        }


        private fun dfs(path: List<Valve>, valvesMap: Map<Valve, Map<Valve, Int>>, timeLeft: Int = 30): Int {
            if (timeLeft <= 1) {
                return 0
            }
            val current = path.last()
            val timeInCurrentCave = if (current.rate > 0)  1 else 0
            val valveTotalPressure = current.rate * (timeLeft - timeInCurrentCave)
            val openValves = valvesMap[current]!!.filter { !path.contains(it.key) }
            if (openValves.isEmpty()){
                return valveTotalPressure
            }
            return valveTotalPressure + (openValves.maxOfOrNull { (target, distance) ->
                dfs(listOf(*path.toTypedArray(), target), valvesMap, timeLeft - (timeInCurrentCave + distance))
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
