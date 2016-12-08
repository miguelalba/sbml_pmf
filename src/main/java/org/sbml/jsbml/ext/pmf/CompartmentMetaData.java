package org.sbml.jsbml.ext.pmf;

import org.sbml.jsbml.AbstractSBase;
import org.sbml.jsbml.util.StringTools;

import java.util.Map;
import java.util.TreeMap;

/**
 * Extended meta data for a {@link org.sbml.jsbml.Compartment}
 * <p>
 * Example: <code>&lt;compartmentMetaData source="7" detail="some details" /&gt;</code>
 *
 * @author Miguel Alba
 * @date 9.09.2016
 */
public class CompartmentMetaData extends AbstractSBase {

    /**
     * Integer code from the PMF matrix vocabulary. Null if not set.
     */
    public Integer source = null;

    /**
     * Description of the compartment. Null or empty string if not set.
     */
    public String detail = null;

    /**
     * Creates a CompartmentMetaData instance.
     */
    public CompartmentMetaData() {
        packageName = PmfConstants.shortLabel;
    }

    public CompartmentMetaData(int level, int version) {
        super(level, version);
        packageName = PmfConstants.shortLabel;
    }

    /**
     * Clone constructor.
     */
    public CompartmentMetaData(CompartmentMetaData obj) {
        super(obj);
        source = obj.source;
        detail = obj.detail;
    }

    /**
     * Clones this class.
     */
    @Override
    public CompartmentMetaData clone() {
        return new CompartmentMetaData(this);
    }

    @Override
    public Map<String, String> writeXMLAttributes() {
        Map<String, String> attributes = new TreeMap<>();
        if (source != null) {
            attributes.put("source", source.toString());
        }
        if (detail != null && !detail.isEmpty()) {
            attributes.put("detail", detail);
        }
        return attributes;
    }

    @Override
    public boolean readAttribute(String attributeName, String prefix, String value) {

        boolean attributeIsRead;

        if (attributeName.equals("source")) {
            source = StringTools.parseSBMLInt(value);
            attributeIsRead = true;
        } else if (attributeName.equals("detail")) {
            detail = value;
            attributeIsRead = true;
        } else {
            attributeIsRead = false;
        }

        return attributeIsRead;
    }

    @Override
    public String toString() {
        String sb = PmfConstants.compartmentMetaData + " [";
        sb += "source=\"" + (source == null ? "" : source) + "\"";
        sb += " detail=\"" + (detail == null || detail.isEmpty() ? "" : detail) + "\"]";

        return sb;
    }

}
