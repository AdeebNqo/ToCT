package main.research.models.template;

import com.hp.hpl.jena.rdf.model.Resource;
import main.research.interfaces.SlotFiller;
import main.research.models.feature.Feature;
import main.research.models.interfaces.InternalSlotOrWordPortion;
import main.research.models.interfaces.InternalSlotRootAffix;

import java.util.List;

public class Slot extends TemplatePortion implements InternalSlotOrWordPortion, InternalSlotRootAffix {

    private Resource underlyingResource;

    private int index;
    private String value;
    private String label;


    private SlotFiller insertedValue;
    private List<Feature> features;

    private List<String> reliesOnLabels;

    public Slot(String label, List<Feature> featureList) {
        this("", label, featureList);
    }

    public Slot(String value, String label, List<Feature> featureList) {
        this.value = value;
        this.label = label;
        this.features = featureList;
    }

    public String getValue() {
        return toString();
    }

    public Resource getUnderlyingResource() {
        return underlyingResource;
    }

    public void setUnderlyingResource(Resource underlyingResource) {
        this.underlyingResource = underlyingResource;
    }

    public String getLabel() {
        return label;
    }

    public void setValue(String value) {
        this.value = value;
    }


    public SlotFiller getInsertedValue() {
        return insertedValue;
    }

    public void insertValue(SlotFiller insertedValue) {
        this.insertedValue = insertedValue;
        this.value = insertedValue.getValue();
    }

    public boolean reliesOn(String label) {
        return reliesOnLabels.contains(label);
    }

    public boolean doesReliesOnSomething() {
        return reliesOnLabels != null && reliesOnLabels.size() > 0;
    }


    public void setReliesOnLabels(List<String> reliesOnLabels) {
        this.reliesOnLabels = reliesOnLabels;
    }

    @Override
    public int getIndex() {
        return index;
    }

    @Override
    public void setIndex(int index) {
        this.index = index;
    }

    @Override
    public String getType() {
        return "Slot";
    }

    @Override
    public String toString() {
        if (value == null || value.isEmpty()) {
            return "["+label+"]";
        }
        return insertedValue.getValue();
    }
}
