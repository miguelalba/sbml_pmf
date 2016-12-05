import org.junit.Test;
import org.sbml.jsbml.ext.pmf.ParameterMetaData;

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
        assertTrue(Double.isNaN(metaData.p));
        assertTrue(Double.isNaN(metaData.t));
        assertTrue(Double.isNaN(metaData.error));
        assertNull(metaData.description);

        // Test copy constructor
        metaData.p = 0.006;
        metaData.error = 0.471;
        metaData.t = 3.101;
        metaData.min = 3.0;
        metaData.max = 10.0;
        metaData.description = "physiological state of the microorganism";

        metaData = new ParameterMetaData(metaData);
        assertEquals(0.006, metaData.p, 0.0);
        assertEquals(0.471, metaData.error, 0.0);
        assertEquals(3.101, metaData.t, 0.0);
        assertEquals(3.0, metaData.min, 0.0);
        assertEquals(10.0, metaData.max, 0.0);
        assertEquals("physiological state of the microorganism", metaData.description);
    }

    /**
     * Test clone method.
     */
    @Test
    public void testClone() {
        ParameterMetaData metaData = new ParameterMetaData();
        metaData.p = 0.006;
        metaData.error = 0.471;
        metaData.t = 3.101;
        metaData.min = 3.0;
        metaData.max = 10.0;
        metaData.description = "physiological state of the microorganism";

        metaData = metaData.clone();
        assertEquals(0.006, metaData.p, 0.0);
        assertEquals(0.471, metaData.error, 0.0);
        assertEquals(3.101, metaData.t, 0.0);
        assertEquals(3.0, metaData.min, 0.0);
        assertEquals(10.0, metaData.max, 0.0);
        assertEquals("physiological state of the microorganism", metaData.description);
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

        metaData.p = 2.22;
        metaData.t = 34.394;
        metaData.error = 9.922;
        metaData.description = "max conc";
        metaData.min = 3.0;
        metaData.max = 10.0;
        assertEquals(expectedAttributes, metaData.writeXMLAttributes());
    }

    @Test
    public void testReadAttribute() {
        ParameterMetaData metaData = new ParameterMetaData();

        // Parsing a non-double as the p attribute should return true and set Double.NaN as p
        assertTrue(metaData.readAttribute("p", "pmf", "not a double"));
        assertTrue(Double.isNaN(metaData.p));

        // Parsing a double as the p attribute should return true and set it as p
        assertTrue(metaData.readAttribute("p", "pmf", "2.220"));
        assertEquals(2.220, metaData.p, 0.0);

        // Parsing a non-double as the t attribute should return true and set Double.NaN as t
        assertTrue(metaData.readAttribute("t", "pmf", "not a double"));
        assertTrue(Double.isNaN(metaData.t));

        // Parsing a double as the t attribute should return true and set it as t
        assertTrue(metaData.readAttribute("t", "pmf", "34.394"));
        assertEquals(34.394, metaData.t, 0.0);

        // Parsing a non-double as the error attribute should return true and set Double.NaN as error
        assertTrue(metaData.readAttribute("error", "pmf", "not a double"));
        assertTrue(Double.isNaN(metaData.error));

        // Parsing a double as the error attribute should return true and set it as error
        assertTrue(metaData.readAttribute("error", "pmf", "9.922"));
        assertEquals(9.922, metaData.error, 0.0);

        // Parsing an string as the description attribute should return true and set it as description
        assertTrue(metaData.readAttribute("description", "pmf", "max conc"));
        assertEquals("max conc", metaData.description);

        // Parsing a non-double as the min attribute should return true and set it as min
        assertTrue(metaData.readAttribute("min", "pmf", "not a double"));
        assertTrue(Double.isNaN(metaData.min));

        // Parsing a double as the min attribute should return true and set it as min
        assertTrue(metaData.readAttribute("min", "pmf", "3.0"));
        assertEquals(3.0, metaData.min, 0.0);

        // Parsing a non-double as the max attribute should return true and set it as max
        assertTrue(metaData.readAttribute("max", "pmf", "not a double"));
        assertTrue(Double.isNaN(metaData.max));

        // Parsing a double as the max attribute should return true and set it as max
        assertTrue(metaData.readAttribute("max", "pmf", "10.0"));
        assertEquals(10.0, metaData.max, 0.0);

        // Parsing an attribute other than p, t, error, description, min or max should return false
        assertFalse(metaData.readAttribute("nonExistentAttribute", "pmf", "asdf"));
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
        metaData.p = 2.22;
        metaData.t = 34.394;
        metaData.error = 9.922;
        metaData.description = "max conc";
        metaData.min = 3.0;
        metaData.max = 10.0;
        expected = "parameterMetaData [p=\"2.22\" t=\"34.394\" error=\"9.922\" description=\"max conc\" min=\"3.0\" " +
                "max=\"10.0\"]";
        assertEquals(expected, metaData.toString());
    }
}
