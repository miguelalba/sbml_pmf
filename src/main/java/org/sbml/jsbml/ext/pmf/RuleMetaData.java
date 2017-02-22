package org.sbml.jsbml.ext.pmf;

import org.sbml.jsbml.AbstractSBase;
import org.sbml.jsbml.PropertyUndefinedError;
import org.sbml.jsbml.util.StringTools;

import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;

/**
 * Example:
 * <code>&lt;ruleMetaData formulaName="2 plus 2" pmmLabID="1" ruleClass="growth" /&gt;</code>
 *
 * @author Miguel Alba
 */
public class RuleMetaData extends AbstractSBase {

    public static enum ModelClass {
        UNKNOWN, GROWTH, INACTIVATION, SURVIVAL, GROWTH_INACTIVATION,
        INACTIVATION_SURVIVAL, GROWTH_SURVIVAL, GROWTH_INACTIVATION_SURVIVAL,
        T, PH, AW, T_PH, T_AW, PH_AW, T_PH_AW;

        @Override
        public String toString() {
            String classStr = "";

            if (this.equals(UNKNOWN)) {
                classStr = "unknown";
            } else if (this.equals(GROWTH)) {
                classStr = "growth";
            } else if (this.equals(INACTIVATION)) {
                classStr = "inactivation";
            } else if (this.equals(SURVIVAL)) {
                classStr = "survival";
            } else if (this.equals(GROWTH_INACTIVATION)) {
                classStr = "growth/inactivation";
            } else if (this.equals(INACTIVATION_SURVIVAL)) {
                classStr = "inactivation/survival";
            } else if (this.equals(GROWTH_SURVIVAL)) {
                classStr = "growth/survival";
            } else if (this.equals(GROWTH_INACTIVATION_SURVIVAL)) {
                classStr = "growth/inactivation/survival";
            } else if (this.equals(T)) {
                classStr = "T";
            } else if (this.equals(PH)) {
                classStr = "pH";
            } else if (this.equals(AW)) {
                classStr = "aw";
            } else if (this.equals(T_PH)) {
                classStr = "T/pH";
            } else if (this.equals(T_AW)) {
                classStr = "T/aw";
            } else if (this.equals(PH_AW)) {
                classStr = "pH/aw";
            } else if (this.equals(T_PH_AW)) {
                classStr = "T/pH/aw";
            }

            return classStr;
        }

        public static ModelClass fromName(String name) {
            ModelClass modelClass;
            if (name.toLowerCase().equals("unknown")) {
                modelClass = ModelClass.UNKNOWN;
            } else if (name.toLowerCase().equals("growth")) {
                modelClass = ModelClass.GROWTH;
            } else if (name.toLowerCase().equals("inactivation")) {
                modelClass = ModelClass.INACTIVATION;
            } else if (name.toLowerCase().equals("survival")) {
                modelClass = ModelClass.SURVIVAL;
            } else if (name.toLowerCase().equals("growth/inactivation")) {
                modelClass = ModelClass.GROWTH_INACTIVATION;
            } else if (name.toLowerCase().equals("inactivation/survival")) {
                modelClass = ModelClass.INACTIVATION_SURVIVAL;
            } else if (name.toLowerCase().equals("growth/survival")) {
                modelClass = ModelClass.GROWTH_SURVIVAL;
            } else if (name.toLowerCase().equals("growth/inactivation/survival")) {
                modelClass = ModelClass.GROWTH_INACTIVATION_SURVIVAL;
            } else if (name.toLowerCase().equals("t")) {
                modelClass = ModelClass.T;
            } else if (name.toLowerCase().equals("ph")) {
                modelClass = ModelClass.PH;
            } else if (name.toLowerCase().equals("aw")) {
                modelClass = ModelClass.AW;
            } else if (name.toLowerCase().equals("t/ph")) {
                modelClass = ModelClass.T_PH;
            } else if (name.toLowerCase().equals("t/aw")) {
                modelClass = ModelClass.T_AW;
            } else if (name.toLowerCase().equals("ph/aw")) {
                modelClass = ModelClass.PH_AW;
            } else if (name.toLowerCase().equals("t/ph/aw")) {
                modelClass = ModelClass.T_PH_AW;
            } else {
                modelClass = ModelClass.UNKNOWN;
            }

            return modelClass;
        }
    }

    private String formulaName;
    private Integer pmmLabId;
    private ModelClass modelClass;

    /**
     * Creates a RuleMetaData instance.
     */
    public RuleMetaData() {
        modelClass = ModelClass.UNKNOWN;
        packageName = PmfConstants.shortLabel;
    }

