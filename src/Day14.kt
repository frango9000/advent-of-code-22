fun main() {
    val input = readInput("Day14")
    printTime { println(Day14.part1(input)) }
    printTime { println(Day14.part2(input)) }
}

class Day14 {
    companion object {
        fun part1(input: List<String>): Int {
            val pointMatrix = input.map { it.split(" -> ").map(::Point) }
            val points = pointMatrix.flatten()
            val minX = points.minOf { it.x }
            val maxX = points.maxOf { it.x }
            val maxY = points.maxOf { it.y }
            points.forEach { it.x -= minX }
            val lines = pointMatrix.map { it.windowed(2).map { (start, end) -> Line(start, end) } }.flatten()

            val map = (0..maxY).map { (minX..maxX).map { "." }.toTypedArray() }.toTypedArray()

            for (line in lines) {
                for (y in (line.start.y towards line.end.y)) {
                    for (x in (line.start.x towards line.end.x)) {
                        map[y][x] = "#"
                    }
                }
            }
            map[0][500 - minX] = "+"
//            map.print()

            val quickSand = mutableListOf(Point(500 - minX, 0))
            val stuckSand = mutableListOf<Point>()
            while (true) {
                try {
                    for (sandIndex in (quickSand.lastIndex downTo 0)) {
                        val sand = quickSand[sandIndex]
                        while (true) {
                            if (ifIsEmpty(map, sand.y + 1, sand.x)) {
                                map[sand.y][sand.x] = "."
                                map[++sand.y][sand.x] = "o"
                            } else if (ifIsEmpty(map, sand.y + 1, sand.x - 1)) {
                                map[sand.y][sand.x] = "."
                                map[++sand.y][--sand.x] = "o"
                            } else if (ifIsEmpty(map, sand.y + 1, sand.x + 1)) {
                                map[sand.y][sand.x] = "."
                                map[++sand.y][++sand.x] = "o"
                            } else {
                                stuckSand.add(sand)
                                break
                            }
                        }
                    }
                } catch (e: ArrayIndexOutOfBoundsException) {
                    break
                }
                if (!quickSand.removeAll(stuckSand)) break
                quickSand.add(Point(500 - minX, 0))
            }
//            map.print()
            return stuckSand.size
        }


        fun part2(input: List<String>): Int {
            val pointMatrix = input.map { it.split(" -> ").map(::Point) }
            val points = pointMatrix.flatten()
            val maxY = points.maxOf { it.y }
            val base = maxY + 2
            val lines = pointMatrix.map { it.windowed(2).map { (start, end) -> Line(start, end) } }.flatten()

            val map = (0..base).map { (0..700).map { "." }.toTypedArray() }.toTypedArray()

            for (line in lines) {
                for (y in (line.start.y towards line.end.y)) {
                    for (x in (line.start.x towards line.end.x)) {
                        map[y][x] = "#"
                    }
                }
            }
            map[base].fill("#")
            map[0][500] = "+"

            val quickSand = mutableListOf(Point(500, 0))
            val stuckSand = mutableListOf<Point>()
            while (true) {
                try {
                    for (sandIndex in (quickSand.lastIndex downTo 0)) {
                        val sand = quickSand[sandIndex]
                        while (true) {
                            if (ifIsEmpty(map, sand.y + 1, sand.x)) {
                                map[sand.y][sand.x] = "."
                                map[++sand.y][sand.x] = "o"
                            } else if (ifIsEmpty(map, sand.y + 1, sand.x - 1)) {
                                map[sand.y][sand.x] = "."
                                map[++sand.y][--sand.x] = "o"
                            } else if (ifIsEmpty(map, sand.y + 1, sand.x + 1)) {
                                map[sand.y][sand.x] = "."
                                map[++sand.y][++sand.x] = "o"
                            } else {
                                stuckSand.add(sand)
                                if (sand.y == 0) {
                                    map[0][sand.x] = "o"
                                    throw ArrayIndexOutOfBoundsException()
                                }
                                break
                            }
                        }
                    }
                    quickSand.removeAll(stuckSand)
                } catch (e: ArrayIndexOutOfBoundsException) {
                    break
                }
                quickSand.add(Point(500, 0))
            }
//            map.print()
            return stuckSand.size
        }


        private fun ifIsEmpty(map: Array<Array<String>>, y: Int = 0, x: Int = 0) = map[y][x] == "."
    }


    data class Point(var x: Int = 0, var y: Int = 0) {
        constructor(input: String) : this() {
            val (x, y) = input.split(',').map { it.toInt() }
            this.x = x
            this.y = y
        }
    }

    data class Line(val start: Point, val end: Point)
}

private fun <T> Array<Array<T>>.print() {
    this.forEach { it.forEach { print(it) }; println() }; println()
}
