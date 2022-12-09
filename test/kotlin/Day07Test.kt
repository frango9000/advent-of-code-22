import org.junit.jupiter.api.Test

class Day07Test {

    private val testInput = listOf(
        "$ cd /",
        "$ ls",
        "dir a",
        "14848514 b.txt",
        "8504156 c.dat",
        "dir d",
        "$ cd a",
        "$ ls",
        "dir e",
        "29116 f",
        "2557 g",
        "62596 h.lst",
        "$ cd e",
        "$ ls",
        "584 i",
        "$ cd ..",
        "$ cd ..",
        "$ cd d",
        "$ ls",
        "4060174 j",
        "8033020 d.log",
        "5626152 d.ext",
        "7214296 k",
    )

    @Test
    fun part1() {
        check(Day07.part1(testInput) == 95437)
    }

    @Test
    fun part2() {
        check(Day07.part2(testInput) == 24933642)
    }
}
