# OWLSIZ

OWL Simplified isiZulu (OWLSIZ) is an isiZulu controlled natural language. It is designed for generating grammatical and fluent questions from an ontology. The purpose is to allow a domain expert to independently validate the contents of an ontology without understanding a ontology authoring language such as OWL.

# What's in here

- Numbered list of all the texts used the OWLSIZ evaluation (**NumberedEvaluatedTexts.tsv**)
- Quick access to examples of verbaliser's input, chosen template for each input, and the generated text output (**VerbaliserInputOutput.txt**)
- Version 1.1 of the Java source code (**Code**)
- Test ontology and list of nouns & their classes (**Other resources**)

# Using the code

- Open using intellij
- Open OWLSIZ/Code/src/OntologyVerbaliser.java and add paths to the required files:
	- Line 37: full path to "Other resources/testOntoisiZuluWithPW"
	- Line 44: full path to "Other resources/nncPairs-fixed.txt"
	- Line 66: full path to output file (e.g., "/tmp/owlsiz-output.csv")
	- Line 92: full path to "OWLSIZ/Templates/"
- Run code

# More details

> Mahlaza, Z., Keet, C.M.: OWLSIZ: An isiZulu CNL for structured knowledge validation. In: Proc. of WebNLG+ 2020. pp. 15–25. ACL, Dublin, Ireland (Virtual) (2020)
