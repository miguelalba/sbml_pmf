package org.sbml.jsbml.ext.pmf;

import org.junit.Test;
import org.sbml.jsbml.Species;

import static org.junit.Assert.*;

/**
 * Created by de on 08.12.2016.
 */
public class PmfSpeciesPluginTest {

    @Test
    public void testConstructors() {
        // Test PmfSpeciesPlugin(PmfSpeciesPlugin)
        PmfSpeciesPlugin plugin = new PmfSpeciesPlugin(new Species());
        assertFalse(plugin.isSetMetaData());

        // Test PmfSpeciesPlugin(Species)
        plugin = new PmfSpeciesPlugin(plugin);
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
        PmfSpeciesPlugin plugin = new PmfSpeciesPlugin(new Species());

        assertEquals("pmf", plugin.getPackageName());
        assertEquals("pmf", plugin.getPrefix());
        assertEquals("http://www.sbml.org/sbml/level3/version1/pmf/version1", plugin.getURI());
        assertFalse(plugin.readAttribute("a", "b", "c"));
        assertTrue(plugin.writeXMLAttributes().isEmpty());
    }

    /**
     * Test misc methods:
     * <ul>
     * <li>clone()</li>
     * <li>getAllowsChildren()</li>
     * <li>getChildCount()</li>
     * <li>getChildAt()</li>
     * </ul>
     */
    @Test
    public void testMisc() {
        // Clone
        Species species = new Species();
        PmfSpeciesPlugin plugin = new PmfSpeciesPlugin(species).clone();
        assertFalse(plugin.isSetMetaData());

        // Test getAllowsChildren
        assertTrue(plugin.getAllowsChildren());

        // Test getChildCount and getChildAt
        assertTrue(plugin.getChildCount() == 0);

        plugin.setMetaData(new SpeciesMetaData());
        assertTrue(1 == plugin.getChildCount());

        assertTrue(plugin.getChildAt(0) instanceof SpeciesMetaData);
    }

    /**
     * Test speciesMetaData methods:
     * <ul>
     * <li>getMetaData</li>
     * <li>isSetMetaData</li>
     * <li>setMetaData</li>
     * <li>unsetMetaData</li>
     * </ul>
     */
    @Test
    public void testMetaData() {
        // Test plugin without metadata
        PmfSpeciesPlugin plugin = new PmfSpeciesPlugin(new Species());
        assertNull(plugin.getMetadata());
        assertFalse(plugin.isSetMetaData());
        assertFalse(plugin.unsetMetadata());

        // Test plugin with metadata
        plugin.setMetaData(new SpeciesMetaData());
        assertNotNull(plugin.getMetadata());
        assertTrue(plugin.isSetMetaData());
        assertTrue(plugin.unsetMetadata());
    }
}
