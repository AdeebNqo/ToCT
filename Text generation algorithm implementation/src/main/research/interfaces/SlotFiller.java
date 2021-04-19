package main.research.interfaces;

import main.research.models.feature.Feature;

import java.util.List;

public interface SlotFiller {
    void addSlotItCanFill(String slotName);
    List<String> getSlotsItCanFill();
    String getValue();
    List<Feature> getFeatures();
    void setFeatures(List<Feature> features);

    //for debugging
    void shuffleFillsInLabels();
}
