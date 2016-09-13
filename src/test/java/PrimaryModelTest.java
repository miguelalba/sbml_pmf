import org.junit.Test;
import org.sbml.jsbml.ext.pmf.PrimaryModel;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created by de on 13.09.2016.
 */
public class PrimaryModelTest {

    @Test
    public void testConstructors() {
        // Test empty constructor
        assertNull(new PrimaryModel().src);

        // Test parametrized constructor
        PrimaryModel pm = new PrimaryModel("model1.sbml");
        assertEquals("model1.sbml", pm.src);

        // Test copy constructor
        assertEquals("model1.sbml", new PrimaryModel(pm).src);
    }

    @Test
    public void testClone() {
        PrimaryModel clone = new PrimaryModel("model1.sbml").clone();
        assertEquals("model1.sbml", clone.src);
    }

    @Test
    public void testReadAttribute() {
        PrimaryModel pm = new PrimaryModel();
        assertTrue(pm.readAttribute("src", "pmf", "model1.sbml"));
        assertFalse(pm.readAttribute("nonExistentAttribute", "pmf", "asdf"));
    }

    @Test
    public void testWriteXMLAttributes() {
        // test attributes with empty PrimaryModel
        assertTrue(new PrimaryModel().writeXMLAttributes().isEmpty());

        // test attributes with filled ModelVariable
        Map<String, String> expectedAttributes = new HashMap<>();
        expectedAttributes.put("src", "model1.sbml");

        assertEquals(expectedAttributes, new PrimaryModel("model1.sbml").writeXMLAttributes());
    }

    @Test
    public void test2String() {
        assertEquals("PrimaryModel [src=\"\"]", new PrimaryModel().toString());
        assertEquals("PrimaryModel [src=\"model1.sbml\"]", new PrimaryModel("model1.sbml").toString());
    }
}
