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
        setPackageVersion(-1);
    }

    /**
     * Creates a ModelVariable instance from a name.
     */
    public ModelVariable(String name) {
        this.name = name;
        packageName = PmfConstants.shortLabel;
        setPackageVersion(-1);
    }

    /**
     * Creates a ModelVariable instance from a name and value.
     */
    public ModelVariable(String name, double value) {
        this.name = name;
        this.value = value;
        packageName = PmfConstants.shortLabel;
        setPackageVersion(-1);
    }

    /**
     * Creates a ModelVariable instance from a name, value, level and version.
     */
    public ModelVariable(String name, double value, int level, int version) {
        super(level, version);
        this.name = name;
        this.value = value;
        packageName = PmfConstants.shortLabel;
        setPackageVersion(-1);
    }

    /**
     * Clone constructor.
     */
    public ModelVariable(ModelVariable obj) {
        this(obj.name, obj.value, obj.getLevel(), obj.getVersion());
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
        if (attributeName.equals("name")) {
            name = value;
            return true;
        }
        if (attributeName.equals("value")) {
            this.value = StringTools.parseSBMLDouble(value);
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("ModelVariable [");
        if (name != null && !name.isEmpty())
            sb.append("name=\"" + name + "\"");
        if (!Double.isNaN(value))
            sb.append(" value=\"" + value + "\"");
        sb.append("]");

        return sb.toString();
    }
}
