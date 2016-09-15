import org.junit.Test;
import org.sbml.jsbml.ext.pmf.ModelVariable;
import org.sbml.jsbml.util.StringTools;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created by de on 13.09.2016.
 */
public class ModelVariableTest {

    @Test
    public void testConstructors() {

        // Test empty constructor
        ModelVariable mv = new ModelVariable();
        assertNull(mv.name);
        assertTrue(Double.isNaN(mv.value));

        // Test parametrized constructor
        mv = new ModelVariable("Temperature");
        assertEquals("Temperature", mv.name);
        assertTrue(Double.isNaN(mv.value));

        mv = new ModelVariable("Temperature", 10.0);
        assertEquals("Temperature", mv.name);
        assertEquals(mv.value, 10.0, 0.0);

        // Test copy constructor
        mv = new ModelVariable(mv);
        assertEquals("Temperature", mv.name);
        assertEquals(mv.value, 10.0, 0.0);
    }

    @Test
    public void testClone() {
        ModelVariable mv = new ModelVariable("h0", 7.0).clone();
        assertEquals(mv.name, "h0");
        assertEquals(mv.value, 7.0, .0);
    }

    @Test
    public void testReadAttribute() {
        ModelVariable mv = new ModelVariable();

        assertTrue(mv.readAttribute("name", "pmf", "Temperature"));
        assertEquals("Temperature", mv.name);

        assertTrue(mv.readAttribute("value", "pmf", StringTools.toString(10.0)));
        assertEquals(10.0, mv.value, 0.0);

        assertFalse(mv.readAttribute("nonExistentAttribute", "pmf", "asdf"));
    }

    @Test
    public void testWriteXMLAttributes() {
        // test attributes with empty ModelVariable
        assertTrue(new ModelVariable().writeXMLAttributes().isEmpty());

        // test attributes with filled ModelVariable
        Map<String, String> expectedAttributes = new HashMap<>();
        expectedAttributes.put("name", "Temperature");
        expectedAttributes.put("value", StringTools.toString(10.0));

        ModelVariable modelVariable = new ModelVariable("Temperature", 10.0);
        assertEquals(expectedAttributes, modelVariable.writeXMLAttributes());
    }

    @Test
    public void test2String() {
        assertEquals("ModelVariable [name=\"h0\" value=\"7.0\"]", new ModelVariable("h0", 7.0).toString());
        assertEquals("ModelVariable [name=\"h0\"]", new ModelVariable("h0").toString());
    }
}
