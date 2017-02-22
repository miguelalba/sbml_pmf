package org.sbml.jsbml.ext.pmf;

import org.sbml.jsbml.AbstractSBase;
import org.sbml.jsbml.PropertyUndefinedError;
import org.sbml.jsbml.util.StringTools;

import java.util.Map;
import java.util.Objects;
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
     * Integer code from the PMF matrix vocabulary.
     */
    private Integer source;

    /**
     * Description of the compartment.
     */
    private String detail;

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
            attributes.put(PmfConstants.compartment_source, source.toString());
        }
        if (detail != null && !detail.isEmpty()) {
            attributes.put(PmfConstants.compartment_detail, detail);
        }
        return attributes;
    }

    @Override
    public boolean readAttribute(String attributeName, String prefix, String value) {
        switch (attributeName) {
            case PmfConstants.compartment_source:
                source = StringTools.parseSBMLInt(value);
                return true;
            case PmfConstants.compartment_detail:
                detail = value;
                return true;
            default:
                return false;
        }
    }

    @Override
    public String toString() {
        String sb = PmfConstants.compartmentMetaData + " [";
        sb += PmfConstants.compartment_source + "=\"" + (source == null ? "" : source) + "\"";
        sb += " " + PmfConstants.compartment_detail + "=\"" + (detail == null || detail.isEmpty() ? "" : detail) +
                "\"]";

        return sb;
    }

    // --- source attribute ---
    public int getSource() {
        if (isSetSource())
            return source;
        throw new PropertyUndefinedError(PmfConstants.compartment_source, this);
    }

    public boolean isSetSource() {
        return source != null;
    }

    public void setSource(int source) {
        Integer oldSource = this.source;
        this.source = source;
        firePropertyChange(PmfConstants.compartment_source, oldSource, this.source);
    }

    public boolean unsetSource() {
        if (isSetSource()) {
            Integer oldSource = this.source;
            this.source = null;
            firePropertyChange(PmfConstants.compartment_source, oldSource, this.source);
            return true;
        }
        return false;
    }

    // --- detail attribute ---
    public String getDetail() {
        return isSetDetail() ? detail : null;
    }

    public boolean isSetDetail() {
        return detail != null;
    }

    public void setDetail(String detail) {
        String oldDetail = this.detail;
        this.detail = detail;
        firePropertyChange(PmfConstants.compartment_detail, oldDetail, this.detail);
    }

    public boolean unsetDetail() {
        if (isSetDetail()) {
            String oldDetail = this.detail;
            this.detail = null;
            firePropertyChange(PmfConstants.compartment_detail, oldDetail, this.detail);
            return true;
        }
        return false;
    }

    @Override
    public boolean equals(Object object) {
        if (object == null || getClass() != object.getClass())
            return false;

        CompartmentMetaData other = (CompartmentMetaData) object;
        return Objects.equals(source, other.source) && Objects.equals(detail, other.detail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(source, detail);
    }
}
