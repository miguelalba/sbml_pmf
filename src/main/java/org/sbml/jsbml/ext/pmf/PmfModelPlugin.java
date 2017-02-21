package org.sbml.jsbml.ext.pmf;

import org.sbml.jsbml.ListOf;
import org.sbml.jsbml.Model;
import org.sbml.jsbml.PropertyUndefinedError;
import org.sbml.jsbml.SBase;
import org.sbml.jsbml.ext.AbstractSBasePlugin;
import org.sbml.jsbml.util.StringTools;

import java.text.MessageFormat;
import java.util.Objects;

/**
 * @author Miguel Alba
 */
public class PmfModelPlugin extends AbstractSBasePlugin {

    private ListOf<ModelVariable> listOfModelVariables;
    private ListOf<DataSource> listOfDataSources;
    private ListOf<PrimaryModel> listOfPrimaryModels;
    private Double r2;
    private Double rms;
    private Double sse;
    private Double aic;
    private Double bic;
    private Integer dof;

    public PmfModelPlugin(PmfModelPlugin plugin) {
        super(plugin);
        // we do not clone the pointer to the containing model
        if (plugin.isSetListOfModelVariables())
            setListOfModelVariables(plugin.listOfModelVariables.clone());
        if (plugin.isSetListOfDataSources())
            setListOfDataSources(plugin.listOfDataSources.clone());
        if (plugin.isSetListOfPrimaryModels())
            setListOfPrimaryModels(plugin.listOfPrimaryModels.clone());
        if (plugin.isSetR2())
            setR2(plugin.getR2());
        if (plugin.isSetRMS())
            setRMS(plugin.getRMS());
        if (plugin.isSetSSE())
            setSSE(plugin.getSSE());
        if (plugin.isSetAIC())
            setAIC(plugin.getAIC());
        if (plugin.isSetBIC())
            setBIC(plugin.getBIC());
        if (plugin.isSetDOF())
            setDOF(plugin.getDOF());
    }

    public PmfModelPlugin(Model model) {
        super(model);
        setPackageVersion(-1);
        setNamespace(PmfConstants.namespaceURI);
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
        if (attributeName.equals("r2")) {
            setR2(StringTools.parseSBMLDouble(value));
            return true;
        }
        if (attributeName.equals("rms")) {
            setRMS(StringTools.parseSBMLDouble(value));
            return true;
        }
        if (attributeName.equals("sse")) {
            setSSE(StringTools.parseSBMLDouble(value));
            return true;
        }
        if (attributeName.equals("aic")) {
            setAIC(StringTools.parseSBMLDouble(value));
            return true;
        }
        if (attributeName.equals("bic")) {
            setBIC(StringTools.parseSBMLDouble(value));
            return true;
        }
        if (attributeName.equals("dof")) {
            setDOF(StringTools.parseSBMLInt(value));
            return true;
        }

        return false;
    }

    // --- other ---
    @Override
    public PmfModelPlugin clone() {
        return new PmfModelPlugin(this);
    }

    public boolean getAllowsChildren() {
        return true;
    }

    @Override
    public int getChildCount() {
        int childCount = 0;
        if (isSetListOfModelVariables())
            childCount++;
        if (isSetListOfDataSources())
            childCount++;
        if (isSetListOfPrimaryModels())
            childCount++;
        return childCount;
    }

    /* (non-Javadoc)
     * @see org.sbml.jsbml.ext.SBasePlugin#getChildAt(int)
     */
    @Override
    public SBase getChildAt(int childIndex) {
        if (childIndex < 0) {
            throw new IndexOutOfBoundsException(MessageFormat.format(
                    resourceBundle.getString("IndexSurpassesBoundsException"),
                    childIndex, 0));
        }
        int pos = 0;
        if (isSetListOfModelVariables()) {
            if (pos == childIndex)
                return getListOfModelVariables();
            pos++;
        }
        if (isSetListOfDataSources()) {
            if (pos == childIndex)
                return getListOfDataSources();
            pos++;
        }
        if (isSetListOfPrimaryModels()) {
            if (pos == childIndex)
                return getListOfPrimaryModels();
            pos++;
        }
        throw new IndexOutOfBoundsException(
                MessageFormat.format("Index {0, number, integer} >= {1, number, integer}",
                        childIndex, Math.min(pos, 0)));
    }

