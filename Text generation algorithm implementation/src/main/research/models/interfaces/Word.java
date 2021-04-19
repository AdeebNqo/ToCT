package main.research.models.interfaces;

import main.research.interfaces.SlotFiller;
import main.research.models.template.TemplatePortion;

public abstract class Word extends TemplatePortion implements SlotFiller {
    public abstract String getValue();
}
