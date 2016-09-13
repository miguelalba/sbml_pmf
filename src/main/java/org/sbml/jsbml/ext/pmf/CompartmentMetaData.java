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
 * @data 9.09.2016
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
        initDefaults();
    }

    /**
     * Clone constructor.
     */
    public CompartmentMetaData(CompartmentMetaData obj) {
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

    /**
     * Initializes the default values using the namespace.
     */
    public void initDefaults() {
        setPackageVersion(-1);
        packageName = PmfConstants.shortLabel;
    }

    @Override
    public Map<String, String> writeXMLAttributes() {
        Map<String, String> attributes = new TreeMap<>();
        if (source != null) {
            attributes.put(PmfConstants.compartmentMetaDataSource, source.toString());
        }
        if (detail != null && !detail.isEmpty()) {
            attributes.put(PmfConstants.compartmentMetaDataDetail, detail);
        }
        return attributes;
    }

    @Override
    public boolean readAttribute(String attributeName, String prefix, String value) {
        if (attributeName.equals(PmfConstants.compartmentMetaDataSource)) {
            source = StringTools.parseSBMLInt(value);
            return true;
        }

        if (attributeName.equals(PmfConstants.compartmentMetaDataDetail)) {
            detail = value;
            return true;
        }

        return false;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(PmfConstants.compartmentMetaData + "[");
        if (source != null)
            sb.append(PmfConstants.compartmentMetaDataSource + "=\"" + source + "\"");
        if (detail != null)
            sb.append(PmfConstants.compartmentMetaDataDetail + "=\"" + detail + "\"");
        return sb.toString();
    }
}
