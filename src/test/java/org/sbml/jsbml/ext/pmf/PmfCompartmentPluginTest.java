package org.sbml.jsbml.ext.pmf;

import org.junit.Test;
import org.sbml.jsbml.Compartment;

import static org.junit.Assert.*;


/**
 * Created by miguelalba on 06/12/2016.
 */
public class PmfCompartmentPluginTest {

    @Test
    public void testConstructors() {
        // Test PmfParameterPlugin(PmfCompartmentPlugin)
        PmfCompartmentPlugin plugin = new PmfCompartmentPlugin(new Compartment());
        assertFalse(plugin.isSetMetaData());

        // Test PmfParameterPlugin(Compartment)
        plugin = new PmfCompartmentPlugin(plugin);
        assertFalse(plugin.isSetMetaData());
    }

    /**
     * Test common methods:
     * <ul>
     * <li>getPackageName()</li>
     * <li>getPrefix()</li>
     * <li>readAttribute(String,String,String)</li>
     * <li>writeXMLAttributes()</li>
     * </ul>
     */
    @Test
    public void testCommon() {
        PmfCompartmentPlugin plugin = new PmfCompartmentPlugin(new Compartment());

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
        Compartment compartment = new Compartment();
        PmfCompartmentPlugin plugin = new PmfCompartmentPlugin(compartment).clone();
        assertFalse(plugin.isSetMetaData());

        // Test getAllowsChildren()
        assertTrue(plugin.getAllowsChildren());

        // Test getChildCount and getChildAt
        assertTrue(plugin.getChildCount() == 0);

        plugin.setMetaData(new CompartmentMetaData());
        assertTrue(1 == plugin.getChildCount());

        assertTrue(plugin.getChildAt(0) instanceof CompartmentMetaData);
    }

    /**
     * Test parameterMetaData methods:
     * <ul>
     * <li>getMetaData()</li>
     * <li>isSetMetaData()</li>
     * <li>setMetaData()</li>
     * <li>unsetMetaData()</li>
     * </ul>
     */
    @Test
    public void testMetaData() {
        // Test plugin without metadata
        PmfCompartmentPlugin plugin = new PmfCompartmentPlugin(new Compartment());
        assertNull(plugin.getMetaData());
        assertFalse(plugin.isSetMetaData());
        assertFalse(plugin.unsetMetaData());

        // Test plugin with metadata
        plugin.setMetaData(new CompartmentMetaData());
        assertNotNull(plugin.getMetaData());
        assertTrue(plugin.isSetMetaData());
        assertTrue(plugin.unsetMetaData());
    }
}
