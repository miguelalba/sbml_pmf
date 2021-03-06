package org.sbml.jsbml.ext.pmf;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.sbml.jsbml.PropertyUndefinedError;
import org.sbml.jsbml.util.StringTools;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Tests for the {@link Reference} class. Checks:
 * <ul>
 * <li>Constructors</li>
 * TODO: ...
 * </ul>
 */
public class ReferenceTest {

    @Test
    public void testReferenceType() {
        assertTrue(1 == Reference.ReferenceType.Paper.value());
        assertTrue(2 == Reference.ReferenceType.SOP.value());
        assertTrue(3 == Reference.ReferenceType.LA.value());
        assertTrue(4 == Reference.ReferenceType.Handbuch.value());
        assertTrue(5 == Reference.ReferenceType.Laborbuch.value());
        assertTrue(6 == Reference.ReferenceType.Buch.value());
        assertTrue(7 == Reference.ReferenceType.Webseite.value());
        assertTrue(8 == Reference.ReferenceType.Bericht.value());

        assertEquals(Reference.ReferenceType.Paper, Reference.ReferenceType.valueOf(1));
        assertEquals(Reference.ReferenceType.SOP, Reference.ReferenceType.valueOf(2));
        assertEquals(Reference.ReferenceType.LA, Reference.ReferenceType.valueOf(3));
        assertEquals(Reference.ReferenceType.Handbuch, Reference.ReferenceType.valueOf(4));
        assertEquals(Reference.ReferenceType.Laborbuch, Reference.ReferenceType.valueOf(5));
        assertEquals(Reference.ReferenceType.Buch, Reference.ReferenceType.valueOf(6));
        assertEquals(Reference.ReferenceType.Webseite, Reference.ReferenceType.valueOf(7));
        assertEquals(Reference.ReferenceType.Bericht, Reference.ReferenceType.valueOf(8));
    }

    /**
     * Test constructors: empty and copy
     */
    @Test
    public void testConstructors() {
        // Test empty constructor
        Reference ref = new Reference();
        assertFalse(ref.isSetAuthor());
        assertFalse(ref.isSetYear());
        assertFalse(ref.isSetTitle());
        assertFalse(ref.isSetAbstractText());
        assertFalse(ref.isSetJournal());
        assertFalse(ref.isSetVolume());
        assertFalse(ref.isSetIssue());
        assertFalse(ref.isSetPage());
        assertFalse(ref.isSetApprovalMode());
        assertFalse(ref.isSetWebsite());
        assertFalse(ref.isSetType());
        assertFalse(ref.isSetComment());

        // Test constructor with level and version
        ref = new Reference(3, 1);
        assertTrue(3 == ref.getLevel());
        assertTrue(1 == ref.getVersion());

        assertFalse(ref.isSetAuthor());
        assertFalse(ref.isSetYear());
        assertFalse(ref.isSetTitle());
        assertFalse(ref.isSetAbstractText());
        assertFalse(ref.isSetJournal());
        assertFalse(ref.isSetVolume());
        assertFalse(ref.isSetIssue());
        assertFalse(ref.isSetPage());
        assertFalse(ref.isSetApprovalMode());
        assertFalse(ref.isSetWebsite());
        assertFalse(ref.isSetType());
        assertFalse(ref.isSetComment());

        // Test copy constructor
        ref.setAuthor("Baranyi, J.");
        ref.setYear(1994);
        ref.setTitle("A dynamic approach to predicting bacterial growth in food");
        ref.setAbstractText("A new member ...");
        ref.setJournal("International Journal of Food Microbiology");
        ref.setVolume("23");
        ref.setIssue("3");
        ref.setPage(277);
        ref.setApprovalMode(1);
        ref.setWebsite("http://www.sciencedirect.com/science/article/pii/0168160594901570");
        ref.setType(Reference.ReferenceType.Paper);
        ref.setComment("improvised comment");


        ref = new Reference(ref);
        assertEquals("Baranyi, J.", ref.getAuthor());
        assertTrue(1994 == ref.getYear());
        assertEquals("A dynamic approach to predicting bacterial growth in food", ref.getTitle());
        assertEquals("A new member ...", ref.getAbstractText());
        assertEquals("International Journal of Food Microbiology", ref.getJournal());
        assertEquals("23", ref.getVolume());
        assertEquals("3", ref.getIssue());
        assertTrue(277 == ref.getPage());
        assertTrue(1 == ref.getApprovalMode());
        assertEquals("http://www.sciencedirect.com/science/article/pii/0168160594901570", ref.getWebsite());
        assertEquals(Reference.ReferenceType.Paper, ref.getType());
        assertEquals("improvised comment", ref.getComment());
    }

