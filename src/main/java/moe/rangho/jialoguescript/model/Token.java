package moe.rangho.jialoguescript.model;

public class Token {

    private Object content;

    private final Type type;

    /**
     * Represents a type of the token.
     */
    public enum Type {
        KEYWORD,        // Keyword token
        IDENTIFIER,     // Identifier token
        PUNCTUATION,    // Punctuation token
        NUMBER,         // NumberLiteral token (including floating point and integers
        STRING,         // StringLiteral literal token
        NEWLINE         // End of line token
    }

    private Token(Type tokenType, Object value) {
        this.type = tokenType;
        this.content = value;
    }

    public Object getContent() {
        return content;
    }

    public Type getType() {
        return this.type;
    }

    public static Token createKeyword(String keyword) {
        return new Token(Type.KEYWORD, keyword);
    }

    public static Token createIdentifier(String identifier) {
        return new Token(Type.IDENTIFIER, identifier);
    }

    public static Token createPunctuation(char punctuation) {
        return new Token(Type.PUNCTUATION, punctuation);
    }

    public static Token createNumber(float number) {
        return new Token(Type.NUMBER, number);
    }

    public static Token createString(String string) {
        return new Token(Type.STRING, string);
    }

    public static Token createNewline() {
        return new Token(Type.NEWLINE, null);
    }
}
