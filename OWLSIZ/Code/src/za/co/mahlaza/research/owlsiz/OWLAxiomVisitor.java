package za.co.mahlaza.research.owlsiz;

import org.semanticweb.owlapi.model.*;
import uk.ac.manchester.cs.owl.owlapi.OWLClassImpl;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class OWLAxiomVisitor implements OWLObjectVisitor {

    List<Object> components = new ArrayList<>();

    public List<Object> getComponents() {
        return components;
    }

    @Override
    public void visit(@Nonnull OWLAnnotation owlAnnotation) {

    }

    @Override
    public void visit(@Nonnull OWLDeclarationAxiom owlDeclarationAxiom) {

    }

    @Override
    public void visit(@Nonnull OWLDatatypeDefinitionAxiom owlDatatypeDefinitionAxiom) {

    }

    @Override
    public void visit(@Nonnull OWLAnnotationAssertionAxiom owlAnnotationAssertionAxiom) {

    }

    @Override
    public void visit(@Nonnull OWLSubAnnotationPropertyOfAxiom owlSubAnnotationPropertyOfAxiom) {

    }

    @Override
    public void visit(@Nonnull OWLAnnotationPropertyDomainAxiom owlAnnotationPropertyDomainAxiom) {

    }

    @Override
    public void visit(@Nonnull OWLAnnotationPropertyRangeAxiom owlAnnotationPropertyRangeAxiom) {

    }

    @Override
    public void visit(@Nonnull IRI iri) {

    }

    @Override
    public void visit(@Nonnull OWLClass owlClass) {

    }

    @Override
    public void visit(@Nonnull OWLObjectIntersectionOf owlObjectIntersectionOf) {

    }

    @Override
    public void visit(@Nonnull OWLObjectUnionOf owlObjectUnionOf) {

    }

    @Override
    public void visit(@Nonnull OWLObjectComplementOf owlObjectComplementOf) {
        //components.add(owlObjectComplementOf.getObjectComplementOf());
        components.add(owlObjectComplementOf.getOperand());
    }

    @Override
    public void visit(@Nonnull OWLObjectSomeValuesFrom owlObjectSomeValuesFrom) {
        components.add(owlObjectSomeValuesFrom.getProperty());
        components.add(owlObjectSomeValuesFrom.getFiller());
    }

    @Override
    public void visit(@Nonnull OWLObjectAllValuesFrom owlObjectAllValuesFrom) {
        components.add(owlObjectAllValuesFrom.getProperty());
        components.add(owlObjectAllValuesFrom.getFiller());
    }

    @Override
    public void visit(@Nonnull OWLObjectHasValue owlObjectHasValue) {

    }

    @Override
    public void visit(@Nonnull OWLObjectMinCardinality owlObjectMinCardinality) {
        components.add(owlObjectMinCardinality.getProperty());
        components.add(owlObjectMinCardinality.getCardinality());
        components.add(owlObjectMinCardinality.getFiller());
    }

    @Override
    public void visit(@Nonnull OWLObjectExactCardinality owlObjectExactCardinality) {
        components.add(owlObjectExactCardinality.getProperty());
        components.add(owlObjectExactCardinality.getCardinality());
        components.add(owlObjectExactCardinality.getFiller());
    }

    @Override
    public void visit(@Nonnull OWLObjectMaxCardinality owlObjectMaxCardinality) {
        components.add(owlObjectMaxCardinality.getProperty());
        components.add(owlObjectMaxCardinality.getCardinality());
        components.add(owlObjectMaxCardinality.getFiller());
    }

    @Override
    public void visit(@Nonnull OWLObjectHasSelf owlObjectHasSelf) {

    }

    @Override
    public void visit(@Nonnull OWLObjectOneOf owlObjectOneOf) {

    }

    @Override
    public void visit(@Nonnull OWLDataSomeValuesFrom owlDataSomeValuesFrom) {

    }

    @Override
    public void visit(@Nonnull OWLDataAllValuesFrom owlDataAllValuesFrom) {

    }

    @Override
    public void visit(@Nonnull OWLDataHasValue owlDataHasValue) {

    }

    @Override
    public void visit(@Nonnull OWLDataMinCardinality owlDataMinCardinality) {

    }

    @Override
    public void visit(@Nonnull OWLDataExactCardinality owlDataExactCardinality) {

    }

    @Override
    public void visit(@Nonnull OWLDataMaxCardinality owlDataMaxCardinality) {

    }

    @Override
    public void visit(@Nonnull OWLLiteral owlLiteral) {

    }

    @Override
    public void visit(@Nonnull OWLFacetRestriction owlFacetRestriction) {

    }

    @Override
    public void visit(@Nonnull OWLDatatype owlDatatype) {

    }

    @Override
    public void visit(@Nonnull OWLDataOneOf owlDataOneOf) {

    }

    @Override
    public void visit(@Nonnull OWLDataComplementOf owlDataComplementOf) {

    }

    @Override
    public void visit(@Nonnull OWLDataIntersectionOf owlDataIntersectionOf) {

    }

    @Override
    public void visit(@Nonnull OWLDataUnionOf owlDataUnionOf) {

    }


    @Override
    public void visit(@Nonnull OWLDatatypeRestriction owlDatatypeRestriction) {

    }

    @Override
    public void visit(@Nonnull OWLNamedIndividual owlNamedIndividual) {

    }

    @Override
    public void visit(@Nonnull OWLAnonymousIndividual owlAnonymousIndividual) {

    }

    @Override
    public void visit(@Nonnull OWLSubClassOfAxiom owlSubClassOfAxiom) {
        Set<OWLEntity> sig = owlSubClassOfAxiom.getSignature();
        if (sig.size() > 2) {
            components.add(owlSubClassOfAxiom.getSubClass());
            components.add(owlSubClassOfAxiom.getSuperClass());
        } else {
            Iterator<OWLEntity> it = sig.iterator();
            OWLEntity subj = it.next();
            OWLEntity obj = it.next();
            components.add(subj);
            components.add(obj);
        }
    }

    @Override
    public void visit(@Nonnull OWLNegativeObjectPropertyAssertionAxiom owlNegativeObjectPropertyAssertionAxiom) {

    }

    @Override
    public void visit(@Nonnull OWLAsymmetricObjectPropertyAxiom owlAsymmetricObjectPropertyAxiom) {

    }

    @Override
    public void visit(@Nonnull OWLReflexiveObjectPropertyAxiom owlReflexiveObjectPropertyAxiom) {

    }

    @Override
    public void visit(@Nonnull OWLDisjointClassesAxiom owlDisjointClassesAxiom) {
        Iterator<OWLClassExpression> classExpressions = owlDisjointClassesAxiom.getClassExpressions().iterator();
        components.add(classExpressions.next());
        components.add(classExpressions.next());
    }

    @Override
    public void visit(@Nonnull OWLDataPropertyDomainAxiom owlDataPropertyDomainAxiom) {

    }

    @Override
    public void visit(@Nonnull OWLObjectPropertyDomainAxiom owlObjectPropertyDomainAxiom) {

    }

    @Override
    public void visit(@Nonnull OWLEquivalentObjectPropertiesAxiom owlEquivalentObjectPropertiesAxiom) {

    }

    @Override
    public void visit(@Nonnull OWLNegativeDataPropertyAssertionAxiom owlNegativeDataPropertyAssertionAxiom) {

    }

    @Override
    public void visit(@Nonnull OWLDifferentIndividualsAxiom owlDifferentIndividualsAxiom) {

    }

    @Override
    public void visit(@Nonnull OWLDisjointDataPropertiesAxiom owlDisjointDataPropertiesAxiom) {

    }

    @Override
    public void visit(@Nonnull OWLDisjointObjectPropertiesAxiom owlDisjointObjectPropertiesAxiom) {

    }

    @Override
    public void visit(@Nonnull OWLObjectPropertyRangeAxiom owlObjectPropertyRangeAxiom) {

    }

    @Override
    public void visit(@Nonnull OWLObjectPropertyAssertionAxiom owlObjectPropertyAssertionAxiom) {
        components.add(owlObjectPropertyAssertionAxiom.getSubject());
        components.add(owlObjectPropertyAssertionAxiom.getProperty());
        components.add(owlObjectPropertyAssertionAxiom.getObject());
    }

    @Override
    public void visit(@Nonnull OWLFunctionalObjectPropertyAxiom owlFunctionalObjectPropertyAxiom) {

    }

    @Override
    public void visit(@Nonnull OWLSubObjectPropertyOfAxiom owlSubObjectPropertyOfAxiom) {

    }

    @Override
    public void visit(@Nonnull OWLDisjointUnionAxiom owlDisjointUnionAxiom) {

    }

    @Override
    public void visit(@Nonnull OWLSymmetricObjectPropertyAxiom owlSymmetricObjectPropertyAxiom) {

    }

    @Override
    public void visit(@Nonnull OWLDataPropertyRangeAxiom owlDataPropertyRangeAxiom) {

    }

    @Override
    public void visit(@Nonnull OWLFunctionalDataPropertyAxiom owlFunctionalDataPropertyAxiom) {

    }

    @Override
    public void visit(@Nonnull OWLEquivalentDataPropertiesAxiom owlEquivalentDataPropertiesAxiom) {

    }

    @Override
    public void visit(@Nonnull OWLClassAssertionAxiom owlClassAssertionAxiom) {
        Set<OWLEntity> sig = owlClassAssertionAxiom.getSignature();
        if (sig.size() == 2) {
            components.add(owlClassAssertionAxiom.getIndividual());
            components.add(owlClassAssertionAxiom.getClassExpression());
        }
    }

    @Override
    public void visit(@Nonnull OWLEquivalentClassesAxiom owlEquivalentClassesAxiom) {
        Iterator<OWLClass> classesIt = owlEquivalentClassesAxiom.getNamedClasses().iterator();
        components.add(classesIt.next());
        components.add(classesIt.next());
    }

    @Override
    public void visit(@Nonnull OWLDataPropertyAssertionAxiom owlDataPropertyAssertionAxiom) {
        components.add(owlDataPropertyAssertionAxiom.getSubject());
        components.add(owlDataPropertyAssertionAxiom.getProperty());
        components.add(owlDataPropertyAssertionAxiom.getObject());
    }

    @Override
    public void visit(@Nonnull OWLTransitiveObjectPropertyAxiom owlTransitiveObjectPropertyAxiom) {

    }

    @Override
    public void visit(@Nonnull OWLIrreflexiveObjectPropertyAxiom owlIrreflexiveObjectPropertyAxiom) {

    }

    @Override
    public void visit(@Nonnull OWLSubDataPropertyOfAxiom owlSubDataPropertyOfAxiom) {

    }

    @Override
    public void visit(@Nonnull OWLInverseFunctionalObjectPropertyAxiom owlInverseFunctionalObjectPropertyAxiom) {

    }

    @Override
    public void visit(@Nonnull OWLSameIndividualAxiom owlSameIndividualAxiom) {

    }

    @Override
    public void visit(@Nonnull OWLSubPropertyChainOfAxiom owlSubPropertyChainOfAxiom) {

    }

    @Override
    public void visit(@Nonnull OWLInverseObjectPropertiesAxiom owlInverseObjectPropertiesAxiom) {

    }

    @Override
    public void visit(@Nonnull OWLHasKeyAxiom owlHasKeyAxiom) {

    }

    @Override
    public void visit(@Nonnull SWRLRule swrlRule) {

    }

    @Override
    public void visit(@Nonnull OWLOntology owlOntology) {

    }

    @Override
    public void visit(@Nonnull OWLObjectProperty owlObjectProperty) {

    }

    @Override
    public void visit(@Nonnull OWLObjectInverseOf owlObjectInverseOf) {

    }

    @Override
    public void visit(@Nonnull OWLDataProperty owlDataProperty) {

    }

    @Override
    public void visit(@Nonnull OWLAnnotationProperty owlAnnotationProperty) {

    }

    @Override
    public void visit(@Nonnull SWRLClassAtom swrlClassAtom) {

    }

    @Override
    public void visit(@Nonnull SWRLDataRangeAtom swrlDataRangeAtom) {

    }

    @Override
    public void visit(@Nonnull SWRLObjectPropertyAtom swrlObjectPropertyAtom) {

    }

    @Override
    public void visit(@Nonnull SWRLDataPropertyAtom swrlDataPropertyAtom) {

    }

    @Override
    public void visit(@Nonnull SWRLBuiltInAtom swrlBuiltInAtom) {

    }

    @Override
    public void visit(@Nonnull SWRLVariable swrlVariable) {

    }

    @Override
    public void visit(@Nonnull SWRLIndividualArgument swrlIndividualArgument) {

    }

    @Override
    public void visit(@Nonnull SWRLLiteralArgument swrlLiteralArgument) {

    }

    @Override
    public void visit(@Nonnull SWRLSameIndividualAtom swrlSameIndividualAtom) {

    }

    @Override
    public void visit(@Nonnull SWRLDifferentIndividualsAtom swrlDifferentIndividualsAtom) {

    }
}
