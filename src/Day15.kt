import kotlin.math.absoluteValue

fun main() {
    val input = readInput("Day15")
    printTime { println(Day15.part1(input, 2_000_000)) }
    println(Day15.part2(input))
}

class Day15 {
    companion object {
        fun part1(input: List<String>, yTarget: Int): Int {
            val sensorsAndBeacons = input.map {
                it.substring(10).split(": closest beacon is at ").map { it.split(", ").map { it.substring(2).toInt() } }
                    .map { (x, y) -> Point(x, y) }
            }
            val pointsInYTarget = mutableSetOf<Point>()

            for ((sensor, beacon) in sensorsAndBeacons) {
                val sensorCoverage = sensor.rectilinearDistanceTo(beacon)
                val yDistanceToYTarget = (yTarget - sensor.y).absoluteValue
                val yOverlapBy = sensorCoverage - yDistanceToYTarget
                if (yOverlapBy > 0) {
                    pointsInYTarget.addAll(((sensor.x - (yOverlapBy)) towards (sensor.x + (yOverlapBy))).map {
                        Point(it, yTarget)
                    })
                }
            }
            return (pointsInYTarget subtract sensorsAndBeacons.map { it[1] }.toSet()).size
        }

        fun part2(input: List<String>): Int {
            return 0
        }
    }


    class Point(var x: Int = 0, var y: Int = 0) {
        fun rectilinearDistanceTo(point: Point): Int {
            return (this.x - point.x).absoluteValue + (this.y - point.y).absoluteValue
        }

        override fun toString(): String {
            return "Point(x=$x, y=$y)"
        }

        override fun equals(other: Any?): Boolean {
            other as Point
            return (x == other.x && y == other.y)
        }

        override fun hashCode(): Int {
            var result = x
            result = 31 * result + y
            return result
        }
    }
}
