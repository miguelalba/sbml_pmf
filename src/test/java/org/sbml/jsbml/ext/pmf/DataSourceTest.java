package org.sbml.jsbml.ext.pmf;

import org.junit.Test;

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
        // Empty constructor
        DataSource ds = new DataSource();
        assertFalse(ds.isSetSrc());

        // Constructor with level and version
        ds = new DataSource(3, 1);
        assertNull(ds.getSrc());
        assertFalse(ds.isSetSrc());
        assertTrue(3 == ds.getLevel());
        assertTrue(1 == ds.getVersion());

        // Test copy constructor
        ds = new DataSource(ds);
        assertFalse(ds.isSetSrc());
        assertTrue(3 == ds.getLevel());
        assertTrue(1 == ds.getVersion());
    }

    /**
     * Test clone method.
     */
    @Test
    public void testClone() {
        DataSource ds = new DataSource(3, 1);
        ds.setSrc("data.numl");
        ds = ds.clone();
        assertTrue(3 == ds.getLevel());
        assertTrue(1 == ds.getVersion());
        assertEquals("data.numl", ds.getSrc());
    }

    /**
     * Test {@link DataSource#readAttribute(String, String, String)}.
     */
    @Test
    public void testReadAttribute() {
        // Parsing an string as the src should return true and set it as src
        DataSource dataSource = new DataSource();
        assertTrue(dataSource.readAttribute("src", "pmf", "data.numl"));
        assertEquals("data.numl", dataSource.getSrc());

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
        ds.setSrc("data.numl");
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
        assertEquals("dataSource [src=\"\"]", ds.toString());

        // Test with initialized DataSource
        ds.setSrc("data.numl");
        assertEquals("dataSource [src=\"data.numl\"]", ds.toString());
    }

    @Test
    public void testSource() {
        DataSource ds = new DataSource();

        // Test without src
        assertFalse(ds.isSetSrc());
        assertNull(ds.getSrc());
        assertFalse(ds.unsetSrc());

        // Test with src
        ds.setSrc("data.numl");
        assertTrue(ds.isSetSrc());
        assertEquals("data.numl", ds.getSrc());
        assertTrue(ds.unsetSrc());
    }
}
