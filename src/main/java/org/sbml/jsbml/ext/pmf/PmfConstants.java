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
}
