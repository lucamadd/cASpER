package it.unisa.casper.statistics;

import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

public class StatsExtracterTest {

    private StatsExtracter statsExtracter;

    @Before
    public void setUp() {
        statsExtracter = mock(StatsExtracter.class);

    }

    @Test
    public void readStatisticsFileCheckTest() {
        try {
            when(statsExtracter.readStatistics(anyInt())).thenReturn(new String[1][]);
            String filename = System.getProperty("user.home") + File.separator + ".casper" + File.separator + "statistics" + File.separator + "statistics.csv";
            statsExtracter.readStatistics(anyInt());
            assertTrue(new File(filename).exists());
        } catch (IOException e) {
            fail("Unexpected IOException");
        }
    }

    @Test
    public void readStatisticsNegativeIndexTest() {
        try {
            doThrow(new Exception()).when(statsExtracter).readStatistics(-1);
            statsExtracter.readStatistics(-1);
            fail("Unexpected Exception");
        } catch (IOException e) {
            //se arriva qui il test è passato
        }
    }

    @Test
    public void readStatisticsPositiveIndexTest() {
        try {
            when(statsExtracter.readStatistics(anyInt())).thenReturn(new String[10][]);
            String[][] result = statsExtracter.readStatistics(1);
            assertEquals(10,result.length);
        } catch (IOException e) {
            fail("Unexpected Exception");
        }
    }
}