    // --- ModelVariable ---

    /**
     * Adds a new {@link ModelVariable} to the {@link #listOfModelVariables}.
     * <p>
     * The listOfModelVariables is initialized if necessary.
     *
     * @param modelVariable the element to add to the list
     * @return {code true} (as specified by {@link java.util.Collection#add})
     * @see java.util.Collection#add(Object)
     */
    public boolean addModelVariable(ModelVariable modelVariable) {
        return getListOfModelVariables().add(modelVariable);
    }

    /**
     * Removes an element from the {@link #listOfModelVariables}.
     *
     * @param modelVariable the element to be removed from the list.
     * @return {@code true} if the list contained the specified element and it was
     * removed.
     * @see java.util.List#remove(Object)
     */
    public boolean removeModelVariable(ModelVariable modelVariable) {
        return isSetListOfModelVariables() && getListOfModelVariables().remove(modelVariable);
    }

    /**
     * Removes an element from the {@link #listOfModelVariables} at the given
     * index.
     *
     * @param i the index where to remove the {@link ModelVariable}.
     * @return the specified element if it was successfully found and removed.
     * @throws IndexOutOfBoundsException if the listOf is not set or if the index is out of bound (
     *                                   {@code (i < 0) || (i > listOfModelVariables)}).
     */
    public ModelVariable removeModelVariable(int i) {
        if (!isSetListOfModelVariables())
            throw new IndexOutOfBoundsException(Integer.toString(i));
        return getListOfModelVariables().remove(i);
    }

    /**
     * Creates a new {@link ModelVariable} element and adds it to this
     * {@link #listOfModelVariables} list.
     *
     * @param name
     * @return the newly created {@link ModelVariable} element, which is the last
     * element in the {@link #listOfModelVariables}.
     */
    public ModelVariable createModelVariable(String name) {
        ModelVariable mv = new ModelVariable(getLevel(), getVersion());
        mv.setName(name);
        addModelVariable(mv);
        return mv;
    }

    /**
     * Creates a new instance of {@link ModelVariable} and add it to this
     * {@link PmfModelPlugin}.
     *
     * @param name  the name to be set to the new {@link ModelVariable}.
     * @param value the value to be set to the new {@link ModelVariable}.
     * @return the new {@link ModelVariable} instance.
     */
    public ModelVariable createModelVariable(String name, double value) {
        ModelVariable mv = new ModelVariable(getLevel(), getVersion());
        mv.setName(name);
        mv.setValue(value);
        addModelVariable(mv);
        return mv;
    }

    /**
     * Returns the number of {@link ModelVariable} of this {@link PmfModelPlugin}.
     *
     * @return the number of {@link ModelVariable} of this {@link PmfModelPlugin}.
     */
    public int getModelVariableCount() {
        return isSetListOfModelVariables() ? listOfModelVariables.size() : 0;
    }

    // *** listOfModelVariables methods ***

    /**
     * Returns the listOfModelVariables. If the {@link ListOf} is not defined,
     * creates an empty one.
     *
     * @return the listOfModelVariables
     */
    public ListOf<ModelVariable> getListOfModelVariables() {
        if (!isSetListOfModelVariables()) {
            listOfModelVariables = new ListOf<>();
            listOfModelVariables.setPackageVersion(-1);
            // changing the listOf package name from 'core' to 'pmf'
            listOfModelVariables.setPackageName(null);
            listOfModelVariables.setPackageName(PmfConstants.shortLabel);
            listOfModelVariables.setSBaseListType(ListOf.Type.other);
            if (extendedSBase != null) {
                extendedSBase.registerChild(listOfModelVariables);
            }
        }
        return listOfModelVariables;
    }

    /**
     * Returns {@code true}, if listOfModelVariables contains at least one
     * element.
     *
     * @return {@code true}, if listOfModelVariables contains at least one
     * element.
     */
    public boolean isSetListOfModelVariables() {
        return listOfModelVariables != null && !listOfModelVariables.isEmpty();
    }


