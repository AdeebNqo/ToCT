# OWLSIZ

OWL Simplified isiZulu (OWLSIZ) is an isiZulu controlled natural language. It is designed for generating grammatical and fluent questions from an ontology. The purpose is to allow a domain expert to independently validate the contents of an ontology without understanding a ontology authoring language such as OWL.

# What's in here

- Numbered list of all the texts used the OWLSIZ evaluation (**NumberedEvaluatedTexts.tsv**)
- Quick access to examples of verbaliser's input, chosen template for each input, and the generated text output (**VerbaliserInputOutput.txt**)
- Version 1.1 of the Java source code (**Code**)
- Test ontology and list of nouns & their classes (**Other resources**)

# Using the code

- Open the project using Intellij IDEA
- Open OWLSIZ/res/config.properties and add paths to the required files:
	- "Other resources/testOntoisiZuluWithPW"
	- full path to "Other resources/nncPairs-fixed.txt"
	- full path to output file (e.g., "/tmp/owlsiz-output.csv")
	- full path to "OWLSIZ/Templates/"
- Run code

# Issues with FaCT++

If Java complains about finding the FaCT++ JNI then you need to specify it. This only applies if you want to use the error detection module, otherwise you should comment out lines 94, 97, 99-101

However, if you need to use the logical error detection  module then you need to download the Fact++ JNI from https://bitbucket.org/dtsarkov/factplusplus/downloads/, create a folder called "FaCT++" alongside the Code, Other resources, and Templates folders. Then unzip the JNI in the newly created folder. After doing so, you should have the following mew folder: 

1. FaCT++
	
	1.1. FaCT++-linux-v1.6.5 (if you're using Linux ofcourse)
	
		1.1.1. 32bit
	
			1.1.1.1. FaCT++
	
			1.1.1.2. libfact.so
	
			1.1.1.3. libFaCTPlusPlusJNI.so
	
		1.1.2. 64bit
	
			1.1.2.1. FaCT++
	
			1.1.2.2. libfact.so
	
			1.1.2.3. libFaCTPlusPlusJNI.so

# More details

> Mahlaza, Z., Keet, C.M.: OWLSIZ: An isiZulu CNL for structured knowledge validation. In: Proc. of WebNLG+ 2020. pp. 15–25. ACL, Dublin, Ireland (Virtual) (2020)
