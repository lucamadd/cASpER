package it.unisa.casper.analysis.code_smell;

import it.unisa.casper.analysis.code_smell_detection.strategy.CodeSmellDetectionStrategy;
import it.unisa.casper.storage.beans.ClassBean;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MisplacedClassCodeSmellTest {

    private MisplacedClassCodeSmell misplacedClassCodeSmell;
    private ClassBean classBean;
    private CodeSmellDetectionStrategy strategy;

    @Before
    public void setUp() {
        strategy = mock(CodeSmellDetectionStrategy.class);
        misplacedClassCodeSmell = mock(MisplacedClassCodeSmell.class);
        classBean = mock(ClassBean.class);

        //classbean ha isSmelly = true
        when(strategy.isSmelly(classBean)).thenReturn(true);

        //se chiamo affects su questa istanza di classBean ottengo true
        when(misplacedClassCodeSmell.affects(classBean)).thenReturn(true);
    }

    @Test
    public void affectsTrue() {
        boolean result = misplacedClassCodeSmell.affects(classBean);
        assertEquals(true,result);
    }

    @Test
    public void affectsFalse(){
        boolean result = misplacedClassCodeSmell.affects(any());
        assertEquals(false,result);
    }
}