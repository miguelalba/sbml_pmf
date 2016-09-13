package org.sbml.jsbml.ext.pmf;

import org.sbml.jsbml.AbstractSBase;

import java.util.Map;
import java.util.TreeMap;

/**
 * @author Miguel Alba
 */
public class SpeciesMetaData extends AbstractSBase {

    public String source;
    public String detail;
    public String description;

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

    public void initDefaults() {
        setPackageVersion(-1);
        packageName = PmfConstants.shortLabel;
    }

    @Override
    public Map<String, String> writeXMLAttributes() {
        Map<String, String> attributes = new TreeMap<>();
        if (source != null && !source.isEmpty())
            attributes.put(PmfConstants.speciesMetaDataSource, source);
        if (detail != null && !detail.isEmpty())
            attributes.put(PmfConstants.speciesMetaDataDetail, detail);
        if (description != null && !description.isEmpty())
            attributes.put(PmfConstants.speciesMetaDataDescription, description);

        return attributes;
    }

    @Override
    public boolean readAttribute(String attributeName, String prefix,
                                 String value) {
        if (attributeName.equals(PmfConstants.speciesMetaDataSource)) {
            source = value;
            return true;
        }
        if (attributeName.equals(PmfConstants.speciesMetaDataDetail)) {
            detail = value;
            return true;
        }
        if (attributeName.equals(PmfConstants.speciesMetaDataDescription)) {
            description = value;
            return true;
        }
        return false;
    }

    /*
     * (non-Javadoc)
     * @see org.sbml.jsbml.AbstractSBase#toString()
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(PmfConstants.speciesMetaData + "[");
        if (source != null && !source.isEmpty())
            sb.append(" " + PmfConstants.speciesMetaDataSource + "=\"" + source + "\"");
        if (detail != null && !detail.isEmpty())
            sb.append(" " + PmfConstants.speciesMetaDataDetail + "=\"" + detail + "\"");
        if (description != null && !description.isEmpty())
            sb.append(" " + PmfConstants.speciesMetaDataDescription + "=\"" + description + "\"");
        return sb.toString();
    }
}
