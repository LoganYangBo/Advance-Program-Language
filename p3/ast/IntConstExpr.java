package ast;

import java.io.PrintStream;
import java.util.*;

public class IntConstExpr extends Expr {
    public final Long ival;

    public IntConstExpr(Long i, Location loc) {
        super(loc);
        ival = i;
    }

    public void print(PrintStream ps) {
        ps.print(ival);
    }

    public void check(ArrayList<Map<String, Type>> tables) {
        type = Type.INT;
    }

    @Override
    public void evaluate(Map<String, Number> valueTable, Scanner s) {
        value = ival;
    }
}
