package main.uct.impl.nguni.xh;

import main.uct.impl.nguni.NguniVowelDetector;
import main.uct.interfaces.ConcordMapper;
import main.uct.models.feature.ConcordType;
import main.uct.models.feature.NounClass;

import java.util.HashMap;

public class XhosaConcordMapper implements ConcordMapper {

    private HashMap<String, String> subjectConcords = new HashMap<>();
    private HashMap<String, String> negativeSubjectConcords = new HashMap<>();
    private HashMap<String, String> objectConcords = new HashMap<>();
    private HashMap<String, String> possessiveConcords = new HashMap<>();
    private HashMap<String, String> phimbiConcords = new HashMap<>();
    private HashMap<String, String> sadjectivalConconrds = new  HashMap<>();
    private HashMap<String, String> ladjectivalConconrds = new HashMap<>();
    private HashMap<String, String> relativeConconrds = new HashMap<>();
    private HashMap<String, String> enumerativeConconrds = new HashMap<>();

    private NguniVowelDetector vowelDetector;

    public XhosaConcordMapper() {
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
        subjectConcords.put("7", "isi");
        subjectConcords.put("8", "izi");
        subjectConcords.put("9", "i");
        subjectConcords.put("10", "zi");
        subjectConcords.put("11", "lu");
        subjectConcords.put("14", "bu");
        subjectConcords.put("15", "ku");
        subjectConcords.put("17", "ku");
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
        //phi or mbi
        phimbiConcords.put("1", "wu");
        phimbiConcords.put("2", "ba");
        phimbiConcords.put("1a", "wu");
        phimbiConcords.put("2a", "ba");
        phimbiConcords.put("3a", "wu");
        phimbiConcords.put("3", "wu");
        phimbiConcords.put("4", "yi");
        phimbiConcords.put("5", "li");
        phimbiConcords.put("6", "wa");
        phimbiConcords.put("7", "si");
        phimbiConcords.put("8", "zi");
        phimbiConcords.put("9", "yi");
        phimbiConcords.put("10", "zi");
        phimbiConcords.put("11", "lu");
        phimbiConcords.put("14", "bu");
        phimbiConcords.put("15", "ku");
        //short adjectival
        sadjectivalConconrds.put("1", "m");
        sadjectivalConconrds.put("2" , "ba");
        sadjectivalConconrds.put("1a", "ms");
        sadjectivalConconrds.put("2a", "ba");
        sadjectivalConconrds.put("3a", "m");
        sadjectivalConconrds.put("3", "m");
        sadjectivalConconrds.put("4", "mi");
        sadjectivalConconrds.put("5", "li");
        sadjectivalConconrds.put("6", "ma");
        sadjectivalConconrds.put("7", "si");
        sadjectivalConconrds.put("8", "zi");
        sadjectivalConconrds.put("9", "n/im");
        sadjectivalConconrds.put("10", "zin/zim"); //TODO: handle these types of concords
        sadjectivalConconrds.put("11", "lu");
        sadjectivalConconrds.put("14", "bu");
        sadjectivalConconrds.put("15", "ku");
        //long adjectival
        ladjectivalConconrds.put("1", "om");
        ladjectivalConconrds.put("2" , "aba");
        ladjectivalConconrds.put("1a", "om");
        ladjectivalConconrds.put("2a", "aba");
        ladjectivalConconrds.put("3a", "om");
        ladjectivalConconrds.put("3", "om");
        ladjectivalConconrds.put("4", "emi");
        ladjectivalConconrds.put("5", "eli");
        ladjectivalConconrds.put("6", "ama");
        ladjectivalConconrds.put("7", "esi");
        ladjectivalConconrds.put("8", "ezi");
        ladjectivalConconrds.put("9", "en/em");
        ladjectivalConconrds.put("10", "ezin/ezim");
        ladjectivalConconrds.put("11", "olu");
        ladjectivalConconrds.put("14", "obu");
        ladjectivalConconrds.put("15", "oku");
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
        //enumerative
        enumerativeConconrds.put("1", "m");
        enumerativeConconrds.put("2", "ba");
        enumerativeConconrds.put("1a", "m");
        enumerativeConconrds.put("2a", "ba");
        enumerativeConconrds.put("3a", "m");
        enumerativeConconrds.put("3", "m");
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
        negativeSubjectConcords.put("1", "aka");
        negativeSubjectConcords.put("2", "aba");
        negativeSubjectConcords.put("1a", "aka");
        negativeSubjectConcords.put("2a", "aba");
        negativeSubjectConcords.put("3a", "awu");
        negativeSubjectConcords.put("3", "awu");
        negativeSubjectConcords.put("4", "ayi");
        negativeSubjectConcords.put("5", "ali");
        negativeSubjectConcords.put("6", "awa");
        negativeSubjectConcords.put("7", "esi");
        negativeSubjectConcords.put("8", "ezi");
        negativeSubjectConcords.put("9", "ayi");
        negativeSubjectConcords.put("10", "azi");
        negativeSubjectConcords.put("11", "alu");
        negativeSubjectConcords.put("14", "abu");
        negativeSubjectConcords.put("15", "aku");
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
            case "PhiMbiConcord" : {
                return phimbiConcords.get(someNounClass.getNounClass());
            }
            case "ShortAdjectivalConcord" : {
                //TODO; Introduce AdjectivalConcord and then resolve the appropriate concord (short or long) based on the gov's leading char
                return sadjectivalConconrds.get(someNounClass.getNounClass());
            }
            case "LongAdjectivalConcord" : {
                return ladjectivalConconrds.get(someNounClass.getNounClass());
            }
            case "RelativeConcord" : {
                return relativeConconrds.get(someNounClass.getNounClass());
            }
            case "EnumerativeConcord" : {
                return enumerativeConconrds.get(someNounClass.getNounClass());
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
