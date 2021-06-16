package it.unisa.casper.statistics;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class GlobalTimerTest {

    private GlobalTimer timer;

    @Before
    public void setUp() {
        timer = mock(GlobalTimer.class);
        doNothing().when(timer).stopViewTime();
        doNothing().when(timer).stopExecutionTime();

    }

    @Test
    public void stopViewTimeTest() {
        timer.stopViewTime();
        verify(timer, times(1)).stopViewTime();
    }

    @Test
    public void stopExecutionTimeTest() {
        timer.stopExecutionTime();
        verify(timer, times(1)).stopExecutionTime();
    }
}