    public RuleMetaData(int level, int version) {
        super(level, version);
        modelClass = ModelClass.UNKNOWN;
        packageName = PmfConstants.shortLabel;
    }

    /**
     * Clone constructor.
     */
    public RuleMetaData(RuleMetaData obj) {
        super(obj);
        this.formulaName = obj.formulaName;
        this.pmmLabId = obj.pmmLabId;
        this.modelClass = obj.modelClass;

        packageName = PmfConstants.shortLabel;
    }

    /**
     * Clones this class.
     */
    @Override
    public RuleMetaData clone() {
        return new RuleMetaData(this);
    }

    @Override
    public boolean readAttribute(String attributeName, String prefix, String value) {
        switch (attributeName) {
            case PmfConstants.rule_formulaName:
                formulaName = value;
                return true;
            case PmfConstants.rule_pmmLabID:
                pmmLabId = StringTools.parseSBMLInt(value);
                return true;
            case PmfConstants.rule_class:
                modelClass = ModelClass.fromName(value);
                return true;
            default:
                return false;
        }
    }

    @Override
    public Map<String, String> writeXMLAttributes() {
        Map<String, String> attributes = new TreeMap<>();
        if (formulaName != null && !formulaName.isEmpty())
            attributes.put(PmfConstants.rule_formulaName, formulaName);
        if (pmmLabId != null)
            attributes.put(PmfConstants.rule_pmmLabID, pmmLabId.toString());
        if (modelClass != null)
            attributes.put(PmfConstants.rule_class, modelClass.toString());

        return attributes;
    }

    @Override
    public String toString() {
        return PmfConstants.ruleMetaData +
                " [" + PmfConstants.rule_formulaName + "=\"" + (isSetFormulaName() ? formulaName : "") + "\"" +
                " " + PmfConstants.rule_class + "=\"" + (isSetModelClass() ? modelClass : "") + "\"" +
                " " + PmfConstants.rule_pmmLabID + "=\"" + (isSetPmmLabId() ? pmmLabId : "") + "\"]";
    }

    @Override
    public boolean equals(Object object) {
        if (object == null || getClass() != object.getClass())
            return false;

        RuleMetaData other = (RuleMetaData) object;
        return Objects.equals(formulaName, other.formulaName) && Objects.equals(pmmLabId, other.pmmLabId) &&
                Objects.equals(modelClass, other.modelClass);
    }

    // --- formulaName attribute ---
    public String getFormulaName() {
        return isSetFormulaName() ? formulaName : null;
    }

    public boolean isSetFormulaName() {
        return formulaName != null;
    }

    public void setFormulaName(String formulaName) {
        String oldFormulaName = this.formulaName;
        this.formulaName = formulaName;
        firePropertyChange(PmfConstants.rule_formulaName, oldFormulaName, formulaName);
    }

    public boolean unsetFormulaName() {
        if (isSetFormulaName()) {
            String oldFormulaName = formulaName;
            formulaName = null;
            firePropertyChange(PmfConstants.rule_formulaName, oldFormulaName, formulaName);
            return true;
        }
        return false;
    }

    // --- pmmLabId attribute ---
    public int getPmmLabId() {
        if (isSetPmmLabId())
            return pmmLabId;
        throw new PropertyUndefinedError(PmfConstants.rule_pmmLabID, this);
    }

    public boolean isSetPmmLabId() {
        return pmmLabId != null;
    }

    public void setPmmLabId(int pmmLabId) {
        Integer oldPmmLabId = this.pmmLabId;
        this.pmmLabId = pmmLabId;
        firePropertyChange(PmfConstants.rule_pmmLabID, oldPmmLabId, pmmLabId);
    }

    public boolean unsetPmmLabId() {
        if (isSetPmmLabId()) {
            Integer oldPmmLabId = this.pmmLabId;
            this.pmmLabId = pmmLabId;
            firePropertyChange(PmfConstants.rule_pmmLabID, oldPmmLabId, pmmLabId);
            return true;
        }
        return false;
    }

    // --- modelClass attribute ---
    public ModelClass getModelClass() {
        return isSetModelClass() ? modelClass : null;
    }

    public boolean isSetModelClass() { return modelClass != null; }

    public void setModelClass(ModelClass modelClass) {
        ModelClass oldModelClass = this.modelClass;
        this.modelClass = modelClass;
        firePropertyChange(PmfConstants.rule_class, oldModelClass, modelClass);
    }

    public boolean unsetModelClass() {
        if (isSetModelClass()) {
            ModelClass oldModelClass = modelClass;
            modelClass = null;
            firePropertyChange(PmfConstants.rule_class, oldModelClass, modelClass);
            return true;
        }
        return false;
    }
}
