import java.io.File
import java.math.BigInteger
import java.security.MessageDigest
import java.util.function.Predicate

/**
 * Reads lines from the given input txt file.
 */
fun readInput(name: String) = File("src", "$name.txt").readLines()

/**
 * Reads lines from the given input txt file.
 */
fun readTestInput(name: String) = File("test/kotlin", "$name.txt").readLines()

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
    return this.fold(arrayListOf(arrayListOf<E>())) { acc: ArrayList<ArrayList<E>>, curr: E ->
        if (partitionWhen.test(curr)) {
            acc.add(arrayListOf())
        } else if (acc.lastIndex >= 0) {
            acc[acc.lastIndex].add(curr)
        }
        acc
    }
}


/**
 * partitions the given list by the provided value
 */
fun <E> List<E>.partitionOnElement(by: E): List<List<E>> {
    return this.partitionWhen { by?.equals(it) == true }
}

data class Coordinate(var x: Int, var y: Int)