    @Test
    public void testClone() {
        Reference ref = new Reference();
        ref.setAuthor("Baranyi, J.");
        ref.setYear(1994);
        ref.setTitle("A dynamic approach to predicting bacterial growth in food");
        ref.setAbstractText("A new member ...");
        ref.setJournal("International Journal of Food Microbiology");
        ref.setVolume("23");
        ref.setIssue("3");
        ref.setPage(277);
        ref.setApprovalMode(1);
        ref.setWebsite("http://www.sciencedirect.com/science/article/pii/0168160594901570");
        ref.setType(Reference.ReferenceType.Paper);
        ref.setComment("improvised comment");

        ref = ref.clone();
        assertEquals("Baranyi, J.", ref.getAuthor());
        assertTrue(1994 == ref.getYear());
        assertEquals("A dynamic approach to predicting bacterial growth in food", ref.getTitle());
        assertEquals("A new member ...", ref.getAbstractText());
        assertEquals("International Journal of Food Microbiology", ref.getJournal());
        assertEquals("23", ref.getVolume());
        assertEquals("3", ref.getIssue());
        assertTrue(277 == ref.getPage());
        assertTrue(1 == ref.getApprovalMode());
        assertEquals("http://www.sciencedirect.com/science/article/pii/0168160594901570", ref.getWebsite());
        assertEquals(Reference.ReferenceType.Paper, ref.getType());
        assertEquals("improvised comment", ref.getComment());
    }

    /**
     * Test {@link Reference#readAttribute(String, String, String)
     */
    @Test
    public void testReadAttribute() {
        Logger logger = Logger.getLogger(StringTools.class);
        Level defaultLevel = logger.getLevel();
        logger.setLevel(Level.OFF);

        Reference ref = new Reference();

        // Parsing an string for the author attribute should return true and set it as author
        assertTrue(ref.readAttribute("AU", "pmf", "Baranyi, J."));
        assertEquals("Baranyi, J.", ref.getAuthor());

        // Parsing a non-integer for the year attribute should return true and set 0 as year
        assertTrue(ref.readAttribute("PY", "pmf", "0.5"));
        assertTrue(ref.getYear() == 0);

        assertTrue(ref.readAttribute("PY", "pmf", "not an integer"));
        assertTrue(ref.getYear() == 0);

        // Parsing an integer for the year attribute should return true and set it as year
        assertTrue(ref.readAttribute("PY", "pmf", "1994"));
        assertTrue(1994 == ref.getYear());

        // Parsing an string for the title attribute should return true and set it as title
        assertTrue(ref.readAttribute("TI", "pmf", "A dynamic approach to predicting bacterial growth in food"));
        assertEquals("A dynamic approach to predicting bacterial growth in food", ref.getTitle());

        // Parsing an string for the abstract attribute should return true and set it as abstract
        assertTrue(ref.readAttribute("AB", "pmf", "A new member ..."));
        assertEquals("A new member ...", ref.getAbstractText());

        // Parsing an string for the journal attribute should return true and set it as journal
        assertTrue(ref.readAttribute("T2", "pmf", "International Journal of Food Microbiology"));
        assertEquals("International Journal of Food Microbiology", ref.getJournal());

        // Parsing an string for the volume attribute should return true and set it as volume
        assertTrue(ref.readAttribute("VL", "pmf", "23"));
        assertEquals("23", ref.getVolume());

        // Parsing an string for the issue attribute should return true and set it as issue
        assertTrue(ref.readAttribute("IS", "pmf", "3"));
        assertEquals("3", ref.getIssue());

        // Parsing a non-integer for the page attribute should return true and set 0 as page
        assertTrue(ref.readAttribute("SP", "pmf", "5.0"));
        assertTrue(ref.getPage() == 0);

        assertTrue(ref.readAttribute("SP", "pmf", "not an integer"));
        assertTrue(ref.getPage() == 0);

        // Parsing an integer for the page attribute should return true and set it as page
        assertTrue(ref.readAttribute("SP", "pmf", "277"));
        assertTrue(ref.getPage() == 277);

        // Parsing a non-integer for the approvalMode attribute should return true and set 0 as approvalMode
        assertTrue(ref.readAttribute("LB", "pmf", "0.5"));
        assertTrue(ref.getApprovalMode() == 0);

        assertTrue(ref.readAttribute("LB", "pmf", "not an integer"));
        assertTrue(ref.getApprovalMode() == 0);

        // Parsing an integer for the approvalMode attribute should return true and set 1 as approvalMode
        assertTrue(ref.readAttribute("LB", "pmf", "1"));
        assertTrue(1 == ref.getApprovalMode());

        // Parsing an string for the website attribute should return true and set it as website
        assertTrue(ref.readAttribute("UR", "pmf", "http://www.sciencedirect.com/science/article/pii/0168160594901570"));
        assertEquals("http://www.sciencedirect.com/science/article/pii/0168160594901570", ref.getWebsite());

        // Parsing an string for the type attribute should return true and set it as type
        assertTrue(ref.readAttribute("M3", "pmf", Reference.ReferenceType.Paper.name()));
        assertEquals(Reference.ReferenceType.Paper, ref.getType());

        // Parsing an string for the comment attribute should return true and set it as comment
        assertTrue(ref.readAttribute("N1", "pmf", "improvised comment"));
        assertEquals("improvised comment", ref.getComment());

        // Parsing other attribute should return false
        assertFalse(ref.readAttribute("someNonExistentAttribute", "pmf", "asdf"));

        logger.setLevel(defaultLevel);
    }

