package org.sbml.jsbml.ext.pmf;

import org.junit.Test;
import org.sbml.jsbml.ListOf;
import org.sbml.jsbml.Parameter;

import static org.junit.Assert.*;

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
        // Test PmfParameterPlugin#PmfParameterPlugin(PmfParameterPlugin)
        PmfParameterPlugin plugin = new PmfParameterPlugin(new Parameter());
        assertFalse(plugin.isSetMetaData());
        assertFalse(plugin.isSetListOfCorrelations());

        // Test PmfParameterPlugin#PmfParameterPlugin(Parameter)
        plugin = new PmfParameterPlugin(plugin);
        assertFalse(plugin.isSetMetaData());
        assertFalse(plugin.isSetListOfCorrelations());
    }

    /**
     * Test common methods: getPackageName(), getPrefix(), readAttribute(String, String, String) and
     * writeXMLAtributes().
     */
    @Test
    public void testCommon() {
        PmfParameterPlugin plugin = new PmfParameterPlugin(new Parameter());

        assertEquals("pmf", plugin.getPackageName());
        assertEquals("pmf", plugin.getPrefix());
        assertEquals("http://www.sbml.org/sbml/level3/version1/pmf/version1", plugin.getURI());
        assertFalse(plugin.readAttribute("a", "b", "c"));
        assertTrue(plugin.writeXMLAttributes().isEmpty());
    }

    /**
     * Test misc methods: clone(), getAllowsChildren(), getChildCount() and getChildAt().
     */
    @Test
    public void testMisc() {
        // Test clone()
        Parameter parameter = new Parameter();
        PmfParameterPlugin plugin = new PmfParameterPlugin(parameter).clone();
        assertFalse(plugin.isSetMetaData());
        assertFalse(plugin.isSetListOfCorrelations());

        // Test getAllowsChildren()
        assertTrue(plugin.getAllowsChildren());

        // Test getChildCount and getChildAt
        assertTrue(plugin.getChildCount() == 0);

        plugin.setMetaData(new ParameterMetaData());
        plugin.createCorrelation("a", 0.0);
        assertTrue(plugin.getChildCount() == 2);

        assertTrue(plugin.getChildAt(0) instanceof ParameterMetaData);
        assertTrue(plugin.getChildAt(1) instanceof ListOf<?>);
    }

    /**
     * Test parameterMetaData methods: getMetaData(), isSetMetaData(), setMetaData() and unsetMetaData().
     */
    @Test
    public void testMetaData() {
        // Test plugin without metadata
        PmfParameterPlugin plugin = new PmfParameterPlugin(new Parameter());
        assertNull(plugin.getMetaData());
        assertFalse(plugin.isSetMetaData());
        assertFalse(plugin.unsetMetaData());

        // Test plugin with metadata
        plugin.setMetaData(new ParameterMetaData());
        assertNotNull(plugin.getMetaData());
        assertTrue(plugin.isSetMetaData());
        assertTrue(plugin.unsetMetaData());
    }

    /**
     * Test correlations methods: addCorrelation(Correlation), removeCorrelation(Correlation), removeCorrelation(int),
     * createCorrelation(String, double) and getCorrelationCount().
     */
    @Test
    public void testCorrelations() {
        PmfParameterPlugin plugin = new PmfParameterPlugin(new Parameter());
        assertTrue(0 == plugin.getCorrelationCount());

        Correlation corr = new Correlation();

        assertTrue(plugin.addCorrelation(corr));
        assertTrue(1 == plugin.getCorrelationCount());

        assertTrue(plugin.removeCorrelation(corr));
        assertTrue(0 == plugin.getCorrelationCount());

        plugin.addCorrelation(corr);
        plugin.removeCorrelation(0);
        assertTrue(0 == plugin.getCorrelationCount());

        plugin.createCorrelation("a", 0);
        assertTrue(1 == plugin.getCorrelationCount());
    }

    /**
     * Test listOfCorrelations:
     * <ul>
     * <li>getListOfCorrelations()</li>
     * <li>isSetListOfCorrelations()</li>
     * <li>setListOfCorrelations(ListOf)</li>
     * <li>unsetListOfCorrelations()</li>
     * </ul>
     */
    @Test
    public void testListOfCorrelations() {
        PmfParameterPlugin plugin = new PmfParameterPlugin(new Parameter());
        assertFalse(plugin.unsetListOfCorrelations());

        // Test plugin without correlations
        assertTrue(plugin.getListOfCorrelations().isEmpty());
        assertFalse(plugin.isSetListOfCorrelations());
        assertTrue(plugin.unsetListOfCorrelations());

        // Test plugin with correlations
        plugin.createCorrelation("pH", 7.0);
        assertFalse(plugin.getListOfCorrelations().isEmpty());
        assertTrue(plugin.isSetListOfCorrelations());
        assertTrue(plugin.unsetListOfCorrelations());
    }
}
