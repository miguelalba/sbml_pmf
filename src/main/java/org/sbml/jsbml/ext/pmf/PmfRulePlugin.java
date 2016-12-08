package org.sbml.jsbml.ext.pmf;

import org.sbml.jsbml.ListOf;
import org.sbml.jsbml.Rule;
import org.sbml.jsbml.ext.AbstractSBasePlugin;

import javax.swing.tree.TreeNode;
import java.text.MessageFormat;

/**
 * Created by de on 12.09.2016.
 */
public class PmfRulePlugin extends AbstractSBasePlugin {

    private RuleMetaData metaData;
    private ListOf<Reference> listOfReferences;

    public PmfRulePlugin(PmfRulePlugin plugin) {
        super(plugin);
        if (plugin.isSetMetaData())
            setMetaData(plugin.metaData.clone());
        if (plugin.isSetListOfReferences())
            setListOfReferences(plugin.listOfReferences.clone());
    }

    public PmfRulePlugin(Rule rule) {
        super(rule);
    }

    @Override
    public PmfRulePlugin clone() {
        return new PmfRulePlugin(this);
    }

    @Override
    public boolean getAllowsChildren() {
        return true;
    }

    @Override
    public int getChildCount() {
        int childCount = 0;
        if (isSetMetaData())
            childCount++;
        if (isSetListOfReferences())
            childCount++;
        return childCount;
    }

    @Override
    public TreeNode getChildAt(int childIndex) {
        if (childIndex < 0) {
            throw new IndexOutOfBoundsException(MessageFormat.format(
                    resourceBundle.getString("IndexSurpassesBoundsException"),
                    childIndex, 0));
        }
        int pos = 0;
        if (isSetMetaData()) {
            if (pos == childIndex)
                return getMetaData();
            pos++;
        }
        if (isSetListOfReferences()) {
            if (pos == childIndex)
                return getListOfReferences();
            pos++;
        }
        throw new IndexOutOfBoundsException(MessageFormat.format(
                resourceBundle.getString("IndexExceedsBoundsException"),
                childIndex, Math.min(pos, 0)));
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

    // --- meta data ---
    public RuleMetaData getMetaData() {
        return metaData;
    }

    public boolean isSetMetaData() {
        return metaData != null;
    }

    public void setMetaData(RuleMetaData metaData) {
        unsetMetaData();
        this.metaData = metaData;
        if (extendedSBase != null) {
            metaData.setPackageVersion(-1);
            extendedSBase.registerChild(this.metaData);
        }
    }

    public boolean unsetMetaData() {
        if (isSetMetaData()) {
            RuleMetaData oldMetaData = metaData;
            metaData = null;
            firePropertyChange(PmfConstants.ruleMetaData, oldMetaData, null);
            return true;
        }
        return false;
    }

    // --- Reference ---
    // addReference(Reference) : boolean
    public boolean addReference(Reference reference) {
        return getListOfReferences().add(reference);
    }

    public boolean removeReference(Reference reference) {
        return isSetListOfReferences() && getListOfReferences().remove(reference);
    }

    public int getReferenceCount() {
        return isSetListOfReferences() ? this.listOfReferences.size() : 0;
    }

    // --- listOfReferences ---

    public ListOf<Reference> getListOfReferences() {
        if (!isSetListOfReferences()) {
            listOfReferences = new ListOf<>();
            listOfReferences.setPackageVersion(-1);
            // changing the listOf package name from 'core' to 'pmf'
            listOfReferences.setPackageName(null);
            listOfReferences.setPackageName(PmfConstants.shortLabel);
            listOfReferences.setSBaseListType(ListOf.Type.other);
            if (extendedSBase != null) {
                extendedSBase.registerChild(listOfReferences);
            }
        }
        return listOfReferences;
    }

    public boolean isSetListOfReferences() {
        return listOfReferences != null && !listOfReferences.isEmpty();
    }

    public void setListOfReferences(ListOf<Reference> listOfReferences) {
        unsetListOfReferences();
        this.listOfReferences = listOfReferences;
        if (listOfReferences != null) {
            listOfReferences.setPackageVersion(-1);
            // changing the listOf packaga name from 'core' to 'pmf'
            listOfReferences.setPackageName(null);
            listOfReferences.setPackageName(PmfConstants.shortLabel);
            listOfReferences.setSBaseListType(ListOf.Type.other);
            if (extendedSBase != null) {
                extendedSBase.registerChild(listOfReferences);
            }
        }
    }

    public boolean unsetListOfReferences() {
        if (listOfReferences != null) {
            ListOf<Reference> oldListOfReferences = listOfReferences;
            listOfReferences = null;
            oldListOfReferences.fireNodeRemovedEvent();
            return true;
        }
        return false;
    }
}
