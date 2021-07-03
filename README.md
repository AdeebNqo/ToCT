# Task ontology for CNL-based Templates

## Introduction

Natural language interfaces are a well-known approach to grant non-experts access to semantic web technologies. A number of such systems use simple templates to achieve that for English and more elaborate solutions for other languages. They keep being designed from scratch in an ad hoc manner, since there is no shared conceptualisation of simple templates and there is no model that is formalised using a Semantic Web language to apply the techniques to itself. ToCT is a general-purpose solution to this problem. It is a novel conceptual model formalised as a task ontology in OWL, of templates. It can be used with an accompanying text generation algorithm to produce well-formed text.

## Overview

The conceptual model for the task ontology for template specifications is as follows:

![Conceptual model](/ToCT/ConceptualModel.png)

This was subsequently formalised into OWL as a task ontology. This ontology was used in two evaluation: one for ontology verbalisation in the form of validation quesions in isiZulu (Tbox only) and one for data-to-text generation of weather forcast messages in isiXhosa.

## Example of OWLSIZ' templates in action

- Input: SubClassOf(<http://www.semanticweb.org/mariakeet/ontologies/2016/10/untitled-ontology-315#ihebhu> <http://www.semanticweb.org/mariakeet/ontologies/2016/10/untitled-ontology-315#umuthi>)
- Chosen template: OWLSIZ/Templates/template1.ttl (i.e., Ingabe [subjC]onke [C1] [subjC][COP][C2]?)
- Output: Ingabe lonke ihebhu lingumuthi?


## People


* Zola Mahlaza
* Maria Keet

## Licence

CC BY 4.0 (https://creativecommons.org/licenses/by/4.0/)

## Citing ToCT

Mahlaza, Z., Keet, C.M. ToCT: A task ontology to manage complex templates. Online (submitted to an international conference)

[![DOI](https://zenodo.org/badge/359607259.svg)](https://zenodo.org/badge/latestdoi/359607259)

## Funding

This work was financially supported by Hasso Plattner Institute for Digital Engineering through the HPI Research School at UCT and the National Research Foundation (NRF) of South Africa (Grant Number 120852).
