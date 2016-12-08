package org.sbml.jsbml.ext.pmf;

import org.sbml.jsbml.AbstractSBase;

import java.util.Collections;
import java.util.Map;

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
        if (attributeName.equals("src")) {
            src = value;
            return true;
        }
        return false;
    }

    @Override
    public Map<String, String> writeXMLAttributes() {
        return (src == null || src.isEmpty()) ? Collections.emptyMap() : Collections.singletonMap("src", src);
    }

    @Override
    public String toString() {
        return "primaryModel [src=\"" + (src == null || src.isEmpty() ? "" : src) + "\"]";
    }
}
