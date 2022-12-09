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
            val moves = input.map { it.split(" ") }.map { HeadMovement(Direction.valueOf(it[0]), it[1].toInt()) }
            var headX = 0
            var tailX = 0
            var headY = 0
            var tailY = 0
            val tailPositions = mutableSetOf<String>("0,0")

            for (move in moves) {
                for (step in (1..move.steps)) {
                    when (move.direction) {
                        Direction.R -> headX++
                        Direction.U -> headY++
                        Direction.D -> headY--
                        Direction.L -> headX--
                    }
                    if (tailX in (headX - 1..headX + 1) && tailY in (headY - 1..headY + 1)) continue
                    if (headX == tailX || headY == tailY) {
                        when (move.direction) {
                            Direction.R -> tailX++
                            Direction.U -> tailY++
                            Direction.D -> tailY--
                            Direction.L -> tailX--
                        }
                    } else {
                        if (headX > tailX) tailX++ else tailX--
                        if (headY > tailY) tailY++ else tailY--
                    }
                    tailPositions.add("$tailX,$tailY")
                }
            }

            return tailPositions.size
        }

        fun part2(input: List<String>): Int {
            return 0
        }
    }
}
