
/**
 * @param {string[]} queries
 * @param {string[]} words
 * @return {number[]}
 */
var numSmallerByFrequency = function (queries, words) {
    const frequenciesOfLexicographicallySmallestChars = new Array(words.length);
    for (let i = 0; i < words.length; ++i) {
        frequenciesOfLexicographicallySmallestChars[i] = findFrequencyOfLexicographicallySmallestChar(words[i]);
    }
    frequenciesOfLexicographicallySmallestChars.sort((x, y) => x - y);

    const resultsPerQueries = new Array(queries.length);
    for (let i = 0; i < queries.length; ++i) {
        const targetFrequency = findFrequencyOfLexicographicallySmallestChar(queries[i]);
        const insertionIndex = binarySearchForInsertionIndex(targetFrequency, frequenciesOfLexicographicallySmallestChars);
        const frequenciesGreaterThanTarget = frequenciesOfLexicographicallySmallestChars.length - insertionIndex;
        resultsPerQueries[i] = frequenciesGreaterThanTarget;
    }
    return resultsPerQueries;
};

/**
 * @param {string} word
 * @return {number}
 */
function findFrequencyOfLexicographicallySmallestChar(word) {
    let lexicographicallySmallestChar = word[0];
    let frequencyOfLexicographicallySmallestChar = 1;

    for (let i = 1; i < word.length; ++i) {
        if (word[i] < lexicographicallySmallestChar) {
            frequencyOfLexicographicallySmallestChar = 1;
            lexicographicallySmallestChar = word[i];
            continue;
        }
        if (word[i] === lexicographicallySmallestChar) {
            ++frequencyOfLexicographicallySmallestChar;
        }
    }
    return frequencyOfLexicographicallySmallestChar;
}

/**
 * @param {number} target
 * @param {number[]} input
 * @return {number}
 */
function binarySearchForInsertionIndex(target, input) {
    let left = 0;
    let right = input.length - 1;

    while (left <= right) {
        const middle = left + Math.floor((right - left) / 2);
        if (input[middle] > target) {
            right = middle - 1;
        } else {
            left = middle + 1;
        }
    }
    return left;
}
