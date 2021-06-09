package it.unisa.casper.analysis.code_smell;

import it.unisa.casper.analysis.code_smell_detection.strategy.CodeSmellDetectionStrategy;
import it.unisa.casper.storage.beans.PackageBean;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PromiscuousPackageCodeSmellTest {

    private PromiscuousPackageCodeSmell promiscuousPackageCodeSmell;
    private PackageBean packageBean;
    private CodeSmellDetectionStrategy strategy;

    @Before
    public void setUp(){
        strategy = mock(CodeSmellDetectionStrategy.class);
        promiscuousPackageCodeSmell = mock(PromiscuousPackageCodeSmell.class);
        packageBean = mock(PackageBean.class);

        //classbean ha isSmelly = true
        when(strategy.isSmelly(packageBean)).thenReturn(true);

        //se chiamo affects su questa istanza di classBean ottengo true
        when(promiscuousPackageCodeSmell.affects(packageBean)).thenReturn(true);
    }

    @Test
    public void affectsTrue() {
        boolean result = promiscuousPackageCodeSmell.affects(packageBean);
        assertEquals(true,result);
    }

    @Test
    public void affectsFalse(){
        boolean result = promiscuousPackageCodeSmell.affects(any());
        assertEquals(false,result);
    }
}