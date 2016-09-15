import org.junit.Test;
import org.sbml.jsbml.Species;
import org.sbml.jsbml.ext.pmf.RuleMetaData;
import org.sbml.jsbml.ext.pmf.SpeciesMetaData;

import java.util.HashMap;
import java.util.Map;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by de on 15.09.2016.
 */
public class SpeciesMetaDataTest {

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

        assertTrue(metadata.readAttribute("source", "pmf", "007"));
        assertEquals("007", metadata.source);

        assertTrue(metadata.readAttribute("detail", "pmf", "Salmonella spec."));
        assertEquals("Salmonella spec.", metadata.detail);

        assertTrue(metadata.readAttribute("description", "pmf", "description"));
        assertEquals("description", metadata.description);

        assertFalse(metadata.readAttribute("nonExistentAttribute", "pmf", "asdf"));
    }

    @Test
    public void test2String() {

        SpeciesMetaData metaData = new SpeciesMetaData();
        assertEquals("speciesMetaData [source=\"\" detail=\"\" description=\"\"]", metaData
                .toString());

        metaData.source = "007";
        metaData.detail = "Salmonella spec.";
        metaData.description = "description";
        assertEquals("speciesMetaData [source=\"007\" detail=\"Salmonella spec.\" description=\"description\"]",
                metaData.toString());
    }
}
