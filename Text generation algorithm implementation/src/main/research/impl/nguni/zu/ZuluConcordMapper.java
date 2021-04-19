package main.research.impl.nguni.zu;

import main.research.impl.nguni.NguniVowelDetector;
import main.research.interfaces.ConcordMapper;
import main.research.models.feature.ConcordType;
import main.research.models.feature.NounClass;

import java.util.HashMap;

public class ZuluConcordMapper implements ConcordMapper {

    private HashMap<String, String> subjectConcords = new HashMap<>();
    private HashMap<String, String> negativeSubjectConcords = new HashMap<>();
    private HashMap<String, String> possessiveConcords = new HashMap<>();
    private HashMap<String, String> relativeConconrds = new HashMap<>();
    private HashMap<String, String> objectConcords = new HashMap<>();
    private HashMap<String, String> adjectivalConconrds = new HashMap<>();
    private HashMap<String, String> enumerativeConconrds = new HashMap<>();

    private NguniVowelDetector vowelDetector;

    public ZuluConcordMapper() {
        this.vowelDetector = new NguniVowelDetector();
        //subjectival concord
        subjectConcords.put("1", "u");
        subjectConcords.put("2", "ba");
        subjectConcords.put("1a", "u");
        subjectConcords.put("2a", "ba");
        subjectConcords.put("3a", "u");
        subjectConcords.put("3", "u");
        subjectConcords.put("4", "i");
        subjectConcords.put("5", "li");
        subjectConcords.put("6", "a");
        subjectConcords.put("7", "usi");
        subjectConcords.put("8", "zi");
        subjectConcords.put("9", "i");
        subjectConcords.put("10", "zi");
        subjectConcords.put("11", "lu");
        subjectConcords.put("14", "bu");
        subjectConcords.put("15", "ku");
        //objectival concord
        objectConcords.put("1", "m");
        objectConcords.put("2", "ba");
        objectConcords.put("1a", "m");
        objectConcords.put("2a", "ba");
        objectConcords.put("3a", "wu");
        objectConcords.put("3", "wu");
        objectConcords.put("4", "yi");
        objectConcords.put("5", "li");
        objectConcords.put("6", "wa");
        objectConcords.put("7", "si");
        objectConcords.put("8", "zi");
        objectConcords.put("9", "yi");
        objectConcords.put("10", "zi");
        objectConcords.put("11", "lu");
        objectConcords.put("14", "bu");
        objectConcords.put("15", "ku");
        subjectConcords.put("17", "ku");
        //possessive
        possessiveConcords.put("1", "wa");
        possessiveConcords.put("2", "ba");
        possessiveConcords.put("1a", "wa");
        possessiveConcords.put("2a", "ba");
        possessiveConcords.put("3a", "wa");
        possessiveConcords.put("3", "wa");
        possessiveConcords.put("4", "ya");
        possessiveConcords.put("5", "la");
        possessiveConcords.put("6", "a");
        possessiveConcords.put("7", "sa");
        possessiveConcords.put("8", "za");
        possessiveConcords.put("9", "ya");
        possessiveConcords.put("10", "za");
        possessiveConcords.put("11", "lwa");
        possessiveConcords.put("14", "ba");
        possessiveConcords.put("15", "kwa");
        possessiveConcords.put("16", "kwa");
        possessiveConcords.put("17", "kwa");
        possessiveConcords.put("18", "kwa");
        //relative
        relativeConconrds.put("1", "u");
        relativeConconrds.put("2", "aba");
        relativeConconrds.put("1a", "u");
        relativeConconrds.put("2a", "aba");
        relativeConconrds.put("3a", "o");
        relativeConconrds.put("3", "o");
        relativeConconrds.put("4", "e");
        relativeConconrds.put("5", "eli");
        relativeConconrds.put("6", "a");
        relativeConconrds.put("7", "esi");
        relativeConconrds.put("8", "ezi");
        relativeConconrds.put("9", "e");
        relativeConconrds.put("10", "ezi");
        relativeConconrds.put("11", "olu");
        relativeConconrds.put("14", "obu");
        relativeConconrds.put("15", "oku");
        //adjectival
        adjectivalConconrds.put("1", "om/omu");
        adjectivalConconrds.put("2" , "aba");
        adjectivalConconrds.put("1a", "om/omu");
        adjectivalConconrds.put("2a", "aba");
        adjectivalConconrds.put("3a", "om/omu");
        adjectivalConconrds.put("3", "om/omu");
        adjectivalConconrds.put("4", "emi");
        adjectivalConconrds.put("5", "eli");
        adjectivalConconrds.put("6", "ama");
        adjectivalConconrds.put("7", "esi");
        adjectivalConconrds.put("8", "ezin/ezim");
        adjectivalConconrds.put("9", "en/em");
        adjectivalConconrds.put("10", "ezin/ezim");
        adjectivalConconrds.put("11", "olu");
        adjectivalConconrds.put("14", "obu");
        adjectivalConconrds.put("15", "oku");
        //enumerative
        enumerativeConconrds.put("1", "mu");
        enumerativeConconrds.put("2", "ba");
        enumerativeConconrds.put("1a", "mu");
        enumerativeConconrds.put("2a", "ba");
        enumerativeConconrds.put("3a", "mu");
        enumerativeConconrds.put("3", "mu");
        enumerativeConconrds.put("4", "mi");
        enumerativeConconrds.put("5", "li");
        enumerativeConconrds.put("6", "ma");
        enumerativeConconrds.put("7", "si");
        enumerativeConconrds.put("8", "zi");
        enumerativeConconrds.put("9", "i");
        enumerativeConconrds.put("10", "zi");
        enumerativeConconrds.put("11", "lu");
        enumerativeConconrds.put("14", "bu");
        enumerativeConconrds.put("15", "ku");
        //negative subject concords
        //TODO: implement the concords
        negativeSubjectConcords.put("1", "??");
        negativeSubjectConcords.put("2", "??");
        negativeSubjectConcords.put("1a", "??");
        negativeSubjectConcords.put("2a", "??");
        negativeSubjectConcords.put("3a", "??");
        negativeSubjectConcords.put("3", "??");
        negativeSubjectConcords.put("4", "??");
        negativeSubjectConcords.put("5", "??");
        negativeSubjectConcords.put("6", "??");
        negativeSubjectConcords.put("7", "??");
        negativeSubjectConcords.put("8", "??");
        negativeSubjectConcords.put("9", "??");
        negativeSubjectConcords.put("10", "??");
        negativeSubjectConcords.put("11", "??");
        negativeSubjectConcords.put("14", "??");
        negativeSubjectConcords.put("15", "??");
    }

