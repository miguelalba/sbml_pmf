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
 * Tests for the {@link ParameterMetaData}. Checks:
 * <ul>
 * <li>Constructors</li>
 * <li>Clone method</li>
 * <li>{@link ParameterMetaData#writeXMLAttributes()}</li>
 * <li>{@link ParameterMetaData#readAttribute(String, String, String)}</li>
 * <li>{@link ParameterMetaData#toString()}</li>
 * </ul>
 */
public class ParameterMetaDataTest {

    /**
     * Test constructors.
     */
    @Test
    public void testConstructors() {
        // Empty constructor initializes p, t, error, min and max to Double.NaN and description to null.
        ParameterMetaData metaData = new ParameterMetaData();
        assertFalse(metaData.isSetP());
        assertFalse(metaData.isSetT());
        assertFalse(metaData.isSetError());
        assertFalse(metaData.isSetDescription());

        // Constructor with level and version
        metaData = new ParameterMetaData(3, 1);
        assertTrue(3 == metaData.getLevel());
        assertTrue(1 == metaData.getVersion());
        assertFalse(metaData.isSetP());
        assertFalse(metaData.isSetT());
        assertFalse(metaData.isSetError());
        assertFalse(metaData.isSetDescription());

        // Test copy constructor
        metaData.setP(0.006);
        metaData.setError(0.471);
        metaData.setT(3.101);
        metaData.setDescription("physiological state of the microorganism");
        metaData.setMin(3.0);
        metaData.setMax(10.0);

        metaData = new ParameterMetaData(metaData);
        assertEquals(0.006, metaData.getP(), 0.0);
        assertEquals(0.471, metaData.getError(), 0.0);
        assertEquals(3.101, metaData.getT(), 0.0);
        assertEquals(3.0, metaData.getMin(), 0.0);
        assertEquals(10.0, metaData.getMax(), 0.0);
        assertEquals("physiological state of the microorganism", metaData.getDescription());
    }

    /**
     * Test clone method.
     */
    @Test
    public void testClone() {
        ParameterMetaData metaData = new ParameterMetaData();
        metaData.setP(0.006);
        metaData.setError(0.471);
        metaData.setT(3.101);
        metaData.setMin(3.0);
        metaData.setMax(10.0);
        metaData.setDescription("physiological state of the microorganism");

        metaData = metaData.clone();
        assertEquals(0.006, metaData.getP(), 0.0);
        assertEquals(0.471, metaData.getError(), 0.0);
        assertEquals(3.101, metaData.getT(), 0.0);
        assertEquals(3.0, metaData.getMin(), 0.0);
        assertEquals(10.0, metaData.getMax(), 0.0);
        assertEquals("physiological state of the microorganism", metaData.getDescription());
    }

    /**
     * Test {@link ParameterMetaData#writeXMLAttributes()} for empty and initialized {@link ParameterMetaData}.
     */
    @Test
    public void testWriteXMLAttributes() {

        ParameterMetaData metaData = new ParameterMetaData();

        // test attributes with empty ParameterMetadata
        assertTrue(metaData.writeXMLAttributes().isEmpty());

        // test attributes with filled ParameterMetadata
        Map<String, String> expectedAttributes = new HashMap<>(6);
        expectedAttributes.put("p", "2.22");
        expectedAttributes.put("t", "34.394");
        expectedAttributes.put("error", "9.922");
        expectedAttributes.put("description", "max conc");
        expectedAttributes.put("min", "3.0");
        expectedAttributes.put("max", "10.0");

        metaData.setP(2.22);
        metaData.setT(34.394);
        metaData.setError(9.922);
        metaData.setDescription("max conc");
        metaData.setMin(3.0);
        metaData.setMax(10.0);
        assertEquals(expectedAttributes, metaData.writeXMLAttributes());
    }

    @Test
    public void testReadAttribute() {
        Logger logger = Logger.getLogger(StringTools.class);
        Level defaultLevel = logger.getLevel();
        logger.setLevel(Level.OFF);

        ParameterMetaData metaData = new ParameterMetaData();

        // Parsing a non-double as the p attribute should return false
        assertFalse(metaData.readAttribute("p", "pmf", "not a double"));
        assertFalse(metaData.isSetP());

        // Parsing a double as the p attribute should return true and set it as p
        assertTrue(metaData.readAttribute("p", "pmf", "2.220"));
        assertEquals(2.220, metaData.getP(), 0.0);

        // Parsing a non-double as the t attribute should return false
        assertFalse(metaData.readAttribute("t", "pmf", "not a double"));
        assertFalse(metaData.isSetT());

        // Parsing a double as the t attribute should return true and set it as t
        assertTrue(metaData.readAttribute("t", "pmf", "34.394"));
        assertEquals(34.394, metaData.getT(), 0.0);

        // Parsing a non-double as the error attribute should return false
        assertFalse(metaData.readAttribute("error", "pmf", "not a double"));
        assertFalse(metaData.isSetError());

        // Parsing a double as the error attribute should return true and set it as error
        assertTrue(metaData.readAttribute("error", "pmf", "9.922"));
        assertEquals(9.922, metaData.getError(), 0.0);

        // Parsing an string as the description attribute should return true and set it as description
        assertTrue(metaData.readAttribute("description", "pmf", "max conc"));
        assertEquals("max conc", metaData.getDescription());

        // Parsing a non-double as the min attribute should return false
        assertFalse(metaData.readAttribute("min", "pmf", "not a double"));
        assertFalse(metaData.isSetMin());

        // Parsing a double as the min attribute should return true and set it as min
        assertTrue(metaData.readAttribute("min", "pmf", "3.0"));
        assertEquals(3.0, metaData.getMin(), 0.0);

        // Parsing a non-double as the max attribute should return false
        assertFalse(metaData.readAttribute("max", "pmf", "not a double"));
        assertFalse(metaData.isSetMax());

        // Parsing a double as the max attribute should return true and set it as max
        assertTrue(metaData.readAttribute("max", "pmf", "10.0"));
        assertEquals(10.0, metaData.getMax(), 0.0);

        // Parsing an attribute other than p, t, error, description, min or max should return false
        assertFalse(metaData.readAttribute("nonExistentAttribute", "pmf", "asdf"));

        logger.setLevel(defaultLevel);
    }

    @Test
    public void testP() {
        ParameterMetaData metaData = new ParameterMetaData();

        // Test without P
        assertFalse(metaData.isSetP());
        try {
            metaData.getP();
            fail();
        } catch (PropertyUndefinedError e) {}
        assertFalse(metaData.unsetP());

        // Test with P
        metaData.setP(2.22);
        assertTrue(metaData.isSetP());
        assertEquals(2.22, metaData.getP(), .0);
        assertTrue(metaData.unsetP());
    }

    @Test
    public void testT() {
        ParameterMetaData metaData = new ParameterMetaData();

        // Test without T
        assertFalse(metaData.isSetT());
        try {
            metaData.getT();
            fail();
        } catch (PropertyUndefinedError e) {
        }
        assertFalse(metaData.unsetT());

        // Test with T
        metaData.setT(34.394);
        assertTrue(metaData.isSetT());
        assertEquals(34.394, metaData.getT(), .0);
        assertTrue(metaData.unsetT());
    }

    @Test
    public void testError() {
        ParameterMetaData metaData = new ParameterMetaData();

        // Test without error
        assertFalse(metaData.isSetError());
        try {
            metaData.getError();
            fail();
        } catch (PropertyUndefinedError e) {}
        assertFalse(metaData.unsetError());

        // Test with error
        metaData.setError(9.922);
        assertTrue(metaData.isSetError());
        assertEquals(9.922, metaData.getError(), .0);
        assertTrue(metaData.unsetError());
    }

    @Test
    public void testDescription() {
        ParameterMetaData metaData = new ParameterMetaData();

        // Test without description
        assertFalse(metaData.isSetDescription());
        assertNull(metaData.getDescription());
        assertFalse(metaData.unsetDescription());

        // Test with description
        metaData.setDescription("max conc");
        assertTrue(metaData.isSetDescription());
        assertEquals("max conc", metaData.getDescription());
        assertTrue(metaData.unsetDescription());
    }

    @Test
    public void testMin() {
        ParameterMetaData metaData = new ParameterMetaData();

        // Test without min
        assertFalse(metaData.isSetMin());
        try {
            metaData.getMin();
            fail();
        } catch (PropertyUndefinedError e) {}
        assertFalse(metaData.unsetMin());

        // Test with min
        metaData.setMin(3.0);
        assertTrue(metaData.isSetMin());
        assertEquals(3.0, metaData.getMin(), .0);
        assertTrue(metaData.unsetMin());
    }

    // 10.0
    @Test
    public void testMax() {
        ParameterMetaData metaData = new ParameterMetaData();

        // Test without max
        assertFalse(metaData.isSetMax());
        try {
            metaData.getMax();
            fail();
        } catch (PropertyUndefinedError e) {
        }
        assertFalse(metaData.unsetMax());

        // Test with max
        metaData.setMax(10.0);
        assertTrue(metaData.isSetMax());
        assertEquals(10.0, metaData.getMax(), .0);
        assertTrue(metaData.unsetMax());
    }

    /**
     * Test {@link ParameterMetaData#toString()} for empty and initialized {@link ParameterMetaData}.
     */
    @Test
    public void test2String() {
        ParameterMetaData metaData = new ParameterMetaData();

        // Test with empty ParameterMetadata
        String expected = "parameterMetaData [p=\"\" t=\"\" error=\"\" description=\"\" min=\"\" max=\"\"]";
        assertEquals(expected, metaData.toString());

        // Test with filled ParameterMetadata
        metaData.setP(2.22);
        metaData.setT(34.394);
        metaData.setError(9.922);
        metaData.setDescription("max conc");
        metaData.setMin(3.0);
        metaData.setMax(10.0);
        expected = "parameterMetaData [p=\"2.22\" t=\"34.394\" error=\"9.922\" description=\"max conc\" min=\"3.0\" " +
                "max=\"10.0\"]";
        assertEquals(expected, metaData.toString());
    }
}
