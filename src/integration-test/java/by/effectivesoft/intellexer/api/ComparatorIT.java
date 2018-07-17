package by.effectivesoft.intellexer.api;

import by.effectivesoft.intellexer.BaseIT;
import by.effectivesoft.intellexer.model.comparator.CompareResult;
import by.effectivesoft.intellexer.util.IntellexerTestUtils;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class ComparatorIT extends BaseIT {
    private Comparator comparator;

    @Before
    public void setUp() {
        comparator = new Comparator(client);
    }

    @Test
    public void compareTexts_whenValidTexts_shouldReturnNotEmptyResult() throws Exception {
        String text1 = "When encoding URI, one of the common pitfalls is encoding the complete URI. " +
                "Typically, we need to encode only the query portion of the URI.";
        String text2 = "Reserved characters in path segment are different than in query parameter values. " +
                "For example, a + sign is a valid character in path segment and therefore should not be encoded.\n" +
                "To encode the path segment, we use the UriUtils class by Spring Framework instead. UriUtils " +
                "class provides encodePath and encodePathSegment methods for encoding path and path segment " +
                "respectively.";
        CompareResult result = comparator.compareTexts(text1, text2);
        assertNotNull(result);
        assertTrue(result.getFirstDocument().getSize() == 139);
        assertTrue(result.getSecondDocument().getSize() == 374);
    }

    @Test
    public void compareURLs_whenValidURLs_shouldReturnNotEmptyResult() throws Exception {
        String url1 = "https://www.intellexer.com/about_us.html";
        String url2 = "https://www.intellexer.com/sdk_overview.html";

        CompareResult result = comparator.compareURLs(url1, url2, false);
        assertNotNull(result);
        assertTrue(result.getFirstDocument().getSize() == 1606);
        assertTrue(result.getSecondDocument().getSize() == 4939);
    }

    @Test
    public void compareURLWithFile_whenValidURLAndFile_shouldReturnNotEmptyResult() throws Exception {
        String url = "https://www.intellexer.com/about_us.html";
        File file = IntellexerTestUtils.readTestFile("1.txt");

        CompareResult result = comparator.compareURLWithFile(url, file, false);

        assertNotNull(result);
        assertTrue(result.getFirstDocument().getSize() == 1606);
        assertTrue(result.getSecondDocument().getSize() == 2055);
    }

    @Test
    public void compareFiles_whenValidFiles_shouldReturnNotEmptyResult() throws Exception {
        File file1 = IntellexerTestUtils.readTestFile("1.txt");
        File file2 = IntellexerTestUtils.readTestFile("2.txt");

        CompareResult result = comparator.compareFiles(file1, file2);
        assertNotNull(result);
    }

}