package moe.rangho.jialoguescript.ast;

import moe.rangho.jialoguescript.model.Character;
import moe.rangho.jialoguescript.model.Executable;

/**
 * Represents DialogueScript Say statement.
 *
 * Usage:
 * <code>(Character value) : (String value)</code>
 */
public final class Say extends Statement {

    private Character character;

    public Say(int line, int column, Executable exec, Character character) {
        super(line, column, exec);
        this.character = character;
    }

    public Character getCharacter() {
        return character;
    }
}
