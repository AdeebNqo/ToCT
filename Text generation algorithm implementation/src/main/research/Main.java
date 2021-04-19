package main.research;


import main.research.impl.nguni.MMNguniTemplateLinearizer;
import main.research.impl.nguni.xh.XhFeatureParser;
import main.research.impl.nguni.xh.XhosaConcordMapper;
import main.research.impl.nguni.xh.XhosaMorphophonoAlternator;
import main.research.impl.nguni.zu.ZuluConcordMapper;
import main.research.impl.nguni.zu.ZuluMorphophonoAlternator;
import main.research.interfaces.ConcordMapper;
import main.research.interfaces.MorphophonoAlternator;
import main.research.models.template.SlotValues;
import main.research.models.template.Template;
import main.research.parser.TemplateReader;
import static main.research.parser.URIS.GW_BASE;

public class Main {

    public static void main(String[] args) {

        String baseFilePath = "res/cloudcover1/";

        //TODO: find a way to handle features better. There could be different kinds of features. Perhaps need to register different parsers?
        TemplateReader.Init(new XhFeatureParser());

        Template template = TemplateReader.parseTemplate("cloud4Template", GW_BASE, baseFilePath + "cloud4-xh.ttl");
        System.out.println(template);

        ConcordMapper concordMapper = null;
        MorphophonoAlternator alternator = null;
        switch (template.getLanguageFamily()) {
            case "isiXhosa": {
                concordMapper = new XhosaConcordMapper();
                alternator = new XhosaMorphophonoAlternator();
                break;
            }
            case "isiZulu": {
                concordMapper = new ZuluConcordMapper();
                alternator = new ZuluMorphophonoAlternator();
                break;
            }
        }
        if (concordMapper == null || alternator == null) {
            throw new UnsupportedOperationException("Template language not supported");
        }

        SlotValues SV = new SlotValues(baseFilePath + "slot-fillers.ttl", GW_BASE);

        //linearization
        MMNguniTemplateLinearizer linearizer = new MMNguniTemplateLinearizer(concordMapper, alternator);
        String finalText = linearizer.linearize(template, SV);
        System.out.println(finalText);
    }

}
