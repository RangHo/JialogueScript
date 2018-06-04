package moe.rangho.jialoguescript.ast;

public class NumberLiteral extends Value<Float> {

    public static final String NAME = "NumberLiteral";

    public NumberLiteral(float content) {
        super(content);
    }

    @Override
    public String toString() {
        return NumberLiteral.NAME + " (" + this.content + ")";
    }
}
