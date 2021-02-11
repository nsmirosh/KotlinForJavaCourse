package week4

import java.math.BigInteger

infix fun Int.divBy(toDivideBy: Int): Rational {
    return Rational(this, toDivideBy)
}

infix fun Long.divBy(toDivideBy: Long): Rational {
    return Rational("$this/$toDivideBy")
}

infix fun BigInteger.divBy(toDivideBy: BigInteger): Rational {
    return Rational(this, toDivideBy)
}

class Rational() : Comparable<Rational> {

    lateinit var numerator: BigInteger
    lateinit var denominator: BigInteger

    constructor(numerator: Int, denominator: Int) : this() {
        this.numerator = numerator.toBigInteger()
        this.denominator = denominator.toBigInteger()
    }

    constructor(numerator: BigInteger, denominator: BigInteger) : this() {
        this.numerator = numerator
        this.denominator = denominator
    }

    constructor(rawData: String) : this() {

        if (rawData.contains("/")) {
            rawData.split("/").apply {
                numerator = first().toBigInteger()
                denominator = last().toBigInteger()
            }
        }
        else {
            numerator = rawData.toBigInteger()
            denominator = BigInteger.ONE
        }
    }

    override fun compareTo(other: Rational): Int {
        if ((this - other).numerator > BigInteger.ZERO) {
            return 1
        } else if ((this - other).numerator == BigInteger.ZERO) {
            return 0
        }
        return -1
    }

    override fun equals(other: Any?): Boolean {
        if (!(other is Rational)) {
            return false
        }
        return (this - other).numerator == BigInteger.ZERO
    }

    override fun toString(): String {
        val gcd = numerator.gcd(denominator)

        val normalizedNumerator = numerator / gcd
        val normalizedDenominator = denominator / gcd

        if (normalizedNumerator < BigInteger.ZERO && normalizedDenominator < BigInteger.ZERO) {
            return "${normalizedNumerator.abs()}/${normalizedDenominator.abs()}"
        }
        if (normalizedNumerator < BigInteger.ZERO || normalizedDenominator < BigInteger.ZERO) {
            return "-${normalizedNumerator.abs()}/${normalizedDenominator.abs()}"
        }
        if (normalizedDenominator == BigInteger.ONE) {
            return "$normalizedNumerator"
        }
        return "$normalizedNumerator/$normalizedDenominator"
    }
}

operator fun Rational.plus(other: Rational): Rational {
    val lcm = this.denominator.lcm(other.denominator)

    val thisToMultiplyBy = lcm / this.denominator
    val otherToMultiplyBy = lcm / other.denominator

    val sumOfNumerators = this.numerator * thisToMultiplyBy + other.numerator * otherToMultiplyBy

    return Rational(sumOfNumerators, lcm)
}

fun BigInteger.lcm( b: BigInteger): BigInteger {
    val gcd = this.gcd(b)
    return this / gcd * b
}

operator fun Rational.minus(other: Rational): Rational {

    val lcm = this.denominator.lcm(other.denominator)

    val thisToMultiplyBy = lcm / this.denominator
    val otherToMultiplyBy = lcm / other.denominator

    val numeratorDifference = this.numerator * thisToMultiplyBy - other.numerator * otherToMultiplyBy
    return Rational(numeratorDifference, lcm)
}

operator fun Rational.times(other: Rational): Rational =
    Rational(this.numerator * other.numerator, this.denominator * other.denominator)

operator fun Rational.div(other: Rational): Rational =
    Rational(this.numerator * other.denominator, this.denominator * other.numerator)

operator fun Rational.unaryMinus(): Rational = Rational(-this.numerator, this.denominator)

fun String.toRational(): Rational {
    return Rational(this)
}

fun main() {
    val half = 1 divBy 2
    val third = 1 divBy 3

    val sum: Rational = half + third


    println("sum = $sum")
    println(5 divBy 6 == sum)

    val difference: Rational = half - third
    println(1 divBy 6 == difference)

    val product: Rational = half * third
    println(1 divBy 6 == product)

    val quotient: Rational = half / third
    println(3 divBy 2 == quotient)

    val negation: Rational = -half
    println(-1 divBy 2 == negation)

    println((2 divBy 1).toString() == "2")

    println("6")

    println((-2 divBy 4).toString() == "-1/2")

    println("7")

    println("117/1098".toRational().toString() == "13/122") // divide numerator and denominator by gcm here

    val twoThirds = 2 divBy 3
    println(half < twoThirds)

    println(half in third..twoThirds)

    println(2000000000L divBy 4000000000L == 1 divBy 2)
    println(
        "912016490186296920119201192141970416029".toBigInteger() divBy
                "1824032980372593840238402384283940832058".toBigInteger() == 1 divBy 2
    )



    println("5670711258187766016096/1017819969418316977248".toRational().toString() == "39/7")

    println("started1")


    println("20325830850349869048604856908".toRational() > "-9192901948302584358938698".toRational())

    println("finished1")

}