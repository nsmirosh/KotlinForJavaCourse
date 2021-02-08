package week4

import java.math.BigInteger


infix fun Int.divBy(toDivideBy: Int): Rational {
    return Rational("$this/$toDivideBy")
}


infix fun Long.divBy(toDivideBy: Long): Rational {
    return Rational("$this/$toDivideBy")
}


class Rational(rawData: String) : Comparable<Rational> {
    var numerator: BigInteger
    var denominator: BigInteger

    init {
        rawData.split("/").apply {
            numerator = first().toBigInteger()
            denominator = last().toBigInteger()
        }
    }

    override fun compareTo(other: Rational): Int {
        TODO("Not yet implemented")
    }


    override fun equals(other: Any?): Boolean {
        if (!(other is Rational)) {
            return false
        }
        return other.numerator == this.numerator && other.denominator == this.denominator
    }

    override fun toString(): String {
        return "numerator = $numerator, denominator = $denominator"
    }
}

operator fun Rational.plus(other: Rational): Rational {


    val absHigherNumber=  this.denominator.max(other.denominator)
    val absLowerNumber = this.denominator.min(other.denominator)
    var lcm = absHigherNumber
    while (lcm.mod(absLowerNumber).toInt() != 0) {
        lcm += absHigherNumber
    }
    val thisToMultiplyBy = lcm / this.denominator
    val otherToMultiplyBy = lcm / other.denominator

    val sumOfNumerators = this.numerator * thisToMultiplyBy + other.numerator * otherToMultiplyBy

    return Rational("$sumOfNumerators/$lcm")
}

operator fun Rational.minus(otherRational: Rational): Rational {
    return Rational("balls")
}

operator fun Rational.times(otherRational: Rational): Rational {
    return Rational("balls")
}

operator fun Rational.div(otherRational: Rational): Rational {
    return Rational("balls")
}

operator fun Rational.unaryMinus(): Rational {
    return Rational("balls")
}

operator fun Rational.rangeTo(otherRational: Rational): Int {
    return 0
}


fun String.toRational(): Rational {
    return Rational(this)
}

fun main() {
    val half = 1 divBy 2
    val third = 1 divBy 3

    println(half)
    println(third)


    val sum: Rational = half + third

    println(sum)
    println(5 divBy 6)
    println(5 divBy 6 == sum)
/*
    val difference: Rational = half - third
    println(1 divBy 6 == difference)

    val product: Rational = half * third
    println(1 divBy 6 == product)

    val quotient: Rational = half / third
    println(3 divBy 2 == quotient)

    val negation: Rational = -half
    println(-1 divBy 2 == negation)

    println((2 divBy 1).toString() == "2")
    println((-2 divBy 4).toString() == "-1/2")
    println("117/1098".toRational().toString() == "13/122")

    val twoThirds = 2 divBy 3
    println(half < twoThirds)*/

  /*  println(half in third..twoThirds)

    println(2000000000L divBy 4000000000L == 1 divBy 2)

    println(
        "912016490186296920119201192141970416029".toBigInteger() divBy
                "1824032980372593840238402384283940832058".toBigInteger() == 1 divBy 2
    )*/
}