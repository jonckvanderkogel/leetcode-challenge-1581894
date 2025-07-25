package org.bullit.challenge32

import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import org.junit.jupiter.params.provider.Arguments
import java.util.stream.Stream
import kotlin.test.assertEquals

class Challenge32Test {
    @ParameterizedTest(name = "longest valid parentheses in \"{1}\" = {0}")
    @MethodSource("testCases")
    fun `verify longest valid parentheses length`(expected: Int, sample: String) {
        assertEquals(expected, calculateLongestParenthesisChain(sample))
    }

    companion object {
        @JvmStatic
        fun testCases(): Stream<Arguments> = Stream.of(
            Arguments.of(2,  "(()"),
            Arguments.of(4,  ")()())"),
            Arguments.of(0,  ""),
            Arguments.of(10, ")()()))))((((()))))")
        )
    }
}