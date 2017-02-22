package org.sbml.jsbml.ext.pmf;

import org.sbml.jsbml.AbstractSBase;
import org.sbml.jsbml.PropertyUndefinedError;
import org.sbml.jsbml.util.StringTools;

import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;

/**
 * Extended meta data for a {@link org.sbml.jsbml.Model}
 * <p>
 *
 * @author Miguel Alba
 */
public class ModelMetaData extends AbstractSBase {

    private Double rSquared;
    private Double rootMeanSquare;
    private Double sumOfSquaredErrors;
    private Double akaikeInformationCriterion;
    private Double bayesianInformationCriterion;
    private Integer degreesOfFreedom;

    public ModelMetaData() {
        packageName = PmfConstants.shortLabel;
    }

    public ModelMetaData(int level, int version) {
        super(level, version);
        packageName = PmfConstants.shortLabel;
    }

    /**
     * Clone constructor.
     */
    public ModelMetaData(ModelMetaData obj) {
        super(obj);
        if (obj.isSetRSquared()) {
            setRSquared(obj.getRSquared());
        }
        if (obj.isSetRootMeanSquare()) {
            setRootMeanSquare(obj.getRootMeanSquare());
        }
        if (obj.isSetSumOfSquaredErrors()) {
            setSumOfSquaredErrors(obj.getSumOfSquaredErrors());
        }
        if (obj.isSetAkaikeInformationCriterion()) {
            setAkaikeInformationCriterion(obj.getAkaikeInformationCriterion());
        }
        if (obj.isSetBayesianInformationCriterion()) {
            setBayesianInformationCriterion(obj.getBayesianInformationCriterion());
        }
        if (obj.isSetDegreesOfFreedom()) {
            setDegreesOfFreedom(obj.getDegreesOfFreedom());
        }
    }

    /**
     * Clones this class.
     */
    @Override
    public ModelMetaData clone() {
        return new ModelMetaData(this);
    }

    @Override
    public Map<String, String> writeXMLAttributes() {
        Map<String, String> attributes = new TreeMap<>();
        if (isSetRSquared()) {
            attributes.put(PmfConstants.rSquared, StringTools.toString(Locale.ENGLISH, rSquared));
        }
        if (isSetRootMeanSquare()) {
            attributes.put(PmfConstants.rootMeanSquare, StringTools.toString(Locale.ENGLISH, rootMeanSquare));
        }
        if (isSetSumOfSquaredErrors()) {
            attributes.put(PmfConstants.sumOfSquaredErrors, StringTools.toString(Locale.ENGLISH, sumOfSquaredErrors));
        }
        if (isSetAkaikeInformationCriterion()) {
            attributes.put(PmfConstants.akaikeInformationCriterion, StringTools.toString
                    (Locale.ENGLISH, akaikeInformationCriterion));
        }
        if (isSetBayesianInformationCriterion()) {
            attributes.put(PmfConstants.bayesianInformationCriterion, StringTools.toString
                    (Locale.ENGLISH, bayesianInformationCriterion));
        }
        if (isSetDegreesOfFreedom()) {
            attributes.put(PmfConstants.degreesOfFreedom, degreesOfFreedom.toString());
        }
        return attributes;
    }

    @Override
    public boolean readAttribute(String attributeName, String prefix, String value) {

        switch (attributeName) {
            case PmfConstants.rSquared:
                rSquared = StringTools.parseSBMLDouble(value);
                return true;
            case PmfConstants.rootMeanSquare:
                rootMeanSquare = StringTools.parseSBMLDouble(value);
                return true;
            case PmfConstants.sumOfSquaredErrors:
                sumOfSquaredErrors = StringTools.parseSBMLDouble(value);
                return true;
            case PmfConstants.akaikeInformationCriterion:
                akaikeInformationCriterion = StringTools.parseSBMLDouble(value);
                return true;
            case PmfConstants.bayesianInformationCriterion:
                bayesianInformationCriterion = StringTools.parseSBMLDouble(value);
                return true;
            case PmfConstants.degreesOfFreedom:
                degreesOfFreedom = StringTools.parseSBMLInt(value);
                return true;
            default:
                return false;
        }
    }

