package it.unisa.casper.statistics;

import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Field;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class GlobalTimerTest {

    private GlobalTimer mockTimer;
    private GlobalTimer realTimer;

    @Before
    public void setUp() {
        mockTimer = mock(GlobalTimer.class);
        doNothing().when(mockTimer).stopExecutionTime();
        doNothing().when(mockTimer).stopViewTime();
    }

    @Test
    public void stopViewTimeTestInvoke() {
        mockTimer.stopViewTime();
        verify(mockTimer, times(1)).stopViewTime();
    }

    @Test
    public void stopViewTimeTestStarted(){
        realTimer = new GlobalTimer();
        long timeStart = 0;
        //prendo il campo timeStart di GlobalTimer
        Field timeStartField;
        try {
            timeStartField = realTimer.getClass().getDeclaredField("timeStart");
            timeStartField.setAccessible(true);
            timeStart = timeStartField.getLong(realTimer);
        } catch (NoSuchFieldException e) {
            fail("Unexpected NoSuchFieldException");
        } catch (IllegalAccessException e) {
            fail("Unexpected IllegalAccessException");
        }
        //invoco stopViewTime()
        realTimer.stopViewTime();
        //controllo che timeStart sia maggiore di 0
        assertTrue(timeStart > 0);
    }

    @Test
    public void stopViewTimeTestElapsed(){
        realTimer = new GlobalTimer();
        try {
            //prendo il campo timeStart di GlobalTimer
            Field timeStartField = realTimer.getClass().getDeclaredField("timeStart");
            timeStartField.setAccessible(true);
            long timeStart = timeStartField.getLong(realTimer);

            //invoco stopViewTime() dopo 2 secondi
            Thread.sleep(2000);
            realTimer.stopViewTime();

            //prendo il campo timeEnd di GlobalTimer
            Field timeEndField = realTimer.getClass().getDeclaredField("timeEnd");
            timeEndField.setAccessible(true);
            long timeEnd = timeEndField.getLong(realTimer);

            //controllo che la differenza tra i campi sia almeno di 2000 ms
            assertTrue(timeEnd >= 2000 + timeStart);

        } catch (NoSuchFieldException e) {
            fail("Unexpected NoSuchFieldException");
        } catch (IllegalAccessException e) {
            fail("Unexpected IllegalAccessException");
        } catch (InterruptedException e) {
            fail("Unexpected InterruptedException");
        }
    }

    @Test
    public void stopExecutionTimeTestInvoke() {
        mockTimer.stopExecutionTime();
        verify(mockTimer, times(1)).stopExecutionTime();
    }

    @Test
    public void stopExecutionTimeTestStarted(){
        realTimer = new GlobalTimer();
        long timeStart = 0;
        //prendo il campo timeStart di GlobalTimer
        Field timeStartField;
        try {
            timeStartField = realTimer.getClass().getDeclaredField("timeStart");
            timeStartField.setAccessible(true);
            timeStart = timeStartField.getLong(realTimer);
        } catch (NoSuchFieldException e) {
            fail("Unexpected NoSuchFieldException");
        } catch (IllegalAccessException e) {
            fail("Unexpected IllegalAccessException");
        }


        //invoco stopExecutionTime()
        realTimer.stopExecutionTime();

        //controllo che timeStart sia maggiore di 0
        assertTrue(timeStart > 0);

    }

    @Test
    public void stopExecutionTimeTestElapsed(){
        realTimer = new GlobalTimer();
        try {
            //prendo il campo timeStart di GlobalTimer
            Field timeStartField = realTimer.getClass().getDeclaredField("timeStart");
            timeStartField.setAccessible(true);
            long timeStart = timeStartField.getLong(realTimer);

            //invoco stopExecutionTime() dopo 2 secondi
            Thread.sleep(2000);
            realTimer.stopExecutionTime();

            //prendo il campo timeEnd di GlobalTimer
            Field timeEndField = realTimer.getClass().getDeclaredField("timeEnd");
            timeEndField.setAccessible(true);
            long timeEnd = timeEndField.getLong(realTimer);

            //controllo che la differenza tra i campi sia almeno di 2000 ms
            assertTrue(timeEnd >= 2000 + timeStart);

        } catch (NoSuchFieldException e) {
            fail("Unexpected NoSuchFieldException");
        } catch (IllegalAccessException e) {
            fail("Unexpected IllegalAccessException");
        } catch (InterruptedException e) {
            fail("Unexpected InterruptedException");
        }
    }
}