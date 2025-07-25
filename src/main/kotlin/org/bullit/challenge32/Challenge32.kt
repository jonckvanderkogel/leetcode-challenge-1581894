package org.bullit.challenge32

import kotlin.math.max

fun calculateLongestParenthesisChain(parenthesisString: String) = calculate(parenthesisString, listOf(-1), 0, 0)

private data class Result(val stack: List<Int>, val longest: Int)

private tailrec fun calculate(parenthesisString: String, stack: List<Int>, longest: Int, index: Int): Int {
    if (index >= parenthesisString.length) return longest

    val newResult = when(parenthesisString[index]) {
        '(' -> processOpen(stack, longest, index)
        ')' -> processClose(stack, longest, index)
        else -> throw InvalidCharacterException()
    }

    return calculate(parenthesisString, newResult.stack, newResult.longest, index + 1)
}

private fun processOpen(stack: List<Int>, longest: Int, index: Int): Result = Result(stack + index, longest)

private fun processClose(stack: List<Int>, longest: Int, index: Int): Result =
    if (stack.size > 1) {
        val newStack = stack.dropLast(1)
        val newLongest = max(index - newStack.last(), longest)
        Result(newStack, newLongest)
    } else {
        Result(listOf(index), longest)
    }

private class InvalidCharacterException : RuntimeException("Invalid character, must be either '(' or ')'")