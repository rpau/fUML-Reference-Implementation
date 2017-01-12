package org.modeldriven.fuml.test.builtin;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.modeldriven.fuml.test.FUMLTest;
import org.modeldriven.fuml.test.builtin.environment.InitTestEnvironment;

import fUML.Semantics.Classes.Kernel.ExtensionalValueList;
import fUML.Syntax.Classes.Kernel.Classifier;
import fUML.Syntax.Classes.Kernel.Element;


/**
  */
public abstract class BuiltInTest extends FUMLTest {

	protected Log log = LogFactory.getLog(getClass().getName());
	protected InitTestEnvironment initTestEnv;

    public BuiltInTest() {
    	initTestEnv = new InitTestEnvironment();
    }
    
    public BuiltInTest(String name) {
        super(name);
    	initTestEnv = new InitTestEnvironment();
    }

    
    public ExtensionalValueList findExtent(String classifierName) {
    	Element element = this.findElement(classifierName);
    	if (element == null)
    	    return null;
        return initTestEnv.environment.locus.getExtent((Classifier)element);    	
    }

    public Element findElement(String classifierName) {
    	
        Element element = initTestEnv.environment.getElement(classifierName);

        if (element == null) {
            return null; // assert not here but at a 
        }

        if (!(element instanceof Classifier)) {
        	log.warn(classifierName + " is not a classifier.");
            return null;
        }
        return element;    	
    }
    
}
