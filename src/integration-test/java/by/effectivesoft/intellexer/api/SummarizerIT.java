package by.effectivesoft.intellexer.api;

import by.effectivesoft.intellexer.BaseIT;
import by.effectivesoft.intellexer.exception.IntellexerException;
import by.effectivesoft.intellexer.model.DocumentStructure;
import by.effectivesoft.intellexer.model.param.summarizer.SummarizeFileParams;
import by.effectivesoft.intellexer.model.param.summarizer.SummarizeMultipleURLParams;
import by.effectivesoft.intellexer.model.param.summarizer.SummarizeTextParams;
import by.effectivesoft.intellexer.model.param.summarizer.SummarizeURLParams;
import by.effectivesoft.intellexer.model.summarizer.SummarizeMultipleDocumentResult;
import by.effectivesoft.intellexer.model.summarizer.SummarizeResult;
import by.effectivesoft.intellexer.util.IntellexerTestUtils;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SummarizerIT extends BaseIT {
    private Summarizer summarizer;

    @Before
    public void setUp() throws Exception {
        summarizer = new Summarizer(client);
    }

    @Test
    public void summarizeURL_whenAllParamsAreTrueAndAutodetectStructure_shouldReturnNotEmptyResult()
            throws IntellexerException {
        String url = "https://www.intellexer.com/about_us.html";

        SummarizeURLParams params = new SummarizeURLParams.Builder()
                .setUseCache(true)
                .setFullTextTrees(true)
                .setOptions(IntellexerTestUtils.buildOption())
                .setLoadNamedEntityTree(true)
                .setLoadConceptsTree(true)
                .setStructure(DocumentStructure.AUTODETECT)
                .build();

        SummarizeResult result = summarizer.summarizeURL(url, params);
        assertNotNull(result);
        assertNotNull(result.getDocument());
        assertNotNull(result.getConceptTree());
        assertNotNull(result.getNamedEntityTree());
        assertNotNull(result.getItems());
        assertTrue(result.getItems().isEmpty());
        assertTrue(result.getTotalItemsCount() == 18);
        assertEquals("about_us.html.html", result.getDocument().getTitle());
    }

    @Test
    public void summarizeURL_whenParamsAreNull_shouldReturnNotEmptyResultAndNullTreesAndItems()
            throws IntellexerException {
        String url = "https://www.intellexer.com/about_us.html";

        SummarizeResult result = summarizer.summarizeURL(url, null);
        assertNotNull(result);
        assertNotNull(result.getDocument());
        assertNull(result.getConceptTree());
        assertNull(result.getNamedEntityTree());
        assertNull(result.getItems());
        assertTrue(result.getTotalItemsCount() == 0);
        assertEquals("about_us.html.html", result.getDocument().getTitle());
    }

    @Test
    public void summarizeText_whenAllParamsAreSet_shouldReturnNotEmptyResult() throws IntellexerException {
        String text = "Computer programming (often shortened to programming) is a process that leads from an " +
                "original formulation of a computing problem to executable computer programs.";
        SummarizeTextParams params = new SummarizeTextParams.Builder()
                .setFullTextTrees(true)
                .setOptions(IntellexerTestUtils.buildOption())
                .setFullTextTrees(true)
                .setLoadConceptsTree(true)
                .setWrapConcepts(true)
                .setLoadNamedEntityTree(true)
                .setUsePercentRestriction(true)
                .build();

        SummarizeResult result = summarizer.summarizeText(text, params);

        assertNotNull(result);
        assertNotNull(result.getDocument());
        assertNotNull(result.getConceptTree());
        assertNotNull(result.getNamedEntityTree());
        assertNotNull(result.getItems());
        assertTrue(result.getItems().isEmpty());
        assertEquals(DocumentStructure.GENERAL, result.getStructure());
    }

    @Test
    public void summarizeText_whenParamsAreNull_shouldReturnNotEmptyResultAndNullTreesAndItems()
            throws IntellexerException {
        String text = "Computer programming (often shortened to programming) is a process that leads from an " +
                "original formulation of a computing problem to executable computer programs.";

        SummarizeResult result = summarizer.summarizeText(text, null);

        assertNotNull(result);
        assertNotNull(result.getDocument());
        assertNull(result.getConceptTree());
        assertNull(result.getNamedEntityTree());
        assertNull(result.getItems());
        assertTrue(result.getTotalItemsCount() == 0);
    }

    @Test
    public void summarizeFile_whenAllParamsAreSet_shouldReturnNotEmptyResult() throws IntellexerException {

        SummarizeFileParams params = new SummarizeFileParams.Builder()
                .setFullTextTrees(true)
                .setOptions(IntellexerTestUtils.buildOption())
                .setFullTextTrees(true)
                .setLoadConceptsTree(true)
                .setWrapConcepts(true)
                .setLoadNamedEntityTree(true)
                .setUsePercentRestriction(true)
                .build();

        SummarizeResult result = summarizer.summarizeFile(IntellexerTestUtils.readTestFile("1.txt"), params);

        assertNotNull(result);
        assertNotNull(result.getDocument());
        assertNotNull(result.getConceptTree());
        assertNotNull(result.getNamedEntityTree());
        assertNotNull(result.getItems());
        assertTrue(result.getItems().isEmpty());
        assertEquals(DocumentStructure.NEWS_ARTICLE, result.getStructure());
        assertTrue(result.getTotalItemsCount() == 34);
    }

    @Test
    public void summarizeFile_whenParamsAreNull_shouldReturnNotEmptyResultAndNullTreesAndItems()
            throws IntellexerException {

        SummarizeResult result = summarizer.summarizeFile(IntellexerTestUtils.readTestFile("1.txt"), null);

        assertNotNull(result);
        assertNotNull(result.getDocument());
        assertNull(result.getConceptTree());
        assertNull(result.getNamedEntityTree());
        assertNull(result.getItems());
        assertTrue(result.getTotalItemsCount() == 0);
    }

    @Test
    public void summarizeMultipleURLs_whenAllParamsAreSet_shouldReturnNotEmptyResult() throws IntellexerException {
        SummarizeMultipleURLParams params = new SummarizeMultipleURLParams.Builder()
                .setFullTextTrees(true)
                .setOptions(IntellexerTestUtils.buildOption())
                .setFullTextTrees(true)
                .setLoadConceptsTree(true)
                .setLoadNamedEntityTree(true)
                .setUsePercentRestriction(true)
                .build();

        SummarizeMultipleDocumentResult result = summarizer.summarizeMultipleURLs(
                IntellexerTestUtils.buildListOfURLs(),
                params
        );

        assertNotNull(result);
        assertNotNull(result.getDocuments());
        assertNotNull(result.getConceptTree());
        assertNotNull(result.getNamedEntityTree());
        assertNotNull(result.getItems());
        assertTrue(result.getItems().isEmpty());
        assertTrue(result.getDocuments().size() == 2);
    }

    @Test
    public void summarizeMultipleURLs_whenParamsAreNull_shouldReturnNotEmptyResultAndNullTrees() throws IntellexerException {
        SummarizeMultipleDocumentResult result = summarizer.summarizeMultipleURLs(
                IntellexerTestUtils.buildListOfURLs(),
                null
        );

        assertNotNull(result);
        assertNotNull(result.getDocuments());
        assertNull(result.getConceptTree());
        assertNull(result.getNamedEntityTree());
        assertNotNull(result.getItems());
        assertTrue(result.getItems().isEmpty());
        assertTrue(result.getDocuments().size() == 2);
    }
}