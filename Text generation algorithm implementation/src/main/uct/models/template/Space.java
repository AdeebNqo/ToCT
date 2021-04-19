package main.uct.models.template;

public class Space extends TemplatePortion {
    String value;

    public Space(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
