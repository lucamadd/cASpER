package it.unisa.casper.analysis.code_smell_detection.smellynessMetricProcessing;

import it.unisa.casper.analysis.code_smell_detection.similarityComputation.CosineSimilarity;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class SmellynessMetricTest {

    private SmellynessMetric smellynessMetric;
    private CosineSimilarity cosineSimilarity;

    @Before
    public void setUp() {
        smellynessMetric = new SmellynessMetric();
        cosineSimilarity = mock(CosineSimilarity.class);
    }

    @Test
    public void computeSmellynessExceptionTest() {
        try {
            smellynessMetric.computeSmellyness(null);
            fail("Unexpected behavior!");
        } catch (Exception e) {
            //se arriva qua vuol dire che il test è passato con successo
        }

    }
}