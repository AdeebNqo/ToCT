package main.uct;


import main.uct.impl.nguni.MMNguniTemplateLinearizer;
import main.uct.impl.nguni.xh.XhFeatureParser;
import main.uct.impl.nguni.xh.XhosaConcordMapper;
import main.uct.impl.nguni.xh.XhosaMorphophonoAlternator;
import main.uct.impl.nguni.zu.ZuFeatureParser;
import main.uct.impl.nguni.zu.ZuluConcordMapper;
import main.uct.impl.nguni.zu.ZuluMorphophonoAlternator;
import main.uct.interfaces.ConcordMapper;
import main.uct.interfaces.MorphophonoAlternator;
import main.uct.models.template.SlotValues;
import main.uct.models.template.Template;
import main.uct.parser.TemplateReader;
import static main.uct.parser.URIS.GW_BASE;
import static main.uct.parser.URIS.Test_BASE;

public class Main {

    public static void main(String[] args) {

        TemplateReader.Init(new XhFeatureParser());

        Template template = TemplateReader.parseTemplate("cloud4Template", GW_BASE, "cloud4-xh.ttl");
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

        SlotValues SV = new SlotValues("/home/zola/Documents/phd/work/tasks/isixhosagenerationiter2/GaliWeather/templates/slot-fillers.ttl", GW_BASE);
        SV.shuffle(); //TODO: remove this

        //linearization
        MMNguniTemplateLinearizer linearizer = new MMNguniTemplateLinearizer(concordMapper, alternator);
        String finalText = linearizer.linearize(template, SV);
        System.out.println(finalText);
    }

}
