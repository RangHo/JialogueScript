package moe.rangho.jialoguescript.ast;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents one DialogueScript code file.
 */
public class Program extends Node {

    public static final String NAME = "Program";

    public final Statement[] statements;

    public Program(List<Statement> input) {
        this.statements = new Statement[input.size()];
        input.toArray(this.statements);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (Statement item : this.statements) {
            sb.append("\n");
            sb.append(item.toString());
            sb.append(",");
        }

        sb.replace(sb.lastIndexOf(","), sb.length(), "\n");

        return Program.NAME + " {"
                + sb.toString()
                + "}";
    }
}
