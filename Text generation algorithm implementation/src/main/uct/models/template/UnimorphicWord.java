package main.uct.models.template;

import com.hp.hpl.jena.rdf.model.Resource;
import main.uct.models.feature.Feature;
import main.uct.models.interfaces.Word;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class UnimorphicWord extends Word {

    private Resource underlyingResource;

    private String value;
    private List<Feature> features;
    private int index;

    private List<String> fillsSlotResources;
    private List<String> controlsConcordResources;
    private List<String> providesForResources;

    public UnimorphicWord(String value) {
        this.value = value;
        fillsSlotResources = new LinkedList<>();
        controlsConcordResources = new LinkedList<>();
        providesForResources = new LinkedList<>();
    }

    public void addSlotItCanFill(String slotName) {
        fillsSlotResources.add(slotName);
    }

    public List<String> getSlotsItCanFill() {
        return fillsSlotResources;
    }

    public String getValue() {
        return value;
    }

    public void setFeatures(List<Feature> features) {
        this.features = features;
    }

    public List<Feature> getFeatures() {
        return features;
    }

    public boolean doesControlConcord(String concordLabel) {
        return controlsConcordResources.contains(concordLabel);
    }

    //for debugging
    public void printControlledConcords () {
        StringBuilder sb = new StringBuilder();
        for (String controledCon : controlsConcordResources) {
                sb.append("<"+controledCon+">");
                sb.append(", ");
        }
        System.out.println(sb.toString());
    }

    public void setControlsConcordResources(List<String> controlsConcordResources) {
        this.controlsConcordResources = controlsConcordResources;
    }

    public List<String> getProvidesForPolymorphicWordLabels() {
        return providesForResources;
    }

    public void setProvidesForPolymorphResources(List<String> providesForResources) {
        this.providesForResources = providesForResources;
    }

    public Resource getUnderlyingResource() {
        return underlyingResource;
    }

    public void setUnderlyingResource(Resource underlyingResource) {
        this.underlyingResource = underlyingResource;
    }

    @Override
    public void shuffleFillsInLabels() {
            Collections.shuffle(fillsSlotResources);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(value);
        //uncomment code below when you need the features to be displayed
/*        if (features!=null) {
            if (features.size() > 0) {
                sb.append("[");
            }
            for (int i = 0; i < features.size(); i++) {
                Feature feature = features.get(i);
                sb.append(feature.toString());
                if (i + 1 < features.size()) {
                    sb.append(", ");
                }
            }
            if (features.size() > 0) {
                sb.append("]");
            }
        }*/
        return sb.toString();
    }
}
