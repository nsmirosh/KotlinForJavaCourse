package collectionPractice.coursera

data class Evaluation(val rightPosition: Int, val wrongPosition: Int)

fun evaluateGuess(secret: String, guess: String): Evaluation {

    val rightPositions = secret.zip(guess).count { it.first == it.second }

    val commonLetters = "ABCDEF".sumBy { ch ->

        Math.min(secret.count { it == ch }, guess.count {  it == ch })
    }
    return Evaluation(rightPositions, commonLetters - rightPositions)
}

fun myEvaluate(secret: String, guess: String) {

    val indexesToRemove = mutableSetOf<Int>()
    var wrongPosition = 0

    secret.withIndex().forEach {
        if (it.value == guess[it.index]) {
            indexesToRemove.add(it.index)
        }
    }

    val secretGrouped = secret
        .filterIndexed { index, _ -> !(index in indexesToRemove) }
        .groupBy { it }

    guess
        .filterIndexed { index, _ -> !(index in indexesToRemove) }
        .groupBy { it }
        .forEach { key, value ->
            if (key in secretGrouped.keys) {
                wrongPosition += minOf(value.size, secretGrouped.getValue(key).size)
            }
        }
}

fun main(args: Array<String>) {
    val result = Evaluation(rightPosition = 1, wrongPosition = 1)

    val initialNano2 = System.nanoTime()
    evaluateGuess("BCDF", "ACEB")
    println(System.nanoTime() - initialNano2)

    val initialNano3 = System.nanoTime()
    myEvaluate("BCDF", "ACEB")
    println(System.nanoTime() - initialNano3)


    println(evaluateGuess("AAAF", "ABCA") == result)
    println(evaluateGuess("ABCA", "AAAF") == result)
}