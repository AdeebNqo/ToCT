package main.research.models.template;

import main.research.models.feature.Feature;
import main.research.models.interfaces.InternalSlotRootAffix;

import java.util.List;

public class Root extends WordPortion implements InternalSlotRootAffix {

    private List<Feature> features;

    public Root(String value, List<Feature> featureList) {
        this.value = value;
        this.features = featureList;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public String getType() {
        return "Root";
    }
}
