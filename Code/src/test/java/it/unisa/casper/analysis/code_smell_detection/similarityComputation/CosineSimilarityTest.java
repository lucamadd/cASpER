package it.unisa.casper.analysis.code_smell_detection.similarityComputation;

import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

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

    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

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

    @Test
    public void readFileTest(){
        try {
            File tempFile = folder.newFile("test.txt");
            FileUtils.writeStringToFile(tempFile, "hello world");
            String result = CosineSimilarity.readFile(tempFile.getPath());
            assertEquals("hello world", result);
        } catch (IOException e) {
            fail("Unexpected IOException");
        }
    }
}