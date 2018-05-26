package moe.rangho.jialoguescript.ast;

public abstract class Value<T> extends Node {

    public static final String NAME = "Value";

    public abstract T getContent();
}
