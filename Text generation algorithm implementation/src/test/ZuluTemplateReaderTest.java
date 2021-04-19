package test;

import main.research.impl.nguni.MMNguniTemplateLinearizer;
import main.research.impl.nguni.xh.XhosaConcordMapper;
import main.research.impl.nguni.xh.XhosaMorphophonoAlternator;
import main.research.impl.nguni.zu.ZuFeatureParser;
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

public class ZuluTemplateReaderTest {

    private SlotValues SV;

    @Before
    public void setUp () {
        NounClass nounClass = NounClass.getNounClass("4");
        List<Feature> features = new LinkedList<>();
        features.add(nounClass);
        UnimorphicWord word = new UnimorphicWord("imifula");
        List<String> controlsList = new LinkedList<>();
        controlsList.add("LAdjC");
        controlsList.add("of");
        controlsList.add("quantConcord");
        controlsList.add("subj");
        controlsList.add("RelC");
        word.setControlsConcordResources(controlsList);
        word.setFeatures(features);

        NounClass nounClassTwo = NounClass.getNounClass("7");
        List<Feature> features2 = new LinkedList<>();
        features2.add(nounClassTwo);
        UnimorphicWord word2 = new UnimorphicWord("isithombe");
        List<String> controls2List = new LinkedList<>();
        controls2List.add("enumConc");
        controls2List.add("obj");
        word2.setControlsConcordResources(controls2List);
        word2.setFeatures(features2);

        NounClass nounClassThree = NounClass.getNounClass("4");
        List<Feature> features3 = new LinkedList<>();
        features3.add(nounClassThree);
        UnimorphicWord word3 = new UnimorphicWord("mifula");
        word3.setFeatures(features3);

        HashMap<String, SlotFiller>

        values = new HashMap<>();
        values.put("noun1Slot", word);
        values.put("noun2Slot", word2);
        values.put("noun3Slot", word3);

        SV = new SlotValues(values);

        TemplateReader.Init(new ZuFeatureParser());
    }

    @Test
    public void testPossessiveConcord() {
        Template template = TemplateReader.parseTemplate("possessiveNounTemp", URIS.Test_BASE,"res/zu/postemplate.ttl");

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

        assertEquals("imifula yamadoda", finalText);
    }

    @Test
    public void testSubjectConcord () {
        Template template = TemplateReader.parseTemplate("plainOPPresentTense", URIS.Test_BASE,"res/zu/subjectivaltemplate.ttl");

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
        assertEquals("yonke imifula", finalText);
    }

    @Test
    public void testSubjectivalAndObjectivalConcord () {
        Template template = TemplateReader.parseTemplate("verbObjSubjTemplate", URIS.Test_BASE,"res/zu/objectivaltemplate.ttl");

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

        assertEquals("imifula iyasihambisa isithombe", finalText);
    }

    @Test
    public void testAdjectival() {
        Template template = TemplateReader.parseTemplate("adjectTemplate", URIS.Test_BASE,"res/zu/adjectivaltemplate.ttl");

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

        assertEquals("imifula emidala", finalText);
    }


    @Test
    public void testRelativeConcord () {
        Template template = TemplateReader.parseTemplate("relativeTemplate", URIS.Test_BASE,"res/zu/relativetemplate.ttl");

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

        assertEquals("imifula ehambileyo", finalText);
    }

    @Test
    public void testEnumerativeConcord () {
        Template template = TemplateReader.parseTemplate("enumerativeTempl", URIS.Test_BASE,"res/zu/enumerativetemplate.ttl");

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

        assertEquals("sinye isithombe", finalText);
    }
}