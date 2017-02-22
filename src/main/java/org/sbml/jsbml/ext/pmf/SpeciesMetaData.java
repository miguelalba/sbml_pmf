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
        if (isSetSource())
            attributes.put(PmfConstants.species_source, source);
        if (isSetDetail())
            attributes.put(PmfConstants.species_detail, detail);
        if (isSetDescription())
            attributes.put(PmfConstants.species_description, description);

        return attributes;
    }

    @Override
    public boolean readAttribute(String attributeName, String prefix,
                                 String value) {
        switch (attributeName) {
            case PmfConstants.species_source:
                source = value;
                return true;
            case PmfConstants.species_detail:
                detail = value;
                return true;
            case PmfConstants.species_description:
                description = value;
                return true;
            default:
                return false;
        }
    }

    @Override
    public String toString() {
        return PmfConstants.speciesMetaData + " ["
                + PmfConstants.species_source + "=\"" + (isSetSource() ? source : "") + "\" " +
                PmfConstants.species_detail + "=\"" + (isSetDetail() ? detail : "") + "\" " +
                PmfConstants.species_description + "=\"" + (isSetDescription() ? description : "") + "\"]";
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
        firePropertyChange(PmfConstants.species_source, oldSource, source);
    }

    public boolean unsetSource() {
        if (isSetSource()) {
            String oldSource = source;
            source = null;
            firePropertyChange(PmfConstants.species_source, oldSource, source);
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
        firePropertyChange(PmfConstants.species_detail, oldDetail, detail);
    }

    public boolean unsetDetail() {
        if (isSetDetail()) {
            String oldDetail = detail;
            detail = null;
            firePropertyChange(PmfConstants.species_detail, oldDetail, detail);
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
        firePropertyChange(PmfConstants.species_description, oldDescription, description);
    }

    public boolean unsetDescription() {
        if (isSetDescription()) {
            String oldDescription = description;
            description = null;
            firePropertyChange(PmfConstants.species_description, oldDescription, description);
            return true;
        }
        return false;
    }
}