    @Test
    public void testWriteXMLAttributes() {

        Reference ref = new Reference();

        // test attributes with empty Reference
        assertTrue(ref.writeXMLAttributes().isEmpty());

        // test attributes with filled Reference
        ref.setAuthor("Baranyi, J.");
        ref.setYear(1994);
        ref.setTitle("A dynamic approach to predicting bacterial growth in food");
        ref.setAbstractText("A new member ...");
        ref.setJournal("International Journal of Food Microbiology");
        ref.setVolume("23");
        ref.setIssue("3");
        ref.setPage(277);
        ref.setApprovalMode(1);
        ref.setWebsite("http://www.sciencedirect.com/science/article/pii/0168160594901570");
        ref.setType(Reference.ReferenceType.Paper);
        ref.setComment("improvised comment");

        Map<String, String> expectedAttributes = new HashMap<>();
        expectedAttributes.put("AU", ref.getAuthor());
        expectedAttributes.put("PY", Integer.toString(ref.getYear()));
        expectedAttributes.put("TI", ref.getTitle());
        expectedAttributes.put("AB", ref.getAbstractText());
        expectedAttributes.put("T2", ref.getJournal());
        expectedAttributes.put("VL", ref.getVolume());
        expectedAttributes.put("IS", ref.getIssue());
        expectedAttributes.put("SP", Integer.toString(ref.getPage()));
        expectedAttributes.put("LB", Integer.toString(ref.getApprovalMode()));
        expectedAttributes.put("UR", ref.getWebsite());
        expectedAttributes.put("M3", ref.getType().name());
        expectedAttributes.put("N1", ref.getComment());

        assertEquals(expectedAttributes, ref.writeXMLAttributes());
    }

    @Test
    public void test2String() {
        Reference ref = new Reference();
        ref.setAuthor("Baranyi");
        ref.setYear(1994);
        ref.setTitle("A dynamic approach...");

        String expected = "Baranyi_1994_A dynamic approach...";
        assertEquals(expected, ref.toString());
    }

    @Test
    public void testAuthor() {
        Reference ref = new Reference();

        // Test without author
        assertFalse(ref.isSetAuthor());
        assertNull(ref.getAuthor());
        assertFalse(ref.unsetAuthor());

        // Test with author
        ref.setAuthor("Baranyi");
        assertTrue(ref.isSetAuthor());
        assertEquals("Baranyi", ref.getAuthor());
        assertTrue(ref.unsetAuthor());
    }

    @Test
    public void testYear() {
        Reference ref = new Reference();

        // Test without year
        assertFalse(ref.isSetYear());
        try {
            ref.getYear();
            fail();
        } catch (PropertyUndefinedError e) {
        }
        assertFalse(ref.unsetYear());

        // Test with year
        ref.setYear(1994);
        assertTrue(ref.isSetYear());
        assertTrue(1994 == ref.getYear());
        assertTrue(ref.unsetYear());
    }

