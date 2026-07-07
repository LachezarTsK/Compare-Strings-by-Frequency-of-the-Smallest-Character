
import java.util.Arrays;

public class Solution {

    public int[] numSmallerByFrequency(String[] queries, String[] words) {
        int[] frequenciesOfLexicographicallySmallestChars = new int[words.length];
        for (int i = 0; i < words.length; ++i) {
            frequenciesOfLexicographicallySmallestChars[i] = findFrequencyOfLexicographicallySmallestChar(words[i].toCharArray());
        }
        Arrays.sort(frequenciesOfLexicographicallySmallestChars);

        int[] resultsPerQueries = new int[queries.length];
        for (int i = 0; i < queries.length; ++i) {
            int targetFrequency = findFrequencyOfLexicographicallySmallestChar(queries[i].toCharArray());
            int insertionIndex = binarySearchForInsertionIndex(targetFrequency, frequenciesOfLexicographicallySmallestChars);
            int frequenciesGreaterThanTarget = frequenciesOfLexicographicallySmallestChars.length - insertionIndex;
            resultsPerQueries[i] = frequenciesGreaterThanTarget;
        }
        return resultsPerQueries;
    }

    private int findFrequencyOfLexicographicallySmallestChar(char[] word) {
        char lexicographicallySmallestChar = word[0];
        int frequencyOfLexicographicallySmallestChar = 1;

        for (int i = 1; i < word.length; ++i) {
            if (word[i] < lexicographicallySmallestChar) {
                frequencyOfLexicographicallySmallestChar = 1;
                lexicographicallySmallestChar = word[i];
                continue;
            }
            if (word[i] == lexicographicallySmallestChar) {
                ++frequencyOfLexicographicallySmallestChar;
            }
        }
        return frequencyOfLexicographicallySmallestChar;
    }

    private int binarySearchForInsertionIndex(int target, int[] input) {
        int left = 0;
        int right = input.length - 1;

        while (left <= right) {
            int middle = left + (right - left) / 2;
            if (input[middle] > target) {
                right = middle - 1;
            } else {
                left = middle + 1;
            }
        }
        return left;
    }
}
