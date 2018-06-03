package moe.rangho.jialoguescript;

import moe.rangho.jialoguescript.util.CharacterStream;
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

    private CharacterStream input;

    public Lexer(String code) {
        this.input = new CharacterStream(code);
    }

    public Token next() throws Exception {

        this.input.readUntil(Predicates.isWhitespace);

        char nextChar = this.input.peek();

        if (Predicates.isIdentifierBeginning.test(nextChar))
            return readIdentifier();
        if (Predicates.isPunctuation.test(nextChar))
            return readPunctuation();
        if (nextChar == '"' || nextChar == '\'')
            return readString();
        if (nextChar == '\n')
            return readNewline();

        throw new Exception("Unrecognized character: " + nextChar);
    }

    // Token readers

    private Token readString() {
        char delimiter = this.input.read(); // Get rid of the " or ' character
        String content = this.input.readUntil((character) -> character == delimiter);
        this.input.read();  // Get rid of the trailing " or ' character

        return Token.createString(content);
    }

    private Token readIdentifier() {
        String identifier = this.input.readWhile(Predicates.isIdentifier);

        for (String keyword : Lexer.KEYWORDS)
            if (identifier.equals(keyword))
                return Token.createKeyword(identifier);

        return Token.createIdentifier(identifier);
    }

    private Token readPunctuation() {
        return Token.createPunctuation(this.input.read());
    }

    private Token readNewline() {
        return Token.createNewline();
    }
}