    @Test
    public void testTitle() {
        Reference ref = new Reference();

        // Test without title
        assertFalse(ref.isSetTitle());
        assertNull(ref.getTitle());
        assertFalse(ref.unsetTitle());

        // Test with title
        ref.setTitle("A dynamic approach to predicting bacterial growth in food");
        assertTrue(ref.isSetTitle());
        assertEquals("A dynamic approach to predicting bacterial growth in food", ref.getTitle());
        assertTrue(ref.unsetTitle());
    }

    @Test
    public void testAbstractText() {
        Reference ref = new Reference();

        // Test without abstract text
        assertFalse(ref.isSetAbstractText());
        assertNull(ref.getAbstractText());
        assertFalse(ref.unsetAbstractText());

        // Test with abstract text
        ref.setAbstractText("A new member ...");
        assertTrue(ref.isSetAbstractText());
        assertEquals("A new member ...", ref.getAbstractText());
        assertTrue(ref.unsetAbstractText());
    }

    @Test
    public void testJournal() {
        Reference ref = new Reference();

        // Test without journal
        assertFalse(ref.isSetJournal());
        assertNull(ref.getJournal());
        assertFalse(ref.unsetJournal());

        // Test with journal
        ref.setJournal("International Journal of Food Microbiology");
        assertTrue(ref.isSetJournal());
        assertEquals("International Journal of Food Microbiology", ref.getJournal());
        assertTrue(ref.unsetJournal());
    }

    @Test
    public void testVolume() {
        Reference ref = new Reference();

        // Test without volume
        assertFalse(ref.isSetVolume());
        assertNull(ref.getVolume());
        assertFalse(ref.unsetVolume());

        // Test with volume
        ref.setVolume("23");
        assertTrue(ref.isSetVolume());
        assertEquals("23", ref.getVolume());
        assertTrue(ref.unsetVolume());
    }

    @Test
    public void testIssue() {
        Reference ref = new Reference();

        // Test without issue
        assertFalse(ref.isSetIssue());
        assertNull(ref.getIssue());
        assertFalse(ref.unsetIssue());

        // Test with issue
        ref.setIssue("3");
        assertTrue(ref.isSetIssue());
        assertEquals("3", ref.getIssue());
        assertTrue(ref.unsetIssue());
    }

    @Test
    public void testPage() {
        Reference ref = new Reference();

        // Test without page
        assertFalse(ref.isSetPage());
        try {
            ref.getPage();
            fail();
        } catch (PropertyUndefinedError e) {
        }
        assertFalse(ref.unsetPage());

        // Test with page
        ref.setPage(277);
        assertTrue(ref.isSetPage());
        assertTrue(277 == ref.getPage());
        assertTrue(ref.unsetPage());
    }

    @Test
    public void testApprovalMode() {
        Reference ref = new Reference();

        // Test without approval mode
        assertFalse(ref.isSetApprovalMode());
        try {
            ref.getApprovalMode();
            fail();
        } catch (PropertyUndefinedError e) {
        }
        assertFalse(ref.unsetApprovalMode());

        // Test with approval mode
        ref.setApprovalMode(1);
        assertTrue(ref.isSetApprovalMode());
        assertTrue(1 == ref.getApprovalMode());
        assertTrue(ref.unsetApprovalMode());
    }

    @Test
    public void testWebsite() {
        Reference ref = new Reference();

        // Test without website
        assertFalse(ref.isSetWebsite());
        assertNull(ref.getWebsite());
        assertFalse(ref.unsetWebsite());

        // Test with website
        ref.setWebsite("http://www.sciencedirect.com/science/article/pii/0168160594901570");
        assertTrue(ref.isSetWebsite());
        assertEquals("http://www.sciencedirect.com/science/article/pii/0168160594901570", ref.getWebsite());
        assertTrue(ref.unsetWebsite());
    }

    @Test
    public void testType() {
        Reference ref = new Reference();

        // Test without type
        assertFalse(ref.isSetType());
        assertNull(ref.getType());
        assertFalse(ref.unsetType());

        // Test with type
        ref.setType(Reference.ReferenceType.Buch);
        assertTrue(ref.isSetType());
        assertEquals(Reference.ReferenceType.Buch, ref.getType());
        assertTrue(ref.unsetType());
    }

    @Test
    public void testComment() {
        Reference ref = new Reference();

        // Test without comment
        assertFalse(ref.isSetComment());
        assertNull(ref.getComment());
        assertFalse(ref.unsetComment());

        // Test with comment
        ref.setComment("improvised comment");
        assertTrue(ref.isSetComment());
        assertEquals("improvised comment", ref.getComment());
        assertTrue(ref.unsetComment());
    }
}
