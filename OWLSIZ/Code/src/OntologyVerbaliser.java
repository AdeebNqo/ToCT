import org.semanticweb.owlapi.apibinding.OWLManager;
import research.error.NounResolutionException;
import research.error.UnsupportedAxiomException;
import research.nguni.MMNguniTemplateLinearizer;
import research.nguni.zu.*;
import research.interfaces.*;
import research.models.feature.Feature;
import research.models.feature.NounClass;
import research.models.template.*;
import org.semanticweb.owlapi.model.*;
import research.parser.TTLSlotValues;
import research.parser.TemplateReader;
import research.parser.URIS;
import uk.ac.manchester.cs.owl.owlapi.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class OntologyVerbaliser {

    private static ConcordMapper concordMapper = null;
    private static MorphophonoAlternator alternator = null;
    private static CopulaMapper copulaMapper = null;
    private static NounClassResolver nounClassResolver = null;
    private static ZuluUtils linguisticUtility = new ZuluUtils();
    private static LocativeMapper locativeMapper = null;
    private static TemplateLinearizer linearizer;
    private static SurfaceRealiser surfaceRealiser;

    public static void main(String[] args) {

        try {
            String ontologyLoc = "???"; //TODO: add path
            File file = new File(ontologyLoc);

            OWLOntologyManager manager = OWLManager.createOWLOntologyManager();
            OWLOntology zuOntology = manager.loadOntologyFromOntologyDocument(file);

            //language-specific
            nounClassResolver = new ZuluNounClassResolver("???"); //TODO: add path
            linearizer = getIsiZuluTemplateLineariser();
            surfaceRealiser = new SurfaceRealiser(linearizer);

            Stream<OWLLogicalAxiom> axiomStream = zuOntology.logicalAxioms();

            List<String> verbalisations = axiomStream.map(axiom -> {
                try {

                    //structure selection
                    Tuple tup = selectStructure(axiom);

                    //surface realisation
                    String generatedText = surfaceRealiser.generateText(tup.getSlotFillers(), tup.getTemplate());

                    return generatedText;

                } catch (UnsupportedAxiomException e) {
                    return e.getMessage();
                }
            }).collect(Collectors.toList());

            saveToFile("???", verbalisations); //TODO: add path

        } catch (UnsupportedOperationException e) {
            e.printStackTrace();
        } catch (OWLOntologyCreationException e1) {
            e1.printStackTrace();
        }
    }

    public static TemplateLinearizer getIsiZuluTemplateLineariser() {
        concordMapper = new ZuluConcordMapper();
        alternator = new ZuluMorphophonoAlternator();
        copulaMapper = new ZuluCopulaMapper();
        locativeMapper = new ZuluLocativeMapper();
        return new MMNguniTemplateLinearizer(concordMapper, copulaMapper, locativeMapper, alternator);
    }

    public static Template getTemplate(String templateName, String templateURI,  String templatePath) throws UnsupportedOperationException {
        //TODO: find a way to handle features better. There could be different kinds of features. Perhaps need to register different parsers?
        TemplateReader.Init(new ZuluFeatureParser());
        TemplateReader.setTemplateOntologyNamespace(URIS.ToCT_NS);
        Template template = TemplateReader.parseTemplate(templateName, templateURI, templatePath);
        return template;
    }

    public static Tuple selectStructure(OWLLogicalAxiom classAxiom) throws UnsupportedAxiomException {
        String baseFilePath = "???"; //TODO: add path
        String baseURI = "http://people.cs.uct.ac.za/~zmahlaza/templates/owlsiz/";
        Template template = null;

        TTLSlotValues SV = null;

        System.out.println("===");
        System.out.println(classAxiom);

        if (isAxiomFullySupported(classAxiom)) {
            String axiomType = classAxiom.getAxiomType().getName();
            switch (axiomType) {
                case "SubClassOf": {
                    Iterator compIt = classAxiom.components().iterator();
                    Object potentialSubj = compIt.next();

                    Object subjectSrc;
                    Object objectSrc;
                    if (potentialSubj instanceof List) {
                        subjectSrc = ((List) potentialSubj).get(0);
                        objectSrc = ((List) potentialSubj).get(1);
                    } else {
                        subjectSrc = potentialSubj;
                        objectSrc = compIt.next();
                    }

                    if (objectSrc instanceof OWLClassImpl) {
                        template = getTemplate("templ1", baseURI, baseFilePath + "template1.ttl");
                    } else {
                        template = getTemplate("templ1.1", baseURI, baseFilePath + "template1.1.ttl");
                    }


                    try {
                        SlotFiller subject = getSubject(subjectSrc);
                        SlotFiller object = getObject(objectSrc);

                        if (subject != null) {
                            List<String> controlsList = new LinkedList<>();
                            controlsList.add("bo");
                            controlsList.add("c2Sub");
                            ((UnimorphicWord) subject).setControlsConcordResources(controlsList);
                        }

                        HashMap<String, SlotFiller> values = new HashMap<>();
                        values.put("c1", subject);
                        values.put("c2Slot", object);

                        if (subject != null && object != null) {
                            SV = new TTLSlotValues(values);
                        }
                    }
                    catch (NounResolutionException e) {
			//TODO: remove duplication of this catch statement
                        String unResolvedNoun = e.getNoun();
                        String msg = "Axiom not supported as noun class of \""+unResolvedNoun+"\" cannot be resolved";
                        throw new UnsupportedAxiomException(msg);
                    }
                    break;
                }
                case "DisjointClasses": {
                    Iterator compIt = classAxiom.components().iterator();
                    Object potentialSubj = compIt.next();

                    Object subjectSrc;
                    Object objectSrc;
                    if (potentialSubj instanceof List) {
                        subjectSrc = ((List) potentialSubj).get(0);
                        objectSrc = ((List) potentialSubj).get(1);
                    } else {
                        subjectSrc = potentialSubj;
                        objectSrc = compIt.next();
                    }

                    if (objectSrc instanceof OWLClassImpl) {
                        template = getTemplate("templ5", baseURI, baseFilePath + "template5.ttl");
                    } else {
                        template = getTemplate("templ5.1", baseURI, baseFilePath + "template5.1.ttl");
                    }


                    try {
                        SlotFiller subject = getSubject(subjectSrc);
                        SlotFiller object = getObject(objectSrc);

                        if (subject != null) {
                            List<String> controlsList = new LinkedList<>();
                            controlsList.add("subjC");
                            controlsList.add("relC");
                            ((UnimorphicWord) subject).setControlsConcordResources(controlsList);
                        }

                        HashMap<String, SlotFiller> values = new HashMap<>();
                        values.put("c1", subject);
                        values.put("c2", object);

                        if (subject != null && object != null) {
                            SV = new TTLSlotValues(values);
                        }
                    }
                    catch (NounResolutionException e) {
                        String unResolvedNoun = e.getNoun();
                        String msg = "Axiom not supported as noun class of \""+unResolvedNoun+"\" cannot be resolved";
                        throw new UnsupportedAxiomException(msg);
                    }
                    break;
                }
                case "ClassAssertion": {
                    Iterator compIt = classAxiom.components().iterator();
                    Object potentialSubj = compIt.next();

                    Object subjectSrc;
                    Object objectSrc;
                    if (potentialSubj instanceof List) {
                        subjectSrc = ((List) potentialSubj).get(0);
                        objectSrc = ((List) potentialSubj).get(1);
                    } else {
                        subjectSrc = potentialSubj;
                        objectSrc = compIt.next();
                    }

                    template = getTemplate("templ2", baseURI, baseFilePath + "template2.ttl");

                    try {
                        SlotFiller subject = getSubject(subjectSrc);
                        SlotFiller object = getObject(objectSrc);

                        if (subject != null) {
                            List<String> controlsList = new LinkedList<>();
                            controlsList.add("subjC");
                            ((UnimorphicWord) subject).setControlsConcordResources(controlsList);
                        }

                        HashMap<String, SlotFiller> values = new HashMap<>();
                        values.put("i", subject);
                        values.put("c2", object);

                        if (subject != null && object != null) {
                            SV = new TTLSlotValues(values);
                        }
                    }
                    catch (NounResolutionException e) {
                        String unResolvedNoun = e.getNoun();
                        String msg = "Axiom not supported as noun class of \""+unResolvedNoun+"\" cannot be resolved";
                        throw new UnsupportedAxiomException(msg);
                    }
                    break;
                }
                case "ObjectPropertyAssertion": {
                    Iterator compIt = classAxiom.components().iterator();
                    Object potentialSubj = compIt.next();

                    Object subjectSrc;
                    Object predicateSrc;
                    Object objectSrc;
                    if (potentialSubj instanceof List) {
                        subjectSrc = ((List) potentialSubj).get(0);
                        predicateSrc = ((List) potentialSubj).get(1);
                        objectSrc = ((List) potentialSubj).get(2);
                    } else {
                        subjectSrc = potentialSubj;
                        predicateSrc = compIt.next();
                        objectSrc = compIt.next();
                    }

                    template = getTemplate("templ3", baseURI, baseFilePath + "template3.ttl");

                    try {

                        SlotFiller subject = getSubject(subjectSrc);
                        SlotFiller predicate = getPredicate(predicateSrc);
                        SlotFiller object = getObject(objectSrc);

                        HashMap<String, SlotFiller> values = new HashMap<>();
                        values.put("i1", subject);
                        values.put("op", predicate);
                        values.put("i2", object);

                        if (subject != null) {
                            List<String> controlsList = new LinkedList<>();
                            controlsList.add("subjC");
                            ((UnimorphicWord) subject).setControlsConcordResources(controlsList);
                        }

                        if (subject != null && predicate != null && object != null) {
                            SV = new TTLSlotValues(values);
                        }
                    }
                    catch (NounResolutionException e) {
                        String unResolvedNoun = e.getNoun();
                        String msg = "Axiom not supported as noun class of \""+unResolvedNoun+"\" cannot be resolved";
                        throw new UnsupportedAxiomException(msg);
                    }
                    break;
                }
                case "EquivalentClasses": {
                    Iterator compIt = classAxiom.components().iterator();
                    Object potentialSubj = compIt.next();

                    Object subjectSrc;
                    Object objectSrc;
                    if (potentialSubj instanceof List) {
                        subjectSrc = ((List) potentialSubj).get(0);
                        objectSrc = ((List) potentialSubj).get(1);
                    } else {
                        subjectSrc = potentialSubj;
                        objectSrc = compIt.next();
                    }

                    template = getTemplate("templ4", baseURI, baseFilePath + "template4.ttl");

                    try {
                        SlotFiller subject = getSubject(subjectSrc);
                        SlotFiller object = getObject(objectSrc);

                        HashMap<String, SlotFiller> values = new HashMap<>();
                        values.put("c2", subject);
                        values.put("c1", object);

                        if (subject != null) {
                            List<String> controlsList = new LinkedList<>();
                            controlsList.add("relC");
                            controlsList.add("objC");
                            ((UnimorphicWord) subject).setControlsConcordResources(controlsList);
                        }

                        if (subject != null && object != null) {
                            SV = new TTLSlotValues(values);
                        }
                    }
                    catch (NounResolutionException e) {
                        String unResolvedNoun = e.getNoun();
                        String msg = "Axiom not supported as noun class of \""+unResolvedNoun+"\" cannot be resolved";
                        throw new UnsupportedAxiomException(msg);
                    }
                    break;
                }
                case "DataPropertyAssertion": {
                    Iterator compIt = classAxiom.components().iterator();
                    Object potentialSubj = compIt.next();

                    Object datapropSrc;
                    Object indivSrc;
                    Object litSrc;

                    if (potentialSubj instanceof List) {
                        indivSrc = ((List) potentialSubj).get(0);
                        datapropSrc = ((List) potentialSubj).get(1);
                        litSrc = ((List) potentialSubj).get(2);
                    } else {
                        indivSrc = potentialSubj;
                        datapropSrc = compIt.next();
                        litSrc = compIt.next();
                    }

                    template = getTemplate("templ8", baseURI, baseFilePath + "template8.ttl");

                    try {
                        SlotFiller dataprop = getPredicate(datapropSrc);
                        SlotFiller indiv = getSubject(indivSrc);
                        SlotFiller lit = getObject(litSrc);

                        HashMap<String, SlotFiller> values = new HashMap<>();
                        values.put("i", indiv);
                        values.put("dp", dataprop);
                        values.put("l", lit);

                        if (indiv != null && dataprop != null && lit != null) {
                            SV = new TTLSlotValues(values);
                        }
                    }
                    catch (NounResolutionException e) {
                        String unResolvedNoun = e.getNoun();
                        String msg = "Axiom not supported as noun class of \""+unResolvedNoun+"\" cannot be resolved";
                        throw new UnsupportedAxiomException(msg);
                    }
                    break;
                }
            }

            return new Tuple(template, SV);
        }
        else {
            throw new UnsupportedAxiomException("Axiom not supported by CNL.");
        }
    }

    public static String selectStructure(OWLClassExpression classExpression) throws UnsupportedAxiomException {
        String baseFilePath = "/home/zola/Documents/phd/work/tasks/OWLSIZ/cnl/";
        String baseURI = "http://people.cs.uct.ac.za/~zmahlaza/templates/owlsiz/";
        Template template = null;
        TTLSlotValues SV = null;

        switch(classExpression.getClassExpressionType()) {
            case OBJECT_MIN_CARDINALITY: {
                Iterator  compIt = classExpression.components().iterator();
                Object potentialSubj = compIt.next();

                try {
                    SlotFiller lit;
                    SlotFiller op;
                    SlotFiller object;
                    if (potentialSubj instanceof List) {
                        op = getPredicate(((List) potentialSubj).get(0));
                        lit = getSubject(((List) potentialSubj).get(1));
                        object = getObject(((List) potentialSubj).get(2));
                    } else {
                        op = getPredicate(potentialSubj);
                        lit = getSubject(compIt.next());
                        object = getObject(compIt.next());
                    }
                    template = getTemplate("templ12", baseURI, baseFilePath + "template12.ttl");


                    HashMap<String, SlotFiller> values = new HashMap<>();
                    values.put("op", op);
                    values.put("nslot", lit);
                    values.put("c", object);

                    if (object != null) {
                        List<String> controlsList = new LinkedList<>();
                        controlsList.add("relC");
                        ((UnimorphicWord) object).setControlsConcordResources(controlsList);
                    }

                    SV = new TTLSlotValues(values);
                }
                catch (NounResolutionException e) {
                    String unResolvedNoun = e.getNoun();
                    String msg = "Axiom not supported as noun class of \""+unResolvedNoun+"\" cannot be resolved";
                    throw new UnsupportedAxiomException(msg);
                }
                break;
            }
            case OBJECT_MAX_CARDINALITY: {
                Iterator  compIt = classExpression.components().iterator();
                Object potentialSubj = compIt.next();

                try {
                    SlotFiller lit;
                    SlotFiller op;
                    SlotFiller object;
                    if (potentialSubj instanceof List) {
                        op = getPredicate(((List) potentialSubj).get(0));
                        lit = getSubject(((List) potentialSubj).get(1));
                        object = getObject(((List) potentialSubj).get(2));
                    } else {
                        op = getPredicate(potentialSubj);
                        lit = getSubject(compIt.next());
                        object = getObject(compIt.next());
                    }
                    template = getTemplate("templ13", baseURI, baseFilePath + "template13.ttl");


                    HashMap<String, SlotFiller> values = new HashMap<>();
                    values.put("op", op);
                    values.put("nslot", lit);
                    values.put("c", object);

                    if (object != null) {
                        List<String> controlsList = new LinkedList<>();
                        controlsList.add("relC");
                        ((UnimorphicWord) object).setControlsConcordResources(controlsList);
                    }

                    SV = new TTLSlotValues(values);
                }
                catch (NounResolutionException e) {
                    String unResolvedNoun = e.getNoun();
                    String msg = "Axiom not supported as noun class of \""+unResolvedNoun+"\" cannot be resolved";
                    throw new UnsupportedAxiomException(msg);
                }
                break;
            }
            case OBJECT_EXACT_CARDINALITY: {
                Iterator  compIt = classExpression.components().iterator();
                Object potentialSubj = compIt.next();
                try {
                    SlotFiller lit;
                    SlotFiller op;
                    SlotFiller object;
                    if (potentialSubj instanceof List) {
                        op = getPredicate(((List) potentialSubj).get(0));
                        lit = getSubject(((List) potentialSubj).get(1));
                        object = getObject(((List) potentialSubj).get(2));
                    } else {
                        op = getPredicate(potentialSubj);
                        lit = getSubject(compIt.next());
                        object = getObject(compIt.next());
                    }
                    template = getTemplate("templ11.1", baseURI, baseFilePath + "template11.1.ttl");


                    HashMap<String, SlotFiller> values = new HashMap<>();
                    values.put("op", op);
                    values.put("n", lit);
                    values.put("c", object);

                    if (object != null) {
                        List<String> controlsList = new LinkedList<>();
                        controlsList.add("relC");
                        ((UnimorphicWord) object).setControlsConcordResources(controlsList);
                    }

                    SV = new TTLSlotValues(values);
                }
                catch (NounResolutionException e) {
                    String unResolvedNoun = e.getNoun();
                    String msg = "Axiom not supported as noun class of \""+unResolvedNoun+"\" cannot be resolved";
                    throw new UnsupportedAxiomException(msg);
                }
                break;
            }
            case OBJECT_SOME_VALUES_FROM : {
                Iterator  compIt = classExpression.components().iterator();
                Object potentialSubj = compIt.next();

                try {
                    SlotFiller subject;
                    SlotFiller object;
                    if (potentialSubj instanceof List) {
                        subject = getSubject(((List) potentialSubj).get(0));
                        object = getObject(((List) potentialSubj).get(1));
                    } else {
                        //subject
                        subject = getSubject(potentialSubj);
                        //object
                        object = getObject(compIt.next());
                    }

                    String subjValue = subject.getValue();
                    if (linguisticUtility.isDeepPreposition(subjValue)) {
                        //If the OP is a deep preposition
                        //Note: the following values for these "deep prepositions" is determined by looking at Keet et al.'s python verbaliser (zulurulespw.py)
                        if (subjValue.equals("ffff")) {
                            subject = new UnimorphicWord("s");
                            template = getTemplate("templ6.3", baseURI, baseFilePath + "template6.3.ttl");
                        } else {
                            subject = new UnimorphicWord("na");
                            template = getTemplate("templ6.1", baseURI, baseFilePath + "template6.1.ttl");
                        }
                    }
                    else if (linguisticUtility.isNoun(subjValue)) {
                        //handle when OP is a noun
                        boolean hasNounClass = false;

                        List<Feature> objFeatures = object.getFeatures();
                        if (objFeatures != null) {
                            for (Feature ft : objFeatures) {
                                if (ft instanceof NounClass) {
                                    String nc = ((NounClass) ft).getNounClass();
                                    if (nc.equals("1") || nc.equals("2") || nc.equals("1a") || nc.equals("2a")) {
                                        template = getTemplate("templ6.4", baseURI, baseFilePath + "template6.4.ttl");
                                        hasNounClass = true;
                                    }
                                    break;
                                }
                            }
                        }
                        if (!hasNounClass) {
                            template = getTemplate("templ6.2", baseURI, baseFilePath + "template6.2.ttl");
                        }
                    }
                    else {
                        template = getTemplate("templ6", baseURI, baseFilePath + "template6.ttl");
                    }

                    HashMap<String, SlotFiller> values = new HashMap<>();
                    values.put("op", subject);
                    values.put("c", object);

                    SV = new TTLSlotValues(values);
                }
                catch (NounResolutionException e) {
                    String unResolvedNoun = e.getNoun();
                    String msg = "Axiom not supported as noun class of \""+unResolvedNoun+"\" cannot be resolved";
                    throw new UnsupportedAxiomException(msg);
                }
                break;
            }
            case OBJECT_ALL_VALUES_FROM: {
                Iterator  compIt = classExpression.components().iterator();
                Object potentialSubj = compIt.next();

                try {
                    SlotFiller subject;
                    SlotFiller object;
                    if (potentialSubj instanceof List) {
                        subject = getSubject(((List) potentialSubj).get(0));
                        object = getObject(((List) potentialSubj).get(1));
                    } else {
                        //subject
                        subject = getSubject(potentialSubj);
                        //object
                        object = getObject(compIt.next());
                    }
                    template = getTemplate("templ10", baseURI, baseFilePath + "template10.ttl");


                    HashMap<String, SlotFiller> values = new HashMap<>();
                    values.put("op", subject);
                    values.put("c", object);

                    if (object != null) {
                        List<String> controlsList = new LinkedList<>();
                        controlsList.add("subjC");
                        ((UnimorphicWord) object).setControlsConcordResources(controlsList);
                    }

                    SV = new TTLSlotValues(values);
                }
                catch (NounResolutionException e) {
                    String unResolvedNoun = e.getNoun();
                    String msg = "Axiom not supported as noun class of \""+unResolvedNoun+"\" cannot be resolved";
                    throw new UnsupportedAxiomException(msg);
                }
                break;
            }
            case OBJECT_HAS_VALUE : {
                //TODO: add this back
                break;
            }
            case DATA_HAS_VALUE: {
                //TODO: add this back
                break;
            }
        }

        String finalText = surfaceRealiser.generateText(SV, template);
        return finalText;
    }

    public static SlotFiller getSubject(Object subj) throws UnsupportedAxiomException, NounResolutionException {
        if (subj instanceof OWLClassImpl) {
            OWLClassImpl dom = (OWLClassImpl) subj;
            UnimorphicWord domain = getNounSlotFiller(dom.getIRI());
            return domain;
        }
        else if (subj instanceof OWLNamedIndividualImpl) {
            OWLNamedIndividualImpl dom = (OWLNamedIndividualImpl) subj;
            UnimorphicWord domain = getNounSlotFiller(dom.getIRI());
            return domain;
        }
        else if (subj instanceof OWLObjectPropertyImpl) {
            OWLObjectPropertyImpl dom = (OWLObjectPropertyImpl) subj;
            UnimorphicWord domain = getVerbSlotFiller(dom.getIRI());
            return domain;
        }
        else if (subj instanceof OWLClassExpression) {
            Phrase range = new Phrase(selectStructure((OWLClassExpression) subj));
            return range;
        }
        else if (subj instanceof Integer) {
            Integer integer = (Integer) subj;
            return new UnimorphicWord(integer.toString());
        }
        return null;
    }

    public static SlotFiller getPredicate(Object subj) throws UnsupportedAxiomException, NounResolutionException {
        if (subj instanceof OWLClassImpl) {
            OWLClassImpl dom = (OWLClassImpl) subj;
            UnimorphicWord domain = getNounSlotFiller(dom.getIRI());
            return domain;
        }
        else if (subj instanceof OWLObjectPropertyImpl) {
            OWLObjectPropertyImpl dom = (OWLObjectPropertyImpl) subj;
            UnimorphicWord domain = getVerbSlotFiller(dom.getIRI());
            return domain;
        }
        else if (subj instanceof OWLClassExpression) {
            Phrase range = new Phrase(selectStructure((OWLClassExpression) subj));
            return range;
        }
        else if (subj instanceof OWLDataPropertyImpl) {
            OWLDataPropertyImpl pred = (OWLDataPropertyImpl) subj;
            UnimorphicWord domain = getVerbSlotFiller(pred.getIRI());
            return domain;
        }
        return null;
    }

    public static SlotFiller getObject(Object obj) throws NounResolutionException, UnsupportedAxiomException {
        if (obj instanceof OWLClassImpl) {
            OWLClassImpl ran = (OWLClassImpl) obj;
            UnimorphicWord range = getNounSlotFiller(ran.getIRI());
            return range;
        }
        else if (obj instanceof OWLNamedIndividualImpl) {
            OWLNamedIndividualImpl ran = (OWLNamedIndividualImpl) obj;
            UnimorphicWord range = getNounSlotFiller(ran.getIRI());
            return range;
        }
        else if (obj instanceof OWLObjectPropertyImpl) {
            OWLObjectPropertyImpl dom = (OWLObjectPropertyImpl) obj;
            UnimorphicWord domain = getVerbSlotFiller(dom.getIRI());
            return domain;
        }
        else if (obj instanceof OWLLiteralImplNoCompression) {
            OWLLiteralImplNoCompression dom = (OWLLiteralImplNoCompression) obj;
            return new UnimorphicWord(dom.getLiteral());
        }
        else if (obj instanceof OWLClassExpression) {
            Phrase range = new Phrase(selectStructure((OWLClassExpression) obj));
            return range;
        }
        return null;
    }

    public static UnimorphicWord getNounSlotFiller(IRI wordURI) throws NounResolutionException {
        UnimorphicWord word;

        String name = wordURI.toString().replace(wordURI.getNamespace(), "");
        name = String.join(" ", name.split("_"));
        if (nounClassResolver.hasNoun(name)) {
            List<Feature> features = new LinkedList<>();
            NounClass nounClass = NounClass.getNounClass(nounClassResolver.getNounClass(name));
            features.add(nounClass);
            word = new UnimorphicWord(name);
            word.setFeatures(features);
        } else {
            //System.err.println("WARN. Cannot resolve noun class of word = "+name);
            throw new NounResolutionException(name);
        }
        return word;
    }

    public static UnimorphicWord getVerbSlotFiller(IRI wordURI) {
        UnimorphicWord word;

        String name = wordURI.toString().replace(wordURI.getNamespace(), "");
        name = String.join(" ", name.split("_"));
        word = new UnimorphicWord(name);

        return word;
    }

    public static void saveToFile(String filename, List<String> verbalisations) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
            writer.write("Question\tResponse(Yes/No)\tComment\n");
            for (String text : verbalisations) {
                writer.write(text+"\t\t\n");
            }
            writer.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean isAxiomFullySupported(OWLLogicalAxiom axiom) {
        //collecting the axiom type and class expressions used in axiom
        Set<String> axiomTypes = axiom.components().map(axiomComp -> {
            String axiomType = "";
            if (axiomComp instanceof OWLClassExpressionImpl) {
                axiomType = ((OWLClassExpressionImpl) axiomComp).getClassExpressionType().toString();
            }
            return axiomType;
        }).collect(Collectors.toSet());
        axiomTypes.add(axiom.getAxiomType().toString());


        //Collecting OWLSIZ axiom types
        List<String> supportedAxioms = new LinkedList<>();
        supportedAxioms.add("SubClassOf");
        supportedAxioms.add("ClassAssertion");
        supportedAxioms.add("ObjectPropertyAssertion");
        supportedAxioms.add("EquivalentClasses");
        supportedAxioms.add("DisjointClasses");
        supportedAxioms.add("ObjectSomeValuesFrom");
        supportedAxioms.add("ObjectHasValue");
        supportedAxioms.add("DataPropertyAssertion");
        supportedAxioms.add("DataHasValue");
        supportedAxioms.add("ObjectAllValuesFrom");
        supportedAxioms.add("ObjectExactCardinality");
        supportedAxioms.add("ObjectMinCardinality");
        supportedAxioms.add("ObjectMaxCardinality");
        supportedAxioms.add("Class");

        //checking if the axiom has an unsuported axiom type
        boolean status = true;
        for (String axiomType : axiomTypes) {
            if (!supportedAxioms.contains(axiomType) && !axiomType.isEmpty()) {
                //System.err.println("WARN. Unsupported axiom =  "+axiom+", due to inclusion of "+axiomType);
                status = false;
                break;
            }
        }
        return status;
    }
}
