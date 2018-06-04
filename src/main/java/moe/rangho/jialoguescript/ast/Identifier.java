package moe.rangho.jialoguescript.ast;

public class Identifier extends Value<String> {

    public static final String NAME = "Identifier";

    public Identifier(String content) {
        super(content);
    }

    @Override
    public String toString() {
        return Identifier.NAME + " ("
               + "Name: " + this.content
               + ")";
    }
}
