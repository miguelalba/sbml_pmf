package org.sbml.jsbml.ext.pmf;

import org.junit.Test;
import org.sbml.jsbml.Parameter;

import static org.junit.Assert.fail;

/**
 * Test the {@link PmfParameterPlugin} class. Checks:
 * <ul>
 * <li>Constructors</li>
 * <li>Common methods</li>
 * <li>Misc methods</li>
 * <li>metaData methods</li>
 * <li>correlations methods</li>
 * <li>listOfCorrelations methods</li>
 * </ul>
 */
public class PmfParameterPluginTest {

    /**
     * Test constructors: copy and with {@link Parameter} parameter.
     */
    @Test
    public void testConstructors() {
        // TODO: Test PmfParameterPlugin#PmfParameterPlugin(PmfParameterPlugin)
        // TODO: Test PmfParameterPlugin#PmfParameterPlugin(Parameter)
        fail("To be implemented");
    }

    /**
     * Test common methods: getPackageName(), getPrefix(), readAttribute(String, String, String) and
     * writeXMLAtributes().
     */
    @Test
    public void testCommon() {
        // TODO: Test PmfParameterPlugin#getPackageName()
        // TODO: Test PmfParameterPlugin#getPrefix()
        // TODO: Test PmfParameterPlugin#readAttribute(String, String, String)
        // TODO: Test PmfParameterPlugin#writeXMLAttributes()
        fail("To be implemented");
    }

    /**
     * Test misc methods: clone(), getParent(), getAllowsChildren(), getChildCount() and getChildAt().
     */
    @Test
    public void testMisc() {
        // TODO: Test PmfParameterPlugin#clone()
        // TODO: Test PmfParameterPlugin#getParent()
        // TODO: Test PmfParameterPlugin#getAllowsChildren()
        // TODO: Test PmfParameterPlugin#getChildCount()
        // TODO: Test PmfParameterPlugin#getChildAt()
        fail("To be implemented");
    }

    /**
     * Test parameterMetaData methods: getMetaData(), isSetMetaData(), setMetaData() and unsetMetaData().
     */
    @Test
    public void testMetaData() {
        fail("Test PmfParameterPlugin#getMetaData()");
        fail("Test PmfParameterPlugin#isSetMetaData()");
        fail("Test PmfParameterPlugin#setMetaData()");
        fail("Test PmfParameterPlugin#unsetMetaData()");
    }

    /**
     * Test correlations methods: addCorrelation(Correlation), removeCorrelation(Correlation), removeCorrelation(int),
     * createCorrelation(String, double) and getCorrelationCount().
     */
    @Test
    public void testCorrelations() {
        fail("Test PmfParameterPlugin#addCorrelation(Correlation)");
        fail("Test PmfParameterPlugin#removeCorrelation(Correlation)");
        fail("Test PmfParameterPlugin#removeCorrelation(int)");
        fail("Test PmfParameterPlugin#createCorrelation(String, double)");
        fail("Test PmfParameterPlugin#getCorrelationCount()");
    }

    /**
     * Test listOfCorrelations: getListOfCorrelations(), isSetListOfCorrelations(), setListOfCorrelations(ListOf) and
     * unsetListOfCorrelations().
     */
    @Test
    public void testListOfCorrelations() {
        fail("Test PmfParameterPlugin#getListOfCorrelations()");
        fail("Test PmfParameterPlugin#isSetListOfCorrelations()");
        fail("Test PmfParameterPlugin#setListOfCorrelations(ListOf)");
        fail("Test PmfParameterPlugin#unsetListOfCorrelations()");
    }
}
