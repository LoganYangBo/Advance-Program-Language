package ast;

import java.io.PrintStream;
import java.util.*;

public class FloatConstExpr extends Expr {
    public final Double fval;

    public FloatConstExpr(Double f, Location loc) {
        super(loc);
        fval = f;
    }

    public void print(PrintStream ps) {
        ps.print(fval);
    }

    public void check(ArrayList<Map<String, Type>> tables) {
        type = Type.FLOAT;
    }

    @Override
    public void evaluate(Map<String, Number> valueTable, Scanner s) {
        value = fval;
    }
}
