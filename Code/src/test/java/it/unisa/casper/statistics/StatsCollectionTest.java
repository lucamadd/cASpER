package it.unisa.casper.statistics;

import it.unisa.casper.analysis.code_smell_detection.similarityComputation.CosineSimilarity;
import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;

public class StatsCollectionTest {

    private StatsCollection statsCollection;

    @Before
    public void setUp() {
        statsCollection = mock(StatsCollection.class);
    }

    @Test
    public void createCSVTest() {
        try {
            doThrow(new IOException()).when(statsCollection).createCSV();
            statsCollection.createCSV();
            fail("Unexpected IOException");
        } catch (IOException e) {
            //se arriva qui il test è passato
        }
    }



}