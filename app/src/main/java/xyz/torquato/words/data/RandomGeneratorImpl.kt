package xyz.torquato.words.data

import javax.inject.Inject
import kotlin.random.Random
import kotlin.random.nextInt

class RandomGeneratorImpl @Inject constructor() : RandomGenerator {

    override fun generateSequence(size: Int): String {
        val lowerCase = 'a' .. 'z'
        val upperCase = 'A' .. 'Z'
        val numbers = '0' .. '9'
        val symbols = "@$[]{}"
        val types = arrayOf(
            lowerCase,
            upperCase,
            numbers,
            symbols
        )

        return String((0 until size).map {
            when(val type = types.random()) {
                is CharSequence -> type.random()
                is CharRange -> type.random()
                else -> '.'
            }
        }.toCharArray())

    }
}