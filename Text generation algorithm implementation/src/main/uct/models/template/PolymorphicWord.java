package main.uct.models.template;

import com.hp.hpl.jena.rdf.model.Resource;
import main.uct.models.feature.Feature;
import main.uct.models.interfaces.Chimera;
import main.uct.models.interfaces.InternalSlotRootAffix;
import main.uct.models.interfaces.Word;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class PolymorphicWord extends Chimera {

    private Resource underlyingResource;

    List<Word> governors;
    private List<InternalSlotRootAffix> wordPortions;
    private List<Feature> features;

    private List<String> fillsSlotResources;
    private List<String> dependsOnWordResources;
    private List<String> reliesOnLabels;

    private String value;

    public PolymorphicWord(List<InternalSlotRootAffix> wordPortions, List<Feature> featuresList) {
        this.wordPortions = wordPortions;
        governors = new LinkedList<>();
        this.value = null;
        features = featuresList;
        fillsSlotResources = new LinkedList<>();
        dependsOnWordResources = new LinkedList<>();
    }

    public void addSlotItCanFill(String slotName) {
        fillsSlotResources.add(slotName);
    }

    public List<String> getSlotsItCanFill() {
        return fillsSlotResources;
    }


    public boolean doesDependOnWord(Resource controlingWordResource) {
        return dependsOnWordResources.contains(controlingWordResource);
    }

    public void addDependsOnWordResource(String dependsOnWordResource) {
        this.dependsOnWordResources.add(dependsOnWordResource);
    }

    public boolean doesReliesOnSomething() {
        return reliesOnLabels != null && reliesOnLabels.size() > 0;
    }

    public boolean reliesOn(String slotLabel) {
        return reliesOnLabels.contains(slotLabel);
    }

    public void setReliesOnLabels(List<String> reliesOnLabels) {
        this.reliesOnLabels = reliesOnLabels;
    }

    @Override
    public List<Feature> getFeatures() {
        return features;
    }

    @Override
    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String getValue() {
        return toString();
    }

    public Resource getUnderlyingResource() {
        return underlyingResource;
    }

    public void setUnderlyingResource(Resource underlyingResource) {
        this.underlyingResource = underlyingResource;
    }

    @Override
    public void setFeatures(List<Feature> features) {
        this.features = features;
    }

    //for debugging
    public void printAllLabelsItReliesOn() {
        for (String label : reliesOnLabels) {
            System.out.print(label+" , ");
        }
        System.out.println();
    }


    public List<Concord> getConcords() {
        List<Concord> concords = new LinkedList<>();
        for (InternalSlotRootAffix slotOrWordPortion : wordPortions) {
            if (slotOrWordPortion.getType().equals("AffixChunk")) {
                List<Concord> tmpConcords = ((AffixChunk) slotOrWordPortion).getConcords();
                concords.addAll(tmpConcords);
            }
            if (slotOrWordPortion.getType().equals("Concord")) {
                concords.add((Concord) slotOrWordPortion);
            }
        }
        return concords;
    }

    public List<Slot> getSlots() {
        List<Slot> slots = new LinkedList<>();
        for (InternalSlotRootAffix slotOrWordPortion : wordPortions) {
            if (slotOrWordPortion instanceof  Slot) {
                slots.add((Slot) slotOrWordPortion);
            }
        }
        return slots;
    }

    public List<InternalSlotRootAffix> getAllMorphemes () {
        List<InternalSlotRootAffix> morphemes = new LinkedList<>();
        for (InternalSlotRootAffix slotOrWordPortion : wordPortions) {
            if (slotOrWordPortion  instanceof AffixChunk) {
                AffixChunk affixChunk = (AffixChunk) slotOrWordPortion;
                List<Affix> affixes = affixChunk.getAffixes();
                if (affixes.size() > 0) {
                    for (Affix affix : affixes) {
                        if (affix instanceof Concord) {
                            morphemes.add((Concord) affix);
                        } else if (affix instanceof UnimorphicAffix) {
                            morphemes.add((UnimorphicAffix) affix);
                        }
                    }
                } else {
                    morphemes.add(slotOrWordPortion);
                }
            }
            else {
                morphemes.add(slotOrWordPortion);
            }
        }
        return morphemes;
    }

    public InternalSlotRootAffix getItemAt(int index) {
        return getAllMorphemes().get(index);
    }

    @Override
    public void shuffleFillsInLabels() {
        Collections.shuffle(fillsSlotResources);
    }

    @Override
    public String toString() {
        if (value == null || value.isEmpty() || value.isBlank()) {
            StringBuilder stringBuilder = new StringBuilder();
            List<InternalSlotRootAffix> morphemes = getAllMorphemes();
            for (InternalSlotRootAffix slotRootAffix: morphemes) {
                stringBuilder.append(slotRootAffix.getValue());
            }
            return stringBuilder.toString();
        }
        return value;
    }
}
