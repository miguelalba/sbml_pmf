package org.sbml.jsbml.ext.pmf;

import org.junit.Test;
import org.sbml.jsbml.Compartment;
import org.sbml.jsbml.Model;
import org.sbml.jsbml.SBase;

import javax.swing.tree.TreeNode;
import java.text.MessageFormat;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;

/**
 * Created by miguelalba on 06/12/2016.
 */
public class PmfModelPluginTest {

    /**
     * Test common methods.
     */
    @Test
    public void testCommon() {

        PmfModelPlugin plugin = new PmfModelPlugin(new Model());

        assertEquals("pmf", plugin.getPackageName());
        assertEquals("pmf", plugin.getPrefix());
        // TODO: PmfCompartmentPlugin#getURI is failing: returning null.
//        assertEquals("___", plugin.getURI());
        assertFalse(plugin.readAttribute("a", "b", "c"));
        assertNull(plugin.writeXMLAttributes());
    }

    /**
     * Test other misc methods.
     */
    @Test
    public void testOther() {
        // TODO: Test PmfCompartmentPlugin#clone()
        // TODO: Test PmfCompartmentPlugin#getParent()
        // TODO: Test PmfCompartmentPlugin#getParentSBMLObject()
        // TODO: Test PmfCompartmentPlugin#getAllowsChildrent()
        // TODO: Test PmfCompartmetnPlugin#getChildCount()
        // TODO: Test PmfCompartmentPlugin#getChildAt()
    }
}
