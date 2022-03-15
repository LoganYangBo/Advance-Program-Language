package ast;

import java.util.*;

public abstract class Expr extends ASTNode {
    protected Type type = null;
    protected Number value = null;

    public Expr(Location loc) {
        super(loc);
    }

    public abstract void check(ArrayList<Map<String, Type>> tables);
    public abstract void evaluate(Map<String, Number> valueTable, Scanner s);
}
