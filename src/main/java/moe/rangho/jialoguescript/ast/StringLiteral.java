package moe.rangho.jialoguescript.ast;

public class StringLiteral extends Value<String> {

    public static final String NAME = "String Literal";

    public StringLiteral(String content) {
        super(content);
    }

    @Override
    public String toString() {
        return StringLiteral.NAME + " ("
               + "Content: " + this.content
               + ")";
    }
}
