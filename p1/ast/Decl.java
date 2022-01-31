package ast;
import java.io.PrintStream;
import java.util.*;
import interpreter.Interpreter;

public class Decl extends Unit {
    public final VarDecl varDecl;
    public final Expr expr;
    public Decl(VarDecl d, Location loc) {
	super(loc);
	varDecl = d;
	expr = null;
    }
    public Decl(VarDecl d, Expr e, Location loc) {
	super(loc);
	varDecl = d;
	expr = e;
    }
    public void print(PrintStream ps) { 
	varDecl.print(ps); 
	if (expr != null) {
	    ps.print(" = ");
	    expr.print(ps);
	}
	ps.print(";");
    }

    public String check(Map<String, String> table){
        if(expr!=null && !expr.check(table).equals(varDecl.check(table)))
           Interpreter.fatalError("variable type is not equal to expr type: ", Interpreter.EXIT_STATIC_CHECKING_ERROR);
        return varDecl.check(table);
    }
}