    /**
     * Sets the given {@code ListOf<ModelVariable>}. If listOfModelVariables was
     * defined before and contains some elements, they are all unset.
     *
     * @param listOfModelVariables
     */
    public void setListOfModelVariables(
            final ListOf<ModelVariable> listOfModelVariables) {
        unsetListOfModelVariables();
        this.listOfModelVariables = listOfModelVariables;
        if (listOfModelVariables != null) {
            listOfModelVariables.setPackageVersion(-1);
            // changing the ListOf package name from 'core' to 'pmf'
            listOfModelVariables.setPackageName(null);
            listOfModelVariables.setPackageName(PmfConstants.shortLabel);
            listOfModelVariables.setSBaseListType(ListOf.Type.other);
        }
        if (isSetExtendedSBase())
            extendedSBase.registerChild(listOfModelVariables);
    }


    /**
     * Removes the {@link #listOfModelVariables} from this {@link Model} and
     * notifies
     * all registered instances of
     * {@link org.sbml.jsbml.util.TreeNodeChangeListener}.
     *
     * @return {code true} if calling this method lead to a change in this data
     * structure.
     */
    public boolean unsetListOfModelVariables() {
        if (isSetListOfModelVariables()) {
            ListOf<ModelVariable> oldListOfModelVariables = listOfModelVariables;
            listOfModelVariables = null;
            oldListOfModelVariables.fireNodeRemovedEvent();
            return true;
        }
        return false;
    }

    // --- DataSources ---

    /**
     * Adds a new {@link DataSource} to the {@link #listOfDataSources}.
     * <p>
     * The listOfDataSources is initialized if necessary.
     *
     * @param dataSource the element to add to the list
     * @return {@code true} (as specified by {@link java.util.Collection#add})
     * @see java.util.Collection#add(Object)
     */
    public boolean addDataSource(DataSource dataSource) {
        return getListOfDataSources().add(dataSource);
    }

    /**
     * Removes an element from the {@link #listOfDataSources}.
     *
     * @param dataSource the element to be removed from the list.
     * @return {@code true} if the list contained the specified element and it was
     * removed.
     * @see java.util.List#remove(Object)
     */
    public boolean removeDataSource(DataSource dataSource) {
        return isSetListOfDataSources() && getListOfDataSources().remove(dataSource);
    }

    /**
     * Removes an element from the {@link #listOfDataSources} at the given index.
     *
     * @param i the index where to remove the {@link DataSource}.
     * @return the specified element if it was successfully found and removed.
     * @throws IndexOutOfBoundsException if the listOf is not set or if the index is
     *                                   out of bound ({@code (i < 0) || (i > listOfDataSources)}).
     */
    public DataSource removeDataSource(int i) {
        if (!isSetListOfDataSources())
            throw new IndexOutOfBoundsException(Integer.toString(i));
        return getListOfDataSources().remove(i);
    }

    /**
     * Creates a new {@link DataSource} element and adds it to the
     * {@link #listOfDataSources} list.
     *
     * @param src
     * @return the newly created {@link DataSource} element, which is the
     * last element in the {@link #listOfDataSources}.
     */
    public DataSource createDataSource(String src) {
        DataSource dataSource = new DataSource();
        dataSource.setSrc(src);
        addDataSource(dataSource);
        return dataSource;
    }

    /**
     * Gets an element from the {@link #listOfDataSources} at the given index.
     *
     * @param i the index of the {@link DataSource} element to get.
     * @return an element from the listOfDataSources at the given index.
     * @throws IndexOutOfBoundsException if the listOf is not set or
     *                                   if the index is out of bound (index < 0 || index > list.size).
     */
    public DataSource getDataSource(int i) {
        if (!isSetListOfDataSources())
            throw new IndexOutOfBoundsException(Integer.toString(i));
        return getListOfDataSources().get(i);
    }

    /**
     * Returns the number of {@link DataSource}s in this
     * {@link PmfModelPlugin}.
     *
     * @return the number of {@link DataSource}s in this
     * {@link PmfModelPlugin}.
     */
    public int getDataSourceCount() {
        return isSetListOfDataSources() ? getListOfDataSources().size() : 0;
    }

    // *** listOfDataSources ***

