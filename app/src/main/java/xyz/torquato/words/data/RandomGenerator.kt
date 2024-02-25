package xyz.torquato.words.data

interface RandomGenerator {

    fun generateSequence(size: Int, hasNumbers: Boolean, hasLowerCase: Boolean): String
}