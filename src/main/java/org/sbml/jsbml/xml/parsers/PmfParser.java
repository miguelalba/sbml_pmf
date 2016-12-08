package org.sbml.jsbml.xml.parsers;

import org.apache.log4j.Logger;
import org.mangosdk.spi.ProviderFor;
import org.sbml.jsbml.*;
import org.sbml.jsbml.ext.SBasePlugin;
import org.sbml.jsbml.ext.pmf.*;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
@ProviderFor(ReadingParser.class)
public class PmfParser extends AbstractReaderWriter implements PackageParser {

    /**
     * A {@link Logger} for this class.
     */
    private static final transient Logger logger = Logger.getLogger(PmfParser.class);

    /*
     * (non-Javadoc)
     * @see org.sbml.jsbml.xml.WritingParser#getListOfSBMLElementsToWrite(Object sbase)
     */
    @Override
    public List<Object> getListOfSBMLElementsToWrite(Object sbase) {
        if (logger.isDebugEnabled()) {
            logger.debug(
                    "getListOfSBMLElementsToWrite: " + sbase.getClass().getCanonicalName()
            );
        }

        List<Object> listOfElementsToWrite = new ArrayList<Object>();

        if (sbase instanceof Compartment) {
            SBasePlugin plugin = ((Compartment) sbase).getExtension(getNamespaceURI());
            if (plugin != null) {
                listOfElementsToWrite = super.getListOfSBMLElementsToWrite(plugin);
            }
        } else if (sbase instanceof Model) {
            SBasePlugin plugin = ((Model) sbase).getExtension(getNamespaceURI());
            if (plugin != null) {
                listOfElementsToWrite = super.getListOfSBMLElementsToWrite(plugin);
            }
        } else if (sbase instanceof Parameter) {
            SBasePlugin plugin = ((Parameter) sbase).getExtension(getNamespaceURI());
            if (plugin != null) {
                listOfElementsToWrite = super.getListOfSBMLElementsToWrite(plugin);
            }
        } else if (sbase instanceof Rule) {
            SBasePlugin plugin = ((Rule) sbase).getExtension(getNamespaceURI());
            if (plugin != null) {
                listOfElementsToWrite = super.getListOfSBMLElementsToWrite(plugin);
            }
        } else if (sbase instanceof Species) {
            SBasePlugin plugin = ((Species) sbase).getExtension(getNamespaceURI());
            if (plugin != null) {
                listOfElementsToWrite = super.getListOfSBMLElementsToWrite(plugin);
            }
        } else {
            listOfElementsToWrite = super.getListOfSBMLElementsToWrite(sbase);
        }
        return listOfElementsToWrite;
    }

    /*
     * (non-Javadoc)
     * @see org.sbml.jsbml.xml.ReadingParser#processStartElement(String elementName, String prefix, boolean
     * hasAttributes, boolean hasNamespaces, Object contextObject)
     */

    @Override
    public Object processStartElement(String elementName, String uri, String prefix, boolean hasAttributes,
                                      boolean hasNamespaces, Object contextObject) {
        // contextObject ist the parent node of the found object
        if (logger.isDebugEnabled()) {
            logger.debug("processStartElement: " + elementName);
        }
        // Need to check for every class that may be a parent node of the classes in the extension

        // Parent=Compartment -> Child=CompartmentMetaData
        if (contextObject instanceof Compartment) {
            return processStartElement((Compartment) contextObject, elementName);
        }
        // Parent=Model -> Child=ListOf<ModelVariable>|ListOf<DataSource>|ListOf<PrimaryModel>
        else if (contextObject instanceof Model) {
            return processStartElement((Model) contextObject, elementName);
        }
        // Parent=Parameter -> Child=ParameterMetaData
        else if (contextObject instanceof Parameter) {
            return processStartElement((Parameter) contextObject, elementName);
        }
        // Parent=Rule -> Child=RuleMetaData
        else if (contextObject instanceof Rule) {
            return processStartElement((Rule) contextObject, elementName);
        }
        // Parent=Species -> Child=SpeciesMetaData
        else if (contextObject instanceof Species) {
            return processStartElement((Species) contextObject, elementName);
        } else if (contextObject instanceof ListOf<?>) {
            return processStartElement((ListOf<SBase>) contextObject, elementName);
        }

        return contextObject;
    }

    private static Object processStartElement(final Compartment compartment, final String
            elementName) {
        PmfCompartmentPlugin plugin = new PmfCompartmentPlugin(compartment);
        compartment.addExtension(PmfConstants.shortLabel, plugin);

        if (elementName.equals(PmfConstants.compartmentMetaData)) {
            CompartmentMetaData metaData = new CompartmentMetaData();
            plugin.setMetaData(metaData);
            return metaData;
        }

        return compartment;
    }

    private static Object processStartElement(final Model model, final String elementName) {

        // Gets or creates (if missing) plugin
        PmfModelPlugin plugin = (PmfModelPlugin) model.getPlugin(PmfConstants.shortLabel);
        if (plugin == null) {
            plugin = new PmfModelPlugin(model);
            model.addExtension(PmfConstants.shortLabel, plugin);
        }

        if (elementName.equals(PmfConstants.listOfModelVariables)) {
            return plugin.getListOfModelVariables();
        }
        if (elementName.equals(PmfConstants.listOfDataSources)) {
            return plugin.getListOfDataSources();
        }
        if (elementName.equals(PmfConstants.listOfPrimaryModels)) {
            return plugin.getListOfPrimaryModels();
        }
        return model;
    }

