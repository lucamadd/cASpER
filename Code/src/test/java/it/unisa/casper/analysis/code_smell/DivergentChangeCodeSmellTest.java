package it.unisa.casper.analysis.code_smell;

import it.unisa.casper.analysis.code_smell_detection.strategy.CodeSmellDetectionStrategy;
import it.unisa.casper.storage.beans.ClassBean;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class DivergentChangeCodeSmellTest {

    private DivergentChangeCodeSmell divergentChangeCodeSmell;
    private ClassBean classBean;
    private CodeSmellDetectionStrategy strategy;

    @Before
    public void setUp() {
        strategy = mock(CodeSmellDetectionStrategy.class);
        divergentChangeCodeSmell = mock(DivergentChangeCodeSmell.class);
        classBean = mock(ClassBean.class);

        //classbean ha isSmelly = true
        when(strategy.isSmelly(classBean)).thenReturn(true);

        //se chiamo affects su questa istanza di classBean ottengo true
        when(divergentChangeCodeSmell.affects(classBean)).thenReturn(true);
    }

    @Test
    public void affectsTrue() {
        boolean result = divergentChangeCodeSmell.affects(classBean);
        assertEquals(true,result);
    }

    @Test
    public void affectsFalse(){
        boolean result = divergentChangeCodeSmell.affects(any());
        assertEquals(false,result);
    }
}