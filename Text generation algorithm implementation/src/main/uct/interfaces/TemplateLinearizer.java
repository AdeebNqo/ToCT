package main.uct.interfaces;

import main.uct.models.interfaces.InternalSlotRootAffix;
import main.uct.models.template.*;
import java.util.HashMap;
import java.util.List;

public interface TemplateLinearizer {

    String linearize(Template template, SlotValues slotValues);
    String getSlotLabel(Slot someSlot);
    String getSlotValue(String slotLabel, HashMap<String, SlotFiller> slotValues);
    void insertValueInSlot(Slot someSlot, String someSlotValue);
    String getValueOfPolymorph(List<InternalSlotRootAffix> morphemes);
    void insertValueInPolymorph(PolymorphicWord someChimera, String someChimeraValue);
}
