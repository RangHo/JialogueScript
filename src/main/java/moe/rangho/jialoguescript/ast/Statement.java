package moe.rangho.jialoguescript.ast;

/**
 * Base class for all DialogueScript statements.
 */
public abstract class Statement extends Node {

    public static final String NAME = "Statement";

    @Override
    public String toString() {
        return Statement.NAME;
    }
}
