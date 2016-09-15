package org.sbml.jsbml.ext.pmf;

import org.sbml.jsbml.AbstractSBase;

import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

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
        initDefaults();
    }

    /**
     * Creates a DataSource instance with a src.
     */
    public DataSource(String src) {
        this.src = src;
        initDefaults();
    }

    /**
     * Creates a DataSource instance with a level and version.
     *
     * @param level   SBML level
     * @param version SBML Version
     */
    public DataSource(int level, int version) {
        super(level, version);
        initDefaults();
    }

    /**
     * Creates a DataSource instance with a src, level and version.
     *
     * @param src
     * @param level   SBML level
     * @param version SBML version
     */
    public DataSource(String src, int level, int version) {
        super(level, version);
        this.src = src;
        initDefaults();
    }

    /**
     * Clone constructor.
     */
    public DataSource(DataSource obj) {
        super(obj);
        src = obj.src;
    }

    @Override
    public DataSource clone() {
        return new DataSource(this);
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
        return (src == null || src.isEmpty()) ? Collections.emptyMap() : Collections.singletonMap("src", src);
    }

    @Override
    public boolean readAttribute(String attributeName, String prefix, String value) {
        if (attributeName.equals("src")) {
            src = value;
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "DataSource [src=\"" + (src == null || src.isEmpty() ? "" : src) + "\"]";
    }
}
