package org.sbml.jsbml.ext.pmf;

import java.util.ArrayList;
import java.util.List;

/**
 * Constants in the PMF package.
 *
 * @author Miguel Alba
 */
public class PmfConstants {

    /**
     * The name space URI of this parser for SBML level 3, version 1 and package version 1.
     */
    public static final String namespaceURI_L3V1V1 = "http://www.sbml.org/sbml/level3/version1/pmf/version1";

    /**
     * The name space URI of this parser, this value can change between releases.
     */
    public static final String namespaceURI = namespaceURI_L3V1V1;

    public static final String shortLabel = "pmf";

    public static final int MIN_SBML_LEVEL = 3;

    public static final int MIN_SBML_VERSION = 1;

    public static final List<String> namespaces;

    static {
        namespaces = new ArrayList<>(1);
        namespaces.add(namespaceURI_L3V1V1);
    }

    // Objects defined in this plugin
    public static final String compartmentMetaData = "compartmentMetaData";
    public static final String parameterMetaData = "parameterMetaData";
    public static final String reference = "reference";
    public static final String ruleMetaData = "ruleMetaData";
    public static final String speciesMetaData = "speciesMetaData";

    // ComparmentMetaData attributes
    public static final String compartmentMetaDataSource = "source";
    public static final String compartmentMetaDataDetail = "detail";

    // Correlation attributes
    public static final String correlationName = "name";
    public static final String correlationValue = "value";

    // DataSource attributes
    public static final String dataSourceSrc = "src";

    // ModelVariable attributes
    public static final String modelVariableName = "name";
    public static final String modelVariableValue = "value";

    // ParameterMetaData attributes
    public static final String parameterMetaDataP = "p";
    public static final String parameterMetaDataT = "t";
    public static final String parameterMetaDataError = "error";
    public static final String parameterMetaDataDescription = "description";
    public static final String parameterMetaDataMin = "min";
    public static final String parameterMetaDataMax = "max";

    // Reference attributes (RIS subset)
    public static final String referenceAuthor = "AU";
    public static final String referenceYear = "PY";
    public static final String referenceTitle = "TI";
    public static final String referenceAbstract = "AB";
    public static final String referenceJournal = "T2";
    public static final String referenceVolume = "VL";
    public static final String referenceIssue = "IS";
    public static final String referencePage = "SP";
    public static final String referenceApproval = "LB";
    public static final String referenceWebsite = "UR";
    public static final String referenceType = "M3";
    public static final String referenceComment = "N1";

    // RuleMetaData attributes
    public static final String ruleMetaDataFormula = "formulaName";
    public static final String ruleMetaDataId = "pmmLabID";
    public static final String ruleMetaDataClass = "ruleClass";

    // SpeciesMetaData attributes
    public static final String speciesMetaDataSource = "source";
    public static final String speciesMetaDataDetail = "detail";
    public static final String speciesMetaDataDescription = "description";
}
