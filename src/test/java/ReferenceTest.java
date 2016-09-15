import org.junit.Test;
import org.sbml.jsbml.ext.pmf.Reference;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created by de on 13.09.2016.
 */
public class ReferenceTest {

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

        // Test copy constructor
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

    @Test
    public void testReadAttribute() {
        Reference ref = new Reference();

        assertTrue(ref.readAttribute("AU", "pmf", "Baranyi, J."));
        assertEquals("Baranyi, J.", ref.author);

        assertTrue(ref.readAttribute("PY", "pmf", "1994"));
        assertTrue(1994 == ref.year);

        assertTrue(ref.readAttribute("TI", "pmf", "A dynamic approach to predicting bacterial growth in food"));
        assertEquals("A dynamic approach to predicting bacterial growth in food", ref.title);

        assertTrue(ref.readAttribute("AB", "pmf", "A new member ..."));
        assertEquals("A new member ...", ref.abstractText);

        assertTrue(ref.readAttribute("T2", "pmf", "International Journal of Food Microbiology"));
        assertEquals("International Journal of Food Microbiology", ref.journal);

        assertTrue(ref.readAttribute("IS", "pmf", "3"));
        assertEquals("3", ref.issue);

        assertTrue(ref.readAttribute("SP", "pmf", "277"));
        assertTrue(277 == ref.page);

        assertTrue(ref.readAttribute("LB", "pmf", "1"));
        assertTrue(1 == ref.approvalMode);

        assertTrue(ref.readAttribute("UR", "pmf", "http://www.sciencedirect.com/science/article/pii/0168160594901570"));
        assertEquals("http://www.sciencedirect.com/science/article/pii/0168160594901570", ref.website);

        assertTrue(ref.readAttribute("M3", "pmf", Reference.ReferenceType.Paper.name()));
        assertEquals(Reference.ReferenceType.Paper, ref.type);

        assertTrue(ref.readAttribute("N1", "pmf", "improvised comment"));
        assertEquals("improvised comment", ref.comment);

        assertFalse(ref.readAttribute("someNonExistentAttribute", "pmf", "asdf"));
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
