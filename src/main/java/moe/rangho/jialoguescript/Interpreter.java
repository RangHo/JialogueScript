package moe.rangho.jialoguescript;

import moe.rangho.jialoguescript.ast.*;
import moe.rangho.jialoguescript.model.AbstractCharacter;
import moe.rangho.jialoguescript.model.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class Interpreter {

    public static final String TERMINATE_MESSAGE = "Terminate";

    private int treeNumber;

    private int statementNumber;

    private List<Program> trees;

    private Map<String, Object> variables;

    private Map<String, Consumer> callbacks;

    public Interpreter(Map<String, Object> providedVariables, Map<String, Consumer> bridges) {
        this.treeNumber = 0;
        this.statementNumber = 0;
        this.trees = new ArrayList<>();
        this.variables = providedVariables;
        this.callbacks = bridges;
    }

    public void addProgram(Program prog) {
        this.trees.add(prog);
    }

    public boolean interpret() {
        // TODO: Find main label and execute that first


        Program targetTree = this.trees.get(this.treeNumber);

        if (this.statementNumber >= targetTree.statements.length) {
            this.statementNumber = 0;
            this.treeNumber++;

            if (this.treeNumber >= this.trees.size())
                return false;

            targetTree = this.trees.get(++this.treeNumber);
        }

        Statement targetStatement = targetTree.statements[this.statementNumber++];
        interpretStatement(targetStatement);

        return true;
    }

    public void interpretStatement(Statement target) {
        if (target instanceof Say)
            interpretSay((Say)target);
    }

    public void interpretSay(Say target) {
        Object character = interpretValue(target.character);
        Object dialogue = interpretValue(target.dialogue);

        if (character instanceof String)
            character = new AbstractCharacter((String)character) {
                @Override
                public String getName() {
                    return super.getName();
                }
            };

        if (!(character instanceof AbstractCharacter)
            || !(dialogue instanceof String))
            throw new RuntimeException("Type error");

        this.callbacks.get(Say.NAME).accept(new Pair<>((AbstractCharacter)character, (String)dialogue));
    }

    public Object interpretValue(Value target) {
        if (target instanceof NumberLiteral)
            return interpretNumberLiteral((NumberLiteral)target);
        else if (target instanceof StringLiteral)
            return interpretStringLiteral((StringLiteral)target);
        else if (target instanceof Identifier)
            return interpretIdentifier((Identifier)target);

        return null;
    }

    public float interpretNumberLiteral(NumberLiteral target) {
        return target.content;
    }

    public String interpretStringLiteral(StringLiteral target) {
        return target.content;
    }

    public Object interpretIdentifier(Identifier target) {
        return this.variables.get(target.content);
    }
}
