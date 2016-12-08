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
        String sb = PmfConstants.correlation + " [";
        sb += "name=\"" + (name == null || name.isEmpty() ? "" : name) + "\"";
        sb += " value=\"" + (Double.isNaN(value) ? "" : value) + "\"]";

        return sb;
    }
}
