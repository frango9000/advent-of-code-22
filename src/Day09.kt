fun main() {
    val input = readInput("Day09")
    println(Day09.part1(input))
    println(Day09.part2(input))
}

data class HeadMovement(val direction: Direction, val steps: Int)

enum class Direction { U, R, D, L, }

class Day09 {
    companion object {
        fun part1(input: List<String>): Int {
            return getTailPositions(input, 2)
        }

        fun part2(input: List<String>): Int {
            return getTailPositions(input, 10)
        }

        fun getTailPositions(input: List<String>, numOfKnots: Int): Int {
            val moves = input.map { it.split(" ") }.map { HeadMovement(Direction.valueOf(it[0]), it[1].toInt()) }
            val knots = (1..numOfKnots.coerceAtLeast(2)).toList().map { Coordinate(0, 0) }
            val tailPositions = mutableSetOf<String>("0,0")

            val head = knots.first()
            val tail = knots.last()
            for (move in moves) {
                for (step in (1..move.steps)) {
                    when (move.direction) {
                        Direction.R -> head.x++
                        Direction.U -> head.y--
                        Direction.D -> head.y++
                        Direction.L -> head.x--
                    }
                    for (index in (1 until knots.size)) {
                        val lead = knots[index - 1]
                        val current = knots[index]
                        if (current.x in (lead.x - 1..lead.x + 1) && current.y in (lead.y - 1..lead.y + 1)) break
                        if (lead.x > current.x) current.x++ else if (lead.x < current.x) current.x--
                        if (lead.y > current.y) current.y++ else if (lead.y < current.y) current.y--
                    }
                    tailPositions.add("${tail.x},${tail.y}")
                }
            }
            return tailPositions.size
        }
    }
}
