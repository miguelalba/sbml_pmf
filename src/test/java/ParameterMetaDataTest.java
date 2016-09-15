import org.junit.Test;
import org.sbml.jsbml.ext.pmf.ParameterMetaData;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by de on 15.09.2016.
 */
public class ParameterMetaDataTest {

    @Test
    public void testWriteXMLAttributes() {
        // test attributes with empty ParameterMetadata
        assertTrue(new ParameterMetaData().writeXMLAttributes().isEmpty());

        // test attributes with filled ParameterMetadata
        Map<String, String> expectedAttributes = new HashMap<>();
        expectedAttributes.put("p", "2.22");
        expectedAttributes.put("t", "34.394");
        expectedAttributes.put("error", "9.922");
        expectedAttributes.put("description", "max conc");
        expectedAttributes.put("min", "3.0");
        expectedAttributes.put("max", "10.0");

        ParameterMetaData metadata = new ParameterMetaData();
        metadata.p = 2.22;
        metadata.t = 34.394;
        metadata.error = 9.922;
        metadata.description = "max conc";
        metadata.min = 3.0;
        metadata.max = 10.0;

        assertEquals(expectedAttributes, metadata.writeXMLAttributes());
    }

    @Test
    public void testReadAttribute() {
        ParameterMetaData metadata = new ParameterMetaData();

        assertTrue(metadata.readAttribute("p", "pmf", "2.220"));
        assertEquals(2.220, metadata.p, 0.0);

        assertTrue(metadata.readAttribute("t", "pmf", "34.394"));
        assertEquals(34.394, metadata.t, 0.0);

        assertTrue(metadata.readAttribute("error", "pmf", "9.922"));
        assertEquals(9.922, metadata.error, 0.0);

        assertTrue(metadata.readAttribute("description", "pmf", "max conc"));
        assertEquals("max conc", metadata.description);

        assertTrue(metadata.readAttribute("min", "pmf", "3.0"));
        assertEquals(3.0, metadata.min, 0.0);

        assertTrue(metadata.readAttribute("max", "pmf", "10.0"));
        assertEquals(10.0, metadata.max, 0.0);

        assertFalse(metadata.readAttribute("nonExistentAttribute", "pmf", "asdf"));
    }

    @Test
    public void test2String() {
        assertEquals("parameterMetaData [p=\"\" t=\"\" error=\"\" description=\"\" min=\"\" max=\"\"]",
                new ParameterMetaData().toString());
    }
}