    private static Object processStartElement(final Parameter parameter, final String elementName) {

        // Gets or creates (if missing) plugin
        PmfParameterPlugin plugin = (PmfParameterPlugin) parameter.getPlugin(PmfConstants.shortLabel);
        if (plugin == null) {
            plugin = new PmfParameterPlugin(parameter);
            parameter.addExtension(PmfConstants.shortLabel, plugin);
        }

        if (elementName.equals(PmfConstants.parameterMetaData)) {
            ParameterMetaData metaData = new ParameterMetaData();
            plugin.setMetaData(metaData);
            return metaData;
        }
        if (elementName.equals(PmfConstants.listOfCorrelations)) {
            return plugin.getListOfCorrelations();
        }

        return parameter;
    }

    private static Object processStartElement(final Rule rule, final String elementName) {

        // Gets or creates (if missing) plugin
        PmfRulePlugin plugin = (PmfRulePlugin) rule.getPlugin(PmfConstants.shortLabel);
        if (plugin == null) {
            plugin = new PmfRulePlugin(rule);
            rule.addExtension(PmfConstants.shortLabel, plugin);
        }

        if (elementName.equals(PmfConstants.ruleMetaData)) {
            RuleMetaData metaData = new RuleMetaData();
            plugin.setMetaData(metaData);
            return metaData;
        }
        if (elementName.equals(PmfConstants.listOfReferences)) {
            return plugin.getListOfReferences();
        }
        return rule;
    }

    private static Object processStartElement(final Species species, final String elementName) {

        PmfSpeciesPlugin plugin = (PmfSpeciesPlugin) species.getPlugin(PmfConstants.shortLabel);

        if (elementName.equals(PmfConstants.speciesMetaData)) {
            SpeciesMetaData metaData = new SpeciesMetaData();
            plugin.setMetaData(metaData);
            return metaData;
        }
        return species;
    }

    @SuppressWarnings("unchecked")
    private static Object processStartElement(final ListOf<SBase> listOf, final String elementName) {


        if (elementName.equals(PmfConstants.correlation)) {
            Parameter parameter = (Parameter) listOf.getParentSBMLObject();
            PmfParameterPlugin plugin = (PmfParameterPlugin) parameter.getExtension(PmfConstants.shortLabel);
            Correlation correlation = new Correlation();
            plugin.addCorrelation(new Correlation());
            return correlation;
        }

        if (elementName.equals(PmfConstants.dataSource)) {
            Model model = (Model) listOf.getParentSBMLObject();
            PmfModelPlugin plugin = (PmfModelPlugin) model.getExtension(PmfConstants.shortLabel);
            DataSource dataSource = new DataSource();
            plugin.addDataSource(dataSource);
            return dataSource;
        }

        if (elementName.equals(PmfConstants.modelVariable)) {
            Model model = (Model) listOf.getParentSBMLObject();
            PmfModelPlugin plugin = (PmfModelPlugin) model.getExtension(PmfConstants.shortLabel);
            ModelVariable modelVariable = new ModelVariable();
            plugin.addModelVariable(modelVariable);
            return modelVariable;
        }

        if (elementName.equals(PmfConstants.primaryModel)) {
            Model model = (Model) listOf.getParentSBMLObject();
            PmfModelPlugin plugin = (PmfModelPlugin) model.getExtension(PmfConstants.shortLabel);
            PrimaryModel primaryModel = new PrimaryModel();
            plugin.addPrimaryModel(primaryModel);
            return primaryModel;
        }

        if (elementName.equals(PmfConstants.reference)) {
            Rule rule = (Rule) listOf.getParentSBMLObject();
            PmfRulePlugin plugin = (PmfRulePlugin) rule.getExtension(PmfConstants.shortLabel);
            Reference reference = new Reference();
            plugin.addReference(reference);
            return reference;
        }

        return listOf;
    }

    @Override
    public String getShortLabel() {
        return PmfConstants.shortLabel;
    }

    @Override
    public String getNamespaceURI() {
        return PmfConstants.namespaceURI;
    }

    @Override
    public String getNamespaceFor(int level, int version, int packageVersion) {
        if (level == 3 && version == 1 && packageVersion == 1) {
            return PmfConstants.namespaceURI_L3V1V1;
        }
        return null;
    }

    @Override
    public List<String> getNamespaces() {
        return PmfConstants.namespaces;
    }

    @Override
    public List<String> getPackageNamespaces() {
        return getNamespaces();
    }

    @Override
    public String getPackageName() {
        return PmfConstants.shortLabel;
    }

    @Override
    public boolean isRequired() {
        return false;
    }

    @Override
    public SBasePlugin createPluginFor(SBase sbase) {
        SBasePlugin plugin = null;

        if (sbase != null) {
            if (sbase instanceof Compartment) {
                plugin = new PmfCompartmentPlugin((Compartment) sbase);
            } else if (sbase instanceof Model) {
                plugin = new PmfModelPlugin((Model) sbase);
            } else if (sbase instanceof Parameter) {
                plugin = new PmfParameterPlugin((Parameter) sbase);
            } else if (sbase instanceof Rule) {
                plugin = new PmfRulePlugin((Rule) sbase);
            } else if (sbase instanceof Species) {
                plugin = new PmfSpeciesPlugin((Species) sbase);
            }
        }

        return plugin;
    }
}
