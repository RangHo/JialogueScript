package moe.rangho.jialoguescript.ast;

import moe.rangho.jialoguescript.util.Executable;

/**
 * Base class for all DialogueScript statements (non-returning expressions).
 */
public abstract class Statement extends Node {

    public Statement(int line, int column, Executable exec) {
        super(line, column, exec);
    }
}
