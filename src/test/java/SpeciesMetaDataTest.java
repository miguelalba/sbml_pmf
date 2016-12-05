import org.junit.Test;
import org.sbml.jsbml.ext.pmf.SpeciesMetaData;

import java.util.HashMap;
import java.util.Map;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

/**
 * Tests for the {@link SpeciesMetaData} class. Checks:
 * <ul>
 * <li>Constructors</li>
 * <li>Clone method</li>
 * <li>writeXMLAttributes</li>
 * <li>readAttribute</li>
 * <li>toString</li>
 * </ul>
 */
public class SpeciesMetaDataTest {

    @Test
    public void testConstructors() {
        SpeciesMetaData metaData = new SpeciesMetaData();

        // Empty constructor initialize source, detail and description with null
        assertNull(metaData.source);
        assertNull(metaData.detail);
        assertNull(metaData.description);

        // Copy constructor
        metaData.source = "007";
        metaData.detail = "Salmonella spec.";
        metaData.description = "description";
        metaData = new SpeciesMetaData(metaData);

        assertEquals("007", metaData.source);
        assertEquals("Salmonella spec.", metaData.detail);
        assertEquals("description", metaData.description);
    }

    @Test
    public void testClone() {
        SpeciesMetaData metaData = new SpeciesMetaData();
        metaData.source = "007";
        metaData.detail = "Salmonella spec.";
        metaData.description = "description";
        metaData = metaData.clone();

        assertEquals("007", metaData.source);
        assertEquals("Salmonella spec.", metaData.detail);
        assertEquals("description", metaData.description);
    }

    @Test
    public void testWriteXMLAttributes() {
        // test attributes with empty SpeciesMetaData
        assertTrue(new SpeciesMetaData().writeXMLAttributes().isEmpty());

        // test attributes with filled RuleMetadata
        Map<String, String> expectedAttributes = new HashMap<String, String>();
        expectedAttributes.put("source", "007");
        expectedAttributes.put("detail", "Salmonella spec.");
        expectedAttributes.put("description", "description");

        SpeciesMetaData metaData = new SpeciesMetaData();
        metaData.source = "007";
        metaData.detail = "Salmonella spec.";
        metaData.description = "description";
        assertEquals(expectedAttributes, metaData.writeXMLAttributes());
    }

    @Test
    public void testReadAttribute() {
        SpeciesMetaData metadata = new SpeciesMetaData();

        // Parsing an string as the source attribute should return true and set it as source
        assertTrue(metadata.readAttribute("source", "pmf", "007"));
        assertEquals("007", metadata.source);

        // Parsing an string as the detail attribute should return true and set it as detail
        assertTrue(metadata.readAttribute("detail", "pmf", "Salmonella spec."));
        assertEquals("Salmonella spec.", metadata.detail);

        // Parsing an string as the description attribute should return true and set it as description
        assertTrue(metadata.readAttribute("description", "pmf", "description"));
        assertEquals("description", metadata.description);

        // Parsing an attribute other than name and value should return false
        assertFalse(metadata.readAttribute("nonExistentAttribute", "pmf", "asdf"));
    }

    /**
     * Test {@link SpeciesMetaData#toString()} for empty and initialized {@link SpeciesMetaData}.
     */
    @Test
    public void test2String() {
        SpeciesMetaData metaData = new SpeciesMetaData();

        // Test with empty SpeciesMetaData
        assertEquals("speciesMetaData [source=\"\" detail=\"\" description=\"\"]", metaData
                .toString());

        metaData.source = "007";
        metaData.detail = "Salmonella spec.";
        metaData.description = "description";
        assertEquals("speciesMetaData [source=\"007\" detail=\"Salmonella spec.\" description=\"description\"]",
                metaData.toString());
    }
}
