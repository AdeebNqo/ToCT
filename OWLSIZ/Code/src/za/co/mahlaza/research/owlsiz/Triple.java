package za.co.mahlaza.research.owlsiz;

import za.co.mahlaza.research.grammarengine.base.models.template.Template;
import za.co.mahlaza.research.templateparsing.TTLSlotValues;

public class Triple {

    String templateFilePath;
    Template template;
    TTLSlotValues slotFillers;

    public Triple(Template templ, TTLSlotValues slotFillers) {
        this.template = templ;
        this.slotFillers = slotFillers;
    }

    public void setTemplateFilePath(String templateFilePath) {
        this.templateFilePath = templateFilePath;
    }

    public String getTemplateFilePath() {
        return templateFilePath;
    }

    public Template getTemplate() {
        return template;
    }

    public TTLSlotValues getSlotFillers() {
        return slotFillers;
    }
}
