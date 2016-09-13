package org.sbml.jsbml.ext.pmf;

import org.sbml.jsbml.AbstractSBase;

import java.util.Map;
import java.util.TreeMap;

/**
 * Link to a primary model.
 * <p>
 * Example:
 * <code>&lt;pmf:primaryModel src="model1.sbml" value="7.0 /&gt;</code>
 *
 * @author Miguel Alba
 */
public class PrimaryModel extends AbstractSBase {

    /**
     * Null or empty string if not set.
     */
    public String src;

    public PrimaryModel() {
        initDefaults();
    }

    /**
     * Creates a PrimaryModel instance with a src.
     *
     * @param src
     */
    public PrimaryModel(String src) {
        this.src = src;
        initDefaults();
    }

    /**
     * Creates a PrimaryModel instance with a level and version.
     *
     * @param level   SBML level
     * @param version SBML version
     */
    public PrimaryModel(String src, int level, int version) {
        super(level, version);
        this.src = src;
        initDefaults();
    }

    /**
     * Clone constructor.
     */
    public PrimaryModel(PrimaryModel primaryModel) {
        this(primaryModel.src, primaryModel.getLevel(), primaryModel.getVersion());
    }

    /**
     * Clones this class.
     */
    @Override
    public PrimaryModel clone() {
        return new PrimaryModel(this);
    }

    /**
     * Initializes the default values using the namespace.
     */
    private void initDefaults() {
        setPackageVersion(-1);
        packageName = PmfConstants.shortLabel;
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
    public Map<String, String> writeXMLAttributes() {
        Map<String, String> attributes = new TreeMap<>();
        if (src != null && !src.isEmpty())
            attributes.put("src", src);
        return attributes;
    }

    @Override
    public String toString() {
        return "PrimaryModel [src=\"" + (src == null || src.isEmpty() ? "" : src) + "\"]";
    }
}
