package by.effectivesoft.intellexer.api;

import by.effectivesoft.intellexer.BaseTest;
import by.effectivesoft.intellexer.exception.IntellexerException;
import by.effectivesoft.intellexer.model.comparator.CompareResult;
import by.effectivesoft.intellexer.util.IntellexerTestUtils;
import com.squareup.okhttp.mockwebserver.MockResponse;
import com.squareup.okhttp.mockwebserver.RecordedRequest;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.Map;

import static org.junit.Assert.*;

public class ComparatorTest extends BaseTest {
    private static final String TEST_URL = "https://www.intellexer.com/about_us.html";

    private Comparator comparator;

    @Before
    public void setUp() {
        comparator = new Comparator(client);
    }

    @Test
    public void compareTexts_whenValidTexts_shouldSendPostRequestAndReturnNotEmptyResult()
            throws IntellexerException, InterruptedException {
        mockWebServer.enqueue(new MockResponse()
                .setBody(IntellexerTestUtils.readTestResponse("comparator/result.json"))
                .setResponseCode(200)
        );
        String text1 = "When encoding URI, one of the common pitfalls is encoding the complete URI. " +
                "Typically, we need to encode only the query portion of the URI.";
        String text2 = "Reserved characters in path segment are different than in query parameter values. " +
                "For example, a + sign is a valid character in path segment and therefore should not be encoded.\n" +
                "To encode the path segment, we use the UriUtils class by Spring Framework instead. UriUtils " +
                "class provides encodePath and encodePathSegment methods for encoding path and path segment " +
                "respectively.";


        CompareResult result = comparator.compareTexts(text1, text2);

        RecordedRequest request = mockWebServer.takeRequest();
        String path = request.getPath();
        Map<String, String> requestParams = IntellexerTestUtils.extractParameters(path);
        assertEquals("POST", request.getMethod());
        assertEquals("/compareText", path.substring(0, path.indexOf('?')));
        assertEquals(1, requestParams.size());
        assertEquals(apiKey, requestParams.get("apiKey"));
        assertNotNull(result);
        assertTrue(result.getFirstDocument().getSize() == 139);
        assertTrue(result.getSecondDocument().getSize() == 374);
    }

    @Test(expected = IllegalArgumentException.class)
    public void compareTexts_whenFirstTextIsNull_shouldThrowIllegalArgumentException() throws IntellexerException {
        comparator.compareTexts(null, TEST_URL);
    }

