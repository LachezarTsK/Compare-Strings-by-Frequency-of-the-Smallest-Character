
#include <span>
#include <vector>
#include <ranges>
#include <string>
#include <string_view>
using namespace std;

class Solution {

public:
    vector<int> numSmallerByFrequency(vector<string>& queries, vector<string>& words) {
        vector<int> frequenciesOfLexicographicallySmallestChars(words.size());
        for (int i = 0; i < words.size(); ++i) {
            frequenciesOfLexicographicallySmallestChars[i] = findFrequencyOfLexicographicallySmallestChar(words[i]);
        }
        ranges::sort(frequenciesOfLexicographicallySmallestChars);

        vector<int> resultsPerQueries(queries.size());
        for (int i = 0; i < queries.size(); ++i) {
            int targetFrequency = findFrequencyOfLexicographicallySmallestChar(queries[i]);
            int insertionIndex = binarySearchForInsertionIndex(targetFrequency, frequenciesOfLexicographicallySmallestChars);
            int frequenciesGreaterThanTarget = frequenciesOfLexicographicallySmallestChars.size() - insertionIndex;
            resultsPerQueries[i] = frequenciesGreaterThanTarget;
        }
        return resultsPerQueries;
    }

private:
    int findFrequencyOfLexicographicallySmallestChar(string_view word) {
        char lexicographicallySmallestChar = word[0];
        int frequencyOfLexicographicallySmallestChar = 1;

        for (int i = 1; i < word.size(); ++i) {
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

    int binarySearchForInsertionIndex(int target, span<const int> input) {
        int left = 0;
        int right = input.size() - 1;

        while (left <= right) {
            int middle = left + (right - left) / 2;
            if (input[middle] > target) {
                right = middle - 1;
            }
            else {
                left = middle + 1;
            }
        }
        return left;
    }
};
