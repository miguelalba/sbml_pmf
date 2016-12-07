package org.sbml.jsbml.ext.pmf;

import org.junit.Test;
import org.sbml.jsbml.ListOf;
import org.sbml.jsbml.Model;

import static org.junit.Assert.*;

/**
 * Test for the {@link PmfModelPlugin} class. Checks:
 * <ul>
 * <li>Constructors</li>
 * <li>Common methods</li>
 * <li>Misc methods</li>
 * <li>ModelVariable methods</li>
 * <li>listOfModelVariable methods</li>
 * <li>DataSource methods</li>
 * <li>listOfDataSource methods</li>
 * <li>PrimaryModel methods</li>
 * <li>ListOfPrimaryModel methods</li>
 * </ul>
 */
public class PmfModelPluginTest {

    /**
     * Test constructors: copy and with {@link Model} parameter.
     */
    @Test
    public void testConstructors() {
        // Test PmfModelPlugin#PmfModelPlugin(PmfModelPlugin)
        PmfModelPlugin plugin = new PmfModelPlugin(new Model());
        assertFalse(plugin.isSetListOfModelVariables());
        assertFalse(plugin.isSetListOfDataSources());
        assertFalse(plugin.isSetListOfPrimaryModels());

        // Test PmfModelPlugin#PmgModelPlugin(Model)
        plugin = new PmfModelPlugin(plugin);
        assertFalse(plugin.isSetListOfModelVariables());
        assertFalse(plugin.isSetListOfDataSources());
        assertFalse(plugin.isSetListOfPrimaryModels());
    }

    /**
     * Test common methods: getPackageName(), getPrefix(), getURI() and readAttribute(String, String, String).
     */
    @Test
    public void testCommon() {
        PmfModelPlugin plugin = new PmfModelPlugin(new Model());

        assertEquals("pmf", plugin.getPackageName());
        assertEquals("pmf", plugin.getPrefix());
        assertEquals("http://www.sbml.org/sbml/level3/version1/pmf/version1", plugin.getURI());
        assertFalse(plugin.readAttribute("a", "b", "c"));
        assertNull(plugin.writeXMLAttributes());
    }

    /**
     * Test other misc methods: clone(), getAllowsChildren(), getChildCount() and getChildAt().
     */
    @Test
    public void testOther() {
        // Test PmfModelPlugin#clone()
        Model model = new Model();
        PmfModelPlugin plugin = new PmfModelPlugin(model).clone();
        assertFalse(plugin.isSetListOfModelVariables());
        assertFalse(plugin.isSetListOfDataSources());
        assertFalse(plugin.isSetListOfPrimaryModels());

        // Test PmfModelPlugin#getAllowsChildren()
        assertTrue(plugin.getAllowsChildren());

        // TODO: Test PmfModelPlugin#getChildCount()
        {
            PmfModelPlugin emptyPlugin = new PmfModelPlugin(new Model());
            assertTrue(emptyPlugin.getChildCount() == 0);

            PmfModelPlugin filledPlugin = new PmfModelPlugin(new Model());
            filledPlugin.createPrimaryModel("a_model.sbml");
            filledPlugin.createDataSource("a_data.numl");
            filledPlugin.createModelVariable("a_variable");
            assertTrue(filledPlugin.getChildCount() == 3);
        }

        // TODO: Test PmfModelPlugin#getChildAt()
        fail("To be implemented");
    }

    /**
     * Test model variable methods:
     * <ul>
     * <li>{@link PmfModelPlugin#addModelVariable(ModelVariable)}</li>
     * <li>{@link PmfModelPlugin#removeModelVariable(ModelVariable)}</li>
     * <li>{@link PmfModelPlugin#removeModelVariable(int)}</li>
     * <li>{@link PmfModelPlugin#createModelVariable(String)}</li>
     * <li>{@link PmfModelPlugin#createModelVariable(String, double)}</li>
     * <li>{@link PmfModelPlugin#getModelVariableCount()}</li>
     * </ul>
     */
    @Test
    public void testModelVariable() {
        // TODO: Test PmfModelPlugin#addModelVariable(ModelVariable)
        // TODO: Test PmfModelPlugin#removeModelVariable(ModelVariable)
        // TODO: Test PmfModelPlugin#removeModelVariable(int)
        // TODO: Test PmfModelPlugin#createModelVariable(String)
        // TODO: Test PmfModelPlugin#createModelVariable(String, double)
        // TODO: Test PmfModelPlugin#getModelVariableCount()
        fail("To be implemented");
    }

    /**
     * Test listOfModelVariable methods:
     * <ul>
     * <li>{@link PmfModelPlugin#getListOfModelVariables()}</li>
     * <li>{@link PmfModelPlugin#isSetListOfModelVariables()}</li>
     * <li>{@link PmfModelPlugin#setListOfModelVariables(ListOf)}</li>
     * <li>{@link PmfModelPlugin#unsetListOfModelVariables()}</li>
     * </ul>
     */
    @Test
    public void testListOfModelVariables() {
        // TODO: Test PmfModelPlugin#getListOfModelVariables()
        // TODO: Test PmfModelPlugin#isSetListOfModelVariables()
        // TODO: Test PmfModelPlugin#setListOfModelVariables()
        // TODO: Test PmfModelPlugin#unsetListOfModelVariables()
        fail("To be implemented");
    }

    // TODO: Test DataSource as in testModelVariable
    @Test
    public void testDataSource() {
        fail("To be implemented");
    }

    // TODO: Test listOfDataSources as in testListOfModelVariables
    @Test
    public void testListOfDataSources() {
        fail("To be implemented");
    }

    // TODO: Test PrimaryModel as in testModelVariable
    @Test
    public void testPrimaryModel() {
        fail("To be implemented");
    }

    // TODO: Test listOfPrimaryModels as in testListOfModelVariables
    @Test
    public void testListOfPrimaryModels() {
        fail("To be implemented");
    }
}
