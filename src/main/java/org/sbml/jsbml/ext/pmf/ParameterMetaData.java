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
 *              description="max conc" min="3.0" max="10.0" /&gt;</code>
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
    public void initDefaults() {
        setPackageVersion(-1);
        packageName = PmfConstants.shortLabel;
    }

    @Override
    public Map<String, String> writeXMLAttributes() {
        Map<String, String> attributes = new TreeMap<>();
        if (!Double.isNaN(p))
            attributes.put(PmfConstants.parameterMetaDataP, StringTools.toString(p));
        if (!Double.isNaN(t))
            attributes.put(PmfConstants.parameterMetaDataT, StringTools.toString(t));
        if (!Double.isNaN(error))
            attributes.put(PmfConstants.parameterMetaDataError, StringTools.toString(error));
        if (description != null && !description.isEmpty())
            attributes.put(PmfConstants.parameterMetaDataDescription, description);
        if (!Double.isNaN(min))
            attributes.put(PmfConstants.parameterMetaDataMin, StringTools.toString(min));
        if (!Double.isNaN(max))
            attributes.put(PmfConstants.parameterMetaDataMax, StringTools.toString(max));
        return attributes;
    }

    @Override
    public boolean readAttribute(String attributeName, String prefix, String value) {
        if (attributeName.equals(PmfConstants.parameterMetaDataP)) {
            p = StringTools.parseSBMLDouble(value);
            return true;
        }
        if (attributeName.equals(PmfConstants.parameterMetaDataT)) {
            t = StringTools.parseSBMLDouble(value);
            return true;
        }
        if (attributeName.equals(PmfConstants.parameterMetaDataError)) {
            error = StringTools.parseSBMLDouble(value);
            return true;
        }
        if (attributeName.equals(PmfConstants.parameterMetaDataDescription)) {
            description = value;
            return true;
        }
        if (attributeName.equals(PmfConstants.parameterMetaDataMin)) {
            min = StringTools.parseSBMLDouble(value);
            return true;
        }
        if (attributeName.equals(PmfConstants.parameterMetaDataMax)) {
            max = StringTools.parseSBMLDouble(value);
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(PmfConstants.parameterMetaData + "[");
        if (!Double.isNaN(p))
            sb.append(" " + PmfConstants.parameterMetaDataP + "=\"" + p + "\"");
        if (!Double.isNaN(t))
            sb.append(" " + PmfConstants.parameterMetaDataT + "=\"" + t + "\"");
        if (!Double.isNaN(error))
            sb.append(" " + PmfConstants.parameterMetaDataError + "=\"" + error + "\"");
        if (description != null && !description.isEmpty())
            sb.append(" " + PmfConstants.parameterMetaDataDescription + "=\"" + description + "\"");
        if (!Double.isNaN(min))
            sb.append(" " + PmfConstants.parameterMetaDataMin + "=\"" + min + "\"");
        if (!Double.isNaN(max))
            sb.append(" " + PmfConstants.parameterMetaDataMax + "=\"" + max + "\"");
        sb.append("]");

        return sb.toString();
    }
}
