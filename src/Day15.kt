import kotlin.math.absoluteValue

fun main() {
    val input = readInput("Day15")
    printTime { print(Day15.part1(input, 2_000_000)) }
    printTime { print(Day15.part2(input, 4_000_000)) }
}

class Day15 {
    companion object {
        fun part1(input: List<String>, targetY: Int): Int {
            val sensorsAndBeacons = input.map {
                it.substring(10).split(": closest beacon is at ").map { it.split(", ").map { it.substring(2).toInt() } }
                    .map { (x, y) -> Point(x, y) }
            }

            val blockedRanges: List<IntRange> = sensorsAndBeacons.mapNotNull {
                scannerRangeOnRow(it, targetY)
            }.merge()


            val blockedXsInYTarget = sensorsAndBeacons.flatten().filter { it.y == targetY }.map { it.x }.toSet()
            return blockedRanges.sumOf { it.size } - blockedXsInYTarget.size
        }

        fun part2(input: List<String>, side: Int): Long {
            val sensorsAndBeacons = input.map {
                it.substring(10).split(": closest beacon is at ").map { it.split(", ").map { it.substring(2).toInt() } }
                    .map { (x, y) -> Point(x, y) }
            }
            val validRange = 0..side

            for (y in validRange) {

                val blockedRanges: List<IntRange> = sensorsAndBeacons.mapNotNull {
                    scannerRangeOnRow(it, y)
                }
                val unblocked = validRange.subtract(blockedRanges)
                if (unblocked.isNotEmpty()) {
                    return unblocked.first().first * 4_000_000L + y
                }
            }
            error("Not Found")
        }

        private fun scannerRangeOnRow(points: List<Point>, targetY: Int): IntRange? {
            val (sensor, beacon) = points
            val sensorCoverage = sensor.rectilinearDistanceTo(beacon)
            val yDistanceToYTarget = (targetY - sensor.y).absoluteValue
            val yOverlapBy = sensorCoverage - yDistanceToYTarget
            return if (yOverlapBy <= 0) null
            else (sensor.x - yOverlapBy..sensor.x + yOverlapBy)
        }

//        fun part1withArray(input: List<String>, targetY: Int): Int {
//            val sensorsAndBeacons = input.map {
//                it.substring(10).split(": closest beacon is at ").map { it.split(", ").map { it.substring(2).toInt() } }
//                    .map { (x, y) -> Point(x, y) }
//            }
//
//            val offset = sensorsAndBeacons.maxOf { it[0].rectilinearDistanceTo(it[1]) } * 2
//            val beaconMaxX = sensorsAndBeacons.maxOf { it[1].x }
//            val blockedXsInTargetY = BooleanArray(beaconMaxX + offset)
//
//            for ((sensor, beacon) in sensorsAndBeacons) {
//                val sensorCoverage = sensor.rectilinearDistanceTo(beacon)
//                val yDistanceToYTarget = (targetY - sensor.y).absoluteValue
//                val yOverlapBy = sensorCoverage - yDistanceToYTarget
//                if (yOverlapBy > 0) {
//                    for (x in (sensor.x - (yOverlapBy)) towards (sensor.x + (yOverlapBy))) {
//                        blockedXsInTargetY[x + offset] = true
//                    }
//                }
//            }
//
//            val beaconXsInYTarget = sensorsAndBeacons.map { it[1] }.filter { it.y == targetY }.map { it.x }
//            for (beaconX in beaconXsInYTarget) {
//                blockedXsInTargetY[beaconX + offset] = false
//            }
//
//            return blockedXsInTargetY.count { it }
//        }
//
//        fun part2WithArray(input: List<String>, side: Int): Int {
//            val sensorsAndBeacons = input.map {
//                it.substring(10).split(": closest beacon is at ").map { it.split(", ").map { it.substring(2).toInt() } }
//                    .map { (x, y) -> Point(x, y) }
//            }
//            val validRange = 0..side
//
//            for (y in validRange) {
//                val blockedXsInTargetY = BooleanArray(side + 1)
//                for ((sensor, beacon) in sensorsAndBeacons) {
//                    val sensorCoverage = sensor.rectilinearDistanceTo(beacon)
//                    val yDistanceToYTarget = (y - sensor.y).absoluteValue
//                    val yOverlapBy = sensorCoverage - yDistanceToYTarget
//                    if (yOverlapBy > 0) {
//                        for (x in (sensor.x - (yOverlapBy)) towards (sensor.x + (yOverlapBy))) {
//                            if (x in validRange && y in validRange) blockedXsInTargetY[x] = true
//                        }
//                    }
//                }
//                if (blockedXsInTargetY.any { !it }) return blockedXsInTargetY.indexOfFirst { !it } * 4_000_000 + y
//            }
//            error("Not Found")
//        }
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
