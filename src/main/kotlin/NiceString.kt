package collectionPractice.coursera

fun main() {
    println("abba".hasDoubleLetter())
    println("bba".hasDoubleLetter())
    println("ba".hasDoubleLetter())
    println("a".hasDoubleLetter())
    println("aacc".hasDoubleLetter())

}


fun String.hasThreeVowels(): Boolean {
    if (count {
            it in "aeiou"
        } < 3) return false
    return false
}


fun String.containsSubstrings(): Boolean {
    return !contains("bu") && !contains("ba") && !contains("be")
}


fun String.hasDoubleLetter(): Boolean {
    forEachIndexed { index, char ->
        if (index != 0 && char == this[index - 1]) {
            return true
        }
    }
    return false
}


