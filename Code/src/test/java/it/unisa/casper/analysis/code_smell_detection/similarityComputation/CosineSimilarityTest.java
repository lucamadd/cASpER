package it.unisa.casper.analysis.code_smell_detection.similarityComputation;

import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CosineSimilarityTest {

    private CosineSimilarity cosineSimilarityMock;
    private CosineSimilarity cosineSimilarity;

    @Before
    public void setUp() {
        cosineSimilarityMock = mock(CosineSimilarity.class);
        cosineSimilarity = new CosineSimilarity();
    }

    @Test
    public void computeSimilarityFileTest() throws IOException {
        when(cosineSimilarityMock.computeSimilarity(any(),any())).thenReturn(0.5d);
        String filename = System.getProperty("user.home") + File.separator + ".casper" + File.separator + "stopwordlist.txt";
        try {
            cosineSimilarityMock.computeSimilarity(any(),any());
            assertTrue(new File(filename).exists());
        } catch (IOException e) {
            fail("Unexpected IOException");
        }
    }

    

}