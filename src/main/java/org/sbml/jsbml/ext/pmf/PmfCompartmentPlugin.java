package org.sbml.jsbml.ext.pmf;

import org.sbml.jsbml.Compartment;
import org.sbml.jsbml.ext.AbstractSBasePlugin;

import javax.swing.tree.TreeNode;
import java.text.MessageFormat;
import java.util.Objects;

/**
 * @author Miguel Alba
 */
public class PmfCompartmentPlugin extends AbstractSBasePlugin {

    private CompartmentMetaData metaData;

    public PmfCompartmentPlugin(PmfCompartmentPlugin plugin) {
        super(plugin);
        if (plugin.metaData != null)
            metaData = plugin.metaData.clone();
    }

    public PmfCompartmentPlugin(Compartment compartment) {
        super(compartment);
        setNamespace(PmfConstants.namespaceURI);
    }

    // --- Common plugin methods ---

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
    public boolean readAttribute(String attributeName, String prefix, String value) { return false; }

    // other

    @Override
    public PmfCompartmentPlugin clone() {
        return new PmfCompartmentPlugin(this);
    }

    @Override
    public boolean getAllowsChildren() {
        return true;
    }

    @Override
    public int getChildCount() {
        return metaData == null ? 0 : 1;
    }

    @Override
    public TreeNode getChildAt(int childIndex) {
        if (childIndex < 0) {
            throw new IndexOutOfBoundsException(MessageFormat.format(
                    resourceBundle.getString("IndexSurpassesBoundsException"),
                    Integer.valueOf(childIndex), Integer.valueOf(0)));
        }
        int pos = 0;
        if (metaData != null) {
            if (pos == childIndex)
                return metaData;
            pos++;
        }
        throw new IndexOutOfBoundsException(MessageFormat.format(
                resourceBundle.getString("IndexExceedsBoundsException"),
                Integer.valueOf(childIndex), Integer.valueOf(Math.min(pos, 0))));
    }

    // --- CompartmentMetaData ---

    public CompartmentMetaData getMetaData() {
        return metaData;
    }

    public boolean isSetMetaData() {
        return metaData != null;
    }

    public void setMetaData(CompartmentMetaData metaData) {
        unsetMetaData();
        this.metaData = metaData;
        if (extendedSBase != null) {
            this.metaData.setPackageVersion(-1);
            this.extendedSBase.registerChild(this.metaData);
        }
    }

    public boolean unsetMetaData() {
        if (isSetMetaData()) {
            CompartmentMetaData oldMetaData = metaData;
            metaData = null;
            firePropertyChange(PmfConstants.compartmentMetaData, oldMetaData, metaData);
            return true;
        }
        return false;
    }

    @Override
    public boolean equals(Object object) {
        if (object == null || getClass() != object.getClass())
            return false;

        PmfCompartmentPlugin other = (PmfCompartmentPlugin) object;
        return Objects.equals(metaData, other.metaData);
    }

    @Override
    public int hashCode() {
        return Objects.hash(metaData);
    }
}
