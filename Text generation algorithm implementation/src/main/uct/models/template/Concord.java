package main.uct.models.template;

import com.hp.hpl.jena.rdf.model.Resource;
import main.uct.interfaces.SlotFiller;
import main.uct.models.feature.ConcordType;
import main.uct.models.feature.Feature;
import main.uct.models.interfaces.InternalSlotRootAffix;

import java.util.List;

public class Concord extends Affix implements InternalSlotRootAffix {

    private Resource underlyingResource;

    private String value;
    private String label;
    private List<Feature> featureList;

    private SlotFiller controller;

    private int index;

    public Concord(String label, List<Feature> featureList) {
        this("", label, featureList);
    }


    public Concord(String value, String label, List<Feature> featureList) {
        this.value = value;
        this.label = label;
        this.featureList = featureList;
    }

    public Resource getUnderlyingResource() {
        return underlyingResource;
    }

    public void setUnderlyingResource(Resource underlyingResource) {
        this.underlyingResource = underlyingResource;
    }

    @Override
    public String getValue() {
        return toString();
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public ConcordType getConcordType() {
        for (Feature feature : featureList) {
            if (feature instanceof ConcordType) {
                return (ConcordType) feature;
            }
        }
        return null;
    }

    public void setController(SlotFiller controller) {
        this.controller = controller;
    }

    public SlotFiller getController() {
        if (controller != null) {
            return controller;
        }
        return null;
    }

    @Override
    public String toString() {
        if (value != null && !value.isEmpty()) {
            return value;
        }
        return "["+label+"]";
    }

    @Override
    public String getType() {
        return "Concord";
    }

    @Override
    public int getIndex() {
        return index;
    }

    @Override
    public void setIndex(int index) {
        this.index = index;
    }
}
