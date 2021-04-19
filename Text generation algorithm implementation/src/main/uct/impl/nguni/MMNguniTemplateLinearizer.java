package main.uct.impl.nguni;

import main.uct.interfaces.ConcordMapper;
import main.uct.interfaces.MorphophonoAlternator;
import main.uct.interfaces.SlotFiller;
import main.uct.interfaces.TemplateLinearizer;
import main.uct.models.feature.Feature;
import main.uct.models.feature.NounClass;
import main.uct.models.interfaces.InternalSlotRootAffix;
import main.uct.models.interfaces.Word;
import main.uct.models.template.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class MMNguniTemplateLinearizer implements TemplateLinearizer {

    private ConcordMapper concordMapper;
    private MorphophonoAlternator morphophonoAlternator;

    public MMNguniTemplateLinearizer(ConcordMapper concordMapper, MorphophonoAlternator morphophonoAlternator) {
        this.concordMapper = concordMapper;
        this.morphophonoAlternator = morphophonoAlternator;
    }

    @Override
    public String linearize(Template template, SlotValues SV) {

        StringBuilder templateValue = new StringBuilder();

        //Inserting values into slots
        List<Slot> slots = template.getSlots();
        HashMap<String, SlotFiller> slotValues = SV.getValues();


        for (Slot slot: slots) {
            String label = slot.getUnderlyingResource().getLocalName();
            SlotFiller slotFiller = slotValues.get(label);
            if (slotFiller != null) {
                slot.insertValue(slotFiller);
            }
            //TODO: throw an exception, missing slot filler.
        }

        parseWordDependencies(template);

        List<PolymorphicWord> templateWords = template.getPolymorphicWords();
        //forming the chimeras
        for (PolymorphicWord currWord : templateWords) {
            //setting values of concords using governors
            //List<Concord> concords =
            for (Concord concord : currWord.getConcords()) {
                SlotFiller gov = concord.getController();

                //System.out.println("Gov "+gov);
                if (gov != null) {
                    List<Feature> features = gov.getFeatures();
                    //System.out.println("Features = "+features.size());
                    for (Feature feature : features) {
                        if (feature instanceof NounClass) {
                            String concordValue = concordMapper.getConcordValue(((NounClass) feature), concord.getConcordType());
                            if (concordValue.contains("/")) {
                                //if class has two concords then choose appropriate one
                                int concordIndex = concord.getIndex();
                                InternalSlotRootAffix nextItem = currWord.getItemAt(concordIndex + 1);
                                String nextMorphemeVal = nextItem.getValue();
                                concordValue = concordMapper.getConcordValue((NounClass) feature, concord.getConcordType(), nextMorphemeVal);
                            }
                            concord.setValue(concordValue);
                            break;
                        }
                    }
                }
            }

            List<InternalSlotRootAffix> morphemes = currWord.getAllMorphemes();
            String value = getValueOfPolymorph(morphemes);
            insertValueInPolymorph(currWord, value);
        }

        List<TemplatePortion> templatePortions = template.getTemplatePortions();
        //collecting the linearized words
        for (int i = 0; i < templatePortions.size()-1; i++) {
            templateValue.append(templatePortions.get(i));
            if (!(templatePortions.get(i+1) instanceof Punctuation) && !(templatePortions.get(i) instanceof Punctuation)) {
                templateValue.append(" ");
            }
        }
        templateValue.append(templatePortions.get(templatePortions.size()-1));

        return templateValue.toString().trim();
    }

    private static void parseWordDependencies(Template template) {
        //case 1 -- when polymorphs in slots depend on unimorphs
        for (UnimorphicWord unimorphicWord : template.getUnimorphicWords()) {
            List<Slot> slots = template.getSlots();
            for (Slot slot : slots) {
                if (slot.doesReliesOnSomething()) {
                    if (slot.reliesOn(unimorphicWord.getUnderlyingResource().getLocalName())) {
                        //if slot relies on unimorphic word
                        SlotFiller slotFiller = slot.getInsertedValue();
                        List<PolymorphicWord> polymorphicWords = getPolymorphicWordsFromSlotFiller(slotFiller);
                        for (PolymorphicWord polymorphicWord : polymorphicWords) {
                            List<Concord> concords = polymorphicWord.getConcords();
                            for (Concord concord : concords) {
                                if (unimorphicWord.doesControlConcord(concord.getUnderlyingResource().getLocalName())) {
                                    concord.setController(unimorphicWord);
                                }
                            }
                        }
                    }
                }
            }
        }
        //case 2 -- when polymorphs are providedFor by unimorphs placed in slots
        for (Slot slot : template.getSlots()) {
            for (PolymorphicWord polymorphicWord : template.getPolymorphicWords()) {
                if (polymorphicWord.doesReliesOnSomething()) {
                    if (polymorphicWord.reliesOn(slot.getUnderlyingResource().getLocalName())) {
                        //if poly relies on the slot
                        SlotFiller slotFiller = slot.getInsertedValue();
                        List<UnimorphicWord> unimorphicWords = getUnimorphicWordsFromSlotFiller(slotFiller);
                        for (UnimorphicWord unimorphicWord : unimorphicWords) {
                            List<Concord> concords = polymorphicWord.getConcords();
                            for (Concord concord : concords) {
                                if (unimorphicWord.doesControlConcord(concord.getUnderlyingResource().getLocalName())) {
                                    concord.setController(unimorphicWord);
                                }
                            }
                        }
                    }
                }
            }
        }

    }


    private static List<UnimorphicWord> getUnimorphicWordsFromSlotFiller(SlotFiller slotFiller) {
        List<UnimorphicWord> unimorphicWords = new LinkedList<>();

        if (slotFiller instanceof UnimorphicWord) {
            unimorphicWords.add((UnimorphicWord) slotFiller);
        }
        else if (slotFiller instanceof Phrase) {
            List<Word> words = ((Phrase) slotFiller).getWords();
            for (Word word : words) {
                if (word instanceof UnimorphicWord) {
                    unimorphicWords.add((UnimorphicWord) word);
                }
            }
        }

        return unimorphicWords;
    }

    private static List<PolymorphicWord> getPolymorphicWordsFromSlotFiller(SlotFiller slotFiller) {
        List<PolymorphicWord> polymorphicWords = new LinkedList<>();

        if (slotFiller instanceof  PolymorphicWord) {
            polymorphicWords.add((PolymorphicWord) slotFiller);
        }
        else if (slotFiller instanceof Phrase) {
            List<Word> words = ((Phrase) slotFiller).getWords();
            for (Word word : words) {
                if (word instanceof PolymorphicWord) {
                    polymorphicWords.add((PolymorphicWord) word);
                }
            }
        }

        return polymorphicWords;
    }

    private static List<PolymorphicWord> getPolymorphicWordsWithLabels(Template template, List<String> labels) {
        List<PolymorphicWord> supportedPolyWords = new LinkedList<>();

        List<PolymorphicWord> polymorphicWords = template.getPolymorphicWords();
        for (PolymorphicWord word : polymorphicWords) {
            if (labels.contains(word.getUnderlyingResource().getLocalName())) {
                supportedPolyWords.add(word);
            }
        }


        return supportedPolyWords;
    }

    @Override
    public String getSlotLabel(Slot someSlot) {
        return someSlot.getLabel();
    }

    @Override
    public String getSlotValue(String slotLabel, HashMap<String, SlotFiller> slotValues) {
        return slotValues.get(slotLabel).getValue();
    }

    @Override
    public void insertValueInSlot(Slot someSlot, String someSlotValue) {
        someSlot.setValue(someSlotValue);
    }

    @Override
    public String getValueOfPolymorph(List<InternalSlotRootAffix> morphemes) {
        String leftValue = null;
        for (int i=0; i<morphemes.size()-1; i++) {
            InternalSlotRootAffix leftMorph = morphemes.get(i);
            InternalSlotRootAffix rightMorph = morphemes.get(i+1);
            if (leftValue == null) {
                leftValue = leftMorph.getValue();
            }
            String newMorphValue = morphophonoAlternator.joinMorpheme(leftValue, rightMorph.getValue());
            leftValue = newMorphValue;
        }
        return leftValue;
    }

    @Override
    public void insertValueInPolymorph(PolymorphicWord someChimera, String someChimeraValue) {
        someChimera.setValue(someChimeraValue);
    }
}
