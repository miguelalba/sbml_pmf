package org.sbml.jsbml.ext.pmf;

import org.junit.Test;
import org.sbml.jsbml.PropertyUndefinedError;

import java.util.HashMap;
import java.util.Map;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.*;

/**
 * Test for the {@link RuleMetaData} class. Checks:
 * <ul>
 * TODO: ...
 * </ul>
 */
public class RuleMetaDataTest {

    @Test
    public void testModelClassEnum() {

        // Test toString
        assertEquals("unknown", RuleMetaData.ModelClass.UNKNOWN.toString());
        assertEquals("growth", RuleMetaData.ModelClass.GROWTH.toString());
        assertEquals("inactivation", RuleMetaData.ModelClass.INACTIVATION.toString());
        assertEquals("survival", RuleMetaData.ModelClass.SURVIVAL.toString());
        assertEquals("growth/inactivation", RuleMetaData.ModelClass.GROWTH_INACTIVATION.toString());
        assertEquals("inactivation/survival", RuleMetaData.ModelClass.INACTIVATION_SURVIVAL.toString());
        assertEquals("growth/survival", RuleMetaData.ModelClass.GROWTH_SURVIVAL.toString());
        assertEquals("growth/inactivation/survival", RuleMetaData.ModelClass.GROWTH_INACTIVATION_SURVIVAL.toString());
        assertEquals("T", RuleMetaData.ModelClass.T.toString());
        assertEquals("pH", RuleMetaData.ModelClass.PH.toString());
        assertEquals("aw", RuleMetaData.ModelClass.AW.toString());
        assertEquals("T/pH", RuleMetaData.ModelClass.T_PH.toString());
        assertEquals("T/aw", RuleMetaData.ModelClass.T_AW.toString());
        assertEquals("pH/aw", RuleMetaData.ModelClass.PH_AW.toString());
        assertEquals("T/pH/aw", RuleMetaData.ModelClass.T_PH_AW.toString());

        // Test fromName
        assertEquals(RuleMetaData.ModelClass.UNKNOWN, RuleMetaData.ModelClass.fromName("unknown"));
        assertEquals(RuleMetaData.ModelClass.GROWTH, RuleMetaData.ModelClass.fromName("growth"));
        assertEquals(RuleMetaData.ModelClass.INACTIVATION, RuleMetaData.ModelClass.fromName("inactivation"));
        assertEquals(RuleMetaData.ModelClass.SURVIVAL, RuleMetaData.ModelClass.fromName("survival"));
        assertEquals(RuleMetaData.ModelClass.GROWTH_INACTIVATION, RuleMetaData.ModelClass.fromName
                ("growth/inactivation"));
        assertEquals(RuleMetaData.ModelClass.INACTIVATION_SURVIVAL, RuleMetaData.ModelClass.fromName
                ("inactivation/survival"));
        assertEquals(RuleMetaData.ModelClass.GROWTH_SURVIVAL, RuleMetaData.ModelClass.fromName("growth/survival"));
        assertEquals(RuleMetaData.ModelClass.GROWTH_INACTIVATION_SURVIVAL, RuleMetaData.ModelClass.fromName
                ("growth/inactivation/survival"));
        assertEquals(RuleMetaData.ModelClass.T, RuleMetaData.ModelClass.fromName("T"));
        assertEquals(RuleMetaData.ModelClass.PH, RuleMetaData.ModelClass.fromName("pH"));
        assertEquals(RuleMetaData.ModelClass.AW, RuleMetaData.ModelClass.fromName("aw"));
        assertEquals(RuleMetaData.ModelClass.T_PH, RuleMetaData.ModelClass.fromName("T/pH"));
        assertEquals(RuleMetaData.ModelClass.T_AW, RuleMetaData.ModelClass.fromName("T/aw"));
        assertEquals(RuleMetaData.ModelClass.PH_AW, RuleMetaData.ModelClass.fromName("pH/aw"));
        assertEquals(RuleMetaData.ModelClass.T_PH_AW, RuleMetaData.ModelClass.fromName("T/pH/aw"));
        assertEquals(RuleMetaData.ModelClass.UNKNOWN, RuleMetaData.ModelClass.fromName(""));
    }

    /**
     * Test constructors.
     */
    @Test
    public void testConstructors() {
        // Empty constructor initializes formulaName and pmmLabId to null and ModelClass to UNKNOWN
        RuleMetaData metaData = new RuleMetaData();
        assertFalse(metaData.isSetFormulaName());
        assertFalse(metaData.isSetPmmLabId());
        assertEquals(RuleMetaData.ModelClass.UNKNOWN, metaData.getModelClass());

        // Constructor with level and version
        metaData = new RuleMetaData(3, 1);
        assertFalse(metaData.isSetFormulaName());
        assertFalse(metaData.isSetPmmLabId());
        assertEquals(RuleMetaData.ModelClass.UNKNOWN, metaData.getModelClass());
        assertTrue(3 == metaData.getLevel());
        assertTrue(1 == metaData.getVersion());

        // Test copy constructor
        metaData.setFormulaName("a formula");
        metaData.setPmmLabId(151);
        metaData.setModelClass(RuleMetaData.ModelClass.GROWTH);
        metaData = new RuleMetaData(metaData);

        assertEquals("a formula", metaData.getFormulaName());
        assertTrue(151 == metaData.getPmmLabId());
        assertEquals(RuleMetaData.ModelClass.GROWTH, metaData.getModelClass());
    }

