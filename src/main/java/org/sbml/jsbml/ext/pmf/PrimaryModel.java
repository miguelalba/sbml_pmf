package org.sbml.jsbml.ext.pmf;

import org.sbml.jsbml.AbstractSBase;

import java.util.Collections;
import java.util.Map;
import java.util.Objects;

/**
 * Link to a primary model.
 * <p>
 * Example:
 * <code>&lt;pmf:primaryModel src="model1.sbml" value="7.0 /&gt;</code>
 *
 * @author Miguel Alba
 */
public class PrimaryModel extends AbstractSBase {

    private String src;

    public PrimaryModel() {
        packageName = PmfConstants.shortLabel;
    }

    public PrimaryModel(int level, int version) {
        super(level, version);
        packageName = PmfConstants.shortLabel;
    }

    /**
     * Clone constructor.
     */
    public PrimaryModel(PrimaryModel primaryModel) {
        super(primaryModel);
        src = primaryModel.src;
        packageName = PmfConstants.shortLabel;
    }

    /**
     * Clones this class.
     */
    @Override
    public PrimaryModel clone() {
        return new PrimaryModel(this);
    }

    @Override
    public boolean readAttribute(String attributeName, String prefix, String value) {
        if (attributeName.equals(PmfConstants.primaryModel_src)) {
            src = value;
            return true;
        }
        return false;
    }

    @Override
    public Map<String, String> writeXMLAttributes() {
        return isSetSrc() ? Collections.singletonMap(PmfConstants.primaryModel_src, src) : Collections.emptyMap();
    }

    @Override
    public String toString() {
        return "primaryModel [src=\"" + (src == null || src.isEmpty() ? "" : src) + "\"]";
    }

    // --- src attribute ---
    public String getSrc() {
        return isSetSrc() ? src : null;
    }

    public boolean isSetSrc() {
        return src != null;
    }

    public void setSrc(String src) {
        String oldSrc = this.src;
        this.src = src;
        firePropertyChange(PmfConstants.src, oldSrc, this.src);
    }

    public boolean unsetSrc() {
        if (isSetSrc()) {
            String oldSrc = src;
            src = null;
            firePropertyChange(PmfConstants.src, oldSrc, src);
            return true;
        }
        return false;
    }

    @Override
    public boolean equals(Object object) {
        if (object == null || getClass() != object.getClass())
            return false;

        PrimaryModel other = (PrimaryModel) object;
        return Objects.equals(src, other.src);
    }

    @Override
    public int hashCode() {
        return Objects.hash(src);
    }
}
