package moe.rangho.jialoguescript;

import moe.rangho.jialoguescript.util.GenericStream;
import moe.rangho.jialoguescript.model.Token;
import moe.rangho.jialoguescript.util.Predicates;

import java.util.ArrayList;
import java.util.List;
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
        List<Character> inputList = new ArrayList<>();

        // why primitive types no object java wtf
        for (int i = 0; i < code.length(); i++)
            inputList.add(code.charAt(i));

        this.input = new GenericStream<>(inputList);
    }

    public Token next() {

        readWhile(Predicates.isWhitespace);

        if (this.input.isEnd())
            return null;

        char nextChar = this.input.peek();

        if (Predicates.isIdentifierBeginning.test(nextChar))
            return readIdentifier();
        if (Predicates.isPunctuation.test(nextChar))
            return readPunctuation();
        if (nextChar == '"' || nextChar == '\'')
            return readString();
        if (Predicates.isDigit.test(nextChar))
            return readNumber();
        if (nextChar == '\n')
            return readNewline();

        throw new RuntimeException("Unrecognized character: " + (int)nextChar);
    }
    
    public String readWhile(Predicate<Character> predicate) {
        StringBuilder sb = new StringBuilder();

        while (!this.input.isEnd()
               && predicate.test(this.input.peek()))
            sb.append(this.input.read());

        return sb.toString();
    }
    
    public String readUntil(Predicate<Character> predicate) {
        StringBuilder sb = new StringBuilder();

        while (!this.input.isEnd())
            if (predicate.test(this.input.peek()))
                break;
            else
                sb.append(this.input.read());

        return sb.toString();
    }

    // Token readers

    private Token readString() {
        char delimiter = this.input.read(); // Get rid of the " or ' character
        String content = readUntil((character) -> character == delimiter);
        this.input.read();  // Get rid of the trailing " or ' character

        return Token.createString(content);
    }

    private Token readNumber() {
        String content = readUntil(Predicates.isDigit);
        int result = Integer.parseInt(content);
        return Token.createNumber(result);
    }

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

    private Token readNewline() {
        this.input.read();  // eat the newline character
        return Token.createNewline();
    }
}
