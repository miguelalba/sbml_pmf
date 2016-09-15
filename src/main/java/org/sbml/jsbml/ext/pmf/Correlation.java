package org.sbml.jsbml.ext.pmf;

import org.sbml.jsbml.AbstractSBase;
import org.sbml.jsbml.util.StringTools;

import java.util.Map;
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
     * Parameter name. Null or empty string if not set.
     */
    public String name;

    /**
     * Correlation value. Double.NaN if not set.
     */
    public double value = Double.NaN;

    public Correlation() {
        packageName = PmfConstants.shortLabel;
    }

    /**
     * Creates a Correlation instance from a name and value.
     */
    public Correlation(String name, double value) {
        this.name = name;
        this.value = value;
        packageName = PmfConstants.shortLabel;
    }

    /**
     * Creates a Correlation instance from a name, value, level and version.
     */
    public Correlation(String name, double value, int level, int version) {
        super(level, version);
        this.name = name;
        this.value = value;
        packageName = PmfConstants.shortLabel;
    }

    /**
     * Clone constructor.
     */
    public Correlation(Correlation correlation) {
        this(correlation.name, correlation.value, correlation.getLevel(), correlation.getVersion());
    }

    /**
     * Clones this class.
     */
    public Correlation clone() {
        return new Correlation(this);
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
    public Map<String, String> writeXMLAttributes() {
        Map<String, String> attributes = new TreeMap<>();
        if (name != null && !name.isEmpty())
            attributes.put("name", name);
        if (!Double.isNaN(value))
            attributes.put("value", StringTools.toString(value));
        return attributes;
    }

    @Override
    public String toString() {
        String sb = "Correlation [";
        sb += "name=\"" + (name == null || name.isEmpty() ? "" : name) + "\"";
        sb += " value=\"" + (Double.isNaN(value) ? "" : value) + "\"]";

        return sb;
    }
}
