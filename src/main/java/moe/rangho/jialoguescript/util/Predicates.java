package moe.rangho.jialoguescript.util;

import java.util.function.Predicate;

public class Predicates {

    /**
     * Checks if the given character is an ignored whitespace character.
     */
    public static final Predicate<Character> isWhitespace = (character)
            -> " \t\r".indexOf(character) >= 0;

    /**
     * Checks if the given character is a valid beginning of an identifier.
     */
    public static final Predicate<Character> isIdentifierBeginning = (character) -> {
        int charCode = (int)character;
        return (0x41 <= charCode && charCode <= 0x5a)
               || (0x61 <= charCode && charCode <= 0x7a)
               || charCode == 0x5f;
    };

    /**
     * Checks if the given character is a valid identifier character.
     */
    public static final Predicate<Character> isIdentifier = (character) -> {
        int charCode = (int)character;
        return isIdentifierBeginning.test(character)
               || (0x30 <= charCode && charCode <= 0x39);
    };

    public static final Predicate<Character> isPunctuation = (character)
            -> ".,:;(){}[]".indexOf(character) >= 0;

    public static final Predicate<Character> isDigit = (character)
            -> "0123456789".indexOf(character) >= 0;
}
