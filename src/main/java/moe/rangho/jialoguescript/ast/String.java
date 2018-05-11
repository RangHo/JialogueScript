package moe.rangho.jialoguescript.ast;

import java.lang.reflect.Type;

import moe.rangho.jialoguescript.util.Executable;

/**
 * Represents DialogueScript string literal.
 *
 * Usage:
 * <code>"Content"</code>
 */
public class String extends Value {

    private String content;

    public String(int line, int column, Executable exec, String content) {
        super(line, column, exec);
        this.content = content;
    }

    @Override
    public Object evaluate() {
        return this.content;
    }

    @Override
    public Type getType() {
        return String.class;
    }
}
