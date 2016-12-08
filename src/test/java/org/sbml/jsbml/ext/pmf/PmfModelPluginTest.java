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
     * Test common methods:
     * <ul>
     * <li>getPackageName()</li>
     * <li>getPrefix()</li>
     * <li>getPrefix()</li>
     * <li>readAttribute(String,String,String)</li>
     * </ul>
     */
    @Test
    public void testCommon() {
        PmfModelPlugin plugin = new PmfModelPlugin(new Model());

        assertEquals("pmf", plugin.getPackageName());
        assertEquals("pmf", plugin.getPrefix());
        assertEquals("http://www.sbml.org/sbml/level3/version1/pmf/version1", plugin.getURI());
        assertFalse(plugin.readAttribute("a", "b", "c"));
        assertTrue(plugin.writeXMLAttributes().isEmpty());
    }

    /**
     * Test other misc methods:
     * <ul>
     * <li>clone()</li>
     * <li>getAllowsChildren()</li>
     * <li>getChildCount()</li>
     * <li>getChildAt()</li>
     * </ul>
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

        // Test getChildCount and getChildAt
        assertTrue(plugin.getChildCount() == 0);

        plugin.createPrimaryModel("a_model.sbml");
        plugin.createDataSource("a_data.numl");
        plugin.createModelVariable("a_variable");
        assertTrue(plugin.getChildCount() == 3);

        assertTrue(plugin.getChildAt(0) instanceof ListOf<?>);
        assertTrue(plugin.getChildAt(1) instanceof ListOf<?>);
        assertTrue(plugin.getChildAt(2) instanceof ListOf<?>);
    }

    /**
     * Test model variable methods:
     * <ul>
     * <li>addModelVariable(ModelVariable)</li>
     * <li>removeModelVariable(ModelVariable)</li>
     * <li>removeModelVariable(int)</li>
     * <li>createModelVariable(String)</li>
     * <li>createModelVariable(String, double)</li>
     * <li>getModelVariableCount()</li>
     * </ul>
     */
    @Test
    public void testModelVariable() {
        PmfModelPlugin plugin = new PmfModelPlugin(new Model());
        assertTrue(0 == plugin.getModelVariableCount());

        ModelVariable mv = new ModelVariable();

        assertTrue(plugin.addModelVariable(mv));
        assertTrue(1 == plugin.getModelVariableCount());

        assertTrue(plugin.removeModelVariable(mv));
        assertTrue(0 == plugin.getModelVariableCount());

        plugin.addModelVariable(mv);
        plugin.removeModelVariable(0);
        assertTrue(0 == plugin.getModelVariableCount());

        plugin.createModelVariable("a", 0);
        assertTrue(1 == plugin.getModelVariableCount());
    }

    /**
     * Test listOfModelVariable methods:
     * <p>
     * <ul>
     * <li>getListOfModelVariables()</li>
     * <li>isSetListOfModelVariables()</li>
     * <li>setListOfModelVariables(ListOf)</li>
     * <li>unsetListOfModelVariables()</li>
     * </ul>
     */
    @Test
    public void testListOfModelVariables() {
        PmfModelPlugin plugin = new PmfModelPlugin(new Model());
        assertFalse(plugin.unsetListOfModelVariables());

        // Test plugin without model variables
        assertTrue(plugin.getListOfModelVariables().isEmpty());
        assertFalse(plugin.isSetListOfModelVariables());
        assertFalse(plugin.unsetListOfModelVariables());

        // Test plugin with correlations
        plugin.createModelVariable("pH", 7.0);
        assertFalse(plugin.getListOfModelVariables().isEmpty());
        assertTrue(plugin.isSetListOfModelVariables());
        assertTrue(plugin.unsetListOfModelVariables());
    }

    /**
     * Test data source methods:
     * <p>
     * <ul>
     * <li>addDataSource(DataSource)</li>
     * <li>removeDataSource(DataSource)</li>
     * <li>removeDataSource(int)</li>
     * <li>createDataSource(String)</li>
     * <li>getDataSourceCount()</li>
     * </ul>
     */
    @Test
    public void testDataSource() {
        PmfModelPlugin plugin = new PmfModelPlugin(new Model());
        assertTrue(0 == plugin.getDataSourceCount());

        DataSource ds = new DataSource();

        assertTrue(plugin.addDataSource(ds));
        assertTrue(1 == plugin.getDataSourceCount());

        assertTrue(plugin.removeDataSource(ds));
        assertTrue(0 == plugin.getDataSourceCount());

        plugin.addDataSource(ds);
        plugin.removeDataSource(0);
        assertTrue(0 == plugin.getDataSourceCount());

        plugin.createDataSource("data.numl");
        assertTrue(1 == plugin.getDataSourceCount());
    }

    /**
     * Test listOfDataSources methods:
     * <p>
     * <ul>
     * <li>getListOfDataSources()</li>
     * <li>isSetListOfDataSources()</li>
     * <li>setListOfDataSources(ListOf)</li>
     * <li>unsetListOfDataSources()</li>
     * </ul>
     */
    @Test
    public void testListOfDataSources() {
        PmfModelPlugin plugin = new PmfModelPlugin(new Model());
        assertFalse(plugin.unsetListOfDataSources());

        // Test plugin without model variables
        assertTrue(plugin.getListOfDataSources().isEmpty());
        assertFalse(plugin.isSetListOfDataSources());
        assertFalse(plugin.unsetListOfDataSources());

        // Test plugin with correlations
        plugin.createDataSource("data.numl");
        assertFalse(plugin.getListOfDataSources().isEmpty());
        assertTrue(plugin.isSetListOfDataSources());
        assertTrue(plugin.unsetListOfDataSources());
    }

    /**
     * Test primary model methods:
     * <p>
     * <ul>
     * <li>addPrimaryModel(PrimaryModel)</li>
     * <li>removePrimaryModel(PrimaryModel)</li>
     * <li>removePrimaryModel(int)</li>
     * <li>createPrimaryModel(String)</li>
     * <li>getPrimaryModelCount()</li>
     * </ul>
     */
    @Test
    public void testPrimaryModel() {

        PmfModelPlugin plugin = new PmfModelPlugin(new Model());
        assertTrue(0 == plugin.getPrimaryModelCount());

        PrimaryModel pm = new PrimaryModel();

        assertTrue(plugin.addPrimaryModel(pm));
        assertTrue(1 == plugin.getPrimaryModelCount());

        assertTrue(plugin.removePrimaryModel(pm));
        assertTrue(0 == plugin.getPrimaryModelCount());

        plugin.addPrimaryModel(pm);
        plugin.removePrimaryModel(0);
        assertTrue(0 == plugin.getPrimaryModelCount());

        plugin.createPrimaryModel("model.sbml");
        assertTrue(1 == plugin.getPrimaryModelCount());
    }

    /**
     * Test listOfPrimaryModels methods:
     * <p>
     * <ul>
     * <li>getListOfPrimaryModels</li>
     * <li>isSetListOfPrimaryModels</li>
     * <li>setListOfPrimaryModels(ListOf)</li>
     * <li>unsetListOfPrimaryModels()</li>
     * </ul>
     */
    @Test
    public void testListOfPrimaryModels() {
        // Test plugin without primary models
        PmfModelPlugin plugin = new PmfModelPlugin(new Model());
        assertTrue(plugin.getListOfPrimaryModels().isEmpty());
        assertFalse(plugin.isSetListOfPrimaryModels());
        assertFalse(plugin.unsetListOfPrimaryModels());

        // Test plugin with primary models
        plugin.createPrimaryModel("model.sbml");
        assertFalse(plugin.getListOfPrimaryModels().isEmpty());
        assertTrue(plugin.isSetListOfPrimaryModels());
        assertTrue(plugin.unsetListOfPrimaryModels());
    }
}
