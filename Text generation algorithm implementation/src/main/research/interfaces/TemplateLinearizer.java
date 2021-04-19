package main.research.interfaces;

import main.research.models.interfaces.InternalSlotRootAffix;
import main.research.models.template.*;
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