    @Override
    public String getConcordValue(NounClass someNounClass, ConcordType someConcordType) {
        switch (someConcordType.getTypeString()) {
            case "SubjectivalConcord" : {
                return subjectConcords.get(someNounClass.getNounClass());
            }
            case "ObjectivalConcord" : {
                return objectConcords.get(someNounClass.getNounClass());
            }
            case "PossessiveConcord" : {
                return possessiveConcords.get(someNounClass.getNounClass());
            }
            case "EnumerativeConcord" : {
                return enumerativeConconrds.get(someNounClass.getNounClass());
            }
            case "AdjectivalConcord" : {
                return adjectivalConconrds.get(someNounClass.getNounClass());
            }
            case "RelativeConcord" : {
                return relativeConconrds.get(someNounClass.getNounClass());
            }
            case "NegativeSubjectivalConcord" : {
                return negativeSubjectConcords.get(someNounClass.getNounClass());
            }
        }
        return null;
    }

    @Override
    public String getConcordValue(NounClass someNounClass, ConcordType someConcordType, String nextMorpheme) {
        String concordValue = null;

        String doubleConcord = getConcordValue(someNounClass, someConcordType);
        String[] concordVals = doubleConcord.split("/");
        if (vowelDetector.isLabial(nextMorpheme.substring(0,2)) || vowelDetector.isLabial(nextMorpheme.charAt(0))) {
            if (concordVals[0].endsWith("m")) {
                concordValue = concordVals[0];
            } else if (concordVals[1].endsWith("m")) {
                concordValue = concordVals[1];
            }
        } else {
            if (concordVals[0].endsWith("m")) {
                concordValue = concordVals[1];
            } else if (concordVals[1].endsWith("m")) {
                concordValue = concordVals[0];
            }
        }
        return concordValue;
    }
}
