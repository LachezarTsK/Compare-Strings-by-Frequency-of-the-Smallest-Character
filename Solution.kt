
class Solution {

    fun numSmallerByFrequency(queries: Array<String>, words: Array<String>): IntArray {
        val frequenciesOfLexicographicallySmallestChars = IntArray(words.size)
        for (i in words.indices) {
            frequenciesOfLexicographicallySmallestChars[i] = findFrequencyOfLexicographicallySmallestChar(words[i])
        }
        frequenciesOfLexicographicallySmallestChars.sort()

        val resultsPerQueries = IntArray(queries.size)
        for (i in queries.indices) {
            val targetFrequency = findFrequencyOfLexicographicallySmallestChar(queries[i])
            val insertionIndex = binarySearchForInsertionIndex(targetFrequency, frequenciesOfLexicographicallySmallestChars)
            val frequenciesGreaterThanTarget = frequenciesOfLexicographicallySmallestChars.size - insertionIndex
            resultsPerQueries[i] = frequenciesGreaterThanTarget
        }
        return resultsPerQueries
    }

    private fun findFrequencyOfLexicographicallySmallestChar(word: String): Int {
        var lexicographicallySmallestChar = word[0]
        var frequencyOfLexicographicallySmallestChar = 1

        for (i in 1..<word.length) {
            if (word[i] < lexicographicallySmallestChar) {
                frequencyOfLexicographicallySmallestChar = 1
                lexicographicallySmallestChar = word[i]
                continue
            }
            if (word[i] == lexicographicallySmallestChar) {
                ++frequencyOfLexicographicallySmallestChar
            }
        }
        return frequencyOfLexicographicallySmallestChar
    }

    private fun binarySearchForInsertionIndex(target: Int, input: IntArray): Int {
        var left = 0
        var right = input.size - 1

        while (left <= right) {
            val middle = left + (right - left) / 2
            if (input[middle] > target) {
                right = middle - 1
            } else {
                left = middle + 1
            }
        }
        return left
    }
}