    @Test(expected = IllegalArgumentException.class)
    public void compareTexts_whenSecondTextIsNull_shouldThrowIllegalArgumentException() throws IntellexerException {
        comparator.compareTexts(TEST_URL, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void compareTexts_whenFirstTextIsEmpty_shouldThrowIllegalArgumentException() throws IntellexerException {
        comparator.compareTexts("", TEST_URL);
    }

    @Test(expected = IllegalArgumentException.class)
    public void compareTexts_whenSecondTextIsEmpty_shouldThrowIllegalArgumentException() throws IntellexerException {
        comparator.compareTexts(TEST_URL, "");
    }

    @Test(expected = IllegalArgumentException.class)
    public void compareTexts_whenTextsAreNull_shouldThrowIllegalArgumentException() throws IntellexerException {
        comparator.compareTexts(null, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void compareTexts_whenTextsAreEmpty_shouldThrowIllegalArgumentException() throws IntellexerException {
        comparator.compareTexts("", "");
    }

    @Test(expected = IntellexerException.class)
    public void compareTexts_whenDeserializationFailed_shouldThrowIntellexerException() throws IntellexerException {
        mockWebServer.enqueue(new MockResponse()
                .setBody(IntellexerTestUtils.readTestResponse("invalid.json"))
                .setResponseCode(200)
        );
        comparator.compareTexts("q", "q");
    }

    @Test
    public void compareURLs_whenValidURLs_shouldSendGetRequestAndReturnNotEmptyResult()
            throws IntellexerException, InterruptedException {
        mockWebServer.enqueue(new MockResponse()
                .setBody(IntellexerTestUtils.readTestResponse("comparator/result.json"))
                .setResponseCode(200)
        );

        String url1 = "https://www.intellexer.com/about_us.html";
        String url2 = "https://www.intellexer.com/sdk_overview.html";

        CompareResult result = comparator.compareURLs(url1, url2, false);

        RecordedRequest request = mockWebServer.takeRequest();
        String path = request.getPath();
        Map<String, String> requestParams = IntellexerTestUtils.extractParameters(path);
        assertEquals("GET", request.getMethod());
        assertEquals("/compareUrls", path.substring(0, path.indexOf('?')));
        assertEquals(4, requestParams.size());
        assertEquals(apiKey, requestParams.get("apiKey"));
        assertEquals(url1, requestParams.get("url1"));
        assertEquals(url2, requestParams.get("url2"));
        assertEquals("false", requestParams.get("useCache"));
        assertNotNull(result);
        assertTrue(result.getFirstDocument().getSize() == 139);
        assertTrue(result.getSecondDocument().getSize() == 374);
    }

    @Test(expected = IllegalArgumentException.class)
    public void compareURLs_whenFirstURLIsNull_shouldThrowIllegalArgumentException() throws IntellexerException {
        comparator.compareURLs(null, TEST_URL, false);
    }

    @Test(expected = IllegalArgumentException.class)
    public void compareURLs_whenSecondURLIsNull_shouldThrowIllegalArgumentException() throws IntellexerException {
        comparator.compareURLs(TEST_URL, null, false);
    }

    @Test(expected = IllegalArgumentException.class)
    public void compareURLs_whenFirstURLIsEmpty_shouldThrowIllegalArgumentException() throws IntellexerException {
        comparator.compareURLs("", TEST_URL, false);
    }

    @Test(expected = IllegalArgumentException.class)
    public void compareURLs_whenSecondURLIsEmpty_shouldThrowIllegalArgumentException() throws IntellexerException {
        comparator.compareURLs(TEST_URL, "", false);
    }

    @Test(expected = IllegalArgumentException.class)
    public void compareURLs_whenURLsAreNull_shouldThrowIllegalArgumentException() throws IntellexerException {
        comparator.compareURLs(null, null, false);
    }

    @Test(expected = IllegalArgumentException.class)
    public void compareURLs_whenURLsAreEmpty_shouldThrowIllegalArgumentException() throws IntellexerException {
        comparator.compareURLs("", "", false);
    }

    @Test(expected = IntellexerException.class)
    public void compareURLs_whenDeserializationFailed_shouldThrowIntellexerException() throws IntellexerException {
        mockWebServer.enqueue(new MockResponse()
                .setBody(IntellexerTestUtils.readTestResponse("invalid.json"))
                .setResponseCode(200)
        );
        comparator.compareURLs("q", "q", false);
    }

    @Test
    public void compareURLWithFile_whenValidURLAndFile_shouldSendPostRequestAndReturnNotEmptyResult()
            throws IntellexerException, InterruptedException {
        mockWebServer.enqueue(new MockResponse()
                .setBody(IntellexerTestUtils.readTestResponse("comparator/result.json"))
                .setResponseCode(200)
        );

        File file = IntellexerTestUtils.readTestFile("1.txt");
        CompareResult result = comparator.compareURLWithFile(TEST_URL, file, false);

        RecordedRequest request = mockWebServer.takeRequest();
        String path = request.getPath();
        Map<String, String> requestParams = IntellexerTestUtils.extractParameters(path);
        assertEquals("POST", request.getMethod());
        assertEquals("/compareUrlwithFile", path.substring(0, path.indexOf('?')));
        assertEquals(4, requestParams.size());
        assertEquals(apiKey, requestParams.get("apiKey"));
        assertEquals(TEST_URL, requestParams.get("url"));
        assertEquals("1.txt", requestParams.get("fileName"));
        assertEquals("false", requestParams.get("useCache"));
        assertNotNull(result);
        assertTrue(result.getFirstDocument().getSize() == 139);
        assertTrue(result.getSecondDocument().getSize() == 374);
    }

    @Test(expected = IntellexerException.class)
    public void compareURLWithFile_whenDeserializationFailed_shouldThrowIntellexerException()
            throws IntellexerException {
        mockWebServer.enqueue(new MockResponse()
                .setBody(IntellexerTestUtils.readTestResponse("invalid.json"))
                .setResponseCode(200)
        );
        comparator.compareURLWithFile("q", IntellexerTestUtils.readTestFile("1.txt"), false);
    }

    @Test(expected = IllegalArgumentException.class)
    public void compareURLWithFile_whenURLIsNull_shouldThrowIllegalArgumentException() throws IntellexerException {
        comparator.compareURLWithFile(null, IntellexerTestUtils.readTestFile("1.txt"), false);
    }

    @Test(expected = IllegalArgumentException.class)
    public void compareURLWithFile_whenURLIsEmpty_shouldThrowIllegalArgumentException() throws IntellexerException {
        comparator.compareURLWithFile("", IntellexerTestUtils.readTestFile("1.txt"), false);
    }

    @Test(expected = IllegalArgumentException.class)
    public void compareURLWithFile_whenFileIsNull_shouldThrowIllegalArgumentException() throws IntellexerException {
        comparator.compareURLWithFile("s", null, false);
    }

    @Test
    public void compareFiles_whenValidFiles_shouldSendPostRequestAndReturnNotEmptyResult()
            throws IntellexerException, InterruptedException {
        mockWebServer.enqueue(new MockResponse()
                .setBody(IntellexerTestUtils.readTestResponse("comparator/result.json"))
                .setResponseCode(200)
        );

        File file1 = IntellexerTestUtils.readTestFile("1.txt");
        File file2 = IntellexerTestUtils.readTestFile("2.txt");

        CompareResult result = comparator.compareFiles(file1, file2);

        RecordedRequest request = mockWebServer.takeRequest();
        String path = request.getPath();
        Map<String, String> requestParams = IntellexerTestUtils.extractParameters(path);
        assertEquals("POST", request.getMethod());
        assertEquals("/compareFiles", path.substring(0, path.indexOf('?')));
        assertEquals(4, requestParams.size());
        assertEquals(apiKey, requestParams.get("apiKey"));
        assertEquals("1.txt", requestParams.get("fileName1"));
        assertEquals("2.txt", requestParams.get("fileName2"));
        assertEquals("2055", requestParams.get("firstFileSize"));
        assertNotNull(result);
        assertTrue(result.getFirstDocument().getSize() == 139);
        assertTrue(result.getSecondDocument().getSize() == 374);
    }

    @Test(expected = IntellexerException.class)
    public void compareFiles_whenDeserializationFailed_shouldThrowIntellexerException()
            throws IntellexerException {
        mockWebServer.enqueue(new MockResponse()
                .setBody(IntellexerTestUtils.readTestResponse("invalid.json"))
                .setResponseCode(200)
        );
        File file1 = IntellexerTestUtils.readTestFile("1.txt");
        File file2 = IntellexerTestUtils.readTestFile("2.txt");

        comparator.compareFiles(file1, file2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void compareFiles_whenFirstFileIsNull_shouldThrowIllegalArgumentException() throws IntellexerException {
        comparator.compareFiles(null, IntellexerTestUtils.readTestFile("1.txt"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void compareFiles_whenSecondFileIsNull_shouldThrowIllegalArgumentException() throws IntellexerException {
        comparator.compareFiles(IntellexerTestUtils.readTestFile("1.txt"), null);
    }


}