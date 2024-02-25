package xyz.torquato.words.data

import javax.inject.Inject

class RandomGeneratorImpl @Inject constructor() : RandomGenerator {

    override fun generateSequence(
        size: Int,
        hasNumbers: Boolean,
        hasLowerCase: Boolean,
        hasSymbols: Boolean,
        symbols: String
    ): String {
        val lowerCase = 'a'..'z'
        val upperCase = 'A'..'Z'
        val numbers = '0'..'9'
        val types = MutableList<Any>(0) {}

        if (hasNumbers)
            types.add(numbers)

        if (hasLowerCase)
            types.add(lowerCase)
        types.add(upperCase)

        if (hasSymbols)
            types.add(symbols)

        return String((0 until size).map {
            when (val type = types.random()) {
                is CharSequence -> type.random()
                is CharRange -> type.random()
                else -> '.'
            }
        }.toCharArray())

    }
}