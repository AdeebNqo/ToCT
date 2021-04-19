package main.uct.interfaces;

import main.uct.models.feature.ConcordType;
import main.uct.models.feature.NounClass;

public interface ConcordMapper {
    String getConcordValue(NounClass someNounClass, ConcordType someConcordType);
    String getConcordValue(NounClass someNounClass, ConcordType someConcordType, String nextMorpheme);
}