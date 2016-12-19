package org.sbml.jsbml.ext.pmf;

import org.junit.Test;

import java.util.Collections;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Tests for the {@link PrimaryModel} class. Checks:
 * <ul>
 *     <li>Constructors</li>
 *     <li>Clone method</li>
 *     <li>{@link PrimaryModel#readAttribute(String, String, String)}</li>
 *     <li>{@link PrimaryModel#writeXMLAttributes()}</li>
 *     <li>{@link PrimaryModel#toString()}</li>
 * </ul>
 */
public class PrimaryModelTest {

    /**
     * Test constructors: empty, parametrized and copy.
     */
    @Test
    public void testConstructors() {
        // Test empty constructor
        assertFalse(new PrimaryModel().isSetSrc());

        // Test constructor with level and version
        PrimaryModel pm = new PrimaryModel(3, 1);
        assertTrue(3 == pm.getLevel());
        assertTrue(1 == pm.getVersion());

        // Test copy constructor
        pm.setSrc("model1.sbml");
        assertEquals("model1.sbml", new PrimaryModel(pm).getSrc());
    }

    @Test
    public void testClone() {
        PrimaryModel pm = new PrimaryModel(3, 1);
        pm.setSrc("model1.sbml");
        pm = pm.clone();

        assertTrue(3 == pm.getLevel());
        assertTrue(1 == pm.getVersion());
        assertEquals("model1.sbml", pm.getSrc());
    }

    /**
     * Test {@link PrimaryModel#readAttribute(String, String, String)} for the src attribute.
     */
    @Test
    public void testReadAttribute() {
        PrimaryModel pm = new PrimaryModel();
        assertTrue(pm.readAttribute("src", "pmf", "model1.sbml"));
        assertEquals("model1.sbml", pm.getSrc());

        assertFalse(pm.readAttribute("nonExistentAttribute", "pmf", "asdf"));
    }

    /**
     * Test {@link PrimaryModel#writeXMLAttributes()} for empty and initialized {@link PrimaryModel}.
     */
    @Test
    public void testWriteXMLAttributes() {
        PrimaryModel pm = new PrimaryModel();

        // test attributes with empty PrimaryModel
        assertTrue(pm.writeXMLAttributes().isEmpty());

        // test attributes with filled PrimaryModel
        pm.setSrc("model1.sbml");
        Map<String, String> expectedAttributes = Collections.singletonMap("src", "model1.sbml");
        assertEquals(expectedAttributes, pm.writeXMLAttributes());
    }

    /**
     * Test {@link PrimaryModel#toString()} for empty and initialized {@link PrimaryModel}.
     */
    @Test
    public void test2String() {
        PrimaryModel pm = new PrimaryModel();

        // Test with empty PrimaryModel
        assertEquals("primaryModel [src=\"\"]", pm.toString());

        // Test with filled PrimaryModel
        pm.setSrc("model1.sbml");
        assertEquals("primaryModel [src=\"model1.sbml\"]", pm.toString());
    }

    @Test
    public void testSrc() {
        PrimaryModel pm = new PrimaryModel();

        // Test without src
        assertFalse(pm.isSetSrc());
        assertNull(pm.getSrc());
        assertFalse(pm.unsetSrc());

        // Test with src
        pm.setSrc("model.sbml");
        assertTrue(pm.isSetSrc());
        assertEquals("model.sbml", pm.getSrc());
        assertTrue(pm.unsetSrc());
    }
}
