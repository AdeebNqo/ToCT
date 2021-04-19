package main.research.models.feature;

import java.util.HashMap;

public class NounClass extends Feature {

    private static HashMap<String, NounClass> nounClasses = new HashMap<>();

    public static NounClass getNounClass(String nounClass) {
        if (nounClasses.get(nounClass) == null) {
            NounClass tmp =  new NounClass(nounClass);
            nounClasses.put(nounClass, tmp);
        }
        return nounClasses.get(nounClass);
    }



    private String nounClass;
    public NounClass (String someNounClass) {
        nounClass = someNounClass;
    }

    public String getNounClass() {
        return nounClass;
    }

    @Override
    public String toString() {
        return "NounClass{"
                 + nounClass  +
                '}';
    }
}
