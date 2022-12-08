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
        return 0
    }

    val testInput = readInput("Day08_test")
    check(part1(testInput) == 21)
//    check(part2(testInput) == 0)

    val input = readInput("Day08")
    println(part1(input))
//    println(part2(input))
}
