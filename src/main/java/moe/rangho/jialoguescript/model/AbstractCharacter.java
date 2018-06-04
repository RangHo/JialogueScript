package moe.rangho.jialoguescript.model;

/**
 * DialogueScript representation of a character.
 *
 * Future user may extend this object to create their own AbstractCharacter representation.
 */
public abstract class AbstractCharacter {

    private String name;

    public AbstractCharacter(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
