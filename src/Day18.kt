import javax.swing.JOptionPane

fun main() {
    val input = readInput("Day18")
    printTime { print(Day18.part1(input)) }
    printTime { print(Day18.part1Bitwise(input)) }

    printTime { print(Day18.part2(input)) }
}

class Day18 {

    companion object {
        fun part1(input: List<String>): Int {
            val cubes = input.map { it.split(",").map { it.toInt() } }.map { (x, y, z) -> Position(x, y, z) }
            return cubes.getNumberOfSidesArrayImpl()
        }

        fun part1Bitwise(input: List<String>): Int {
            val cubes = input.map { it.split(",").map { it.toInt() } }.map { (x, y, z) -> Position(x, y, z) }
            return cubes.getNumberOfSides()
        }


        fun part2(input: List<String>): Int {
            val cubes = input.map { it.split(",").map { it.toInt() } }.map { (x, y, z) -> Position(x, y, z) }
            return cubes.withoutBubbles().getNumberOfSides()
        }
    }
}


private fun List<Position>.withoutBubbles(): List<Position> {
    val maxX = maxOf { it.x }
    val maxY = maxOf { it.y }
    val maxZ = maxOf { it.z }

    val occupiedArea = Array(maxX + 1) { Array(maxY + 1) { Array(maxZ + 1) { false } } }

    for ((x, y, z) in this) {
        occupiedArea[x][y][z] = true
    }
//    showSlice(occupiedArea)

    val floodedArea = Array(maxX + 1) { Array(maxY + 1) { Array(maxZ + 1) { false } } }

    val queue = ArrayDeque<Position>()
    queue.add(Position(0, 0, 0))

    while (queue.isNotEmpty()) {
        val (x, y, z) = queue.removeFirst()
        if (!floodedArea[x][y][z] && !occupiedArea[x][y][z]) {
            floodedArea[x][y][z] = true

            if (x > 0) {
                queue.add(Position(x - 1, y, z))
            }
            if (x < maxX) {
                queue.add(Position(x + 1, y, z))
            }
            if (y > 0) {
                queue.add(Position(x, y - 1, z))
            }
            if (y < maxY) {
                queue.add(Position(x, y + 1, z))
            }
            if (z > 0) {
                queue.add(Position(x, y, z - 1))
            }
            if (z < maxZ) {
                queue.add(Position(x, y, z + 1))
            }
        }
    }
//    showSlice(floodedArea)

    return floodedArea.mapIndexed { x, table ->
        table.mapIndexed { y, row ->
            row.mapIndexed { z, flooded ->
                if (flooded) null else Position(
                    x,
                    y,
                    z
                )
            }
        }
    }.flatten().flatten().filterNotNull()
}

data class Position(val x: Int, val y: Int, val z: Int)


fun List<Position>.getNumberOfSides(): Int {
    val maxX = maxOf { it.x }
    val maxY = maxOf { it.y }
    val maxZ = maxOf { it.z }

    val sideX = (0..maxY).map { (0..maxZ).map { 0 }.toMutableList() }
    val sideY = (0..maxZ).map { (0..maxX).map { 0 }.toMutableList() }
    val sideZ = (0..maxX).map { (0..maxY).map { 0 }.toMutableList() }

    for (cube in this) {
        sideX[cube.y][cube.z] = sideX[cube.y][cube.z] xor (1 shl cube.x)
        sideX[cube.y][cube.z] = sideX[cube.y][cube.z] xor (1 shl cube.x + 1)

        sideY[cube.z][cube.x] = sideY[cube.z][cube.x] xor (1 shl cube.y)
        sideY[cube.z][cube.x] = sideY[cube.z][cube.x] xor (1 shl cube.y + 1)

        sideZ[cube.x][cube.y] = sideZ[cube.x][cube.y] xor (1 shl cube.z)
        sideZ[cube.x][cube.y] = sideZ[cube.x][cube.y] xor (1 shl cube.z + 1)
    }

    val numOfSidesX = sideX.sumOf { it.sumOf { Integer.bitCount(it) } }
    val numOfSidesY = sideY.sumOf { it.sumOf { Integer.bitCount(it) } }
    val numOfSidesZ = sideZ.sumOf { it.sumOf { Integer.bitCount(it) } }
    return numOfSidesX + numOfSidesY + numOfSidesZ
}

fun List<Position>.getNumberOfSidesArrayImpl(): Int {
    val maxX = maxOf { it.x }
    val maxY = maxOf { it.y }
    val maxZ = maxOf { it.z }

    val sideX = (0..maxY).map { (0..maxZ).map { (0..maxX + 1).map { false }.toMutableList() } }
    val sideY = (0..maxZ).map { (0..maxX).map { (0..maxY + 1).map { false }.toMutableList() } }
    val sideZ = (0..maxX).map { (0..maxY).map { (0..maxZ + 1).map { false }.toMutableList() } }

    for (cube in this) {
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

fun showSlice(area: Array<Array<Array<Boolean>>>) {
    var x = 0
    var result: Int = -1
    while (true) {
        val slice = area[x].map { it.map { if (it) "_" else "#" }.joinToString("") }.joinToString("\n")
        result = JOptionPane.showConfirmDialog(null, "$x \n\n$slice\n\n Next Slice?")
        when (result) {
            JOptionPane.YES_OPTION -> if (x > 0) x--
            JOptionPane.NO_OPTION -> if (x < area.lastIndex) x++
            JOptionPane.CANCEL_OPTION -> break
        }

    }
}