    @Override
    public String toString() {
        String sb = PmfConstants.modelMetaData + " [";
        sb += PmfConstants.rSquared + "=\"" + (isSetRSquared() ? rSquared : "") + "\"";
        sb += PmfConstants.rootMeanSquare + "=\"" + (isSetRootMeanSquare() ? rootMeanSquare : "") + "\"";
        sb += PmfConstants.sumOfSquaredErrors + "=\"" + (isSetSumOfSquaredErrors() ? sumOfSquaredErrors : "") + "\"";
        sb += PmfConstants.akaikeInformationCriterion + "=\"" + (isSetAkaikeInformationCriterion() ?
                akaikeInformationCriterion : "") + "\"";
        sb += PmfConstants.bayesianInformationCriterion + "=\"" + (isSetBayesianInformationCriterion() ?
                bayesianInformationCriterion : "") + "\"";
        sb += PmfConstants.degreesOfFreedom + "=\"" + (isSetDegreesOfFreedom() ? degreesOfFreedom : "") + "\"";

        return sb;
    }

    // --- rSquared attribute ---
    public double getRSquared() {
        if (isSetRSquared()) {
            return rSquared;
        }
        throw new PropertyUndefinedError(PmfConstants.rSquared, this);
    }

    public boolean isSetRSquared() {
        return rSquared != null;
    }

    public void setRSquared(double r2) {
        Double oldR2 = this.rSquared;
        this.rSquared = r2;
        firePropertyChange(PmfConstants.rSquared, oldR2, this.rSquared);
    }

    public boolean unsetRSquared() {
        if (isSetRSquared()) {
            Double oldR2 = this.rSquared;
            this.rSquared = null;
            firePropertyChange(PmfConstants.rSquared, oldR2, this.rSquared);
            return true;
        }
        return false;
    }

    // --- rootMeanSquare attribute ---
    public double getRootMeanSquare() {
        if (isSetRootMeanSquare()) {
            return rootMeanSquare;
        }
        throw new PropertyUndefinedError(PmfConstants.rootMeanSquare, this);
    }

    public boolean isSetRootMeanSquare() {
        return rootMeanSquare != null;
    }

    public void setRootMeanSquare(double rootMeanSquare) {
        Double oldRootMeanSquare = this.rootMeanSquare;
        this.rootMeanSquare = rootMeanSquare;
        firePropertyChange(PmfConstants.rootMeanSquare, oldRootMeanSquare, this.rootMeanSquare);
    }

    public boolean unsetRMS() {
        if (isSetRootMeanSquare()) {
            Double oldRootMeanSquare = this.rootMeanSquare;
            this.rootMeanSquare = null;
            firePropertyChange(PmfConstants.rootMeanSquare, oldRootMeanSquare, this.rootMeanSquare);
            return true;
        }
        return false;
    }

    // --- sumOfSquaredErrors attribute ---
    public double getSumOfSquaredErrors() {
        if (isSetSumOfSquaredErrors()) {
            return sumOfSquaredErrors;
        }
        throw new PropertyUndefinedError(PmfConstants.sumOfSquaredErrors, this);
    }

    public boolean isSetSumOfSquaredErrors() {
        return sumOfSquaredErrors != null;
    }

    public void setSumOfSquaredErrors(double sumOfSquaredErrors) {
        Double oldSumOfSquaredErrors = this.sumOfSquaredErrors;
        this.sumOfSquaredErrors = sumOfSquaredErrors;
        firePropertyChange(PmfConstants.sumOfSquaredErrors, oldSumOfSquaredErrors, this.sumOfSquaredErrors);
    }

    public boolean unsetSumOfSquaredErrors() {
        if (isSetSumOfSquaredErrors()) {
            Double oldSumOfSquaredErrors = this.sumOfSquaredErrors;
            this.sumOfSquaredErrors = null;
            firePropertyChange(PmfConstants.sumOfSquaredErrors, oldSumOfSquaredErrors, this.sumOfSquaredErrors);
            return true;
        }
        return false;
    }

