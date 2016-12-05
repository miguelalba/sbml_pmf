package org.sbml.jsbml.ext.pmf;

import org.sbml.jsbml.AbstractSBase;
import org.sbml.jsbml.util.StringTools;

import java.util.Map;
import java.util.TreeMap;

/**
 * Extended meta data for a Parameter.
 * <p>
 * Example:
 * <code>&lt;parameterMetaData p="2.220" t="34.394" error="9.922"
 * description="max conc" min="3.0" max="10.0" /&gt;</code>
 *
 * @author Miguel Alba
 */
public class ParameterMetaData extends AbstractSBase {

    public double p = Double.NaN;
    public double t = Double.NaN;
    public double error = Double.NaN;
    public String description;

    /**
     * Minimum value. Double.NaN if not set.
     */
    public double min = Double.NaN;

    /**
     * Maximum value. Double.NaN if not set.
     */
    public double max = Double.NaN;

    /**
     * Creates a ParameterMetaData instance.
     */
    public ParameterMetaData() {
        initDefaults();
    }

    /**
     * Clone constructor.
     */
    public ParameterMetaData(ParameterMetaData obj) {
        super(obj);
        p = obj.p;
        t = obj.t;
        error = obj.error;
        description = obj.description;
        min = obj.min;
        max = obj.max;
    }

    /**
     * Clones this class.
     */
    public ParameterMetaData clone() {
        return new ParameterMetaData(this);
    }

    /**
     * Initializes the default value using the namespace.
     */
    private void initDefaults() {
        setPackageVersion(-1);
        packageName = PmfConstants.shortLabel;
    }

    @Override
    public Map<String, String> writeXMLAttributes() {
        Map<String, String> attributes = new TreeMap<>();
        if (!Double.isNaN(p))
            attributes.put("p", Double.toString(p));
        if (!Double.isNaN(t))
            attributes.put("t", Double.toString(t));
        if (!Double.isNaN(error))
            attributes.put("error", Double.toString(error));
        if (description != null && !description.isEmpty())
            attributes.put("description", description);
        if (!Double.isNaN(min))
            attributes.put("min", Double.toString(min));
        if (!Double.isNaN(max))
            attributes.put("max", Double.toString(max));
        return attributes;
    }

    @Override
    public boolean readAttribute(String attributeName, String prefix, String value) {
        boolean isAttributeRead;

        if (attributeName.equals("p")) {
            p = StringTools.parseSBMLDouble(value);
            isAttributeRead = true;
        } else if (attributeName.equals("t")) {
            t = StringTools.parseSBMLDouble(value);
            isAttributeRead = true;
        } else if (attributeName.equals("error")) {
            error = StringTools.parseSBMLDouble(value);
            isAttributeRead = true;
        } else if (attributeName.equals("description")) {
            description = value;
            isAttributeRead = true;
        } else if (attributeName.equals("min")) {
            min = StringTools.parseSBMLDouble(value);
            isAttributeRead = true;
        } else if (attributeName.equals("max")) {
            max = StringTools.parseSBMLDouble(value);
            isAttributeRead = true;
        } else {
            isAttributeRead = false;
        }

        return isAttributeRead;
    }

    @Override
    public String toString() {
        return PmfConstants.parameterMetaData +
                " [p=\"" + (Double.isNaN(p) ? "" : p) +
                "\" t=\"" + (Double.isNaN(t) ? "" : t) +
                "\" error=\"" + (Double.isNaN(error) ? "" : error) +
                "\" description=\"" + (description == null || description.isEmpty() ? "" : description) +
                "\" min=\"" + (Double.isNaN(min) ? "" : min) +
                "\" max=\"" + (Double.isNaN(max) ? "" : max) + "\"]";
    }
}
