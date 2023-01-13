fun main() {
    val input = readInput("Day17")
    printTime { print(Day17.part1(input)) }
    printTime { print(Day17.part2(input)) }
}

class Day17 {
    companion object {
        fun part1(input: List<String>): Int {
            val airJets = input[0].toCharArray()
            val heightMap = mutableListOf<String>()

            var rockDropIndex = -1
            for (rockIndex in (0 until 2022)) {
                val rock = Rock.values()[rockIndex % 5]
                var rockLeftX = 2
                var rockBottomY = heightMap.indexOfLast { it.contains('#') } + 4
                var rockStopped = false
                while (!rockStopped) {
                    rockDropIndex++
                    val airJetDirection = airJets[rockDropIndex % airJets.size]
                    when (airJetDirection) {
                        '<' -> if (rock.canMoveLeft(heightMap, rockLeftX, rockBottomY)) rockLeftX--

                        '>' -> if (rock.canMoveRight(heightMap, rockLeftX, rockBottomY)) rockLeftX++
                    }
                    if (rock.canDrop(heightMap, rockLeftX, rockBottomY)) {
                        rockBottomY--
                    } else {
                        rockStopped = true
                        for ((lineIndex, line) in rock.lines.withIndex()) {
                            if (heightMap.lastIndex < rockBottomY + lineIndex) {
                                heightMap.add(".".repeat(rockLeftX) + line + ".".repeat(7 - rockLeftX - line.length))
                            } else {
                                val lineToUpdate = heightMap[rockBottomY + lineIndex]
                                val updatedLine = lineToUpdate.substring(
                                    0,
                                    rockLeftX
                                ) + line + lineToUpdate.substring(rockLeftX + rock.lines.first().length)
                                heightMap[rockBottomY + lineIndex] = updatedLine
                            }
                        }
                    }
                }
//                println()
//                println(rockIndex)
//                println(rockDropIndex)
//                heightMap.print()
//                println()
            }

            heightMap.print()
            return heightMap.size
        }


        fun part2(input: List<String>): Int {

            return 0
        }
    }

    enum class Rock(val lines: List<String>, val leftProfile: String, val rightProfile: String) {
        A(listOf("####"), "#", "#"),
        B(listOf(".#.", "###", ".#."), ".#.", ".#."),
        C(listOf("###", "..#", "..#"), "..#", "###"),
        D(listOf("#", "#", "#", "#"), "####", "####"),
        E(listOf("##", "##"), "##", "##");


        fun canMoveLeft(heightMap: List<String>, leftX: Int, bottomY: Int): Boolean {
            if (leftX < 1) return false
            val relevantHeightMapSlice =
                heightMap.filterIndexed { index, _ -> index in ((bottomY + leftProfile.length - 1) towards bottomY) }
                    .map { it[leftX - 1] }

            for ((index, char) in relevantHeightMapSlice.withIndex()) {
                if ('#' == char && '#' == leftProfile[index]) {
                    return false
                }
            }
            return true
        }

        fun canMoveRight(heightMap: List<String>, leftX: Int, bottomY: Int): Boolean {
            if (leftX + lines.first().length > 6) return false
            val relevantHeightMapSlice =
                heightMap.filterIndexed { index, _ -> index in ((bottomY + rightProfile.length - 1) towards bottomY) }
                    .map { it[leftX + lines[0].length] }

            for ((index, char) in relevantHeightMapSlice.withIndex()) {
                if ('#' == char && '#' == rightProfile[index]) {
                    return false
                }
            }
            return true
        }

        fun canDrop(heightMap: List<String>, leftX: Int, bottomY: Int): Boolean {
            if (bottomY < 1) return false
            if (bottomY > heightMap.size) return true
            val relevantHeightMapSlice =
                heightMap[bottomY - 1].substring(leftX, leftX + lines.last().length)

            for ((index, char) in relevantHeightMapSlice.withIndex()) {
                if ('#' == char && '#' == lines.first()[index]) {
                    return false
                }
            }
            return true

        }
    }
}


fun List<String>.print() {
    for ((index, line) in this.withIndex().reversed()) {
        println("$line $index")
    }
}
