fun main() {
    val input = readInput("Day12")
    printTime { println(Day12.part1(input)) }
    printTime { println(Day12.part2(input)) }
}

class Day12 {
    companion object {
        fun part1(input: List<String>): Int {
            val (end, start) = input.toHeightGraph()
            return end.getShortestPathBfs { it == start }
        }


        fun part2(input: List<String>): Int {
            val (end) = input.toHeightGraph()
            return end.getShortestPathBfs { it.height == 0 }
        }
    }

}


class Node(val height: Int, val neighbors: MutableList<Node> = mutableListOf()) {
    var distance = 0

    fun getShortestPathBfs(isDestination: (Node) -> Boolean): Int {
        val queue: ArrayDeque<Node> = ArrayDeque()
        val visited = mutableSetOf(this)
        queue.add(this)

        while (queue.isNotEmpty()) {
            val node = queue.removeFirst()
            if (isDestination(node)) return node.distance
            node.neighbors.filter { it.height >= node.height - 1 }.filter { it !in visited }.forEach {
                it.distance = node.distance + 1
                queue.add(it)
                visited.add(it)
            }
        }
        throw IllegalStateException("Could not find a path to the target node")
    }

    //private fun getShortestPathDfs(from: Node, to: Node, path: List<Node> = listOf()): Long {
    //    if (from == to) {
    //        return 0L
    //    }
    //    val validPaths = from.neighbors.filter { !path.contains(it) }
    //    if (validPaths.isNotEmpty()) {
    //        return validPaths.minOf { 1L + getShortestPathDfs(it, to, listOf(*path.toTypedArray(), from)) }
    //    }
    //    return Int.MAX_VALUE.toLong()
    //}
}

fun List<String>.toHeightGraph(): List<Node> {
    var start = Node(0)
    var end = Node(0)
    val nodes: List<List<Node>> = this.map {
        it.toCharArray().map { height ->
            val newNode: Node
            when (height) {
                'S' -> {
                    newNode = Node(0)
                    start = newNode
                }

                'E' -> {
                    newNode = Node('z' - 'a')
                    end = newNode
                }

                else -> newNode = Node(height - 'a')
            }
            newNode
        }
    }
    nodes.matrixForEachIndexed { i, j, _ ->
        if (i > 0) {
            nodes[i][j].neighbors.add(nodes[i - 1][j])
        }
        if (i < nodes.lastIndex) {
            nodes[i][j].neighbors.add(nodes[i + 1][j])
        }
        if (j > 0) {
            nodes[i][j].neighbors.add(nodes[i][j - 1])
        }
        if (j < nodes[i].lastIndex) {
            nodes[i][j].neighbors.add(nodes[i][j + 1])
        }
    }
    return listOf(end, start)
}
