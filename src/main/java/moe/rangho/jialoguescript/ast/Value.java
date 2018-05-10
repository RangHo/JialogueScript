package moe.rangho.jialoguescript.ast;

import moe.rangho.jialoguescript.util.Executable;

public abstract class Value extends Node {

    public Value(int line, int column, Executable exec) {
        super(line, column, exec);
    }

    /**
     * Evaluates this value node and returns the content.
     * @return content of this node.
     */
    public abstract Object evaluate();  // TODO: I don't want this to box and unbox every time we execute this...
}
