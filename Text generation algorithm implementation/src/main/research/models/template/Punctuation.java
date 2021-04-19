package main.research.models.template;

public class Punctuation extends TemplatePortion {
    String value;

    public Punctuation(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
