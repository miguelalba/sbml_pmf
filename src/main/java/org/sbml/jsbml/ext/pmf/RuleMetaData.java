package org.sbml.jsbml.ext.pmf;

import org.sbml.jsbml.AbstractSBase;
import org.sbml.jsbml.util.StringTools;

import java.util.Map;
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

    public String formulaName;
    public Integer pmmLabId;
    public ModelClass modelClass;

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
        boolean isAttributeRead;
        if (attributeName.equals("formulaName")) {
            formulaName = value;
            isAttributeRead = true;
        } else if (attributeName.equals("pmmLabID")) {
            pmmLabId = StringTools.parseSBMLInt(value);
            isAttributeRead = true;
        } else if (attributeName.equals("ruleClass")) {
            modelClass = ModelClass.fromName(value);
            isAttributeRead = true;
        } else {
            isAttributeRead = false;
        }

        return isAttributeRead;
    }

    @Override
    public Map<String, String> writeXMLAttributes() {
        Map<String, String> attributes = new TreeMap<>();
        if (formulaName != null && !formulaName.isEmpty())
            attributes.put("formulaName", formulaName);
        if (pmmLabId != null)
            attributes.put("pmmLabID", pmmLabId.toString());
        if (modelClass != null)
            attributes.put("ruleClass", modelClass.toString());

        return attributes;
    }

    @Override
    public String toString() {
        return PmfConstants.ruleMetaData +
                " [formulaName=\"" + (formulaName == null || formulaName.isEmpty() ? "" : formulaName) + "\"" +
                " ruleClass=\"" + (modelClass == null ? "" : modelClass) + "\"" +
                " pmmLabID=\"" + (pmmLabId == null ? "" : pmmLabId) + "\"]";
    }
}