    /**
     * Test clone constructor.
     */
    @Test
    public void testClone() {
        RuleMetaData metaData = new RuleMetaData();
        metaData.setFormulaName("a formula");
        metaData.setPmmLabId(151);
        metaData.setModelClass(RuleMetaData.ModelClass.GROWTH);
        metaData = metaData.clone();

        assertEquals("a formula", metaData.getFormulaName());
        assertTrue(151 == metaData.getPmmLabId());
        assertEquals(RuleMetaData.ModelClass.GROWTH, metaData.getModelClass());
    }

    @Test
    public void testWriteXMLAttributes() {

        // test attributes with empty RuleMetadata
        assertTrue(new RuleMetaData().writeXMLAttributes().size() == 1);

        // test attributes with filled RuleMetadata
        Map<String, String> expectedAttributes = new HashMap<String, String>();
        expectedAttributes.put("formulaName", "2 plus 2");
        expectedAttributes.put("ruleClass", RuleMetaData.ModelClass.GROWTH.toString());
        expectedAttributes.put("pmmLabID", "1");

        RuleMetaData metadata = new RuleMetaData();
        metadata.setFormulaName("2 plus 2");
        metadata.setModelClass(RuleMetaData.ModelClass.GROWTH);
        metadata.setPmmLabId(1);

        assertEquals(expectedAttributes, metadata.writeXMLAttributes());
    }

    @Test
    public void testReadAttribute() {
        RuleMetaData metadata = new RuleMetaData();

        assertTrue(metadata.readAttribute("formulaName", "pmf", "2 plus 2"));
        assertEquals("2 plus 2", metadata.getFormulaName());

        assertTrue(metadata.readAttribute("ruleClass", "pmf", RuleMetaData.ModelClass.GROWTH.name()));
        assertEquals(RuleMetaData.ModelClass.GROWTH, metadata.getModelClass());

        assertTrue(metadata.readAttribute("pmmLabID", "pmf", "1"));
        assertTrue(1 == metadata.getPmmLabId());

        assertFalse(metadata.readAttribute("nonExistentAttribute", "pmf", "asdf"));
    }

    /**
     * Test {@link RuleMetaData#toString()} for empty and initialized {@link RuleMetaData}.
     */
    @Test
    public void test2String() {
        RuleMetaData metaData = new RuleMetaData();

        // Test with empty RuleMetaData
        assertEquals("ruleMetaData [formulaName=\"\" ruleClass=\"unknown\" pmmLabID=\"\"]", metaData.toString());

        // Test with filled RuleMetaData
        metaData.setFormulaName("2 plus 2");
        metaData.setModelClass(RuleMetaData.ModelClass.GROWTH);
        metaData.setPmmLabId(1);
        assertEquals("ruleMetaData [formulaName=\"2 plus 2\" ruleClass=\"growth\" pmmLabID=\"1\"]", metaData.toString());
    }

    @Test
    public void testFormulaName() {
        RuleMetaData metaData = new RuleMetaData();

        // test without formula name
        assertFalse(metaData.isSetFormulaName());
        assertNull(metaData.getFormulaName());
        assertFalse(metaData.unsetFormulaName());

        // test with formula name
        metaData.setFormulaName("2 plus 2");
        assertTrue(metaData.isSetFormulaName());
        assertEquals("2 plus 2", metaData.getFormulaName());
        assertTrue(metaData.unsetFormulaName());
    }

    @Test
    public void testModelClass() {
        RuleMetaData metaData = new RuleMetaData();

        // Test without ModelClass
        assertTrue(metaData.isSetModelClass());
        assertEquals(RuleMetaData.ModelClass.UNKNOWN, metaData.getModelClass());

        // Test with ModelClass
        metaData.setModelClass(RuleMetaData.ModelClass.AW);
        assertTrue(metaData.isSetModelClass());
        assertEquals(RuleMetaData.ModelClass.AW, metaData.getModelClass());
        assertTrue(metaData.unsetModelClass());
    }

    @Test
    public void testPmmLabId() {
        RuleMetaData metaData = new RuleMetaData();

        // test without PmmLabId
        assertFalse(metaData.isSetPmmLabId());
        try {
            metaData.getPmmLabId();
            fail();
        } catch (PropertyUndefinedError e) {}
        assertFalse(metaData.unsetPmmLabId());

        // test with PmmLabId
        metaData.setPmmLabId(1);
        assertTrue(metaData.isSetPmmLabId());
        assertTrue(1 == metaData.getPmmLabId());
        assertTrue(metaData.unsetPmmLabId());
    }
}
