package moe.rangho.jialoguescript.ast;

public abstract class Value<T> extends Node {

    public static final String NAME = "Value";

    public final T content;

    public Value(T content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return Value.NAME + content.toString();
    }
}
