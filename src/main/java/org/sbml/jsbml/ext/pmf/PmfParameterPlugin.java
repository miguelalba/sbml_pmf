package org.sbml.jsbml.ext.pmf;

import org.sbml.jsbml.ListOf;
import org.sbml.jsbml.Parameter;
import org.sbml.jsbml.SBase;
import org.sbml.jsbml.ext.AbstractSBasePlugin;

import java.text.MessageFormat;
import java.util.Map;

/**
 * Created by de on 12.09.2016.
 */
public class PmfParameterPlugin extends AbstractSBasePlugin {

    private ParameterMetaData metaData;
    private ListOf<Correlation> listOfCorrelations;

    public PmfParameterPlugin(PmfParameterPlugin plugin) {
        super(plugin);
        if (plugin.isSetMetaData())
            setMetaData(plugin.metaData.clone());
        if (plugin.isSetListOfCorrelations())
            setListOfCorrelations(plugin.listOfCorrelations.clone());
    }

    public PmfParameterPlugin(Parameter parameter) {
        super(parameter);
    }

    public PmfParameterPlugin clone() {
        return new PmfParameterPlugin(this);
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

    @Override
    public Map<String, String> writeXMLAttributes() {
        return null;
    }

    // --- plugin methods ---

    @Override
    public Parameter getParent() {
        return isSetExtendedSBase() ? (Parameter) getExtendedSBase() : null;
    }

    @Override
    public SBase getParentSBMLObject() {
        return getParent();
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
        if (isSetListOfCorrelations())
            childCount++;
        return childCount;
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
                return getMetaData();
            pos++;
        }
        if (isSetListOfCorrelations()) {
            if (pos == childIndex)
                return getListOfCorrelations();
            pos++;
        }
        throw new IndexOutOfBoundsException(MessageFormat.format(
                resourceBundle.getString("IndexExceedsBoundsException"),
                childIndex, Math.min(pos, 0)));
    }

    // --- ParameterMetaData ---
    public ParameterMetaData getMetaData() {
        return metaData;
    }

    public boolean isSetMetaData() {
        return metaData != null;
    }

    public void setMetaData(ParameterMetaData metaData) {
        unsetMetaData();
        this.metaData = metaData;
        if (extendedSBase != null) {
            this.metaData.setPackageVersion(-1);
            extendedSBase.registerChild(this.metaData);
        }
    }

    public boolean unsetMetaData() {
        if (isSetMetaData()) {
            ParameterMetaData oldMetadata = metaData;
            metaData = null;
            firePropertyChange(PmfConstants.parameterMetaData, oldMetadata,
                    metaData);
            return true;
        }
        return false;
    }

    // --- correlations ---
    public boolean addCorrelation(Correlation correlation) {
        return getListOfCorrelations().add(correlation);
    }

    public boolean removeCorrelation(Correlation correlation) {
        return isSetListOfCorrelations() && getListOfCorrelations().remove(correlation);
    }

    public Correlation removeCorrelation(int i) {
        if (!isSetListOfCorrelations()) {
            throw new IndexOutOfBoundsException(Integer.toString(i));
        }
        return getListOfCorrelations().remove(i);
    }

    public Correlation createCorrelation(String name, double value) {
        Correlation correlation =
                new Correlation(name, value, getLevel(), getVersion());
        addCorrelation(correlation);
        return correlation;
    }

    public int getCorrelationCount() {
        return isSetListOfCorrelations() ? listOfCorrelations.size() : 0;
    }

    // --- listOfCorrelations ---

    public ListOf<Correlation> getListOfCorrelations() {
        if (!isSetListOfCorrelations()) {
            listOfCorrelations = new ListOf<>();
            listOfCorrelations.setPackageVersion(-1);
            // changing the listOf package name from 'core' to 'pmf'
            listOfCorrelations.setPackageName(null);
            listOfCorrelations.setPackageName(PmfConstants.shortLabel);
            listOfCorrelations.setSBaseListType(ListOf.Type.other);
            if (extendedSBase != null) {
                extendedSBase.registerChild(listOfCorrelations);
            }
        }
        return listOfCorrelations;
    }

    public boolean isSetListOfCorrelations() {
        return listOfCorrelations != null && !listOfCorrelations.isEmpty();
    }

    public void setListOfCorrelations(ListOf<Correlation> listOfCorrelations) {
        unsetListOfCorrelations();
        this.listOfCorrelations = listOfCorrelations;
        if (listOfCorrelations != null) {
            listOfCorrelations.setPackageVersion(-1);
            // changing the listOf package name from 'core' to 'pmf'
            listOfCorrelations.setPackageName(null);
            listOfCorrelations.setPackageName(PmfConstants.shortLabel);
            listOfCorrelations.setSBaseListType(ListOf.Type.other);
        }
        if (isSetExtendedSBase()) {
            extendedSBase.registerChild(listOfCorrelations);
        }
    }

    public boolean unsetListOfCorrelations() {
        if (listOfCorrelations != null) {
            ListOf<Correlation> oldListOfCorrelations = this.listOfCorrelations;
            listOfCorrelations = null;
            oldListOfCorrelations.fireNodeRemovedEvent();
            return true;
        }
        return false;
    }
}
