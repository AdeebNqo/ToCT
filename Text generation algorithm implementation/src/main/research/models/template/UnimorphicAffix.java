package main.research.models.template;

import main.research.models.feature.Feature;
import main.research.models.interfaces.InternalSlotRootAffix;

import java.util.List;

public class UnimorphicAffix extends Affix implements InternalSlotRootAffix {

    private String value;
    private int index;
    private List<Feature> features;

    public UnimorphicAffix(String value, List<Feature> featureList) {
        this.value = value;
        this.features = featureList;
    }

    @Override
    public String getValue() {
        return value;
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
        return "UnimorphicAffix";
    }

    @Override
    public String toString() {
        return value;
    }
}