    /**
     * Returns the {@link #listOfDataSources}.
     * Creates it if it does not already exist.
     *
     * @return the {@link #listOfDataSources}.
     */
    public ListOf<DataSource> getListOfDataSources() {
        if (listOfDataSources == null) {
            listOfDataSources = new ListOf<>();
            listOfDataSources.setPackageVersion(-1);
            // changing the listOf package name from 'core' to 'pmf'
            listOfDataSources.setPackageName(null);
            listOfDataSources.setPackageName(PmfConstants.shortLabel);
            listOfDataSources.setSBaseListType(ListOf.Type.other);
            if (extendedSBase != null)
                extendedSBase.registerChild(listOfDataSources);
        }
        return listOfDataSources;
    }

    /**
     * Returns {@code true} if {@link #listOfDataSources} contains at least
     * one element.
     *
     * @return {@code true} if {@link #listOfDataSources} contains at least
     * one element, otherwise {@code false}.
     */
    public boolean isSetListOfDataSources() {
        return (listOfDataSources != null) && !listOfDataSources.isEmpty();
    }

    /**
     * Sets the given {@code ListOf<DataSource>}.
     * If {@link #listOfDataSources} was defined before and contains some
     * elements, they are all unset.
     *
     * @param listOfDataSources
     */
    public void setListOfDataSources(ListOf<DataSource> listOfDataSources) {
        unsetListOfDataSources();
        this.listOfDataSources = listOfDataSources;
        if (listOfDataSources != null) {
            listOfDataSources.setPackageVersion(-1);
            // changing the ListOf package name from 'core' to 'pmf'
            listOfDataSources.setPackageName(null);
            listOfDataSources.setPackageName(PmfConstants.shortLabel);
            listOfDataSources.setSBaseListType(ListOf.Type.other);
        }
        if (extendedSBase != null)
            extendedSBase.registerChild(listOfDataSources);
    }

    /**
     * Returns {@code true} if {@link #listOfDataSources} contains at least
     * one element, otherwise {@code false}.
     *
     * @return {@code true} if {@link #listOfDataSources} contains at least
     * one element, otherwise {@code false}.
     */
    public boolean unsetListOfDataSources() {
        if (isSetListOfDataSources()) {
            ListOf<DataSource> oldDataSources = listOfDataSources;
            listOfDataSources = null;
            oldDataSources.fireNodeRemovedEvent();
            return true;
        }
        return false;
    }

    // *** listOfPrimaryModels ***

    /**
     * Adds a new {@link PrimaryModel} to the {@link #listOfPrimaryModels}.
     * <p>
     * The listOfPrimaryModels is initialized if necessary.
     *
     * @param PrimaryModel the element to add to the list
     * @return {@code true} (as specified by {@link java.util.Collection#add})
     * @see java.util.Collection#add(Object)
     */
    public boolean addPrimaryModel(PrimaryModel PrimaryModel) {
        return getListOfPrimaryModels().add(PrimaryModel);
    }

    /**
     * Removes an element from the {@link #listOfPrimaryModels}.
     *
     * @param PrimaryModel the element to be removed from the list.
     * @return {@code true} if the list contained the specified element and it was
     * removed.
     * @see java.util.List#remove(Object)
     */
    public boolean removePrimaryModel(PrimaryModel PrimaryModel) {
        return isSetListOfPrimaryModels() && getListOfPrimaryModels().remove(PrimaryModel);
    }

    /**
     * Removes an element from the {@link #listOfPrimaryModels} at the given
     * index.
     *
     * @param i the index where to remove the {@link PrimaryModel}.
     * @return the specified element if it was successfully found and removed.
     * @throws IndexOutOfBoundsException if the listOf is not set or if the index is
     *                                   out of bound ({@code (i < 0) || (i > listOfPrimaryModels)}).
     */
    public PrimaryModel removePrimaryModel(int i) {
        if (!isSetListOfPrimaryModels()) {
            throw new IndexOutOfBoundsException(Integer.toString(i));
        }
        return getListOfPrimaryModels().remove(i);
    }

    /**
     * Creates a new {@link PrimaryModel} element and adds it to the
     * {@link #listOfPrimaryModels} list.
     *
     * @param src
     * @return the newly created {@link PrimaryModel} element, which is the last
     * element in the {@link #listOfPrimaryModels}.
     */
    public PrimaryModel createPrimaryModel(String src) {
        PrimaryModel primaryModel = new PrimaryModel(getLevel(), getVersion());
        primaryModel.setSrc(src);
        addPrimaryModel(primaryModel);
        return primaryModel;
    }

