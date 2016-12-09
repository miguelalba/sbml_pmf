package org.sbml.jsbml.ext.pmf;

import org.junit.Test;
import org.sbml.jsbml.AssignmentRule;
import org.sbml.jsbml.ListOf;
import org.sbml.jsbml.Rule;

import static org.junit.Assert.*;

/**
 * Created by de on 09.12.2016.
 */
public class PmfRulePluginTest {

    @Test
    public void testConstructors() {
        // Test PmfRulePlugin(PmfRulePlugin)
        PmfRulePlugin plugin = new PmfRulePlugin(new AssignmentRule());
        assertFalse(plugin.isSetMetaData());
        assertFalse(plugin.isSetListOfReferences());

        // Test PmfRulePlugin(Rule)
        plugin = new PmfRulePlugin(plugin);
        assertFalse(plugin.isSetMetaData());
        assertFalse(plugin.isSetListOfReferences());
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
        PmfRulePlugin plugin = new PmfRulePlugin(new AssignmentRule());

        assertEquals("pmf", plugin.getPackageName());
        assertEquals("pmf", plugin.getPrefix());
        assertEquals("http://www.sbml.org/sbml/level3/version1/pmf/version1", plugin.getURI());
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
        // Test clone()
        Rule rule = new AssignmentRule();
        PmfRulePlugin plugin = new PmfRulePlugin(rule).clone();
        assertFalse(plugin.isSetMetaData());
        assertFalse(plugin.isSetListOfReferences());

        // Test getAllowsChildren()
        assertTrue(plugin.getAllowsChildren());

        // Test getChildCount() and getChildAt()
        assertTrue(plugin.getChildCount() == 0);

        plugin.setMetaData(new RuleMetaData());
        plugin.addReference(new Reference());
        assertTrue(plugin.getChildCount() == 2);

        assertTrue(plugin.getChildAt(0) instanceof RuleMetaData);
        assertTrue(plugin.getChildAt(1) instanceof ListOf<?>);
    }

    /**
     * Test metadata methods:
     * <ul>
     * <li>getMetaData()</li>
     * <li>isSetMetaData()</li>
     * <li>setMetaData(RuleMetaData)</li>
     * <li>unsetMetaData()</li>
     * </ul>
     */
    @Test
    public void testMetaData() {
        // Test plugin without metadata
        PmfRulePlugin plugin = new PmfRulePlugin(new AssignmentRule());
        assertNull(plugin.getMetaData());
        assertFalse(plugin.isSetMetaData());
        assertFalse(plugin.unsetMetaData());

        // Test plugin with metadata
        plugin.setMetaData(new RuleMetaData());
        assertNotNull(plugin.getMetaData());
        assertTrue(plugin.isSetMetaData());
        assertTrue(plugin.unsetMetaData());
    }

    /**
     * Test reference methods:
     * <ul>
     * <li>addReference(Reference)</li>
     * <li>removeReference(Reference)</li>
     * <li>getReferenceCount()</li>
     * </ul>
     */
    @Test
    public void testReferences() {
        PmfRulePlugin plugin = new PmfRulePlugin(new AssignmentRule());
        assertTrue(0 == plugin.getReferenceCount());

        Reference ref = new Reference();

        assertTrue(plugin.addReference(ref));
        assertTrue(1 == plugin.getReferenceCount());

        assertTrue(plugin.removeReference(ref));
        assertTrue(0 == plugin.getReferenceCount());
    }

    /**
     * Test listOfReferences:
     * <ul>
     * <li>getListOfReferences()</li>
     * <li>isSetListOfReferences()</li>
     * <li>setListOfReferences(ListOf)</li>
     * <li>unsetListOfReferences()</li>
     * </ul>
     */
    @Test
    public void testListOfReferences() {
        PmfRulePlugin plugin = new PmfRulePlugin(new AssignmentRule());
        assertFalse(plugin.unsetListOfReferences());

        // Test plugin without references
        assertTrue(plugin.getListOfReferences().isEmpty());
        assertFalse(plugin.isSetListOfReferences());
        assertFalse(plugin.unsetListOfReferences());

        // Test plugin with references
        plugin.addReference(new Reference());
        assertFalse(plugin.getListOfReferences().isEmpty());
        assertTrue(plugin.isSetListOfReferences());
        assertTrue(plugin.unsetListOfReferences());
    }
}
