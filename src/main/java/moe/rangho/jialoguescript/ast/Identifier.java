package moe.rangho.jialoguescript.ast;

public class Identifier extends Value<String> {

    public static final String NAME = "Identifier";

    private String content;

    public Identifier(String content) {
        this.content = content;
    }

    @Override
    public String getContent() {
        return this.content;
    }

    @Override
    public String toString() {
        return Identifier.NAME + " ("
               + "Name: " + this.content
               + ")";
    }
}
