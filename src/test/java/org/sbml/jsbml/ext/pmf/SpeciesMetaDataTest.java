package org.sbml.jsbml.ext.pmf;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.*;

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
        assertFalse(metaData.isSetSource());
        assertFalse(metaData.isSetDetail());
        assertFalse(metaData.isSetDescription());

        // Copy constructor
        metaData.setSource("007");
        metaData.setDetail("Salmonella spec.");
        metaData.setDescription("description");
        metaData = new SpeciesMetaData(metaData);

        assertEquals("007", metaData.getSource());
        assertEquals("Salmonella spec.", metaData.getDetail());
        assertEquals("description", metaData.getDescription());
    }

    @Test
    public void testClone() {
        SpeciesMetaData metaData = new SpeciesMetaData();
        metaData.setSource("007");
        metaData.setDetail("Salmonella spec.");
        metaData.setDescription("description");
        metaData = metaData.clone();

        assertEquals("007", metaData.getSource());
        assertEquals("Salmonella spec.", metaData.getDetail());
        assertEquals("description", metaData.getDescription());
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
        metaData.setSource("007");
        metaData.setDetail("Salmonella spec.");
        metaData.setDescription("description");
        assertEquals(expectedAttributes, metaData.writeXMLAttributes());
    }

    @Test
    public void testReadAttribute() {
        SpeciesMetaData metadata = new SpeciesMetaData();

        // Parsing an string as the source attribute should return true and set it as source
        assertTrue(metadata.readAttribute("source", "pmf", "007"));
        assertEquals("007", metadata.getSource());

        // Parsing an string as the detail attribute should return true and set it as detail
        assertTrue(metadata.readAttribute("detail", "pmf", "Salmonella spec."));
        assertEquals("Salmonella spec.", metadata.getDetail());

        // Parsing an string as the description attribute should return true and set it as description
        assertTrue(metadata.readAttribute("description", "pmf", "description"));
        assertEquals("description", metadata.getDescription());

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

        metaData.setSource("007");
        metaData.setDetail("Salmonella spec.");
        metaData.setDescription("description");
        assertEquals("speciesMetaData [source=\"007\" detail=\"Salmonella spec.\" description=\"description\"]",
                metaData.toString());
    }

    @Test
    public void testSource() {
        SpeciesMetaData metaData = new SpeciesMetaData();

        // test without source
        assertFalse(metaData.isSetSource());
        assertNull(metaData.getSource());
        assertFalse(metaData.unsetSource());

        // test with source
        metaData.setSource("007");
        assertTrue(metaData.isSetSource());
        assertEquals("007", metaData.getSource());
        assertTrue(metaData.unsetSource());
    }

    @Test
    public void testDetail() {
        SpeciesMetaData metaData = new SpeciesMetaData();

        // test without detail
        assertFalse(metaData.isSetDetail());
        assertNull(metaData.getDetail());
        assertFalse(metaData.unsetDetail());

        // test with detail
        metaData.setDetail("Salmonella spec.");
        assertTrue(metaData.isSetDetail());
        assertEquals("Salmonella spec.", metaData.getDetail());
        assertTrue(metaData.unsetDetail());
    }

    @Test
    public void testDescription() {
        SpeciesMetaData metaData = new SpeciesMetaData();

        // test without description
        assertFalse(metaData.isSetDescription());
        assertNull(metaData.getDescription());
        assertFalse(metaData.unsetDescription());

        // test with description
        metaData.setDescription("description");
        assertTrue(metaData.isSetDescription());
        assertEquals("description", metaData.getDescription());
        assertTrue(metaData.unsetDescription());
    }
}
