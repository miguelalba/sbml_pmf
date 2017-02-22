package org.sbml.jsbml.ext.pmf;

import org.sbml.jsbml.AbstractSBase;
import org.sbml.jsbml.PropertyUndefinedError;
import org.sbml.jsbml.util.StringTools;

import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;

/**
 * Extended meta data for a Parameter.
 * <p>
 * Example:
 * <code>&lt;parameterMetaData p="2.220" t="34.394" error="9.922"
 * description="max conc" min="3.0" max="10.0" /&gt;</code>
 *
 * @author Miguel Alba
 */
public class ParameterMetaData extends AbstractSBase {

    /** Null if not set. */
    private Double p;

    /** Null if not set. */
    private Double t;

    /** Null if not set. */
    private Double error;

    private String description;

    /**
     * Minimum value. Null if not set.
     */
    private Double min;

    /**
     * Maximum value. Null if not set.
     */
    private Double max;

    /**
     * Creates a ParameterMetaData instance.
     */
    public ParameterMetaData() {
        packageName = PmfConstants.shortLabel;
    }

    public ParameterMetaData(int level, int version) {
        super(level, version);
        packageName = PmfConstants.shortLabel;
    }

    /**
     * Clone constructor.
     */
    public ParameterMetaData(ParameterMetaData obj) {
        super(obj);
        p = obj.p;
        t = obj.t;
        error = obj.error;
        description = obj.description;
        min = obj.min;
        max = obj.max;

        packageName = PmfConstants.shortLabel;
    }

    /**
     * Clones this class.
     */
    public ParameterMetaData clone() {
        return new ParameterMetaData(this);
    }


    @Override
    public Map<String, String> writeXMLAttributes() {
        Map<String, String> attributes = new TreeMap<>();
        if (isSetP()) {
            attributes.put(PmfConstants.parameter_p, StringTools.toString(Locale.ENGLISH, p));
        }
        if (isSetT()) {
            attributes.put(PmfConstants.parameter_t, StringTools.toString(Locale.ENGLISH, t));
        }
        if (isSetError()) {
            attributes.put(PmfConstants.parameter_error, StringTools.toString(Locale.ENGLISH, error));
        }
        if (isSetDescription()) {
            attributes.put(PmfConstants.parameter_description, description);
        }
        if (isSetMin()) {
            attributes.put(PmfConstants.parameter_min, StringTools.toString(Locale.ENGLISH, min));
        }
        if (isSetMax()) {
            attributes.put(PmfConstants.parameter_max, StringTools.toString(Locale.ENGLISH, max));
        }

        return attributes;
    }

    @Override
    public boolean readAttribute(String attributeName, String prefix, String value) {
        switch (attributeName) {
            case PmfConstants.parameter_p:
                p = parseDouble(value);
                return p != null;
            case PmfConstants.parameter_t:
                t = parseDouble(value);
                return t != null;
            case PmfConstants.parameter_error:
                error = parseDouble(value);
                return error != null;
            case PmfConstants.parameter_description:
                description = value;
                return true;
            case PmfConstants.parameter_min:
                min = parseDouble(value);
                return min != null;
            case PmfConstants.parameter_max:
                max = parseDouble(value);
                return max != null;
            default:
                return false;
        }
    }

    private Double parseDouble(String strValue) {
        double parsedValue = StringTools.parseSBMLDouble(strValue);
        return Double.isNaN(parsedValue) ? null : parsedValue;
    }

    @Override
    public String toString() {
        return PmfConstants.parameterMetaData +
                " [" + PmfConstants.parameter_p + "=\"" + (isSetP() ? p : "") +
                "\" " + PmfConstants.parameter_t + "=\"" + (isSetT() ? t : "") +
                "\" " + PmfConstants.parameter_error + "=\"" + (isSetError() ? error : "") +
                "\" " + PmfConstants.parameter_description + "=\"" + (isSetDescription() ? description :
                "") +
                "\" " + PmfConstants.parameter_min + "=\"" + (isSetMin() ? min : "") +
                "\" " + PmfConstants.parameter_max + "=\"" + (isSetMax() ? max : "") + "\"]";
    }

