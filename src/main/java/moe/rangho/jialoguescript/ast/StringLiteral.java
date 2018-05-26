package moe.rangho.jialoguescript.ast;

public class StringLiteral extends Value<String> {

    public static final String NAME = "String Literal";

    private String content;

    public StringLiteral(String content) {
        this.content = content;
    }

    @Override
    public String getContent() {
        return this.content;
    }

    @Override
    public String toString() {
        return StringLiteral.NAME + " ("
               + "Content: " + this.content
               + ")";
    }
}
