package xyz.torquato.words.data

import javax.inject.Inject
import kotlin.random.Random

class RandomGeneratorImpl @Inject constructor (): RandomGenerator {

    override fun generateSequence(size: Int): String {
        val buffer = CharArray(size)

        for (i in 0 until size) {
            buffer[i] = Random(100).nextInt(10).toChar()
        }

        return String(buffer)
    }
}