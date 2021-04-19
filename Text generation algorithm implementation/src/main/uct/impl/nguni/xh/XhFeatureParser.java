package main.uct.impl.nguni.xh;

import main.uct.models.feature.ConcordType;
import main.uct.models.feature.Feature;
import main.uct.models.feature.FeatureParser;
import main.uct.models.feature.NounClass;

import java.util.LinkedList;
import java.util.List;

import static main.uct.parser.URIS.XH_ANNOT_NS;

public class XhFeatureParser extends FeatureParser {

    @Override
    public List<String> getRegisteredFeatureProps() {
        List<String> registeredProps = new LinkedList<>();
        registeredProps.add(XH_ANNOT_NS + "hasNounClass");
        registeredProps.add(XH_ANNOT_NS + "hasConcordType");
        //registeredProps.add(URI + "hasPOS");
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
