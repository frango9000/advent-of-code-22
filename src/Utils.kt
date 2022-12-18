import java.io.File
import java.math.BigInteger
import java.security.MessageDigest
import java.util.function.Predicate
import kotlin.math.absoluteValue
import kotlin.system.measureNanoTime

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
fun String.md5() = BigInteger(1, MessageDigest.getInstance("MD5").digest(toByteArray())).toString(16).padStart(32, '0')


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

data class Coordinate(var x: Int = 0, var y: Int = 0)


fun printTime(pre: String = "\n[", post: String = "]\n\n", function: () -> Unit) {
    print("$pre${measureNanoTime { function() }.toFloat() / 1000000}ms$post")
}


fun Iterable<Int>.product() = this.reduce { acc, i -> acc * i }
fun Iterable<Long>.product() = this.reduce { acc, i -> acc * i }

infix fun Int.isDivisibleBy(divisor: Int) = this % divisor == 0
infix fun Long.isDivisibleBy(divisor: Long) = this % divisor == 0L


/**
 * Euclid's algorithm for finding the greatest common divisor of a and b.
 */
fun gcd(a: Long, b: Long): Long = if (b == 0L) a.absoluteValue else gcd(b, a % b)

/**
 * Find the least common multiple of a and b using the gcd of a and b.
 */
fun lcm(a: Long, b: Long) = (a * b) / gcd(a, b)
fun Iterable<Long>.lcm(): Long = reduce(::lcm)


fun <R, T> List<List<T>>.mapMatrix(mapper: (T) -> R): List<List<R>> {
    return this.map { it.map { mapper(it) } }
}

fun <R, T> List<List<T>>.mapMatrixIndexed(mapper: (Int, Int, T) -> R): List<List<R>> {
    return this.mapIndexed { i, row -> row.mapIndexed { j, item -> mapper(i, j, item) } }
}

fun <R, T> List<List<T>>.matrixForEach(mapper: (T) -> R): Unit {
    return this.forEach { it.forEach { mapper(it) } }
}

fun <R, T> List<List<T>>.matrixForEachIndexed(mapper: (Int, Int, T) -> R): Unit {
    return this.forEachIndexed { i, row -> row.forEachIndexed { j, item -> mapper(i, j, item) } }
}

infix fun Int.towards(to: Int): IntProgression {
    val step = if (this > to) -1 else 1
    return IntProgression.fromClosedRange(this, to, step)
}


fun Iterable<IntRange>.merge(): List<IntRange> {
    val sorted = this.filter { !it.isEmpty() }.sortedBy { it.first }
    sorted.isNotEmpty() || return emptyList()

    val stack = ArrayDeque<IntRange>()
    stack.add(sorted.first())
    sorted.drop(1).forEach { current ->
        if (current.last <= stack.last().last) {
            //
        } else if (current.first > stack.last().last + 1) {
            stack.add(current)
        } else {
            stack.add(stack.removeLast().first..current.last)
        }
    }
    return stack
}

val IntRange.size get() = (last - first + 1).coerceAtLeast(0)


fun IntRange.subtract(others: Iterable<IntRange>): List<IntRange> {
    val relevant = others.merge().filter { it overlaps this }
    if (relevant.isEmpty()) return listOf(this)

    return buildList {
        var includeFrom = first
        relevant.forEach { minus ->
            if (minus.first > includeFrom)
                add(includeFrom until minus.first.coerceAtMost(last))
            includeFrom = minus.last + 1
        }
        if (includeFrom <= last)
            add(includeFrom..last)
    }
}

infix fun <T : Comparable<T>> ClosedRange<T>.overlaps(other: ClosedRange<T>): Boolean {
    if (isEmpty() || other.isEmpty()) return false
    return !(this.endInclusive < other.start || this.start > other.endInclusive)
}
