package it.unisa.casper.analysis.code_smell_detection.comparator;

import org.junit.Before;
import org.junit.Test;

import static com.intellij.testFramework.UsefulTestCase.assertThrows;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BeanComparatorTest {

    private BeanComparator comparator;
    private ComparableBean comparableBean1;
    private ComparableBean comparableBean2;

    @Before
    public void setUp() {
        comparableBean1 = mock(ComparableBean.class);
        comparableBean2 = mock(ComparableBean.class);
        comparator = new BeanComparator();
    }

    @Test
    public void compareLessThan() {
        when(comparableBean1.getSimilarity()).thenReturn(0.1d);
        when(comparableBean2.getSimilarity()).thenReturn(0.9d);
        int result = comparator.compare(comparableBean1, comparableBean2);
        assertEquals(-1, result);
    }

    @Test
    public void compareEquals() {
        when(comparableBean1.getSimilarity()).thenReturn(0.5d);
        when(comparableBean2.getSimilarity()).thenReturn(0.5d);
        int result = comparator.compare(comparableBean1, comparableBean2);
        assertEquals(0, result);
    }

    @Test
    public void compareMoreThan() {
        when(comparableBean1.getSimilarity()).thenReturn(0.9d);
        when(comparableBean2.getSimilarity()).thenReturn(0.1d);
        int result = comparator.compare(comparableBean1, comparableBean2);
        assertEquals(1, result);
    }

}