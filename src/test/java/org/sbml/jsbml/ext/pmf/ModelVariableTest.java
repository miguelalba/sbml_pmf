package org.sbml.jsbml.ext.pmf;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.junit.Test;
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
        assertNull(mv.name);
        assertTrue(Double.isNaN(mv.value));

        // Test copy constructor
        mv.name = "Temperature";
        mv.value = 10.0;
        mv = new ModelVariable(mv);
        assertEquals("Temperature", mv.name);
        assertEquals(mv.value, 10.0, 0.0);

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
        mv.name = "h0";
        mv.value = 7.0;
        mv = mv.clone();

        assertEquals(mv.name, "h0");
        assertEquals(mv.value, 7.0, .0);
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
        assertEquals("Temperature", mv.name);

        // Parsing a non-double as the value attribute should return true and set Double.NaN as value
        assertTrue(mv.readAttribute("value", "pmf", "not a double"));
        assertTrue(Double.isNaN(mv.value));

        // Parsing a double as the value attribute should return true and set it as value
        assertTrue(mv.readAttribute("value", "pmf", StringTools.toString(10.0)));
        assertEquals(10.0, mv.value, 0.0);

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
        mv.name = "Temperature";
        mv.value = 10.0;

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

        mv.name = "h0";
        mv.value = 7.0;
        assertEquals("modelVariable [name=\"h0\" value=\"7.0\"]", mv.toString());
    }
}
