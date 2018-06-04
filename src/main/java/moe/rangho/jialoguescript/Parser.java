package moe.rangho.jialoguescript;

import com.sun.xml.internal.bind.v2.model.core.ID;
import moe.rangho.jialoguescript.ast.*;
import moe.rangho.jialoguescript.model.Token;
import moe.rangho.jialoguescript.util.GenericStream;

import java.util.ArrayList;
import java.util.List;

public class Parser {

    private GenericStream<Token> input;

    private Object stash;

    public Parser(List<Token> input) {
        this.input = new GenericStream<>(input);
    }

    public Program parse() {
        return parseProgram();
    }

    private Program parseProgram() {
        List<Statement> statements = new ArrayList<>();
        statements.add(parseStatement());

        while (!this.input.isEnd()
               && this.input.peek().getType() == Token.Type.NEWLINE) {

            while (this.input.isEnd() || this.input.peek().getType() == Token.Type.NEWLINE)
                this.input.read();  // eat all newlines

            if (this.input.isEnd())
                break;

            statements.add(parseStatement());
        }

        return new Program(statements);
    }

    private Statement parseStatement() {
        if (this.input.getAmountLeft() < 2)
            throw new RuntimeException();

        this.stash = parseValue();

        Statement result = null;

        if (this.stash != null) {
            Token temp = this.input.peek();
            if (temp.getType() == Token.Type.PUNCTUATION
                && temp.getContent().equals(':'))
                result = parseSay();
        }

        if (result == null)
            throw new RuntimeException();

        return result;
    }

    private Say parseSay() {
        Value character = (Value)this.stash;
        this.input.read();  // eat colon punctuation token
        Value dialogue = parseValue();

        if (dialogue == null)
            throw new RuntimeException();

        return new Say(character, dialogue);
    }

    private Value parseValue() {
        Token.Type peekedType = this.input.peek().getType();
        
        if (peekedType == Token.Type.IDENTIFIER)
            return parseIdentifier();
        else if (peekedType == Token.Type.NUMBER)
            return parseNumberLiteral();
        else if (peekedType == Token.Type.STRING)
            return parseStringLiteral();
        else
            ;

        return null;
    }

    private NumberLiteral parseNumberLiteral() {
        return new NumberLiteral((Integer)this.input.read().getContent());
    }

    private StringLiteral parseStringLiteral() {
        return new StringLiteral((String)this.input.read().getContent());
    }

    private Identifier parseIdentifier() {
        return new Identifier((String)this.input.read().getContent());
    }
}
