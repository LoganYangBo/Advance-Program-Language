package ast;

import java.util.*;

public abstract class CondExpr extends ASTNode {
    protected Type type;
    protected boolean value;

    public CondExpr(Location loc) {
        super(loc);
    }

    public abstract void check(ArrayList<Map<String, Type>> tables);

    public abstract void evaluate(Map<String, Number> valueTable, Scanner s);
}
