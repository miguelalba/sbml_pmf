package org.sbml.jsbml.ext.pmf;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.junit.Test;
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
        assertNull(ref.author);
        assertNull(ref.year);
        assertNull(ref.title);
        assertNull(ref.abstractText);
        assertNull(ref.journal);
        assertNull(ref.volume);
        assertNull(ref.issue);
        assertNull(ref.page);
        assertNull(ref.approvalMode);
        assertNull(ref.website);
        assertNull(ref.type);
        assertNull(ref.comment);

        // Test constructor with level and version
        ref = new Reference(3, 1);
        assertNull(ref.author);
        assertNull(ref.year);
        assertNull(ref.title);
        assertNull(ref.abstractText);
        assertNull(ref.journal);
        assertNull(ref.volume);
        assertNull(ref.issue);
        assertNull(ref.page);
        assertNull(ref.approvalMode);
        assertNull(ref.website);
        assertNull(ref.type);
        assertNull(ref.comment);

        // Test copy constructor
        ref.author = "Baranyi, J.";
        ref.year = 1994;
        ref.title = "A dynamic approach to predicting bacterial growth in food";
        ref.abstractText = "A new member ...";
        ref.journal = "International Journal of Food Microbiology";
        ref.volume = "23";
        ref.issue = "3";
        ref.page = 277;
        ref.approvalMode = 1;
        ref.website = "http://www.sciencedirect.com/science/article/pii/0168160594901570";
        ref.type = Reference.ReferenceType.Paper;
        ref.comment = "improvised comment";

        ref = new Reference(ref);
        assertEquals("Baranyi, J.", ref.author);
        assertTrue(1994 == ref.year);
        assertEquals("A dynamic approach to predicting bacterial growth in food", ref.title);
        assertEquals("A new member ...", ref.abstractText);
        assertEquals("International Journal of Food Microbiology", ref.journal);
        assertEquals("23", ref.volume);
        assertEquals("3", ref.issue);
        assertTrue(277 == ref.page);
        assertTrue(1 == ref.approvalMode);
        assertEquals("http://www.sciencedirect.com/science/article/pii/0168160594901570", ref.website);
        assertEquals(Reference.ReferenceType.Paper, ref.type);
        assertEquals("improvised comment", ref.comment);
    }

    @Test
    public void testClone() {
        Reference ref = new Reference();
        ref.author = "Baranyi, J.";
        ref.year = 1994;
        ref.title = "A dynamic approach to predicting bacterial growth in food";
        ref.abstractText = "A new member ...";
        ref.journal = "International Journal of Food Microbiology";
        ref.volume = "23";
        ref.issue = "3";
        ref.page = 277;
        ref.approvalMode = 1;
        ref.website = "http://www.sciencedirect.com/science/article/pii/0168160594901570";
        ref.type = Reference.ReferenceType.Paper;
        ref.comment = "improvised comment";

        ref = ref.clone();
        assertEquals("Baranyi, J.", ref.author);
        assertTrue(1994 == ref.year);
        assertEquals("A dynamic approach to predicting bacterial growth in food", ref.title);
        assertEquals("A new member ...", ref.abstractText);
        assertEquals("International Journal of Food Microbiology", ref.journal);
        assertEquals("23", ref.volume);
        assertEquals("3", ref.issue);
        assertTrue(277 == ref.page);
        assertTrue(1 == ref.approvalMode);
        assertEquals("http://www.sciencedirect.com/science/article/pii/0168160594901570", ref.website);
        assertEquals(Reference.ReferenceType.Paper, ref.type);
        assertEquals("improvised comment", ref.comment);
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
        assertEquals("Baranyi, J.", ref.author);

        // Parsing a non-integer for the year attribute should return true and set 0 as year
        assertTrue(ref.readAttribute("PY", "pmf", "0.5"));
        assertTrue(ref.year == 0);

        assertTrue(ref.readAttribute("PY", "pmf", "not an integer"));
        assertTrue(ref.year == 0);

        // Parsing an integer for the year attribute should return true and set it as year
        assertTrue(ref.readAttribute("PY", "pmf", "1994"));
        assertTrue(1994 == ref.year);

        // Parsing an string for the title attribute should return true and set it as title
        assertTrue(ref.readAttribute("TI", "pmf", "A dynamic approach to predicting bacterial growth in food"));
        assertEquals("A dynamic approach to predicting bacterial growth in food", ref.title);

        // Parsing an string for the abstract attribute should return true and set it as abstract
        assertTrue(ref.readAttribute("AB", "pmf", "A new member ..."));
        assertEquals("A new member ...", ref.abstractText);

        // Parsing an string for the journal attribute should return true and set it as journal
        assertTrue(ref.readAttribute("T2", "pmf", "International Journal of Food Microbiology"));
        assertEquals("International Journal of Food Microbiology", ref.journal);

        // Parsing an string for the volume attribute should return true and set it as volume
        assertTrue(ref.readAttribute("VL", "pmf", "23"));
        assertEquals("23", ref.volume);

        // Parsing an string for the issue attribute should return true and set it as issue
        assertTrue(ref.readAttribute("IS", "pmf", "3"));
        assertEquals("3", ref.issue);

        // Parsing a non-integer for the page attribute should return true and set 0 as page
        assertTrue(ref.readAttribute("SP", "pmf", "5.0"));
        assertTrue(ref.page == 0);

        assertTrue(ref.readAttribute("SP", "pmf", "not an integer"));
        assertTrue(ref.page == 0);

        // Parsing an integer for the page attribute should return true and set it as page
        assertTrue(ref.readAttribute("SP", "pmf", "277"));
        assertTrue(ref.page == 277);

        // Parsing a non-integer for the approvalMode attribute should return true and set 0 as approvalMode
        assertTrue(ref.readAttribute("LB", "pmf", "0.5"));
        assertTrue(ref.approvalMode == 0);

        assertTrue(ref.readAttribute("LB", "pmf", "not an integer"));
        assertTrue(ref.approvalMode == 0);

        // Parsing an integer for the approvalMode attribute should return true and set 1 as approvalMode
        assertTrue(ref.readAttribute("LB", "pmf", "1"));
        assertTrue(1 == ref.approvalMode);

        // Parsing an string for the website attribute should return true and set it as website
        assertTrue(ref.readAttribute("UR", "pmf", "http://www.sciencedirect.com/science/article/pii/0168160594901570"));
        assertEquals("http://www.sciencedirect.com/science/article/pii/0168160594901570", ref.website);

        // Parsing an string for the type attribute should return true and set it as type
        assertTrue(ref.readAttribute("M3", "pmf", Reference.ReferenceType.Paper.name()));
        assertEquals(Reference.ReferenceType.Paper, ref.type);

        // Parsing an string for the comment attribute should return true and set it as comment
        assertTrue(ref.readAttribute("N1", "pmf", "improvised comment"));
        assertEquals("improvised comment", ref.comment);

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
        ref.author = "Baranyi, J.";
        ref.year = 1994;
        ref.title = "A dynamic approach to predicting bacterial growth in food";
        ref.abstractText = "A new member ...";
        ref.journal = "International Journal of Food Microbiology";
        ref.volume = "23";
        ref.issue = "3";
        ref.page = 277;
        ref.approvalMode = 1;
        ref.website = "http://www.sciencedirect.com/science/article/pii/0168160594901570";
        ref.type = Reference.ReferenceType.Paper;
        ref.comment = "improvised comment";

        Map<String, String> expectedAttributes = new HashMap<>();
        expectedAttributes.put("AU", ref.author);
        expectedAttributes.put("PY", Integer.toString(ref.year));
        expectedAttributes.put("TI", ref.title);
        expectedAttributes.put("AB", ref.abstractText);
        expectedAttributes.put("T2", ref.journal);
        expectedAttributes.put("VL", ref.volume);
        expectedAttributes.put("IS", ref.issue);
        expectedAttributes.put("SP", Integer.toString(ref.page));
        expectedAttributes.put("LB", Integer.toString(ref.approvalMode));
        expectedAttributes.put("UR", ref.website);
        expectedAttributes.put("M3", ref.type.name());
        expectedAttributes.put("N1", ref.comment);

        assertEquals(expectedAttributes, ref.writeXMLAttributes());
    }

    @Test
    public void test2String() {
        Reference ref = new Reference();
        ref.author = "Baranyi";
        ref.year = 1994;
        ref.title = "A dynamic approach...";

        String expected = "Baranyi_1994_A dynamic approach...";
        assertEquals(expected, ref.toString());
    }
}