    /**
     * Gets an element from the {@link #listOfPrimaryModels} at the given index.
     *
     * @param i the index of the {@link PrimaryModel} element to get.
     * @return an element from the listOfPrimaryModels at the given index.
     * @throws IndexOutOfBoundsException if the listOf is not set or
     *                                   if the index is out of bound (index < 0 || index > list.size).
     */
    public PrimaryModel getPrimaryModel(int i) {
        if (!isSetListOfPrimaryModels()) {
            throw new IndexOutOfBoundsException(Integer.toString(i));
        }
        return getListOfPrimaryModels().get(i);
    }

    /**
     * Returns the number of {@link PrimaryModel}s in this
     * {@link PmfModelPlugin}.
     *
     * @return the number of {@link PrimaryModel}s in this
     * {@link PmfModelPlugin}.
     */
    public int getPrimaryModelCount() {
        return isSetListOfPrimaryModels() ? getListOfPrimaryModels().size() : 0;
    }

    /**
     * Returns the {@link #listOfPrimaryModels}.
     * Creates it if it does not already exist.
     *
     * @return the {@link #listOfPrimaryModels}.
     */
    public ListOf<PrimaryModel> getListOfPrimaryModels() {
        if (listOfPrimaryModels == null) {
            listOfPrimaryModels = new ListOf<>();
            listOfPrimaryModels.setPackageVersion(-1);
            // changing the listOf package name from 'core' to 'pmf'
            listOfPrimaryModels.setPackageName(null);
            listOfPrimaryModels.setPackageName(PmfConstants.shortLabel);
            listOfPrimaryModels.setSBaseListType(ListOf.Type.other);
            if (isSetExtendedSBase())
                extendedSBase.registerChild(listOfPrimaryModels);
        }
        return listOfPrimaryModels;
    }

    /**
     * Returns {@code true} if {@link #listOfPrimaryModels} contains at least
     * one element.
     *
     * @return {@code true} if {@link #listOfPrimaryModels} contains at least
     * one element, otherwise {@code false}.
     */
    public boolean isSetListOfPrimaryModels() {
        return listOfPrimaryModels != null && !listOfPrimaryModels.isEmpty();
    }

    /**
     * Sets the given {@code ListOf<PrimaryModel>}.
     * If {@link #listOfPrimaryModels} was defined before and contains some
     * elements, they are all unset.
     *
     * @param listOfPrimaryModels
     */
    public void setListOfPrimaryModels(ListOf<PrimaryModel> listOfPrimaryModels) {
        unsetListOfPrimaryModels();
        this.listOfPrimaryModels = listOfPrimaryModels;
        if (listOfPrimaryModels != null) {
            listOfPrimaryModels.setPackageVersion(-1);
            // changing the ListOf package name from 'core' to 'pmf'
            listOfPrimaryModels.setPackageName(null);
            listOfPrimaryModels.setPackageName(PmfConstants.shortLabel);
            listOfPrimaryModels.setSBaseListType(ListOf.Type.other);
        }
        if (isSetExtendedSBase())
            extendedSBase.registerChild(listOfPrimaryModels);
    }

    /**
     * Returns {@code true} if {@link #listOfPrimaryModels} contains at least
     * one element, otherwise {@code false}.
     *
     * @return {@code true} if {@link #listOfPrimaryModels} contains at least
     * one element, otherwise {@code false}.
     */
    public boolean unsetListOfPrimaryModels() {
        if (isSetListOfPrimaryModels()) {
            ListOf<PrimaryModel> oldPrimaryModels = listOfPrimaryModels;
            listOfPrimaryModels = null;
            oldPrimaryModels.fireNodeRemovedEvent();
            return true;
        }
        return false;
    }

    // --- r2 attribute ---
    public double getR2() {
        if (isSetR2()) {
            return r2;
        }
        throw new PropertyUndefinedError(PmfConstants.r2, this);
    }

    public boolean isSetR2() {
        return r2 != null;
    }

    public void setR2(double r2) {
        Double oldR2 = this.r2;
        this.r2 = r2;
        firePropertyChange(PmfConstants.r2, oldR2, this.r2);
    }

