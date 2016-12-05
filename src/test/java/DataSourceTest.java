import org.junit.Test;
import org.sbml.jsbml.ext.pmf.DataSource;

import java.util.Collections;
import java.util.Map;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNull;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Tests for the {@link DataSource} class. Checks
 * <ul>
 * <li>Constructors</li>
 * </ul>
 */
public class DataSourceTest {

    /**
     * Test constructors.
     */
    @Test
    public void testConstructors() {
        // Empty constructor initializes src to null
        DataSource ds = new DataSource();
        assertNull(ds.src);

        // Constructor with level and version
        ds = new DataSource(3, 1);
        assertNull(ds.src);

        // Constructor with src, level and version
        ds = new DataSource("data.numl", 3, 1);
        assertEquals("data.numl", ds.src);

        // Test parametrized constructor
        ds = new DataSource("data.numl");
        assertEquals("data.numl", ds.src);

        // Test copy constructor
        ds = new DataSource(ds);
        assertEquals("data.numl", ds.src);
    }

    /**
     * Test clone method.
     */
    @Test
    public void testClone() {
        assertEquals(new DataSource("data.numl").clone().src, "data.numl");
    }

    /**
     * Test {@link DataSource#readAttribute(String, String, String)}.
     */
    @Test
    public void testReadAttribute() {
        // Parsing an string as the src should return true and set it as src
        DataSource dataSource = new DataSource();
        assertTrue(dataSource.readAttribute("src", "pmf", "data.numl"));
        assertEquals("data.numl", dataSource.src);

        // read error: unrecognized attribute name
        assertFalse(dataSource.readAttribute("nonExistentAttribute", "pmf", "asdf"));
    }

    /**
     * Test {@link DataSource#writeXMLAttributes()}.
     */
    @Test
    public void testWriteXMLAttributes() {

        DataSource ds = new DataSource();

        // Test attribute with empty DataSource
        assertTrue(ds.writeXMLAttributes().isEmpty());

        // Test attribute with filled DataSource
        ds.src = "data.numl";
        Map<String, String> expectedAttributes = Collections.singletonMap("src", "data.numl");
        assertEquals(expectedAttributes, ds.writeXMLAttributes());
    }

    /**
     * Test {@link DataSource#toString()} for empty and initialized {@link DataSource}.
     */
    @Test
    public void test2String() {
        // Test with empty DataSource
        DataSource ds = new DataSource();
        assertEquals("DataSource [src=\"\"]", ds.toString());

        // Test with initialized DataSource
        ds.src = "data.numl";
        assertEquals("DataSource [src=\"data.numl\"]", ds.toString());
    }
}
