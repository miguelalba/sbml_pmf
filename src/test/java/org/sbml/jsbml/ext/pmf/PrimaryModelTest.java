package org.sbml.jsbml.ext.pmf;

import org.junit.Test;
import org.sbml.jsbml.ext.pmf.PrimaryModel;

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
        assertNull(new PrimaryModel().src);

        // Test parametrized constructor
        PrimaryModel pm = new PrimaryModel("model1.sbml");
        assertEquals("model1.sbml", pm.src);

        // Test copy constructor
        assertEquals("model1.sbml", new PrimaryModel(pm).src);
    }

    @Test
    public void testClone() {
        assertEquals("model1.sbml", new PrimaryModel("model1.sbml").clone().src);
    }

    /**
     * Test {@link PrimaryModel#readAttribute(String, String, String)} for the src attribute.
     */
    @Test
    public void testReadAttribute() {
        PrimaryModel pm = new PrimaryModel();
        assertTrue(pm.readAttribute("src", "pmf", "model1.sbml"));
        assertEquals("model1.sbml", pm.src);

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
        pm.src = "model1.sbml";
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
        assertEquals("PrimaryModel [src=\"\"]", pm.toString());

        // Test with filled PrimaryModel
        pm.src = "model1.sbml";
        assertEquals("PrimaryModel [src=\"model1.sbml\"]", pm.toString());
    }
}