    public boolean unsetR2() {
        if (isSetR2()) {
            Double oldR2 = r2;
            r2 = null;
            firePropertyChange(PmfConstants.r2, oldR2, r2);
            return true;
        }
        return false;
    }

    // --- rms attribute ---
    public double getRMS() {
        if (isSetRMS()) {
            return rms;
        }
        throw new PropertyUndefinedError(PmfConstants.rms, this);
    }

    public boolean isSetRMS() { return rms != null; }

    public void setRMS(double rms) {
        Double oldRMS = this.rms;
        this.rms = rms;
        firePropertyChange(PmfConstants.rms, oldRMS, this.rms);
    }

    public boolean unsetRMS() {
        if (isSetRMS()) {
            Double oldRMS = rms;
            rms = null;
            firePropertyChange(PmfConstants.rms, oldRMS, rms);
            return true;
        }
        return false;
    }

    // --- sse attribute ---
    public double getSSE() {
        if (isSetSSE()) {
            return sse;
        }
        throw new PropertyUndefinedError(PmfConstants.sse, this);
    }

    public boolean isSetSSE() { return sse != null; }

    public void setSSE(double sse) {
        Double oldSSE = this.sse;
        this.sse = sse;
        firePropertyChange(PmfConstants.sse, oldSSE, sse);
    }

    public boolean unsetSSE() {
        if (isSetSSE()) {
            Double oldSSE = sse;
            sse = null;
            firePropertyChange(PmfConstants.sse, oldSSE, sse);
            return true;
        }
        return false;
    }

    // --- aic attribute ---
    public double getAIC() {
        if (isSetAIC()) {
            return aic;
        }
        throw new PropertyUndefinedError(PmfConstants.aic, this);
    }

    public boolean isSetAIC() { return aic != null; }

    public void setAIC(double aic) {
        Double oldAIC = this.aic;
        this.aic = aic;
        firePropertyChange(PmfConstants.aic, oldAIC, aic);
    }

    public boolean unsetAIC() {
        if (isSetAIC()) {
            Double oldAIC = aic;
            aic = null;
            firePropertyChange(PmfConstants.aic, oldAIC, aic);
            return true;
        }
        return false;
    }

    // --- bic attribute ---
    public double getBIC() {
        if (isSetBIC()) {
            return bic;
        }
        throw new PropertyUndefinedError(PmfConstants.bic, this);
    }

    public boolean isSetBIC() { return bic != null; }

    public void setBIC(double bic) {
        Double oldBIC = this.bic;
        this.bic = bic;
        firePropertyChange(PmfConstants.bic, oldBIC, bic);
    }

    public boolean unsetBIC() {
        if (isSetBIC()) {
            Double oldBIC = bic;
            bic = null;
            firePropertyChange(PmfConstants.bic, oldBIC, bic);
            return true;
        }
        return false;
    }

    // --- dof attribute ---
    public int getDOF() {
        if (isSetDOF()) {
            return dof;
        }
        throw new PropertyUndefinedError(PmfConstants.dof, this);
    }

    public boolean isSetDOF() { return dof != null; }

    public void setDOF(int dof) {
        Integer oldDOF = this.dof;
        this.dof = dof;
        firePropertyChange(PmfConstants.dof, oldDOF, dof);
    }

    public boolean unsetDOF() {
        if (isSetDOF()) {
            Integer oldDOF = dof;
            dof = null;
            firePropertyChange(PmfConstants.dof, oldDOF, dof);
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

        final PmfModelPlugin other = (PmfModelPlugin) object;
        return Objects.equals(listOfModelVariables, other.listOfModelVariables) &&
                Objects.equals(listOfDataSources, other.listOfDataSources) &&
                Objects.equals(listOfPrimaryModels, other.listOfPrimaryModels) &&
                Objects.equals(r2, other.r2) &&
                Objects.equals(rms, other.rms) &&
                Objects.equals(sse, other.sse) &&
                Objects.equals(aic, other.aic) &&
                Objects.equals(bic, other.bic) &&
                Objects.equals(dof, other.dof);
    }

    @Override
    public int hashCode() {
        return Objects.hash(listOfModelVariables, listOfDataSources, listOfPrimaryModels, r2, rms, sse, aic, bic, dof);
    }
}
