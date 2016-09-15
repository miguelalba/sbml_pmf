import org.junit.Assert;
import org.junit.Test;
import org.sbml.jsbml.ext.pmf.DataSource;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNull;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by de on 12.09.2016.
 */
public class DataSourceTest {

    @Test
    public void testConstructors() {
        // test empty constructor
        assertNull(new DataSource().src);

        // test parametrized constructor
        assertEquals(new DataSource("data.numl").src, "data.numl");

        // test copy constructor
        assertEquals(new DataSource(new DataSource("data.numl")).src, "data.numl");
    }

    @Test
    public void testClone() {
        assertEquals(new DataSource("data.numl").clone().src, "data.numl");
    }

    @Test
    public void testReadAttribute() throws Exception {
        DataSource dataSource = new DataSource();
        assertTrue(dataSource.readAttribute("src", "pmf", "data.numl"));
        // read error: unrecognized attribute name
        assertFalse(dataSource.readAttribute("nonExistentAttribute", "pmf", "asdf"));
    }

    @Test
    public void testWriteXMLAttributes() throws Exception {

        // test attribute with empty DataSource
        assertTrue(new DataSource().writeXMLAttributes().isEmpty());

        // test attribute with filled DataSource
        Map<String, String> expectedAttributes = Collections.singletonMap("src", "data.numl");
        Map<String, String> obtainedAttributes = new DataSource("data.numl").writeXMLAttributes();
        assertEquals(expectedAttributes, obtainedAttributes);
    }

    // test2String
    @Test
    public void test2String() {
        assertEquals("DataSource [src=\"\"]", new DataSource().toString());
        assertEquals("DataSource [src=\"data.numl\"]", new DataSource("data.numl").toString());
    }
}
