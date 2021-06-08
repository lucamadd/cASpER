package it.unisa.casper.analysis.code_smell;

import it.unisa.casper.analysis.code_smell_detection.strategy.CodeSmellDetectionStrategy;
import it.unisa.casper.storage.beans.ClassBean;
import org.apache.xmlbeans.impl.xb.ltgfmt.Code;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BlobCodeSmellTest {

    private BlobCodeSmell blobCodeSmell;
    private ClassBean classBean;
    private CodeSmellDetectionStrategy strategy;

    @Before
    public void setUp(){
        strategy = mock(CodeSmellDetectionStrategy.class);
        blobCodeSmell = mock(BlobCodeSmell.class);
        classBean = mock(ClassBean.class);

        //classbean ha isSmelly = true
        when(strategy.isSmelly(classBean)).thenReturn(true);

        //se chiamo affects su questa istanza di classBean ottengo true
        when(blobCodeSmell.affects(classBean)).thenReturn(true);
    }

    @Test
    public void affectsTrue() {
        boolean result = blobCodeSmell.affects(classBean);
        assertEquals(true,result);
    }

    @Test
    public void affectsFalse(){
        boolean result = blobCodeSmell.affects(any());
        assertEquals(false,result);
    }
}