package moe.rangho.jialoguescript.ast;

/**
 * Represents DialogueScript Say statement.
 */
public class Say extends Statement {

    public static final String NAME = "Say Statement";

    private Value character;

    private Value dialogue;

    public Say(Value character, Value dialogue) {
        this.character = character;
        this.dialogue = dialogue;
    }

    @Override
    public String toString() {
        return Say.NAME + " ("
               + "Character: " + this.character.toString() + " | "
               + "Content: " + this.dialogue.toString()
               + ")";
    }
}
