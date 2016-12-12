package org.sbml.jsbml.ext.pmf;

import org.sbml.jsbml.SBase;
import org.sbml.jsbml.Species;
import org.sbml.jsbml.ext.AbstractSBasePlugin;

import java.text.MessageFormat;
import java.util.Objects;

/**
 * Created by de on 12.09.2016.
 */
public class PmfSpeciesPlugin extends AbstractSBasePlugin {

    private SpeciesMetaData metaData;

    public PmfSpeciesPlugin(PmfSpeciesPlugin plugin) {
        super(plugin);
        if (plugin.isSetMetaData())
            setMetaData(plugin.metaData.clone());
    }

    public PmfSpeciesPlugin(Species species) {
        super(species);
        setNamespace(PmfConstants.namespaceURI);
    }

    public PmfSpeciesPlugin clone() {
        return new PmfSpeciesPlugin(this);
    }

    // --- common ---
    @Override
    public String getPackageName() {
        return PmfConstants.shortLabel;
    }

    @Override
    public String getPrefix() {
        return PmfConstants.shortLabel;
    }

    @Override
    public String getURI() {
        return getElementNamespace();
    }

    @Override
    public boolean readAttribute(String attributeName, String prefix, String value) {
        return false;
    }

    // --- plugin methods ---
    @Override
    public boolean getAllowsChildren() {
        return true;
    }

    @Override
    public int getChildCount() {
        return isSetMetaData() ? 1 : 0;
    }

    @Override
    public SBase getChildAt(int childIndex) {
        if (childIndex < 0) {
            throw new IndexOutOfBoundsException(MessageFormat.format(
                    resourceBundle.getString("IndexSurpassesBoundsException"),
                    childIndex, 0));
        }
        int pos = 0;
        if (isSetMetaData()) {
            if (pos == childIndex)
                return getMetadata();
            pos++;
        }
        throw new IndexOutOfBoundsException(MessageFormat.format(
                resourceBundle.getString("IndexExceedsBoundsException"),
                childIndex, Math.min(pos, 0)));
    }

    // --- common --- (C&P from PmfRulePlugin)

    // --- meta data ---
    public SpeciesMetaData getMetadata() {
        return metaData;
    }


    public boolean isSetMetaData() {
        return this.metaData != null;
    }


    /**
     * Sets the value of metadata
     *
     * @param metadata the value of metadata to be set.
     */
    public void setMetaData(SpeciesMetaData metadata) {
        unsetMetadata();
        this.metaData = metadata;
        if (isSetExtendedSBase()) {
            metadata.setPackageVersion(-1);
            getExtendedSBase().registerChild(metadata);
        }
    }


    /**
     * Unsets the variable metadata.
     *
     * @return {@code true} if metadata was set before, otherwise {@code false}.
     */
    public boolean unsetMetadata() {
        if (isSetMetaData()) {
            SpeciesMetaData oldMetadata = metaData;
            metaData = null;
            firePropertyChange("metadata", oldMetadata, metaData);
            return true;
        }
        return false;
    }

    @Override
    public boolean equals(Object object) {
        if (object == null)
            return false;
        if (getClass() != object.getClass())
            return false;

        final PmfSpeciesPlugin other = (PmfSpeciesPlugin) object;
        return Objects.equals(metaData, other.metaData);
    }

    @Override
    public int hashCode() {
        return Objects.hash(metaData);
    }
}
