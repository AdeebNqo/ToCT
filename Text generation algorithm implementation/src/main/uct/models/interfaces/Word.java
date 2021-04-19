package main.uct.models.interfaces;

import main.uct.interfaces.SlotFiller;
import main.uct.models.template.TemplatePortion;

public abstract class Word extends TemplatePortion implements SlotFiller {
    public abstract String getValue();
}
