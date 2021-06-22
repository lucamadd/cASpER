package it.unisa.casper.statistics;

import it.unisa.casper.analysis.code_smell_detection.similarityComputation.CosineSimilarity;
import org.apache.commons.io.FileUtils;
import org.junit.After;
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

    private static final String STATS_DIRECTORY = System.getProperty("user.home") + File.separator +
            ".casper" + File.separator + "statistics";
    private StatsCollection mockStatsCollection;
    private StatsCollection realStatsCollection;

    @Before
    public void setUp() {
        mockStatsCollection = mock(StatsCollection.class);
        realStatsCollection = StatsCollection.getInstance();
    }

    @Test
    public void createCSVTestException() {
        try {
            doThrow(new IOException()).when(mockStatsCollection).createCSV();
            mockStatsCollection.createCSV();
            fail("Unexpected IOException");
        } catch (IOException e) {
            //se arriva qui il test è passato
        }
    }

    @Test
    public void createCSVTestExists(){
        try {
            realStatsCollection.createCSV();
            assertTrue(new File(STATS_DIRECTORY + File.separator + "statistics.csv").isFile());
        } catch (IOException e) {
            fail("Unexpected IOException");
        }
    }

}