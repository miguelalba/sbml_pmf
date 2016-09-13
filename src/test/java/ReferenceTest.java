import org.junit.Test;
import org.sbml.jsbml.ext.pmf.Reference;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

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
        fail();
    }

    @Test
    public void testClone() {
        fail();
    }

    @Test
    public void testReadAttribute() {
        fail();
    }

    @Test
    public void testWriteXMLAttributes() {
        fail();
    }

    @Test
    public void test2String() {
        fail();
    }
}
