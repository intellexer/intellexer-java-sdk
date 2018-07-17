package by.effectivesoft.intellexer.util;

import by.effectivesoft.intellexer.exception.IntellexerRuntimeException;
import by.effectivesoft.intellexer.model.Option;
import by.effectivesoft.intellexer.model.ReorderConcept;
import by.effectivesoft.intellexer.model.sentiment.Review;

import java.io.*;
import java.net.URLDecoder;
import java.util.*;

public class IntellexerTestUtils {
    private static final String CHARSET = "UTF8";
    private static final String TEST_RESPONSES_DIRECTORY = "src/test/resources/response";
    private static final String TEST_FILES_DIRECTORY = "src/test/resources/file";

    public static String readTestResponse(String filename) {
        File file = new File(TEST_RESPONSES_DIRECTORY, filename);
        StringBuilder builder;
        try {
            try (BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(file), CHARSET))) {
                builder = new StringBuilder();
                String line;
                while ((line = in.readLine()) != null) {
                    builder.append(line.trim().replace("\t\r\n", "").replace(": ", ":"));
                }
            }
        } catch (IOException e) {
            throw new IntellexerRuntimeException(e);
        }
        return builder.toString();
    }

    public static File readTestFile(String filename) {
        return new File(TEST_FILES_DIRECTORY, filename);
    }

    public static Map<String, String> extractParameters(String path) {
        Map<String, String> paramsMap = new HashMap<>();
        String[] params = path.substring(path.indexOf('?') + 1).split("&");
        for (String param : params) {
            String[] paramNameAndValue = param.split("=");
            try {
                paramsMap.put(paramNameAndValue[0], URLDecoder.decode(paramNameAndValue[1], "UTF-8"));
            } catch (UnsupportedEncodingException e) {
                throw new IntellexerRuntimeException(e);
            }
        }
        return paramsMap;
    }


    public static Option buildOption() {
        ReorderConcept reorderConcept = new ReorderConcept();
        reorderConcept.setTerm("fish");
        reorderConcept.setSelection(Arrays.asList("farmed fish", "commercial fish"));
        ReorderConcept namedEntityConcept = new ReorderConcept();
        namedEntityConcept.setTerm("fishing");
        namedEntityConcept.setSelection(Arrays.asList("sport fishing", "commercial fishing"));

        Option option = new Option();
        option.setTopics(Collections.<String>emptyList());
        option.setReorderConcepts(Collections.singletonList(reorderConcept));
        option.setReorderNamedEntities(Collections.singletonList(namedEntityConcept));

        return option;
    }

    public static List<Review> buildListOfReviews() {
        return Arrays.asList(
                new Review("12", "Hello, world! It's been a great day."),
                new Review("45", "Intellexer Summarizer has an unique feature.")
        );
    }

    public static List<String> buildListOfURLs() {
        return Arrays.asList(
                "https://jlordiales.wordpress.com/2012/12/13/the-builder-pattern-in-practice/",
                "http://tutorials.jenkov.com/java-json/jackson-objectmapper.html"
        );
    }
}
