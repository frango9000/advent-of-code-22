import java.io.File
import java.math.BigInteger
import java.security.MessageDigest
import java.util.function.Predicate

/**
 * Reads lines from the given input txt file.
 */
fun readInput(name: String) = File("src", "$name.txt").readLines()

/**
 * Reads the given input txt file.
 */
fun readInputFile(name: String) = File("src", "$name.txt").readText()

/**
 * Converts string to md5 hash.
 */
fun String.md5() =
    BigInteger(1, MessageDigest.getInstance("MD5").digest(toByteArray())).toString(16)
        .padStart(32, '0')


/**
 * partitions the given list by a given predicate is true
 */

fun partition(input: List<String>, partitionWhen: Predicate<String>): ArrayList<ArrayList<String>> {
    return input.fold(arrayListOf(arrayListOf<String>()),
        fun(acc: ArrayList<ArrayList<String>>, curr: String): ArrayList<ArrayList<String>> {
            if (partitionWhen.test(curr)) {
                acc.add(arrayListOf())
            } else if (acc.lastIndex >= 0) {
                acc[acc.lastIndex].add(curr)
            }
            return acc
        })
}


/**
 * partitions the given list by the provided value
 */
fun partitionByLine(input: List<String>, line: String = ""): List<List<String>> {
    return partition(input) { it == line }
}
