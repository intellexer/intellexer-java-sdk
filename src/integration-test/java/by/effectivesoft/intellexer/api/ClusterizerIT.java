package by.effectivesoft.intellexer.api;

import by.effectivesoft.intellexer.BaseIT;
import by.effectivesoft.intellexer.exception.IntellexerException;
import by.effectivesoft.intellexer.model.clusterizer.ClusterizeResult;
import by.effectivesoft.intellexer.model.param.clusterizer.ClusterizeFileParams;
import by.effectivesoft.intellexer.model.param.clusterizer.ClusterizeTextParams;
import by.effectivesoft.intellexer.model.param.clusterizer.ClusterizeURLParams;
import by.effectivesoft.intellexer.util.IntellexerTestUtils;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.*;

public class ClusterizerIT extends BaseIT {
    private Clusterizer clusterizer;

    @Before
    public void setUp() throws Exception {
        clusterizer = new Clusterizer(client);
    }

    @Test
    public void clusterizeURL_whenAllParamsAreSetToTrueConceptsRestrictionIsSet_shouldReturnNotEmptyResultAndChildrenSizeIsFive()
            throws IntellexerException {
        String url = "https://www.intellexer.com/about_us.html";
        int conceptsRestriction = 5;
        ClusterizeURLParams params = new ClusterizeURLParams.Builder()
                .setUseCache(true)
                .setLoadSentences(true)
                .setFullTextTrees(true)
                .setWrapConcepts(true)
                .setConceptsRestriction(conceptsRestriction)
                .setOptions(IntellexerTestUtils.buildOption())
                .build();

        ClusterizeResult result = clusterizer.clusterizeURL(url, params);

        assertNotNull(result);
        assertNotNull(result.getConceptTree());
        assertNotNull(result.getSentences());
        assertEquals(18, result.getSentences().size());
        assertEquals(conceptsRestriction, result.getConceptTree().getChildren().size());
        assertEquals("expert", result.getConceptTree().getChildren().get(0).getText());
    }

    @Test
    public void clusterizeURL_whenLoadSentencesFalseFullTextTreesFalseConceptsRestrictionNotSet_shouldReturnNotEmptyResultWithData()
            throws IntellexerException {
        String url = "https://www.intellexer.com/about_us.html";
        ClusterizeURLParams params = new ClusterizeURLParams.Builder()
                .setLoadSentences(false)
                .setFullTextTrees(false)
                .build();

        ClusterizeResult result = clusterizer.clusterizeURL(url, params);

        assertNotNull(result);
        assertNotNull(result.getConceptTree());
        assertNotNull(result.getSentences());
        assertTrue(result.getSentences().isEmpty());
        assertEquals(46, result.getConceptTree().getChildren().size());
    }

    @Test
    public void clusterizeURL_whenParametersAreNull_shouldReturnEmptySentencesListAndNotEmptyConceptTree()
            throws IntellexerException {
        String url = "https://www.intellexer.com/about_us.html";

        ClusterizeResult result = clusterizer.clusterizeURL(url, null);

        assertNotNull(result);
        assertNotNull(result.getConceptTree());
        assertNotNull(result.getSentences());
        assertTrue(result.getSentences().isEmpty());
        assertEquals(46, result.getConceptTree().getChildren().size());
        assertEquals("expert", result.getConceptTree().getChildren().get(0).getText());
    }

    @Test
    public void clusterizeText_whenNotEmptyParameters_shouldReturnNotEmptyResultWithData()
            throws IntellexerException {
        String text = "Computer programming (often shortened to programming) is a process that leads from an " +
                "original formulation of a computing problem to executable computer programs.";
        ClusterizeTextParams params = new ClusterizeTextParams.Builder()
                .setLoadSentences(true)
                .setFullTextTrees(true)
                .setWrapConcepts(true)
                .setTextStreamLength(1000)
                .build();

        ClusterizeResult result = clusterizer.clusterizeText(text, params);

        assertNotNull(result);
        assertNotNull(result.getConceptTree());
        assertNotNull(result.getSentences());
        assertEquals(1, result.getSentences().size());
        assertEquals(5, result.getConceptTree().getChildren().size());
        assertEquals("programming", result.getConceptTree().getChildren().get(0).getText());
        assertTrue(result.getConceptTree().getWeight() == 0);
    }

    @Test
    public void clusterizeText_whenEmptyParams_shouldReturnEmptySentencesListAndConceptTree()
            throws IntellexerException {
        String text = "Computer programming (often shortened to programming) is a process that leads from an " +
                "original formulation of a computing problem to executable computer programs.";

        ClusterizeResult result = clusterizer.clusterizeText(text, null);

        assertNotNull(result);
        assertNotNull(result.getConceptTree());
        assertNotNull(result.getSentences());
        assertTrue(result.getSentences().isEmpty());
        assertEquals(5, result.getConceptTree().getChildren().size());
        assertEquals("programming", result.getConceptTree().getChildren().get(0).getText());
    }

    @Test
    public void clusterizeFile_whenAllParamsAreSetToTrueConceptsRestrictionIsSet_shouldReturnNotEmptyResultAndChildrenSizeIsFive()
            throws IntellexerException {
        int conceptsRestriction = 2;
        ClusterizeFileParams params = new ClusterizeFileParams.Builder()
                .setLoadSentences(true)
                .setFullTextTrees(true)
                .setWrapConcepts(true)
                .setConceptsRestriction(conceptsRestriction)
                .setOptions(IntellexerTestUtils.buildOption())
                .build();
        File file = IntellexerTestUtils.readTestFile("1.txt");

        ClusterizeResult result = clusterizer.clusterizeFile(file, params);

        assertNotNull(result);
        assertNotNull(result.getConceptTree());
        assertNotNull(result.getSentences());
        assertEquals(34, result.getSentences().size());
        assertEquals(conceptsRestriction, result.getConceptTree().getChildren().size());
        assertEquals("editor", result.getConceptTree().getChildren().get(0).getText());
    }
}