package main.uct.models.feature;

import java.util.HashMap;

public class ConcordType extends Feature {

    private static HashMap<String, ConcordType> concordTypes = new HashMap<>();

    public static ConcordType getConcordType(String concordType) {
        if (concordTypes.get(concordType) == null) {
            ConcordType tmp =  new ConcordType(concordType);
            concordTypes.put(concordType, tmp);
        }
        return concordTypes.get(concordType);
    }

    private String type;
    public ConcordType(String type) {
        this.type = type;
    }

    public String getTypeString() {
        return type;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        } else if (!(obj instanceof ConcordType)) {
            return false;
        } else {
            ConcordType obType = (ConcordType) obj;
            return this.getTypeString().equals(obType.getTypeString());
        }
    }

    @Override
    public String toString() {
        return "ConcordType{" +
                "type='" + type + '\'' +
                '}';
    }
}
