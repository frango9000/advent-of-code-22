fun main() {

    fun getFolderSizes(input: List<String>): MutableMap<String, Int> {
        val folders: MutableMap<String, Int> = mutableMapOf()
        val stack: ArrayDeque<String> = ArrayDeque()

        input.filterNot { it == "$ ls" || it.startsWith("dir") }.forEach {
            if (it.startsWith("$ cd")) {
                val cdParam = it.subSequence(5, it.length).toString()
                if (cdParam == "..") {
                    stack.removeLast()
                } else {
                    val folderRoute = stack.joinToString("/") + "/$cdParam"
                    stack.addLast(folderRoute)
                    if (!folders.containsKey(folderRoute)) {
                        folders[folderRoute] = 0
                    }
                }
            } else {
                val fileSize = it.split(" ").first().toInt()
                for (parent in stack) {
                    folders[parent] = folders[parent]?.plus(fileSize) ?: fileSize
                }
            }
        }
        return folders
    }

    fun part1(input: List<String>): Int {
        val folders: MutableMap<String, Int> = getFolderSizes(input)

        return folders.values.filter { it <= 100000 }.sum()
    }

    fun part2(input: List<String>): Int {
        val folders: MutableMap<String, Int> = getFolderSizes(input)

        val totalSize = folders["//"]
        val requiredCleanup = -40000000 + totalSize!!

        return folders.values.sorted().first { it >= requiredCleanup }
    }

    val testInput = readInput("Day07_test")
    check(part1(testInput) == 95437)
    check(part2(testInput) == 24933642)

    val input = readInput("Day07")

    println(part1(input))
    println(part2(input))
}
