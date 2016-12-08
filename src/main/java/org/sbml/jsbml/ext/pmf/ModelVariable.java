package org.sbml.jsbml.ext.pmf;

import org.sbml.jsbml.AbstractSBase;
import org.sbml.jsbml.util.StringTools;

import java.util.Map;
import java.util.TreeMap;

/**
 * Model variable. Includes a name and a value.
 * <p>
 * Example:
 * <code>&lt;pmf:modelVariable name="temperature" value="10.0 /&gt;</code>
 *
 * @author Miguel Alba
 */
public class ModelVariable extends AbstractSBase {

    /**
     * Model variable name. E.g. pH, Temperature, wa, etc. Null or empty string if not set.
     */
    public String name;

    /**
     * Model variable value. Double.NaN if not set.
     */
    public double value = Double.NaN;

    /**
     * Creates a ModelVariable instance.
     */
    public ModelVariable() {
        packageName = PmfConstants.shortLabel;
    }

    public ModelVariable(int level, int version) {
        super(level, version);
        packageName = PmfConstants.shortLabel;
    }

    /**
     * Clone constructor.
     */
    public ModelVariable(ModelVariable obj) {
        super(obj);
        name = obj.name;
        value = obj.value;
        packageName = PmfConstants.shortLabel;
    }

    /**
     * Clones this class.
     */
    @Override
    public ModelVariable clone() {
        return new ModelVariable(this);
    }

    @Override
    public Map<String, String> writeXMLAttributes() {
        Map<String, String> attributes = new TreeMap<>();
        if (name != null && !name.isEmpty())
            attributes.put("name", name);
        if (!Double.isNaN(value))
            attributes.put("value", StringTools.toString(value));
        return attributes;
    }

    @Override
    public boolean readAttribute(String attributeName, String prefix, String value) {

        boolean attributeIsRead;

        if (attributeName.equals("name")) {
            name = value;
            attributeIsRead = true;
        } else if (attributeName.equals("value")) {
            this.value = StringTools.parseSBMLDouble(value);
            attributeIsRead = true;
        } else {
            attributeIsRead = false;
        }

        return attributeIsRead;
    }

    @Override
    public String toString() {
        String sb = PmfConstants.modelVariable + " [";
        sb += "name=\"" + (name == null || name.isEmpty() ? "" : name) + "\"";
        sb += " value=\"" + (Double.isNaN(value) ? "" : value) + "\"]";

        return sb;
    }
}
