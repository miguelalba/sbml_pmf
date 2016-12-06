package org.sbml.jsbml.ext.pmf;

import org.junit.Test;
import org.sbml.jsbml.Compartment;
import org.sbml.jsbml.ListOf;
import org.sbml.jsbml.Model;
import org.sbml.jsbml.SBase;

import javax.swing.tree.TreeNode;
import java.text.MessageFormat;

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
        // TODO: Test PmfModelPlugin#PmfModelPlugin(PmfModelPlugin)
        // TODO: Test PmfModelPlugin#PmgModelPlugin(Model)
        fail("To be implemented");
    }

    /**
     * Test common methods.
     */
    @Test
    public void testCommon() {

        PmfModelPlugin plugin = new PmfModelPlugin(new Model());

        assertEquals("pmf", plugin.getPackageName());
        assertEquals("pmf", plugin.getPrefix());
        // TODO: PmfModelPlugin#getURI is failing: returning null.
//        assertEquals("___", plugin.getURI());
        assertFalse(plugin.readAttribute("a", "b", "c"));
        assertNull(plugin.writeXMLAttributes());
    }

    /**
     * Test other misc methods.
     * <ul>
     * <li>{@link PmfModelPlugin#clone()}</li>
     * <li>{@link PmfModelPlugin#getParent()}</li>
     * <li>{@link PmfModelPlugin#getParentSBMLObject()}</li>
     * <li>{@link PmfModelPlugin#getAllowsChildren()}</li>
     * <li>{@link PmfModelPlugin#getChildCount()}</li>
     * <li>{@link PmfModelPlugin#getChildAt(int)}</li>
     * </ul>
     */
    @Test
    public void testOther() {
        // TODO: Test PmfModelPlugin#clone()
        // TODO: Test PmfModelPlugin#getParent()
        // TODO: Test PmfModelPlugin#getParentSBMLObject()
        // TODO: Test PmfModelPlugin#getAllowsChildrent()
        // TODO: Test PmfModelPlugin#getChildCount()
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
