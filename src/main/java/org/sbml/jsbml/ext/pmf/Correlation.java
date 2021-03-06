package org.sbml.jsbml.ext.pmf;

import org.sbml.jsbml.AbstractSBase;
import org.sbml.jsbml.PropertyUndefinedError;
import org.sbml.jsbml.util.StringTools;

import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;

/**
 * Correlation of one {@link org.sbml.jsbml.Parameter}.
 * <p>
 * Example:
 * <code>&lt;pmf:correlation name="h0" value="7.0" /&gt;</code>
 *
 * @author Miguel Alba
 */
public class Correlation extends AbstractSBase {

    /**
     * Parameter name.
     */
    private String name;

    /**
     * Correlation value.
     */
    private Double value;

    public Correlation() {
        packageName = PmfConstants.shortLabel;
    }

    public Correlation(int level, int version) {
        super(level, version);
        packageName = PmfConstants.shortLabel;
    }

    /**
     * Clone constructor.
     */
    public Correlation(Correlation correlation) {
        super(correlation);
        name = correlation.name;
        value = correlation.value;
    }

    /**
     * Clones this class.
     */
    public Correlation clone() {
        return new Correlation(this);
    }

    @Override
    public boolean readAttribute(String attributeName, String prefix, String value) {
        switch (attributeName) {
            case PmfConstants.correlation_name:
                name = value;
                return true;
            case PmfConstants.correlation_value:
                this.value = StringTools.parseSBMLDouble(value);
                return true;
            default:
                return false;
        }
    }

    @Override
    public Map<String, String> writeXMLAttributes() {
        Map<String, String> attributes = new TreeMap<>();
        if (isSetName()) {
            attributes.put(PmfConstants.correlation_name, name);
        }
        if (isSetValue()) {
            attributes.put(PmfConstants.correlation_value, StringTools.toString(value));
        }
        return attributes;
    }

    @Override
    public String toString() {
        return PmfConstants.correlation + " [" +
                PmfConstants.correlation_name + "=\"" + (isSetName() ? name : "") + "\" " +
                PmfConstants.correlation_value + "=\"" + (isSetValue() ? value : "") + "\"]";
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

        Correlation other = (Correlation) object;
        return Objects.equals(name, other.name) && Objects.equals(value, other.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, value);
    }
}
