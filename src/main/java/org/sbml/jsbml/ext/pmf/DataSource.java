package org.sbml.jsbml.ext.pmf;

import org.sbml.jsbml.AbstractSBase;

import java.util.Collections;
import java.util.Map;
import java.util.Objects;

/**
 * Links to a separate file with data (E.g. NuMLDocument).
 * <p>
 * Example:
 * <code>&lt;pmf:dataSource src="data.numl" /&gt;</code>
 *
 * @author Miguel Alba
 */
public class DataSource extends AbstractSBase {

    /**
     * Path to external file with data.
     */
    private String src;

    public DataSource() {
        packageName = PmfConstants.shortLabel;
    }

    /**
     * Creates a DataSource instance with a level and version.
     *
     * @param level   SBML level
     * @param version SBML Version
     */
    public DataSource(int level, int version) {
        super(level, version);
        packageName = PmfConstants.shortLabel;
    }

    /**
     * Clone constructor.
     */
    public DataSource(DataSource obj) {
        super(obj);
        src = obj.src;
        packageName = PmfConstants.shortLabel;
    }

    @Override
    public DataSource clone() {
        return new DataSource(this);
    }

    @Override
    public Map<String, String> writeXMLAttributes() {
        return (src == null || src.isEmpty()) ? Collections.emptyMap() : Collections.singletonMap("src", src);
    }

    @Override
    public boolean readAttribute(String attributeName, String prefix, String value) {

        boolean attributeIsRead;
        if (attributeName.equals("src")) {
            src = value;
            attributeIsRead = true;
        } else {
            attributeIsRead = false;
        }

        return attributeIsRead;
    }

    @Override
    public String toString() {
        return PmfConstants.dataSource + " [src=\"" + (src == null || src.isEmpty() ? "" : src) + "\"]";
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
            String oldSrc = this.src;
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

        DataSource other = (DataSource) object;
        return Objects.equals(src, other.src);
    }

    @Override
    public int hashCode() {
        return Objects.hash(src);
    }
}
