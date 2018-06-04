package moe.rangho.jialoguescript;

import moe.rangho.jialoguescript.ast.Program;
import moe.rangho.jialoguescript.model.Token;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class DialogueScript {

    private Lexer lexer;

    private Parser parser;

    private Interpreter interpreter;

    private List<String> scripts;

    private Map<String, Consumer> bridges;

    private Map<String, Object> definedVariables;

    public DialogueScript() {
        this.scripts = new ArrayList<>();
        this.bridges = new HashMap<>();
        this.definedVariables = new HashMap<>();
        this.interpreter = new Interpreter(this.definedVariables, this.bridges);
    }

    public int addScript(String code) {
        this.scripts.add(code);
        int location = this.scripts.indexOf(code);
        reloadScript(location);
        return location;
    }

    public void updateScript(int index, String code) {
        this.scripts.set(index, code);
        reloadScript();
    }

    public void reloadScript() {
        this.interpreter = new Interpreter(this.definedVariables, this.bridges);

        for (int i = 0; i < this.scripts.size(); i++)
            reloadScript(i);
    }

    public void reloadScript(int index) {
        String code = this.scripts.get(index);

        // lex the whole thing

        this.lexer = new Lexer(code);
        List<Token> tokens = new ArrayList<>();

        Token temp = this.lexer.next();
        while (temp != null) {
            tokens.add(temp);
            temp = this.lexer.next();
        }

        this.lexer = null;  // now unnecessary

        // parse the whole thing

        this.parser = new Parser(tokens);
        Program result = this.parser.parse();
        this.parser = null; // now unnecessary

        // then add it to the interpreter

        this.interpreter.addProgram(result);
    }

    public void run() {
        while (this.interpreter.interpret());
    }

    public void registerBridge(String purpose, Consumer function) {
        this.bridges.put(purpose, function);
    }

    public void deregisterBridge(String purpose) {
        this.bridges.remove(purpose);
    }

    public void registerVariable(String name, Object value) {
        this.definedVariables.put(name, value);
    }

    public void deregisterVariable(String name) {
        this.definedVariables.remove(name);
    }
}
