package main.uct.impl.nguni.zu;

import main.uct.models.feature.ConcordType;
import main.uct.models.feature.Feature;
import main.uct.models.feature.FeatureParser;
import main.uct.models.feature.NounClass;

import java.util.LinkedList;
import java.util.List;

public class ZuFeatureParser extends FeatureParser {

    private String URI = "https://people.cs.uct.ac.za/~zmahlaza/ontologies/templates/ZuTemplateAnnotationOntology#";

    @Override
    public List<String> getRegisteredFeatureProps() {
        List<String> registeredProps = new LinkedList<>();
        registeredProps.add(URI + "hasNounClass");
        registeredProps.add(URI + "hasConcordType");
        return registeredProps;
    }

    @Override
    public Feature getFeature(String featureType) {
        if (featureType.startsWith("NounClass")) {
            String classNumber = featureType.replace("NounClass", "");
            return NounClass.getNounClass(classNumber);
        }
        else if (featureType.endsWith("Concord")) {
            return ConcordType.getConcordType(featureType);
        }
        return null;
    }
}
