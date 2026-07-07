
using System;

public class Solution
{
    public int[] NumSmallerByFrequency(string[] queries, string[] words)
    {
        int[] frequenciesOfLexicographicallySmallestChars = new int[words.Length];
        for (int i = 0; i < words.Length; ++i)
        {
            frequenciesOfLexicographicallySmallestChars[i] = FindFrequencyOfLexicographicallySmallestChar(words[i]);
        }
        Array.Sort(frequenciesOfLexicographicallySmallestChars);

        int[] resultsPerQueries = new int[queries.Length];
        for (int i = 0; i < queries.Length; ++i)
        {
            int targetFrequency = FindFrequencyOfLexicographicallySmallestChar(queries[i]);
            int insertionIndex = BinarySearchForInsertionIndex(targetFrequency, frequenciesOfLexicographicallySmallestChars);
            int frequenciesGreaterThanTarget = frequenciesOfLexicographicallySmallestChars.Length - insertionIndex;
            resultsPerQueries[i] = frequenciesGreaterThanTarget;
        }
        return resultsPerQueries;
    }

    private int FindFrequencyOfLexicographicallySmallestChar(string word)
    {
        char lexicographicallySmallestChar = word[0];
        int frequencyOfLexicographicallySmallestChar = 1;

        for (int i = 1; i < word.Length; ++i)
        {
            if (word[i] < lexicographicallySmallestChar)
            {
                frequencyOfLexicographicallySmallestChar = 1;
                lexicographicallySmallestChar = word[i];
                continue;
            }
            if (word[i] == lexicographicallySmallestChar)
            {
                ++frequencyOfLexicographicallySmallestChar;
            }
        }
        return frequencyOfLexicographicallySmallestChar;
    }

    private int BinarySearchForInsertionIndex(int target, int[] input)
    {
        int left = 0;
        int right = input.Length - 1;

        while (left <= right)
        {
            int middle = left + (right - left) / 2;
            if (input[middle] > target)
            {
                right = middle - 1;
            }
            else
            {
                left = middle + 1;
            }
        }
        return left;
    }
}
