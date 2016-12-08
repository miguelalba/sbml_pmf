package org.sbml.jsbml.ext.pmf;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.sbml.jsbml.util.StringTools;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Tests for the {@link Correlation} class. Checks:
 * <ul>
 * <li>Constructors</li>
 * <li>Clone method</li>
 * <li>{@link Correlation#readAttribute(String, String, String)}</li>
 * <li>{@link Correlation#toString()}</li>
 * <li>{@link Correlation#writeXMLAttributes()}</li>
 * </ul>
 */
public class CorrelationTest {

    /**
     * Test constructors: empty, parametrized and copy.
     */
    @Test
    public void testConstructors() {
        // Empty constructor initializes name to null and value to Double.NaN
        Correlation corr = new Correlation();
        assertTrue(corr.name == null);
        assertTrue(Double.isNaN(corr.value));

        // Test parametrized constructor
        corr = new Correlation("h0", 7.0);
        assertEquals("h0", corr.name);
        assertEquals(7.0, corr.value, .0);

        // Test constructor with level and version
        corr = new Correlation("h0", 7.0, 3, 1);
        assertEquals("h0", corr.name);
        assertEquals(7.0, corr.value, .0);

        // Test copy constructor
        corr = new Correlation(corr);
        assertEquals("h0", corr.name);
        assertEquals(7.0, corr.value, .0);
    }

    /**
     * Test clone method.
     */
    @Test
    public void testClone() {
        Correlation corr = new Correlation("h0", 7.0).clone();
        assertEquals("h0", corr.name);
        assertEquals(7.0, corr.value, .0);
    }

    /**
     * Test {@link Correlation#readAttribute(String, String, String)} for the name and value attributes.
     */
    @Test
    public void testReadAttribute() {
        Logger logger = Logger.getLogger(StringTools.class);
        Level defaultLevel = logger.getLevel();
        logger.setLevel(Level.OFF);

        Correlation correlation = new Correlation();

        // Parsing an string as the name should return true and set it as name
        assertTrue(correlation.readAttribute("name", "pmf", "h0"));
        assertEquals("h0", correlation.name);

        // Parsing a non-double as the value attribute should return true and set Double.NaN as value
        assertTrue(correlation.readAttribute("name", "pmf", "not a double"));
        assertTrue(Double.isNaN(correlation.value));

        // Parsing a double as the value attribute should return true and set it as value
        assertTrue(correlation.readAttribute("value", "pmf", Double.toString(7.0)));
        assertEquals(7.0, correlation.value, 0.0);

        // Parsing an attribute other than name and value should return false
        assertFalse(correlation.readAttribute("someNonExistentAttribute", "pmf", "asdf"));

        logger.setLevel(defaultLevel);
    }

    /**
     * Test {@link Correlation#writeXMLAttributes()} for empty and initialized {@link Correlation}.
     */
    @Test
    public void testWriteXMLAttributes() {
        Correlation correlation = new Correlation();

        // Test attributes with empty correlation
        assertTrue(correlation.writeXMLAttributes().isEmpty());

        // Test attributes with filled correlation
        correlation.name = "h0";
        correlation.value = 7.0;

        Map<String, String> expectedAttributes = new HashMap<>(2);
        expectedAttributes.put("name", "h0");
        expectedAttributes.put("value", StringTools.toString(7.0));

        assertEquals(expectedAttributes, correlation.writeXMLAttributes());
    }

    /**
     * Test {@link Correlation#toString()} for empty and initialized {@link Correlation}.
     */
    @Test
    public void test2String() {
        Correlation correlation = new Correlation();
        String expected = "Correlation [name=\"\" value=\"\"]";
        assertEquals(expected, correlation.toString());

        correlation.name = "h0";
        correlation.value = 7.0;
        expected = "Correlation [name=\"h0\" value=\"7.0\"]";
        assertEquals(expected, correlation.toString());
    }
}
