import org.junit.Test;
import org.sbml.jsbml.ext.pmf.RuleMetaData;

import java.util.HashMap;
import java.util.Map;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by de on 15.09.2016.
 */
public class RuleMetaDataTest {

    @Test
    public void testWriteXMLAttributes() {

        // test attributes with empty RuleMetadata
        assertTrue(new RuleMetaData().writeXMLAttributes().isEmpty());

        // test attributes with filled RuleMetadata
        Map<String, String> expectedAttributes = new HashMap<String, String>();
        expectedAttributes.put("formulaName", "2 plus 2");
        expectedAttributes.put("ruleClass", RuleMetaData.ModelClass.GROWTH.getName());
        expectedAttributes.put("pmmLabID", "1");

        RuleMetaData metadata = new RuleMetaData();
        metadata.formulaName = "2 plus 2";
        metadata.modelClass = RuleMetaData.ModelClass.GROWTH;
        metadata.pmmLabId = 1;

        assertEquals(expectedAttributes, metadata.writeXMLAttributes());
    }

    @Test
    public void testReadAttribute() {
        RuleMetaData ruleMetadata = new RuleMetaData();

        assertTrue(ruleMetadata.readAttribute("formulaName", "pmf", "2 plus 2"));
        assertTrue(ruleMetadata.readAttribute("ruleClass", "pmf", RuleMetaData.ModelClass.GROWTH.name()));
        assertTrue(ruleMetadata.readAttribute("pmmLabID", "pmf", "1"));
        assertFalse(ruleMetadata.readAttribute("nonExistentAttribute", "pmf", "asdf"));
    }

    @Test
    public void test2String() {
        assertEquals("ruleMetaData [formulaName=\"\" ruleClass=\"\" pmmLabID=\"\"]", new RuleMetaData().toString());

        RuleMetaData metadata = new RuleMetaData();
        metadata.formulaName = "2 plus 2";
        metadata.modelClass = RuleMetaData.ModelClass.GROWTH;
        metadata.pmmLabId = 1;
        assertEquals("ruleMetaData [formulaName=\"2 plus 2\" ruleClass=\"growth\" pmmLabID=\"1\"]", metadata.toString
                ());
    }
}
