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
 * Tests for the {@link CompartmentMetaData} class. Checks:
 * <ul>
 * <li>Constructors
 * <li>Clone method</li>
 * <li>{@link CompartmentMetaData#readAttribute(String, String, String)}</li>
 * <li>{@link CompartmentMetaData#toString()}</li>
 * <li>{@link CompartmentMetaData#writeXMLAttributes()}</li>
 * </ul>
 */
public class CompartmentMetaDataTest {

    /**
     * Test constructors.
     */
    @Test
    public void testConstructors() {
        // Empty constructor initializes source and detail to null
        CompartmentMetaData metaData = new CompartmentMetaData();
        assertFalse(metaData.isSetSource());
        assertFalse(metaData.isSetDetail());

        // Test constructor with level and version
        metaData = new CompartmentMetaData(3, 1);
        assertTrue(3 == metaData.getLevel());
        assertTrue(1 == metaData.getVersion());
        assertFalse(metaData.isSetSource());
        assertFalse(metaData.isSetDetail());

        // Test copy constructor
        metaData.setSource(7);
        metaData.setDetail("some details");
        CompartmentMetaData copy = new CompartmentMetaData(metaData);
        assertTrue(7 == copy.getSource());
        assertEquals("some details", copy.getDetail());
    }

    /**
     * Test {@link CompartmentMetaData#clone()}.
     */
    @Test
    public void testClone() {
        CompartmentMetaData metaData = new CompartmentMetaData();
        metaData.setSource(7);
        metaData.setDetail("some details");
        CompartmentMetaData clone = metaData.clone();
        assertTrue(7 == clone.getSource());
        assertEquals("some details", clone.getDetail());
    }

    /**
     * Test {@link CompartmentMetaData#writeXMLAttributes()} for empty and initialized {@link CompartmentMetaData}.
     */
    @Test
    public void testWriteXMLAttributes() {

        CompartmentMetaData metaData = new CompartmentMetaData();

        // Test attributes with empty meta data
        assertTrue(metaData.writeXMLAttributes().isEmpty());

        // Test attribute with filled meta data
        metaData.setSource(7);
        metaData.setDetail("some details");

        Map<String, String> expectedAttributes = new HashMap<String, String>(2);
        expectedAttributes.put("source", "7");
        expectedAttributes.put("detail", "some details");

        assertEquals(expectedAttributes, metaData.writeXMLAttributes());
    }

    /**
     * Test {@link CompartmentMetaData#readAttribute(String, String, String)} for the source and detail attributes.
     */
    @Test
    public void testReadAttribute() {
        Logger logger = Logger.getLogger(StringTools.class);
        Level defaultLevel = logger.getLevel();
        logger.setLevel(Level.OFF);

        CompartmentMetaData metaData = new CompartmentMetaData();

        // Parsing an integer as the source attribute should return true and set this integer as source
        assertTrue(metaData.readAttribute("source", "pmf", "7"));
        assertTrue(7 == metaData.getSource());

        // Parsing a non-integer as the source attribute should return true and set 0 as source
        assertTrue(metaData.readAttribute("source", "pmf", "not-an-integer"));
        assertTrue(0 == metaData.getSource());

        // Parsing an string as the detail attribute should return true and set it as detail
        assertTrue(metaData.readAttribute("detail", "pmf", "some details"));
        assertEquals("some details", metaData.getDetail());

        // Parsing an attribute other than source and detail should return false
        assertFalse(metaData.readAttribute("someNonExistentAttribute", "pmf", "asdf"));

        logger.setLevel(defaultLevel);
    }

    @Test
    public void testSource() {
        CompartmentMetaData metaData = new CompartmentMetaData();

        // Test without source
        assertFalse(metaData.isSetSource());
        try {
            metaData.getSource();
            fail();
        } catch (PropertyUndefinedError e) { }
        assertFalse(metaData.unsetSource());

        // Test with source
        metaData.setSource(7);

        assertTrue(metaData.isSetSource());
        assertTrue(7 == metaData.getSource());
        assertTrue(metaData.unsetSource());
    }

    @Test
    public void testDetail() {
        CompartmentMetaData metaData = new CompartmentMetaData();

        // Test without detail
        assertFalse(metaData.isSetDetail());
        assertNull(metaData.getDetail());
        assertFalse(metaData.unsetDetail());

        // Test with detail
        metaData.setDetail("some details");
        assertTrue(metaData.isSetDetail());
        assertEquals("some details", metaData.getDetail());
        assertTrue(metaData.unsetDetail());
    }

    /**
     * Test {@link CompartmentMetaData#toString()} for empty and initialized {@link CompartmentMetaData}.
     */
    @Test
    public void test2String() {
        CompartmentMetaData metaData = new CompartmentMetaData();

        String expected = "compartmentMetaData [source=\"\" detail=\"\"]";
        assertEquals(expected, metaData.toString());

        metaData.setSource(7);
        metaData.setDetail("some details");
        expected = "compartmentMetaData [source=\"7\" detail=\"some details\"]";
        assertEquals(expected, metaData.toString());
    }
}
