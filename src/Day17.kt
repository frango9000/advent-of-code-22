fun main() {
    val input = readInput("Day17")
    printTime { print(Day17.part1(input)) }
//    printTime { print(Day17.part2(input)) }
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

                                val updatedLine2 = StringBuilder()
                                for ((index, char) in lineToUpdate.withIndex()) {
                                    if (char == '#' || (index in (rockLeftX until rockLeftX + line.length) && line[index - rockLeftX] == '#'))
                                        updatedLine2.append('#')
                                    else
                                        updatedLine2.append('.')

                                }
                                heightMap[rockBottomY + lineIndex] = updatedLine2.toString()
                            }
                        }
                    }
                }
//                println()
//                println(rockIndex + 1)
//                println(rockDropIndex)
//                heightMap.print()
//                println()
            }

//            heightMap.print()
            return heightMap.size
        }


        fun part2(input: List<String>): Int {

            return 0
        }
    }

    enum class Rock(val lines: List<String>) {
        A(listOf("####")),
        B(listOf(".#.", "###", ".#.")),
        C(listOf("###", "..#", "..#")),
        D(listOf("#", "#", "#", "#")),
        E(listOf("##", "##"));


        fun canMoveLeft(heightMap: List<String>, leftX: Int, bottomY: Int): Boolean {
            if (leftX < 1) return false
            for ((y, line) in lines.withIndex()) {
                if (y + bottomY > heightMap.lastIndex) return true
                for ((x, char) in line.withIndex()) {
                    if (char == '#') {
                        if (heightMap[y + bottomY][x + leftX - 1] == '#') return false
                        break
                    }
                }
            }
            return true
        }

        fun canMoveRight(heightMap: List<String>, leftX: Int, bottomY: Int): Boolean {
            if (leftX + lines.first().length > 6) return false
            for ((y, line) in lines.withIndex()) {
                if (y + bottomY > heightMap.lastIndex) return true
                for ((x, char) in line.withIndex().reversed()) {
                    if (char == '#') {
                        if (heightMap[y + bottomY][x + leftX + 1] == '#') return false
                        break
                    }
                }
            }
            return true
        }

        fun canDrop(heightMap: List<String>, leftX: Int, bottomY: Int): Boolean {
            if (bottomY < 1) return false
            for ((y, line) in lines.withIndex()) {
                if (y + bottomY > heightMap.size) return true
                for ((x, char) in line.withIndex()) {
                    if (char == '#' && heightMap[y + bottomY - 1][x + leftX] == '#') {
                        return false
                    }
                }
            }
            return true
        }
    }


    fun List<String>.print() {
        for ((index, line) in this.withIndex().reversed()) {
            println("|$line| ${index + 1}")
        }
        println("+-------+")
    }

}
