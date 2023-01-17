fun main() {
    val input = readInput("Day18")
    printTime { print(Day18.part1(input)) }
//    printTime { print(Day18.part2(input)) }
}

class Day18 {

    companion object {
        fun part1(input: List<String>): Int {
            val cubes = input.map { it.split(",").map { it.toInt() } }.map { (x, y, z) -> Position(x, y, z) }
            val maxX = cubes.maxOf { it.x }
            val maxY = cubes.maxOf { it.y }
            val maxZ = cubes.maxOf { it.z }

            val sideX = (0..maxY).map { (0..maxZ).map { (0..maxX + 1).map { false }.toMutableList() } }
            val sideY = (0..maxZ).map { (0..maxX).map { (0..maxY + 1).map { false }.toMutableList() } }
            val sideZ = (0..maxX).map { (0..maxY).map { (0..maxZ + 1).map { false }.toMutableList() } }

            for (cube in cubes) {
                sideX[cube.y][cube.z][cube.x] = !sideX[cube.y][cube.z][cube.x]
                sideX[cube.y][cube.z][cube.x + 1] = !sideX[cube.y][cube.z][cube.x + 1]

                sideY[cube.z][cube.x][cube.y] = !sideY[cube.z][cube.x][cube.y]
                sideY[cube.z][cube.x][cube.y + 1] = !sideY[cube.z][cube.x][cube.y + 1]

                sideZ[cube.x][cube.y][cube.z] = !sideZ[cube.x][cube.y][cube.z]
                sideZ[cube.x][cube.y][cube.z + 1] = !sideZ[cube.x][cube.y][cube.z + 1]
            }

            val numOfSidesX = sideX.sumOf { it.sumOf { it.filter { it }.count() } }
            val numOfSidesY = sideY.sumOf { it.sumOf { it.filter { it }.count() } }
            val numOfSidesZ = sideZ.sumOf { it.sumOf { it.filter { it }.count() } }

            return numOfSidesX + numOfSidesY + numOfSidesZ
        }


        fun part2(input: List<String>): Int {
            return input.size
        }
    }
}

data class Position(val x: Int, val y: Int, val z: Int)
