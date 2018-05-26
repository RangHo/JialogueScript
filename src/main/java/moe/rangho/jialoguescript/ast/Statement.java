package moe.rangho.jialoguescript.ast;

public abstract class Statement extends Node {

    public static final String NAME = "Statement";

    @Override
    public String toString() {
        return Statement.NAME;
    }
}
