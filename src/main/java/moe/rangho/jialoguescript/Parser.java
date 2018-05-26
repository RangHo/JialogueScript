package moe.rangho.jialoguescript;

import moe.rangho.jialoguescript.ast.Node;
import moe.rangho.jialoguescript.model.Token;
import moe.rangho.jialoguescript.util.GenericStream;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Parser {

    private GenericStream<Token> input;

    public Parser(Token[] tokens) {
        this.input = new GenericStream<>(tokens);
    }

    /**
     * Reads one "line" of tokens and parses the line.
     * @return one-line-worth of AST node.
     */
    public Node nextLine() {
        Token nextToken = this.input.peek();

        if (nextToken.getType() == Token.Type.IDENTIFIER)
            return parseSay();
    }

    private Node parse(List<Token> input) {
        Stack<Token> output = new Stack<>();
        Stack<Token> operators = new Stack<>();

        for (Token item : input)
            if (item.getType())
    }
}
