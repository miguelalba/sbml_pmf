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
    static final String compartmentMetaData = "compartmentMetaData";
    static final String parameterMetaData = "parameterMetaData";
    static final String reference = "reference";
    static final String ruleMetaData = "ruleMetaData";
    static final String speciesMetaData = "speciesMetaData";
}
