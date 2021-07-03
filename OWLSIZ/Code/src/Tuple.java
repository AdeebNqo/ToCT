import research.parser.TTLSlotValues;
import research.models.template.Template;

public class Tuple {

    Template template;
    TTLSlotValues slotFillers;

    public Tuple(Template templ, TTLSlotValues slotFillers) {
        this.template = templ;
        this.slotFillers = slotFillers;
    }

    public Template getTemplate() {
        return template;
    }

    public TTLSlotValues getSlotFillers() {
        return slotFillers;
    }
}
