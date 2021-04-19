package main.uct.impl.nguni;

public class NguniVowelDetector {
    private char [] vowels = {'a', 'e', 'i', 'o', 'u'};
    private String [] labials = {"ph", "bh", "b", "p", "m"};

    public boolean isVowel(char someChar) {
        return existsIn(someChar, vowels);
    }

    public boolean isLabial(String someString) {
        return existsIn(someString, labials);
    }

    public boolean isLabial(char someChar) {
        return existsIn(someChar, labials);
    }

    private boolean existsIn(char someChar, char[] someCharList) {
        boolean result;
        String someCharAsString = String.valueOf(someChar);
        String [] someCharListAsStrings = new String [someCharList.length];
        for (int i=0; i<someCharList.length; i++) {
            someCharListAsStrings[i] = String.valueOf(someCharList[i]);
        }
        result = existsIn(someCharAsString, someCharListAsStrings);
        return result;
    }

    private boolean existsIn(char someChar, String[] someCharList) {
        return existsIn(String.valueOf(someChar), someCharList);
    }

    private boolean existsIn(String someChar, String[] someCharList) {
        boolean result = false;
        for (int i=0; i<someCharList.length; i++) {
            if (someCharList[i].equals(someChar)) {
                result = true;
                break;
            }
        }
        return result;
    }
}
