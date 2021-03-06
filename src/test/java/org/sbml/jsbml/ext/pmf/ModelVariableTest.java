package org.sbml.jsbml.ext.pmf;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.sbml.jsbml.PropertyUndefinedError;
import org.sbml.jsbml.util.StringTools;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Tests for the {@link ModelVariable} class. Checks:
 * <ul>
 *     <li>Constructors</li>
 *     <li>Clone method</li>
 *     <li>{@link ModelVariable#readAttribute(String, String, String)}</li>
 *     <li>{@link ModelVariable#toString()}</li>
 *     <li>{@link ModelVariable#writeXMLAttributes()}</li>
 * </ul>
 */
public class ModelVariableTest {

    /**
     * Test constructors.
     */
    @Test
    public void testConstructors() {
        ModelVariable mv = new ModelVariable();

        // Test empty constructor
        assertFalse(mv.isSetName());
        assertFalse(mv.isSetValue());

        // Test copy constructor
        mv.setName("Temperature");
        mv.setValue(10.0);

        mv = new ModelVariable(mv);
        assertEquals("Temperature", mv.getName());
        assertEquals(10.0, mv.getValue(), 0.0);

        // Test constructor with level and version
        mv = new ModelVariable(3, 1);
        assertTrue(3 == mv.getLevel());
        assertTrue(1 == mv.getVersion());
    }

    /**
     * Test clone method.
     */
    @Test
    public void testClone() {
        ModelVariable mv = new ModelVariable();
        mv.setName("h0");
        mv.setValue(7.0);
        mv = mv.clone();

        assertEquals("h0", mv.getName());
        assertEquals(7.0, mv.getValue(), .0);
    }

    /**
     * Test {@link ModelVariable#readAttribute(String, String, String)} for the name and value attribute.
     */
    @Test
    public void testReadAttribute() {
        Logger logger = Logger.getLogger(StringTools.class);
        Level defaultLevel = logger.getLevel();
        logger.setLevel(Level.OFF);

        ModelVariable mv = new ModelVariable();

        // Parsing an string as the name should return true and set it as name
        assertTrue(mv.readAttribute("name", "pmf", "Temperature"));
        assertEquals("Temperature", mv.getName());

        // Parsing a non-double as the value attribute should return true and set Double.NaN as value
        assertTrue(mv.readAttribute("value", "pmf", "not a double"));
        assertTrue(Double.isNaN(mv.getValue()));

        // Parsing a double as the value attribute should return true and set it as value
        assertTrue(mv.readAttribute("value", "pmf", StringTools.toString(10.0)));
        assertEquals(10.0, mv.getValue(), 0.0);

        // Parsing an attribute other than name and value should return false
        assertFalse(mv.readAttribute("nonExistentAttribute", "pmf", "asdf"));

        logger.setLevel(defaultLevel);
    }

    /**
     * Test {@link ModelVariable#writeXMLAttributes()} for empty and initialized {@link ModelVariable}.
     */
    @Test
    public void testWriteXMLAttributes() {
        ModelVariable mv = new ModelVariable();

        // Test attributes with empty ModelVariable
        assertTrue(mv.writeXMLAttributes().isEmpty());

        // Test attribute with filled ModelVariable
        mv.setName("Temperature");
        mv.setValue(10.0);

        Map<String, String> expectedAttributes = new HashMap<>(2);
        expectedAttributes.put("name", "Temperature");
        expectedAttributes.put("value", StringTools.toString(10.0));

        assertEquals(expectedAttributes, mv.writeXMLAttributes());
    }

    /**
     * Test {@link ModelVariable#toString()} for empty and initialized {@link ModelVariable}.
     */
    @Test
    public void test2String() {
        ModelVariable mv = new ModelVariable();
        assertEquals("modelVariable [name=\"\" value=\"\"]", mv.toString());

        mv.setName("h0");
        mv.setValue(7.0);
        assertEquals("modelVariable [name=\"h0\" value=\"7.0\"]", mv.toString());
    }

    @Test
    public void testName() {
        ModelVariable mv = new ModelVariable();

        // test without name
        assertFalse(mv.isSetName());
        assertNull(mv.getName());
        assertFalse(mv.unsetName());

        // test with name
        mv.setName("h0");
        assertTrue(mv.isSetName());
        assertEquals("h0", mv.getName());
        assertTrue(mv.unsetName());
    }

    @Test
    public void testValue() {
        ModelVariable mv = new ModelVariable();

        // test without value
        assertFalse(mv.isSetValue());
        try {
            mv.getValue();
            fail();
        } catch (PropertyUndefinedError e) {}
        assertFalse(mv.unsetValue());

        // test with value
        mv.setValue(7.0);
        assertTrue(mv.isSetValue());
        assertEquals(7.0, mv.getValue(), .0);
        assertTrue(mv.unsetValue());
    }
}
