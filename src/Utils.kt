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
fun <E> List<E>.partitionWhen(partitionWhen: Predicate<E>): List<List<E>> {
    return this.fold(arrayListOf(arrayListOf<E>()),
        fun(acc: ArrayList<ArrayList<E>>, curr: E): ArrayList<ArrayList<E>> {
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
fun <E> List<E>.partitionOnElement(by: E): List<List<E>> {
    return this.partitionWhen { by?.equals(it) == true }
}
