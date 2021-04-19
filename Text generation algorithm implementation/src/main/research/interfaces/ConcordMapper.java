package main.research.interfaces;

import main.research.models.feature.ConcordType;
import main.research.models.feature.NounClass;

public interface ConcordMapper {
    String getConcordValue(NounClass someNounClass, ConcordType someConcordType);
    String getConcordValue(NounClass someNounClass, ConcordType someConcordType, String nextMorpheme);
}