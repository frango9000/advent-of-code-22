fun main() {
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

    val testInput = readInput("Day09_test")
    check(part1(testInput) == 13)
//    check(part2(testInput) == 45000)

    val input = readInput("Day09")
    println(part1(input))
//    println(part2(input))
}

data class HeadMovement(val direction: Direction, val steps: Int)

enum class Direction { U, R, D, L, }
