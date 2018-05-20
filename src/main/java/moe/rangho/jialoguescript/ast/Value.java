package moe.rangho.jialoguescript.ast;

import moe.rangho.jialoguescript.model.Executable;

import java.lang.reflect.Type;

public abstract class Value extends Node {

    public Value(int line, int column, Executable exec) {
        super(line, column, exec);
    }

    /**
     * Evaluates this value node and returns the content.
     * @return content of this node.
     */
    public abstract Object evaluate();  // TODO: I don't want this to box and unbox every time we execute this...

    /**
     * Gets the type of value stored by this object.
     * @return type of this Value.
     */
    public abstract Type getType();
}
