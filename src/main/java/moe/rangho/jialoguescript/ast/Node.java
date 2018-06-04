package moe.rangho.jialoguescript.ast;

/**
 * Base class for all DialogueScript AST nodes.
 */
public abstract class Node {

    public static final String NAME = "Node";

    @Override
    public String toString() {
        return Node.NAME;
    }
}
