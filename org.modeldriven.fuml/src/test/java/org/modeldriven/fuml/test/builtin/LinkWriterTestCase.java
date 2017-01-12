package org.modeldriven.fuml.test.builtin;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.modeldriven.fuml.test.FUMLTestSetup;

import fUML.Semantics.Classes.Kernel.ExtensionalValue;
import fUML.Semantics.Classes.Kernel.ExtensionalValueList;
import fUML.Semantics.Classes.Kernel.FeatureValue;
import fUML.Semantics.Classes.Kernel.Value;
import fUML.Semantics.CommonBehaviors.BasicBehaviors.ParameterValueList;
import fUML.Syntax.Classes.Kernel.Classifier;
import junit.framework.Test;

/**
 * 
 */
public class LinkWriterTestCase extends BuiltInTest {
    private static Log log = LogFactory.getLog(LinkWriterTestCase.class);
    
    public static Test suite() {
        return FUMLTestSetup.newTestSetup(LinkWriterTestCase.class);
    }
    
    public void setUp() throws Exception {
    	initTestEnv.environment.locus.extensionalValues.clear();
   }

    public void testLinkWriter() throws Exception {
        log.info("testLinkWriter");
        ParameterValueList output = initTestEnv.testSuite.testLinkWriter();
        log.info("done");
        
        assertNotNull(output);
        assertEquals("output.size()", 2, output.size());
        assertEquals("output[0].values.size()", 1, output.get(0).values.size());
        assertEquals("output[1].values.size()", 1, output.get(1).values.size());
        
        Value output1 = output.get(0).values.get(0);
        Value output2 = output.get(1).values.get(0);
        
        ExtensionalValueList extent = initTestEnv.environment.locus.getExtent(
				(Classifier)initTestEnv.environment.getElement("AB"));
        
        assertEquals("extent.size()", 2, extent.size());
        
        ExtensionalValue link1 = extent.get(0);
        assertEquals("link1.featureValues.size()", 2, link1.getFeatureValues().size());
        FeatureValue featureValue1a = link1.getFeatureValues().get(1);
        assertEquals("featureValue1a.values.size()", 1, featureValue1a.values.size());
        FeatureValue featureValue1b = link1.getFeatureValues().get(0);
        assertEquals("featureValue1b.values.size()", 1, featureValue1b.values.size());
        assertEquals("featureValue1b.position", 2, featureValue1b.position);
        
        ExtensionalValue link2 = extent.get(1);
        assertEquals("link2.featureValues.size()", 2, link2.getFeatureValues().size());
        FeatureValue featureValue2a = link2.getFeatureValues().get(1);
        assertEquals("featureValue2a.values.size()", 1, featureValue2a.values.size());
        FeatureValue featureValue2b = link2.getFeatureValues().get(0);
        assertEquals("featureValue2b.values.size()", 1, featureValue2b.values.size());
        assertEquals("featureValue2b.position", 1, featureValue2b.position);
        
        Value value1a = featureValue1a.values.get(0);
        Value value2a = featureValue2a.values.get(0);
        assertSame("value1a", output1, value1a);
        assertTrue("value1a.equals(value2a)", value1a.equals(value2a));
        
        Value value1b = featureValue1b.values.get(0);
        Value value2b = featureValue2b.values.get(0);
        assertSame("value1b", output2, value1b);
        assertFalse("!value1b.equals(value2b)", value1b.equals(value2b));
    }
    
}