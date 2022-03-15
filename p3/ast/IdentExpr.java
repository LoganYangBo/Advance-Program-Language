package ast;

import interpreter.Interpreter;

import java.io.PrintStream;
import java.util.*;

public class IdentExpr extends Expr {
    public final String ident;

    public IdentExpr(String i, Location loc) {
        super(loc);
        ident = i;
    }

    public void print(PrintStream ps) {
        ps.print(ident);
    }

    public void check(ArrayList<Map<String, Type>> tables) {
        for (int i = tables.size(); i > 0; i--) {
            Map<String, Type> table = tables.get(i - 1);
            type = table.get(ident);
            if (type != null) {
                break;
            }
        }

        if (type == null) {
            Interpreter.fatalError("Lack decalaration error : " + ident, Interpreter.EXIT_STATIC_CHECKING_ERROR);
        }
    }

    @Override
    public void evaluate(Map<String, Number> valueTable, Scanner s) {
        value = valueTable.get(ident);

        if (value == null) {
            Interpreter.fatalError("Uninitialized error : " + ident, Interpreter.EXIT_UNINITIALIZED_VAR_ERROR);
        }
    }
}
