package moe.rangho.jialoguescript.model;

/**
 * DialogueScript representation of a character.
 *
 * Future user may extend this object to create their own Character representation.
 */
public class Character {

    private String name;

    public Character(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
