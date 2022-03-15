package ast;

import java.io.PrintStream;
import java.util.*;

public abstract class VarDecl extends ASTNode {
    public final String ident;
    protected Type type;

    public VarDecl(String i, Location loc) {
        super(loc);
        ident = i;
    }

    public abstract void print(PrintStream ps);

    public abstract void check(ArrayList<Map<String, Type>> tables);

    public abstract void evaluate(Map<String, Number> valueTable, Scanner s);
}
