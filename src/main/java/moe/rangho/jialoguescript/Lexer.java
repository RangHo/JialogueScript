package moe.rangho.jialoguescript;

import moe.rangho.jialoguescript.util.GenericStream;
import moe.rangho.jialoguescript.model.Token;
import moe.rangho.jialoguescript.util.Predicates;

import java.util.function.Predicate;

/**
 * DialogueScript Lexer
 *
 * It reads raw DialogueScript file and breaks into tokens for Parser to parse.
 */
public class Lexer {

    /**
     * List of available keywords.
     */
    public static final String[] KEYWORDS = {
            "label",    // Label statement keyword
            "jump",     // Jump statement keyword
            "false",    // False boolean value
            "true"      // True boolean value
    };

    private GenericStream<Character> input;

    public Lexer(String code) {
        Character[] inputArr = new Character[code.length()];

        // why primitive types no object java wtf
        for (int i = 0; i < inputArr.length; i++)
            inputArr[i] = code.charAt(i);

        this.input = new GenericStream<>(inputArr);
    }

    public Token next() throws Exception {

        readUntil(Predicates.isWhitespace);

        char nextChar = this.input.peek();

        if (Predicates.isIdentifierBeginning.test(nextChar))
            return readIdentifier();
        if (Predicates.isPunctuation.test(nextChar))
            return readPunctuation();

        throw new Exception("Unrecognized character: " + nextChar);
    }

    private String readWhile(Predicate<Character> predicate) {
        StringBuilder sb = new StringBuilder();

        while (predicate.test(this.input.peek()))
            sb.append(this.input.read());

        return sb.toString();
    }

    private String readUntil(Predicate<Character> predicate) {
        StringBuilder sb = new StringBuilder();

        while (true)
            if (predicate.test(this.input.peek()))
                return sb.toString();
            else
                sb.append(this.input.read());
    }

    // Token readers

    private Token readIdentifier() {
        String identifier = readWhile(Predicates.isIdentifier);

        for (String keyword : Lexer.KEYWORDS)
            if (identifier.equals(keyword))
                return Token.createKeyword(identifier);

        return Token.createIdentifier(identifier);
    }

    private Token readPunctuation() {
        return Token.createPunctuation(this.input.read());
    }
}
