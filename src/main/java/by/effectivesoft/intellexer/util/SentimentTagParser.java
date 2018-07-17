package by.effectivesoft.intellexer.util;

import by.effectivesoft.intellexer.model.sentiment.SentimentWord;
import by.effectivesoft.intellexer.model.sentiment.SentimentWordType;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SentimentTagParser {
    private static final String BASE_TAG_REGEX = "<%s( w=\"(.+?)\")?>(.+?)</%s>";

    private SentimentTagParser() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * Extract words with their weight from tags
     * @param text Text with tags to extract words
     * @param type Type of words to extract (positive, negative ir sentiment object)
     * @return List of extracted words with weights
     */
    public static List<SentimentWord> extractWordsWithWeight(String text, SentimentWordType type) {
        if (type.equals(SentimentWordType.SENTIMENT_OBJECT)) {
            throw new IllegalArgumentException("Sentiment objects does not have weight");
        }
        Pattern pattern = Pattern.compile(String.format(BASE_TAG_REGEX, type.getTagName(), type.getTagName()));
        List<SentimentWord> sentimentWords = new ArrayList<>();
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {
            SentimentWord sentimentWord = new SentimentWord(matcher.group(3), Double.valueOf(matcher.group(2)));
            sentimentWords.add(sentimentWord);
        }
        return sentimentWords;
    }

    /**
     * Extract words from tags
     * @param text Text with tags to extract words
     * @param type Type of words to extract (positive, negative ir sentiment object)
     * @return List of extracted words
     */
    public static List<String> extractWords(String text, SentimentWordType type) {
        Pattern pattern = Pattern.compile(String.format(BASE_TAG_REGEX, type.getTagName(), type.getTagName()));
        List<String> tagValues = new ArrayList<>();
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {
            tagValues.add(matcher.group(3));
        }
        return tagValues;
    }
}
