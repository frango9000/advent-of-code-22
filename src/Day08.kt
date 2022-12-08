fun main() {
    fun part1(input: List<String>): Int {
        val forest = input.map { it.split("").filter { c -> c.isNotEmpty() }.map { character -> character.toInt() } }
        val visibilityForest = input.map { it.map { false }.toMutableList() }


        for (i in forest.indices) {
            var currentTop = -1
            for (j in forest[i].indices) {
                if (forest[i][j] > currentTop) {
                    currentTop = forest[i][j]
                    visibilityForest[i][j] = true
                }
            }

            currentTop = -1
            for (j in forest[i].indices.reversed()) {
                if (forest[i][j] > currentTop) {
                    currentTop = forest[i][j]
                    visibilityForest[i][j] = true
                }
            }
        }


        for (j in forest.first().indices) {
            var currentTop = -1

            for (i in forest.indices) {
                if (forest[i][j] > currentTop) {
                    currentTop = forest[i][j]
                    visibilityForest[i][j] = true
                }
            }

            currentTop = -1
            for (i in forest.indices.reversed()) {
                if (forest[i][j] > currentTop) {
                    currentTop = forest[i][j]
                    visibilityForest[i][j] = true
                }
            }
        }
        return visibilityForest.flatten().count { it }
    }

    fun part2(input: List<String>): Int {
        val forest = input.map { it.split("").filter { c -> c.isNotEmpty() }.map { character -> character.toInt() } }
        val forestOfScores = input.map { it.map { 0 }.toMutableList() }

        for (i in forest.indices) {
            if (i == 0 || i == forest.size - 1) continue
            for (j in forest[i].indices) {
                if (j == 0 || j == forest[i].size - 1) continue
                var leftDistance = 0
                for (j2 in (j - 1 downTo 0)) {
                    leftDistance++
                    if (forest[i][j2] >= forest[i][j]) {
                        break
                    }
                }
                if (leftDistance == 0) continue

                var rightDistance = 0
                for (j2 in (j + 1 until forest[i].size)) {
                    rightDistance++
                    if (forest[i][j2] >= forest[i][j]) {
                        break
                    }
                }
                if (rightDistance == 0) continue

                var topDistance = 0
                for (i2 in (i - 1 downTo 0)) {
                    topDistance++
                    if (forest[i2][j] >= forest[i][j]) {
                        break
                    }
                }
                if (topDistance == 0) continue

                var botDistance = 0
                for (i2 in (i + 1 until forest.size)) {
                    botDistance++
                    if (forest[i2][j] >= forest[i][j]) {
                        break
                    }
                }
                if (botDistance == 0) continue
                forestOfScores[i][j] = leftDistance * rightDistance * topDistance * botDistance
            }
        }

        return forestOfScores.flatten().max()
    }

    val testInput = readInput("Day08_test")
    check(part1(testInput) == 21)
    check(part2(testInput) == 8)

    val input = readInput("Day08")
    println(part1(input))
    println(part2(input))
}
