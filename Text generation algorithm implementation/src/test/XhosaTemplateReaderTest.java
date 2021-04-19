package test;

import main.research.impl.nguni.MMNguniTemplateLinearizer;
import main.research.impl.nguni.xh.XhFeatureParser;
import main.research.impl.nguni.xh.XhosaConcordMapper;
import main.research.impl.nguni.xh.XhosaMorphophonoAlternator;
import main.research.impl.nguni.zu.ZuluConcordMapper;
import main.research.impl.nguni.zu.ZuluMorphophonoAlternator;
import main.research.interfaces.ConcordMapper;
import main.research.interfaces.MorphophonoAlternator;
import main.research.interfaces.SlotFiller;
import main.research.models.feature.Feature;
import main.research.models.feature.NounClass;
import main.research.models.template.SlotValues;
import main.research.models.template.UnimorphicWord;
import main.research.models.template.Template;
import main.research.parser.TemplateReader;
import main.research.parser.URIS;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;

public class XhosaTemplateReaderTest {

    private SlotValues SV;
    //Model model = ModelFactory.createDefaultModel();

    @Before
    public void setUp () {
        NounClass nounClass = NounClass.getNounClass("10");
        List<Feature> features = new LinkedList<>();
        features.add(nounClass);
        UnimorphicWord word = new UnimorphicWord("izinja");
        word.setFeatures(features);
        List<String> controlsList = new LinkedList<>();
        controlsList.add("of");
        controlsList.add("quantConcord");
        controlsList.add("LAdjC");
        controlsList.add("RelC");
        controlsList.add("subj");
        word.setControlsConcordResources(controlsList);
        word.addSlotItCanFill("noun1slot");

        NounClass nounClassTwo = NounClass.getNounClass("1");
        List<Feature> features2 = new LinkedList<>();
        features2.add(nounClassTwo);
        UnimorphicWord word2 = new UnimorphicWord("umntu");
        word2.setFeatures(features2);
        List<String> controls2List = new LinkedList<>();
        controls2List.add("enumConc");
        controls2List.add("obj");
        word2.setControlsConcordResources(controls2List);
        word.addSlotItCanFill("noun2slot");

        NounClass nounClassThree = NounClass.getNounClass("10");
        List<Feature> features3 = new LinkedList<>();
        features3.add(nounClassThree);
        UnimorphicWord word3 = new UnimorphicWord("zinja");
        word3.setFeatures(features3);
        List<String> controls3List = new LinkedList<>();
        controls3List.add("SAdjC");
        controls3List.add("phiconc");
        word3.setControlsConcordResources(controls3List);
        word.addSlotItCanFill("noun3slot");

        HashMap<String, SlotFiller> values = new HashMap<>();
        values.put("noun1Slot", word);
        values.put("noun2Slot", word2);
        values.put("noun3Slot", word3);
        SV = new SlotValues(values);

        TemplateReader.Init(new XhFeatureParser());
    }

    @Test
    public void testPossessiveConcord() {
        Template template = TemplateReader.parseTemplate("possessiveNounTemp",URIS.Test_BASE, "res/xh/postemplate.ttl");

        //linearization
        ConcordMapper concordMapper = null;
        MorphophonoAlternator alternator = null;
        MMNguniTemplateLinearizer linearizer = null;

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

        linearizer = new MMNguniTemplateLinearizer(concordMapper, alternator);
        String finalText = linearizer.linearize(template, SV);

        assertEquals("izinja zamadoda", finalText);
    }

    @Test
    public void testSubjectConcord () {
        Template template = TemplateReader.parseTemplate("plainOPPresentTense", URIS.Test_BASE,"res/xh/subjectivaltemplate.ttl");

        //linearization
        ConcordMapper concordMapper = null;
        MorphophonoAlternator alternator = null;
        MMNguniTemplateLinearizer linearizer = null;

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

        linearizer = new MMNguniTemplateLinearizer(concordMapper, alternator);
        String finalText = linearizer.linearize(template, SV);
        assertEquals("zonke izinja", finalText);
    }

    @Test
    public void testSubjectivalAndObjectivalConcord () {
        Template template = TemplateReader.parseTemplate("verbObjSubjTemplate", URIS.Test_BASE,"res/xh/objectivaltemplate.ttl");

        //linearization
        ConcordMapper concordMapper = null;
        MorphophonoAlternator alternator = null;
        MMNguniTemplateLinearizer linearizer = null;

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

        linearizer = new MMNguniTemplateLinearizer(concordMapper, alternator);
        String finalText = linearizer.linearize(template, SV);

        assertEquals("izinja ziyamhambisa umntu", finalText);
    }

    @Test
    public void testPhiMbiConcord () {
        Template template = TemplateReader.parseTemplate("phiTemplate", URIS.Test_BASE,"res/xh/phimbitemplate.ttl");

        //linearization
        ConcordMapper concordMapper = null;
        MorphophonoAlternator alternator = null;
        MMNguniTemplateLinearizer linearizer = null;

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

        linearizer = new MMNguniTemplateLinearizer(concordMapper, alternator);
        String finalText = linearizer.linearize(template, SV);

        assertEquals("zinja zimbi", finalText);
    }

    @Test
    public void testLongAdjectival() {
        Template template = TemplateReader.parseTemplate("ladjectTemplate", URIS.Test_BASE,"res/xh/ladjectivaltemplate.ttl");

        //linearization
        ConcordMapper concordMapper = null;
        MorphophonoAlternator alternator = null;
        MMNguniTemplateLinearizer linearizer = null;


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

        linearizer = new MMNguniTemplateLinearizer(concordMapper, alternator);
        String finalText = linearizer.linearize(template, SV);

        assertEquals("izinja ezindala", finalText);
    }

    @Test
    public void testShortAdjectival() {
        Template template = TemplateReader.parseTemplate("sadjectTemplate", URIS.Test_BASE,"res/xh/sadjectivaltemplate.ttl");

        //linearization
        ConcordMapper concordMapper = null;
        MorphophonoAlternator alternator = null;
        MMNguniTemplateLinearizer linearizer = null;


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

        linearizer = new MMNguniTemplateLinearizer(concordMapper, alternator);
        String finalText = linearizer.linearize(template, SV);

        assertEquals("zinja zindala", finalText);
    }

    @Test
    public void testRelativeConcord () {
        Template template = TemplateReader.parseTemplate("relativeTemplate", URIS.Test_BASE,"res/xh/relativetemplate.ttl");

        //linearization
        ConcordMapper concordMapper = null;
        MorphophonoAlternator alternator = null;
        MMNguniTemplateLinearizer linearizer = null;


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

        linearizer = new MMNguniTemplateLinearizer(concordMapper, alternator);
        String finalText = linearizer.linearize(template, SV);

        assertEquals("izinja ezihambileyo", finalText);
    }

    @Test
    public void testEnumerative () {
        Template template = TemplateReader.parseTemplate("enumerativeTempl", URIS.Test_BASE,"res/xh/enumerativetemplate.ttl");

        //linearization
        ConcordMapper concordMapper = null;
        MorphophonoAlternator alternator = null;
        MMNguniTemplateLinearizer linearizer = null;


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

        linearizer = new MMNguniTemplateLinearizer(concordMapper, alternator);
        String finalText = linearizer.linearize(template, SV);

        assertEquals("mnye umntu", finalText);
    }
}
