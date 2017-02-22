package org.sbml.jsbml.ext.pmf;

import org.sbml.jsbml.AbstractSBase;

import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;

/**
 * Example:
 * <code>&lt;speciesMetaData source="007" detail="Salmonella spec." description="description" /&gt;</code>
 *
 * @author Miguel Alba
 */
public class SpeciesMetaData extends AbstractSBase {

    private String source;
    private String detail;
    private String description;

    public SpeciesMetaData() {
        initDefaults();
    }

    public SpeciesMetaData(SpeciesMetaData obj) {
        super(obj);
        source = obj.source;
        detail = obj.detail;
        description = obj.description;
    }

    public SpeciesMetaData clone() {
        return new SpeciesMetaData(this);
    }

    private void initDefaults() {
        setPackageVersion(-1);
        packageName = PmfConstants.shortLabel;
    }

    @Override
    public Map<String, String> writeXMLAttributes() {
        Map<String, String> attributes = new TreeMap<>();
        if (source != null && !source.isEmpty())
            attributes.put("source", source);
        if (detail != null && !detail.isEmpty())
            attributes.put("detail", detail);
        if (description != null && !description.isEmpty())
            attributes.put("description", description);

        return attributes;
    }

    @Override
    public boolean readAttribute(String attributeName, String prefix,
                                 String value) {

        boolean isAttributeRead;

        if (attributeName.equals("source")) {
            source = value;
            isAttributeRead = true;
        } else if (attributeName.equals("detail")) {
            detail = value;
            isAttributeRead = true;
        } else if (attributeName.equals("description")) {
            description = value;
            isAttributeRead = true;
        } else {
            isAttributeRead = false;
        }

        return isAttributeRead;
    }

    @Override
    public String toString() {
        return PmfConstants.speciesMetaData +
                " [source=\"" + (source == null || source.isEmpty() ? "" : source) +
                "\" detail=\"" + (detail == null || detail.isEmpty() ? "" : detail) +
                "\" description=\"" + (description == null || description.isEmpty() ? "" : description) + "\"]";
    }

    @Override
    public boolean equals(Object object) {
        if (object == null || getClass() != object.getClass())
            return false;

        final SpeciesMetaData other = (SpeciesMetaData) object;
        return Objects.equals(source, other.source) && Objects.equals(detail, other.detail) &&
                Objects.equals(description, other.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(source, detail, description);
    }

    // --- source attribute ---
    public String getSource() {
        return isSetSource() ? source : null;
    }

    public boolean isSetSource() {
        return source != null;
    }

    public void setSource(String source) {
        String oldSource = this.source;
        this.source = source;
        firePropertyChange("source", oldSource, source);
    }

    public boolean unsetSource() {
        if (isSetSource()) {
            String oldSource = source;
            source = null;
            firePropertyChange("source", oldSource, source);
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
        firePropertyChange("detail", oldDetail, detail);
    }

    public boolean unsetDetail() {
        if (isSetDetail()) {
            String oldDetail = detail;
            detail = null;
            firePropertyChange("detail", oldDetail, detail);
            return true;
        }
        return false;
    }

    // --- description attribute ---
    public String getDescription() {
        return isSetDescription() ? description : null;
    }

    public boolean isSetDescription() {
        return description != null;
    }

    public void setDescription(String description) {
        String oldDescription = this.description;
        this.description = description;
        firePropertyChange("description", oldDescription, description);
    }

    public boolean unsetDescription() {
        if (isSetDescription()) {
            String oldDescription = description;
            description = null;
            firePropertyChange("description", oldDescription, description);
            return true;
        }
        return false;
    }
}
