import org.sbml.jsbml.*;
import org.sbml.jsbml.ext.pmf.*;
import org.sbml.jsbml.text.parser.ParseException;

import javax.xml.stream.XMLStreamException;
import java.io.IOException;
import java.nio.file.Files;

/**
 * Created by de on 08.12.2016.
 */
public class Test {

    public static void main(String[] args) throws XMLStreamException, ParseException, IOException {

        SBMLDocument doc = new SBMLDocument(3, 1);
        doc.enablePackage(PmfConstants.shortLabel);
        Model model = doc.createModel("amodel");

        // Test PmfModelPlugin: model variables, data sources and primary models
        PmfModelPlugin plugin = (PmfModelPlugin) model.createPlugin(PmfConstants.shortLabel);
        plugin.createModelVariable("pH", 5.0);
        plugin.createDataSource("someData.numl");
        plugin.createPrimaryModel("someModel.sbml");

        // Test PmfCompartmentPlugin: source and detail
        Compartment compartment = model.createCompartment("Culture_medium");
        compartment.setName("culture medium");
        compartment.setConstant(true);
        PmfCompartmentPlugin compartmentPlugin = (PmfCompartmentPlugin) compartment.createPlugin(PmfConstants
                .shortLabel);
        CompartmentMetaData compartmentMetaData = new CompartmentMetaData();
        compartmentMetaData.source = 36;
        compartmentMetaData.detail = "broth";
        compartmentPlugin.setMetaData(compartmentMetaData);

        // Test PmfSpeciesPlugin: source, detail and description
        Species species = model.createSpecies("some_species");
        species.setCompartment(compartment);
        species.setBoundaryCondition(false);
        species.setHasOnlySubstanceUnits(true);
        species.setConstant(false);
        PmfSpeciesPlugin speciesPlugin = (PmfSpeciesPlugin) species.createPlugin(PmfConstants.shortLabel);
        SpeciesMetaData speciesMetaData = new SpeciesMetaData();
        speciesMetaData.source = "http://identifiers.org/ncim/C0036111";
        speciesMetaData.detail = "Salmonella spec";
        speciesMetaData.description = "bacterial population at time t - ln()";
        speciesPlugin.setMetaData(speciesMetaData);

        // Test PmfParameterPLugin
        Parameter p = model.createParameter("p");
        p.setConstant(false);
        p.setValue(0);
        PmfParameterPlugin parameterPlugin = (PmfParameterPlugin) p.createPlugin(PmfConstants.shortLabel);

        ParameterMetaData paramMetaData = new ParameterMetaData();
        paramMetaData.p = 0.1;
        paramMetaData.t = 0.5;
        paramMetaData.error = 0.5;
        paramMetaData.description = "some description";
        paramMetaData.min = 0.1;
        paramMetaData.max = 0.9;
        parameterPlugin.setMetaData(paramMetaData);
        parameterPlugin.createCorrelation("a", 1.0);
        parameterPlugin.createCorrelation("b", 2.0);

        // Test PmfRulePlugin
        AssignmentRule rule = model.createAssignmentRule();
        rule.setVariable(species.getId());
        rule.setMath(ASTNode.parseFormula("2+2"));
        PmfRulePlugin rulePlugin = (PmfRulePlugin) rule.createPlugin(PmfConstants.shortLabel);

        RuleMetaData ruleMetaData = new RuleMetaData();
        ruleMetaData.formulaName = "2 plus 2";
        ruleMetaData.pmmLabId = 1;
        ruleMetaData.modelClass = RuleMetaData.ModelClass.GROWTH;
        rulePlugin.setMetaData(ruleMetaData);

        Reference ref = new Reference();
        ref.title = "Baranyi latest model";
        rulePlugin.addReference(ref);

        // Create string with document and print it
        System.out.println(JSBML.writeSBMLToString(doc));

        // Write and read document
        String filename = Files.createTempFile("temp", "sbml").toString();
        JSBML.writeSBML(doc, filename);
        doc = JSBML.readSBML(filename);
        System.out.println(JSBML.writeSBMLToString(doc));
    }
}
