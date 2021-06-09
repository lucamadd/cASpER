package it.unisa.casper.analysis.code_smell;

import it.unisa.casper.analysis.code_smell_detection.strategy.CodeSmellDetectionStrategy;
import it.unisa.casper.storage.beans.MethodBean;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class FeatureEnvyCodeSmellTest {

    private FeatureEnvyCodeSmell featureEnvyCodeSmell;
    private MethodBean aMethod;
    private CodeSmellDetectionStrategy strategy;

    @Before
    public void setUp() {
        strategy = mock(CodeSmellDetectionStrategy.class);
        featureEnvyCodeSmell = mock(FeatureEnvyCodeSmell.class);
        aMethod = mock(MethodBean.class);

        //classbean ha isSmelly = true
        when(strategy.isSmelly(aMethod)).thenReturn(true);

        //se chiamo affects su questa istanza di methodBean ottengo true
        when(featureEnvyCodeSmell.affects(aMethod)).thenReturn(true);
    }

    @Test
    public void affectsTrue() {
        boolean result = featureEnvyCodeSmell.affects(aMethod);
        assertEquals(true,result);
    }

    @Test
    public void affectsFalse(){
        boolean result = featureEnvyCodeSmell.affects(any());
        assertEquals(false,result);
    }
}