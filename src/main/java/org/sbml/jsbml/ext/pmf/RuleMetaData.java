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
            switch (this) {
                case UNKNOWN:
                    return "unknown";
                case GROWTH:
                    return "growth";
                case INACTIVATION:
                    return "inactivation";
                case SURVIVAL:
                    return "survival";
                case GROWTH_INACTIVATION:
                    return "growth/inactivation";
                case INACTIVATION_SURVIVAL:
                    return "inactivation/survival";
                case GROWTH_SURVIVAL:
                    return "growth/survival";
                case GROWTH_INACTIVATION_SURVIVAL:
                    return "growth/inactivation/survival";
                case T:
                    return "T";
                case PH:
                    return "pH";
                case AW:
                    return "aw";
                case T_PH:
                    return "T/pH";
                case T_AW:
                    return "T/aw";
                case PH_AW:
                    return "pH/aw";
                case T_PH_AW:
                    return "T/pH/aw";
                default:
                    return "unknown";
            }
        }

        public static ModelClass fromName(String name) {
            switch (name.toLowerCase()) {
                case "unknown":
                    return UNKNOWN;
                case "growth":
                    return GROWTH;
                case "inactivation":
                    return INACTIVATION;
                case "survival":
                    return SURVIVAL;
                case "growth/inactivation":
                    return GROWTH_INACTIVATION;
                case "inactivation/survival":
                    return INACTIVATION_SURVIVAL;
                case "growth/survival":
                    return GROWTH_SURVIVAL;
                case "growth/inactivation/survival":
                    return GROWTH_INACTIVATION_SURVIVAL;
                case "T":
                    return T;
                case "PH":
                    return PH;
                case "aw":
                    return AW;
                case "T/pH":
                    return T_PH;
                case "T/aw":
                    return T_AW;
                case "pH/aw":
                    return PH_AW;
                case "T/pH/aw":
                    return T_PH_AW;
                default:
                    return UNKNOWN;
            }
        }
    }

    public String formulaName;
    public Integer pmmLabId;
    public ModelClass modelClass;

    /**
     * Creates a RuleMetaData instance.
     */
    public RuleMetaData() {
        initDefaults();
    }

    /**
     * Clone constructor.
     */
    public RuleMetaData(RuleMetaData obj) {
        super(obj);
        this.formulaName = obj.formulaName;
        this.pmmLabId = obj.pmmLabId;
        this.modelClass = obj.modelClass;
    }

    /**
     * Clones this class.
     */
    @Override
    public RuleMetaData clone() {
        return new RuleMetaData(this);
    }

    /**
     * Initializes the default values using the namespace.
     */
    public void initDefaults() {
        setPackageVersion(-1);
        packageName = PmfConstants.shortLabel;
    }

    @Override
    public boolean readAttribute(String attributeName, String prefix, String value) {
        if (attributeName.equals("formulaName")) {
            formulaName = value;
            return true;
        }
        if (attributeName.equals("pmmLabID")) {
            pmmLabId = StringTools.parseSBMLInt(value);
            return true;
        }
        if (attributeName.equals("ruleClass")) {
            modelClass = ModelClass.fromName(value);
            return true;
        }
        return false;
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
        StringBuilder sb = new StringBuilder();
        sb.append(PmfConstants.ruleMetaData + " [");
        if (formulaName != null && !formulaName.isEmpty())
            sb.append(" formulaName=\"" + formulaName + "\"");
        if (pmmLabId != null)
            sb.append(" pmmLabID=\"" + pmmLabId + "\"");
        if (modelClass != null)
            sb.append(" ruleClass=\"" + modelClass + "\"");
        return sb.toString();
    }
}
