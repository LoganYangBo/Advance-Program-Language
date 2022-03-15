package ast;

import interpreter.Interpreter;

import java.io.PrintStream;
import java.util.*;

public class FloatVarDecl extends VarDecl {
    public FloatVarDecl(String i, Location loc) {
        super(i, loc);
    }

    public void print(PrintStream ps) {
        ps.print("float " + ident);
    }

    @Override
    public void check(ArrayList<Map<String, Type>> tables) {
        Map<String, Type> declTable = tables.get(tables.size() - 1);
        type = Type.FLOAT;

        if (declTable.containsKey(ident)) {
            Interpreter.fatalError("Double declaration error: " + ident, Interpreter.EXIT_STATIC_CHECKING_ERROR);
        }
    }

    @Override
    public void evaluate(Map<String, Number> valueTable, Scanner s) {
        assert true;
    }
}
