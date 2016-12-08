package org.sbml.jsbml.ext.pmf;

import org.sbml.jsbml.AbstractSBase;

import java.util.Collections;
import java.util.Map;

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
     * Path to external file with data. Null or empty string if not set.
     */
    public String src;

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
}
