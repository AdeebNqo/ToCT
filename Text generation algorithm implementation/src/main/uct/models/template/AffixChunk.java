package main.uct.models.template;

import main.uct.models.feature.Feature;
import main.uct.models.interfaces.InternalSlotRootAffix;

import java.util.LinkedList;
import java.util.List;

public class AffixChunk extends WordPortion implements InternalSlotRootAffix {

    private List<Affix> affixes;
    private String value;
    private List<Feature> features;

    public AffixChunk(String value, List<Affix> affixes, List<Feature> featureList) {
        this.affixes = affixes;
        this.value = value;
        this.features = featureList;
    }

    public List<Concord> getConcords() {
        List<Concord> concords = new LinkedList<>();
        for (Affix affix : affixes) {
            if (affix instanceof Concord) {
                concords.add((Concord) affix);
            }
        }
        return concords;
    }

    public List<Affix> getAffixes() {
        return affixes;
    }

    @Override
    public String getType() {
        return "AffixChunk";
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        if (value == null || value.isEmpty()) {
            StringBuilder stringBuilder = new StringBuilder();
            for (Affix affix : affixes) {
                stringBuilder.append(affix.toString());
            }
        }
        return value;
    }
}
