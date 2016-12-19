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
        assertFalse(corr.isSetName());
        assertFalse(corr.isSetValue());

        // Test constructor with level and version
        corr = new Correlation(3, 1);
        assertTrue(3 == corr.getLevel());
        assertTrue(1 == corr.getVersion());

        // Test copy constructor
        corr.setName("h0");
        corr.setValue(7.0);
        corr = new Correlation(corr);
        assertEquals("h0", corr.getName());
        assertEquals(7.0, corr.getValue(), .0);
    }

    /**
     * Test clone method.
     */
    @Test
    public void testClone() {
        Correlation corr = new Correlation();
        corr.setName("h0");
        corr.setValue(7.0);
        corr = corr.clone();

        assertEquals("h0", corr.getName());
        assertEquals(7.0, corr.getValue(), .0);
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
        assertEquals("h0", correlation.getName());

        // Parsing a non-double as the value attribute should return true and set Double.NaN as value
        assertTrue(correlation.readAttribute("name", "pmf", "not a double"));
        assertFalse(correlation.isSetValue());

        // Parsing a double as the value attribute should return true and set it as value
        assertTrue(correlation.readAttribute("value", "pmf", Double.toString(7.0)));
        assertEquals(7.0, correlation.getValue(), 0.0);

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
        correlation.setName("h0");
        correlation.setValue(7.0);

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
        String expected = "correlation [name=\"\" value=\"\"]";
        assertEquals(expected, correlation.toString());

        correlation.setName("h0");
        correlation.setValue(7.0);
        expected = "correlation [name=\"h0\" value=\"7.0\"]";
        assertEquals(expected, correlation.toString());
    }

    @Test
    public void testName() {
        Correlation corr = new Correlation();

        // test without name
        assertFalse(corr.isSetName());
        assertNull(corr.getName());
        assertFalse(corr.unsetName());

        // test with name
        corr.setName("h0");
        assertTrue(corr.isSetName());
        assertEquals("h0", corr.getName());
        assertTrue(corr.unsetName());
    }

    @Test
    public void testValue() {
        Correlation corr = new Correlation();

        // test without value
        assertFalse(corr.isSetValue());
        try {
            corr.getValue();
            fail();
        } catch (PropertyUndefinedError e) {}
        assertFalse(corr.unsetValue());

        // test with value
        corr.setValue(7.0);
        assertTrue(corr.isSetValue());
        assertEquals(7.0, corr.getValue(), .0);
        assertTrue(corr.unsetValue());
    }
}
