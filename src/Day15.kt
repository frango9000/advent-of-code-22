import kotlin.math.absoluteValue

fun main() {
    val input = readInput("Day15")
    printTime { print(Day15.part1(input, 2_000_000)) }
//    printTime { println(Day15.part2(input, 4_000_000)) }
}

class Day15 {
    companion object {
        fun part1(input: List<String>, targetY: Int): Int {
            val sensorsAndBeacons = input.map {
                it.substring(10).split(": closest beacon is at ").map { it.split(", ").map { it.substring(2).toInt() } }
                    .map { (x, y) -> Point(x, y) }
            }

            val offset = sensorsAndBeacons.maxOf { it[0].rectilinearDistanceTo(it[1]) } * 2
            val beaconMaxX = sensorsAndBeacons.maxOf { it[1].x }
            val blockedXsInTargetY = BooleanArray(beaconMaxX + offset)

            for ((sensor, beacon) in sensorsAndBeacons) {
                val sensorCoverage = sensor.rectilinearDistanceTo(beacon)
                val yDistanceToYTarget = (targetY - sensor.y).absoluteValue
                val yOverlapBy = sensorCoverage - yDistanceToYTarget
                if (yOverlapBy > 0) {
                    for (x in (sensor.x - (yOverlapBy)) towards (sensor.x + (yOverlapBy))) {
                        blockedXsInTargetY[x + offset] = true
                    }
                }
            }

            val beaconXsInYTarget = sensorsAndBeacons.map { it[1] }.filter { it.y == targetY }.map { it.x }
            for (beaconX in beaconXsInYTarget) {
                blockedXsInTargetY[beaconX + offset] = false
            }

            return blockedXsInTargetY.count { it }
        }

        fun part2(input: List<String>, side: Int): Int {
            val sensorsAndBeacons = input.map {
                it.substring(10).split(": closest beacon is at ").map { it.split(", ").map { it.substring(2).toInt() } }
                    .map { (x, y) -> Point(x, y) }
            }
            val validRange = 0..side
            val blockedXsMap = (validRange.map { BooleanArray(side + 1) }).toTypedArray()

            for ((sensor, beacon) in sensorsAndBeacons) {
                val sensorCoverage = sensor.rectilinearDistanceTo(beacon)
                for (y in ((sensor.y - sensorCoverage) towards (sensor.y + sensorCoverage))) {
                    val yDistanceToYTarget = (y - sensor.y).absoluteValue
                    val yOverlapBy = sensorCoverage - yDistanceToYTarget
                    for (x in (sensor.x - (yOverlapBy)) towards (sensor.x + (yOverlapBy))) {
                        if (x in validRange && y in validRange) blockedXsMap[y][x] = true
                    }
                }
            }

            for ((x, y) in sensorsAndBeacons.map { it[1] }) {
                if (x in validRange && y in validRange) blockedXsMap[y][x] = true
            }

            val (y, x) = blockedXsMap.mapIndexed { index, booleans -> listOf(index, booleans.indexOfFirst { !it }) }
                .first { it[1] >= 0 }

            return x * 4_000_000 + y
        }
    }


    class Point(var x: Int = 0, var y: Int = 0) {
        fun rectilinearDistanceTo(point: Point): Int {
            return (this.x - point.x).absoluteValue + (this.y - point.y).absoluteValue
        }

        override fun toString(): String {
            return "Point(x=$x, y=$y)"
        }

        operator fun component1() = x
        operator fun component2() = y
    }
}
