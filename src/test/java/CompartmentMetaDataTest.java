import org.junit.Test;
import org.sbml.jsbml.ext.pmf.CompartmentMetaData;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created by de on 15.09.2016.
 */
public class CompartmentMetaDataTest {

    @Test
    public void testWriteXMLAttributes() {

        // test attributes with empty meta data
        assertTrue(new CompartmentMetaData().writeXMLAttributes().isEmpty());

        // test attributes with filled meta data
        CompartmentMetaData metadata = new CompartmentMetaData();
        metadata.source = 7;
        metadata.detail = "some details";

        Map<String, String> expectedAttributes = new HashMap<String, String>();
        expectedAttributes.put("source", "7");
        expectedAttributes.put("detail", "some details");

        assertEquals(expectedAttributes, metadata.writeXMLAttributes());
    }

    @Test
    public void testReadAttribute() {
        CompartmentMetaData metaData = new CompartmentMetaData();

        assertTrue(metaData.readAttribute("source", "pmf", "7"));
        assertTrue(7 == metaData.source);

        assertTrue(metaData.readAttribute("detail", "pmf", "some details"));
        assertEquals("some details", metaData.detail);

        assertFalse(metaData.readAttribute("someNonExistentAttribute", "pmf", "asdf"));
    }


    @Test
    public void test2String() {
        assertEquals("compartmentMetaData [source=\"\" detail=\"\"]", new CompartmentMetaData().toString());

        CompartmentMetaData metadata = new CompartmentMetaData();
        metadata.source = 7;
        metadata.detail = "some details";
        assertEquals("compartmentMetaData [source=\"7\" detail=\"some details\"]", metadata.toString());
    }
}
