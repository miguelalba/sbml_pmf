import org.junit.Assert;
import org.junit.Test;
import org.sbml.jsbml.ext.pmf.Correlation;
import org.sbml.jsbml.util.StringTools;

import java.util.HashMap;
import java.util.Map;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

/**
 * Created by de on 12.09.2016.
 */
public class CorrelationTest {

    @Test
    public void testConstructors() {
        // Test empty constructor
        Correlation corr = new Correlation();
        assertTrue(corr.name == null);
        assertTrue(Double.isNaN(corr.value));

        // Test parametrized constructor
        corr = new Correlation("h0", 7.0);
        assertEquals("h0", corr.name);
        assertEquals(7.0, corr.value, .0);

        // Test copy constructor
        corr = new Correlation(corr);
        assertEquals("h0", corr.name);
        assertEquals(7.0, corr.value, .0);
    }

    @Test
    public void testClone() {
        Correlation corr = new Correlation("h0", 7.0).clone();
        assertEquals("h0", corr.name);
        assertEquals(7.0, corr.value, .0);
    }

    @Test
    public void testReadAttribute() {
        Correlation correlation = new Correlation();

        Assert.assertTrue(correlation.readAttribute("name", "pmf", "h0"));
        Assert.assertTrue(correlation.readAttribute("value", "pmf", Double.toString(7.0)));
        Assert.assertFalse(correlation.readAttribute("someNonExistentAttribute", "pmf", "asdf"));
    }

    @Test
    public void testWriteXMLAttributes() {

        // test attributes with empty correlation
        Assert.assertTrue(new Correlation().writeXMLAttributes().isEmpty());

        // test attributes with filled correlation
        Correlation correlation = new Correlation("h0", 7.0);

        Map<String, String> expectedAttributes = new HashMap<>();
        expectedAttributes.put("name", "h0");
        expectedAttributes.put("value", StringTools.toString(7.0));

        Assert.assertEquals(expectedAttributes, correlation.writeXMLAttributes());
    }

    @Test
    public void test2String() {
        assertEquals("Correlation [name=\"h0\" value=\"7.0\"]", new Correlation("h0", 7.0).toString());
        assertEquals("Correlation [name=\"\" value=\"\"]", new Correlation().toString());
    }
}
