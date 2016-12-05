import org.junit.Test;
import org.sbml.jsbml.ext.pmf.CompartmentMetaData;

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
        assertNull(metaData.source);
        assertNull(metaData.detail);

        // Test copy constructor
        metaData.source = 7;
        metaData.detail = "some details";
        CompartmentMetaData copy = new CompartmentMetaData(metaData);
        assertTrue(7 == copy.source);
        assertEquals("some details", copy.detail);
    }

    /**
     * Test {@link CompartmentMetaData#clone()}.
     */
    @Test
    public void testClone() {
        CompartmentMetaData metaData = new CompartmentMetaData();
        metaData.source = 7;
        metaData.detail = "some details";
        CompartmentMetaData clone = metaData.clone();
        assertTrue(7 == clone.source);
        assertEquals("some details", clone.detail);
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
        metaData.source = 7;
        metaData.detail = "some details";

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
        CompartmentMetaData metaData = new CompartmentMetaData();

        // Parsing an integer as the source attribute should return true and set this integer as source
        assertTrue(metaData.readAttribute("source", "pmf", "7"));
        assertTrue(7 == metaData.source);

        // Parsing a non-integer as the source attribute should return true and set 0 as source
        assertTrue(metaData.readAttribute("source", "pmf", "not-an-integer"));
        assertTrue(0 == metaData.source);

        // Parsing an string as the detail attribute should return true and set it as detail
        assertTrue(metaData.readAttribute("detail", "pmf", "some details"));
        assertEquals("some details", metaData.detail);

        // Parsing an attribute other than source and detail should return false
        assertFalse(metaData.readAttribute("someNonExistentAttribute", "pmf", "asdf"));
    }

    /**
     * Test {@link CompartmentMetaData#toString()} for empty and initialized {@link CompartmentMetaData}.
     */
    @Test
    public void test2String() {
        CompartmentMetaData metaData = new CompartmentMetaData();

        String expected = "compartmentMetaData [source=\"\" detail=\"\"]";
        assertEquals(expected, metaData.toString());

        metaData.source = 7;
        metaData.detail = "some details";
        expected = "compartmentMetaData [source=\"7\" detail=\"some details\"]";
        assertEquals(expected, metaData.toString());
    }
}
