package it.unisa.casper;

import it.unisa.casper.analysis.code_smell.*;
import it.unisa.casper.analysis.code_smell_detection.BeanDetectionTest;
import it.unisa.casper.analysis.code_smell_detection.blob.StructuralBlobStrategyTest;
import it.unisa.casper.analysis.code_smell_detection.blob.TextualBlobStrategyTest;
import it.unisa.casper.analysis.code_smell_detection.comparator.BeanComparatorTest;
import it.unisa.casper.analysis.code_smell_detection.feature_envy.TextualFeatureEnvyStrategyTest;
import it.unisa.casper.analysis.code_smell_detection.misplaced_class.StructuralMisplacedClassStrategyTest;
import it.unisa.casper.analysis.code_smell_detection.misplaced_class.TextualMisplacedClassStrategyTest;
import it.unisa.casper.analysis.code_smell_detection.promiscuous_package.StructuralPromiscuousPackageStrategyTest;
import it.unisa.casper.analysis.code_smell_detection.promiscuous_package.TextualPromiscuousPackageStrategyTest;
import it.unisa.casper.analysis.code_smell_detection.similarityComputation.CosineSimilarityTest;
import it.unisa.casper.analysis.code_smell_detection.smellynessMetricProcessing.SmellynessMetricTest;
import it.unisa.casper.refactor.SplitPackagesTest;
import it.unisa.casper.analysis.code_smell_detection.feature_envy.StructuralFeatureEnvyStrategyTest;
import it.unisa.casper.refactor.SplitClassTest;
import it.unisa.casper.statistics.GlobalTimerTest;
import it.unisa.casper.statistics.StatsCollectionTest;
import it.unisa.casper.statistics.StatsExtracterTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)

@Suite.SuiteClasses({
        TextualFeatureEnvyStrategyTest.class,
        StructuralFeatureEnvyStrategyTest.class,
        TextualMisplacedClassStrategyTest.class,
        StructuralMisplacedClassStrategyTest.class,
        TextualBlobStrategyTest.class,
        StructuralBlobStrategyTest.class,
        TextualPromiscuousPackageStrategyTest.class,
        StructuralPromiscuousPackageStrategyTest.class,
        SplitPackagesTest.class,
        SplitClassTest.class,
        BeanDetectionTest.class,
        BlobCodeSmellTest.class,
        FeatureEnvyCodeSmellTest.class,
        MisplacedClassCodeSmellTest.class,
        PromiscuousPackageCodeSmellTest.class,
        BeanComparatorTest.class,
        CosineSimilarityTest.class,
        SmellynessMetricTest.class,
        GlobalTimerTest.class,
        StatsCollectionTest.class,
        StatsExtracterTest.class
})

public class JUnitTestSuite {
}