    // --- akaikeInformationCriterion attribute ---
    public double getAkaikeInformationCriterion() {
        if (isSetAkaikeInformationCriterion()) {
            return akaikeInformationCriterion;
        }
        throw new PropertyUndefinedError(PmfConstants.akaikeInformationCriterion, this);
    }

    public boolean isSetAkaikeInformationCriterion() {
        return akaikeInformationCriterion != null;
    }

    public void setAkaikeInformationCriterion(double akaikeInformationCriterion) {
        Double oldAkaikeInformationCriterior = this.akaikeInformationCriterion;
        this.akaikeInformationCriterion = akaikeInformationCriterion;
        firePropertyChange(PmfConstants.akaikeInformationCriterion, oldAkaikeInformationCriterior, this.akaikeInformationCriterion);
    }

    public boolean unsetAkaikeInformationCriterion() {
        if (isSetAkaikeInformationCriterion()) {
            Double oldAkaikeInformationCriterion = this.akaikeInformationCriterion;
            this.akaikeInformationCriterion = null;
            firePropertyChange(PmfConstants.akaikeInformationCriterion, oldAkaikeInformationCriterion, this.akaikeInformationCriterion);
            return true;
        }
        return false;
    }

    // --- bayesianInformationCriterion attribute ---
    public double getBayesianInformationCriterion() {
        if (isSetBayesianInformationCriterion()) {
            return bayesianInformationCriterion;
        }
        throw new PropertyUndefinedError(PmfConstants.bayesianInformationCriterion, this);
    }

    public boolean isSetBayesianInformationCriterion() {
        return bayesianInformationCriterion != null;
    }

    public void setBayesianInformationCriterion(double bayesianInformationCriterion) {
        Double oldBayesianInformationCriterion = this.bayesianInformationCriterion;
        this.bayesianInformationCriterion = bayesianInformationCriterion;
        firePropertyChange(PmfConstants.bayesianInformationCriterion, oldBayesianInformationCriterion, this.bayesianInformationCriterion);
    }

    public boolean unsetBayesianInformationCriterion() {
        if (isSetBayesianInformationCriterion()) {
            Double oldBayesianInformationCriterion = this.bayesianInformationCriterion;
            this.bayesianInformationCriterion = null;
            firePropertyChange(PmfConstants.bayesianInformationCriterion, oldBayesianInformationCriterion, this.bayesianInformationCriterion);
            return true;
        }
        return false;
    }

    // --- degreesOfFreedom attribute ---
    public int getDegreesOfFreedom() {
        if (isSetDegreesOfFreedom()) {
            return degreesOfFreedom;
        }
        throw new PropertyUndefinedError(PmfConstants.degreesOfFreedom, this);
    }

    public boolean isSetDegreesOfFreedom() {
        return degreesOfFreedom != null;
    }

    public void setDegreesOfFreedom(int degreesOfFreedom) {
        Integer oldDegreesOfFreedom = this.degreesOfFreedom;
        this.degreesOfFreedom = degreesOfFreedom;
        firePropertyChange(PmfConstants.degreesOfFreedom, oldDegreesOfFreedom, this.degreesOfFreedom);
    }

    public boolean unsetDegreesOfFreedom() {
        if (isSetDegreesOfFreedom()) {
            Integer oldDegreesOfFreedom = this.degreesOfFreedom;
            this.degreesOfFreedom = null;
            firePropertyChange(PmfConstants.degreesOfFreedom, oldDegreesOfFreedom, this.degreesOfFreedom);
            return true;
        }
        return false;
    }

    @Override
    public boolean equals(Object object) {
        if (object == null || getClass() != object.getClass())
            return false;

        ModelMetaData other = (ModelMetaData) object;
        return Objects.equals(rSquared, other.rSquared) &&
                Objects.equals(rootMeanSquare, other.rootMeanSquare) &&
                Objects.equals(sumOfSquaredErrors, other.sumOfSquaredErrors) &&
                Objects.equals(akaikeInformationCriterion, other.akaikeInformationCriterion) &&
                Objects.equals(bayesianInformationCriterion, other.bayesianInformationCriterion) &&
                Objects.equals(degreesOfFreedom, other.degreesOfFreedom);
    }
}
