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
    public static final String correlation = "correlation";
    public static final String dataSource = "dataSource";
    public static final String modelVariable = "modelVariable";
    public static final String primaryModel = "primaryModel";
    public static final String reference = "reference";

    public static final String compartmentMetaData = "compartmentMetaData";
    public static final String modelMetaData = "modelMetaData";
    public static final String parameterMetaData = "parameterMetaData";
    public static final String ruleMetaData = "ruleMetaData";
    public static final String speciesMetaData = "speciesMetaData";

    public static final String listOfCorrelations = "listOfCorrelations";
    public static final String listOfDataSources = "listOfDataSources";
    public static final String listOfModelVariables = "listOfModelVariables";
    public static final String listOfPrimaryModels = "listOfPrimaryModels";
    public static final String listOfReferences = "listOfReferences";

    // Attributes
    public static final String name = "name";
    public static final String value = "value";

    // Uncertainties
    public static final String rSquared = "rSquared";
    public static final String rootMeanSquare = "rootMeanSquared";
    public static final String sumOfSquaredErrors = "sumOfSquaredErrors";
    public static final String akaikeInformationCriterion = "akaikeInformationCriterion";
    public static final String bayesianInformationCriterion = "bayesianInformationCriterion";
    public static final String degreesOfFreedom = "degreesOfFreedom";

    // Compartment
    public static final String compartment_source = "source";
    public static final String compartment_detail = "detail";

    // Correlation
    public static final String correlation_name = "name";
    public static final String correlation_value = "value";

    // DataSource
    public static final String dataSource_src = "src";

    // ModelVariable
    public static final String modelVariable_name = "name";
    public static final String modelVariable_value = "value";

    // ParameterMetaData
    public static final String parameter_p = "p";
    public static final String parameter_t = "t";
    public static final String parameter_error = "error";
    public static final String parameter_description = "description";
    public static final String parameter_min = "min";
    public static final String parameter_max = "max";

    // PrimaryModel
    public static final String primaryModel_src = "src";

    // References
    public static final String ref_author = "AU";
    public static final String ref_year = "PY";
    public static final String ref_title = "TI";
    public static final String ref_abstractText = "AB";
    public static final String ref_journal = "T2";
    public static final String ref_volume = "VL";
    public static final String ref_issue = "IS";
    public static final String ref_page = "SP";
    public static final String ref_approvalMode = "LB";
    public static final String ref_website = "UR";
    public static final String ref_type = "M3";
    public static final String ref_comment = "N1";

    // RuleMetaData
    public static final String rule_formulaName = "formulaName";
    public static final String rule_pmmLabID = "pmmLabID";
    public static final String rule_class = "ruleClass";

    // SpeciesMetaData
    public static final String species_source = "source";
    public static final String species_detail = "detail";
    public static final String species_description = "description";

    public static final String src = "src";
}
