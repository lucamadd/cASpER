package it.unisa.casper;

import it.casper.analysis.code_smell_detection.BeanDetectionTest;
import it.casper.analysis.code_smell_detection.blob.StructuralBlobStrategyTest;
import it.casper.analysis.code_smell_detection.blob.TextualBlobStrategyTest;
import it.casper.analysis.code_smell_detection.feature_envy.TextualFeatureEnvyStrategyTest;
import it.casper.analysis.code_smell_detection.misplaced_class.StructuralMisplacedClassStrategyTest;
import it.casper.analysis.code_smell_detection.misplaced_class.TextualMisplacedClassStrategyTest;
import it.casper.analysis.code_smell_detection.promiscuous_package.StructuralPromiscuousPackageStrategyTest;
import it.casper.analysis.code_smell_detection.promiscuous_package.TextualPromiscuousPackageStrategyTest;
import it.casper.refactor.SplitPackagesTest;
import it.casper.analysis.code_smell_detection.feature_envy.StructuralFeatureEnvyStrategyTest;
import it.casper.refactor.SplitClassTest;
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
        BeanDetectionTest.class
})

public class JUnitTestSuite {
}
