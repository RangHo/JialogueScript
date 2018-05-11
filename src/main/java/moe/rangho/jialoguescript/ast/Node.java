package moe.rangho.jialoguescript.ast;

import moe.rangho.jialoguescript.util.Executable;
import moe.rangho.jialoguescript.util.Pair;

/**
 * Base class of all DialogueScript nodes.
 */
public abstract class Node {

    /**
     * Location of this node in DialogueScript code.
     *
     * <code>val1</code> is line number, and <code>val2</code> is column number.
     */
    protected Pair<Integer> location;

    /**
     * Method to execute when encountered.
     */
    protected Executable exec;

    /**
     * Constructs a node object.
     * @param line where this node objects appears.
     * @param column where this node object appears.
     * @param exec method to execute when this node is processed.
     */
    public Node(int line, int column, Executable exec) {
        this.location = new Pair<>(line, column);
        this.exec = exec;
    }
}
