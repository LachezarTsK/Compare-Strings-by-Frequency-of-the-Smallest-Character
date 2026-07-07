
package main
import "slices"

func numSmallerByFrequency(queries []string, words []string) []int {
    frequenciesOfLexicographicallySmallestChars := make([]int, len(words))
    for i := range words {
        frequenciesOfLexicographicallySmallestChars[i] = findFrequencyOfLexicographicallySmallestChar(words[i])
    }
    slices.Sort(frequenciesOfLexicographicallySmallestChars)

    resultsPerQueries := make([]int, len(queries))
    for i := range queries {
        targetFrequency := findFrequencyOfLexicographicallySmallestChar(queries[i])
        insertionIndex := binarySearchForInsertionIndex(targetFrequency, frequenciesOfLexicographicallySmallestChars)
        frequenciesGreaterThanTarget := len(frequenciesOfLexicographicallySmallestChars) - insertionIndex
        resultsPerQueries[i] = frequenciesGreaterThanTarget
    }
    return resultsPerQueries
}

func findFrequencyOfLexicographicallySmallestChar(word string) int {
    lexicographicallySmallestChar := word[0]
    frequencyOfLexicographicallySmallestChar := 1

    for i := 1; i < len(word); i++ {
        if word[i] < lexicographicallySmallestChar {
            frequencyOfLexicographicallySmallestChar = 1
            lexicographicallySmallestChar = word[i]
            continue
        }
        if word[i] == lexicographicallySmallestChar {
            frequencyOfLexicographicallySmallestChar++
        }
    }
    return frequencyOfLexicographicallySmallestChar
}

func binarySearchForInsertionIndex(target int, input []int) int {
    left := 0
    right := len(input) - 1

    for left <= right {
        middle := left + (right - left) / 2
        if input[middle] > target {
            right = middle - 1
        } else {
            left = middle + 1
        }
    }
    return left
}
