package moe.rangho.jialoguescript.ast;

import moe.rangho.jialoguescript.model.Executable;

/**
 * Base class of all DialogueScript nodes.
 */
public abstract class Node {

    /**
     * Number of line on which this node appears in the original script.
     */
    protected int line;

    /**
     * Column of line on which this node appears in the original script.
     */
    protected int column;

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
        this.line = line;
        this.column = column;
        this.exec = exec;
    }
}
