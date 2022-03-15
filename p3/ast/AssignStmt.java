package ast;

import interpreter.Interpreter;

import java.io.PrintStream;
import java.util.*;

public class AssignStmt extends Stmt {
    public final String ident;
    public final Expr expr;

    public AssignStmt(String i, Expr e, Location loc) {
        super(loc);
        ident = i;
        expr = e;
    }

    public void print(PrintStream ps, String indent) {
        ps.print(indent + ident + " = ");
        expr.print(ps);
        ps.print(";");
    }

    public void print(PrintStream ps) {
        print(ps, "");
    }

    @Override
    public void check(ArrayList<Map<String, Type>> tables) {
        Type id_type = null;
        for (int i = tables.size(); i > 0; i--) {
            Map<String, Type> table = tables.get(i - 1);
            id_type = table.get(ident);
            if (id_type != null) {
                break;
            }
        }

        expr.check(tables);

        if (id_type == null) {
            Interpreter.fatalError("Lack declaration error : " + ident, Interpreter.EXIT_STATIC_CHECKING_ERROR);
        } else if (id_type != expr.type) {
            Interpreter.fatalError("Type error : " + ident, Interpreter.EXIT_STATIC_CHECKING_ERROR);
        }
    }

    @Override
    public void evaluate(Map<String, Number> valueTable, Scanner s) {
        expr.evaluate(valueTable, s);
        valueTable.put(ident, expr.value);
    }
}
