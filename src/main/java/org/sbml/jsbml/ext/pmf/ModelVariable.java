package org.sbml.jsbml.ext.pmf;

import org.sbml.jsbml.AbstractSBase;
import org.sbml.jsbml.PropertyUndefinedError;
import org.sbml.jsbml.util.StringTools;

import java.util.Map;
import java.util.Objects;
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
     * Model variable name. E.g. pH, Temperature, wa, etc.
     */
    private String name;

    /**
     * Model variable value.
     */
    private Double value;

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
        if (isSetName())
            attributes.put("name", name);
        if (isSetValue())
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
        return PmfConstants.modelVariable + " [name=\"" + (isSetName() ? name : "") +
                "\" value=\"" + (isSetValue() ? value : "") + "\"]";
    }

    // --- name attribute ---
    public String getName() {
        return isSetName() ? name : null;
    }

    public boolean isSetName() {
        return name != null;
    }

    public void setName(String name) {
        String oldName = this.name;
        this.name = name;
        firePropertyChange(PmfConstants.name, oldName, this.name);
    }

    public boolean unsetName() {
        if (isSetName()) {
            String oldName = this.name;
            name = null;
            firePropertyChange(PmfConstants.name, oldName, name);
            return true;
        }
        return false;
    }

    // --- value attribute ---
    public double getValue() {
        if (isSetValue()) {
            return value;
        }
        // This is necessary if we cannot return null here
        throw new PropertyUndefinedError(PmfConstants.value, this);
    }

    public boolean isSetValue() {
        return value != null;
    }

    public void setValue(double value) {
        Double oldValue = this.value;
        this.value = value;
        firePropertyChange(PmfConstants.value, oldValue, this.value);
    }

    public boolean unsetValue() {
        if (isSetValue()) {
            Double oldValue = value;
            value = null;
            firePropertyChange(PmfConstants.value, oldValue, value);
            return true;
        }
        return false;
    }

    @Override
    public boolean equals(Object object) {
        if (object == null || getClass() != object.getClass())
            return false;

        ModelVariable other = (ModelVariable) object;
        return Objects.equals(name, other.name) && Objects.equals(value, other.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, value);
    }
}
