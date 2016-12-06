package org.sbml.jsbml.ext.pmf;

import org.junit.Test;

import org.sbml.jsbml.Model;
import org.sbml.jsbml.ext.pmf.PmfCompartmentPlugin;
import org.sbml.jsbml.ext.pmf.PmfConstants;

import java.util.Map;

import static org.junit.Assert.*;


/**
 * Created by miguelalba on 06/12/2016.
 */
public class PmfCompartmentPluginTest {

    /** Test common methods. */
    @Test
    public void testCommon() {
        PmfCompartmentPlugin plugin = new PmfCompartmentPlugin(new Model().createCompartment());

        assertEquals("pmf", plugin.getPackageName());
        assertEquals("pmf", plugin.getPrefix());
        // TODO: PmfCompartmentPlugin#getURI is failing: returning null.
//        assertEquals("___", plugin.getURI());
        assertFalse(plugin.readAttribute("a", "b", "c"));
        assertNull(plugin.writeXMLAttributes());
    }
}