    @Override
    public boolean equals(Object object) {
        if (object == null || getClass() != object.getClass())
            return false;

        ParameterMetaData other = (ParameterMetaData) object;
        return Objects.equals(p, other.p) && Objects.equals(t, other.t) && Objects.equals(error, other.error) &&
                Objects.equals(description, other.description) && Objects.equals(min, other.min) &&
                Objects.equals(max, other.max);
    }

    @Override
    public int hashCode() {
        return Objects.hash(p, t, error, description, min, max);
    }

    // --- p attribute ---
    public double getP() {
        if (isSetP())
            return p;
        throw new PropertyUndefinedError(PmfConstants.parameter_p, this);
    }

    public boolean isSetP() {
        return p != null;
    }

    public void setP(double p) {
        Double oldP = this.p;
        this.p = p;
        firePropertyChange(PmfConstants.parameter_p, oldP, p);
    }

    public boolean unsetP() {
        if (isSetP()) {
            Double oldP = this.p;
            this.p = null;
            firePropertyChange(PmfConstants.parameter_p, oldP, p);
            return true;
        }
        return false;
    }

    // --- t attribute ---
    public double getT() {
        if (isSetT())
            return t;
        throw new PropertyUndefinedError(PmfConstants.parameter_t, this);
    }

    public boolean isSetT() {
        return t != null;
    }

    public void setT(double t) {
        Double oldT = this.t;
        this.t = t;
        firePropertyChange(PmfConstants.parameter_t, oldT, t);
    }

    public boolean unsetT() {
        if (isSetT()) {
            Double oldT = this.t;
            this.t = null;
            firePropertyChange(PmfConstants.parameter_t, oldT, t);
            return true;
        }
        return false;
    }

    // --- error attribute ---
    public double getError() {
        if (isSetError())
            return error;
        throw new PropertyUndefinedError(PmfConstants.parameter_error, this);
    }

    public boolean isSetError() {
        return error != null;
    }

    public void setError(double error) {
        Double oldError = this.error;
        this.error = error;
        firePropertyChange(PmfConstants.parameter_error, oldError, error);
    }

    public boolean unsetError() {
        if (isSetError()) {
            Double oldError = this.error;
            this.error = null;
            firePropertyChange(PmfConstants.parameter_error, oldError, error);
            return true;
        }
        return false;
    }

    // --- description attribute ---
    public String getDescription() {
        return isSetDescription() ? description : null;
    }

    public boolean isSetDescription() {
        return description != null;
    }

    public void setDescription(String description) {
        String oldDescription = this.description;
        this.description = description;
        firePropertyChange(PmfConstants.parameter_description, oldDescription, description);
    }

    public boolean unsetDescription() {
        if (isSetDescription()) {
            String oldDescription = this.description;
            this.description = null;
            firePropertyChange(PmfConstants.parameter_description, oldDescription, description);
            return true;
        }
        return false;
    }

    // --- min attribute ---
    public double getMin() {
        if (isSetMin())
            return min;
        throw new PropertyUndefinedError(PmfConstants.parameter_min, this);
    }

    public boolean isSetMin() {
        return min != null;
    }

    public void setMin(double min) {
        Double oldMin = this.min;
        this.min = min;
        firePropertyChange(PmfConstants.parameter_min, oldMin, min);
    }

    public boolean unsetMin() {
        if (isSetMin()) {
            Double oldMin = min;
            min = null;
            firePropertyChange(PmfConstants.parameter_min, oldMin, min);
            return true;
        }
        return false;
    }


    // --- max attribute ---
    public double getMax() {
        if (isSetMax())
            return max;
        throw new PropertyUndefinedError(PmfConstants.parameter_max, this);
    }

    public boolean isSetMax() {
        return max != null;
    }

    public void setMax(double max) {
        Double oldMax = this.max;
        this.max = max;
        firePropertyChange(PmfConstants.parameter_max, oldMax, max);
    }

    public boolean unsetMax() {
        if (isSetMax()) {
            Double oldMax = max;
            max = null;
            firePropertyChange(PmfConstants.parameter_max, oldMax, max);
            return true;
        }
        return false;
    }
}
