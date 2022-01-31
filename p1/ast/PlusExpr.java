package ast;
import java.io.PrintStream;
import java.util.Map;
import interpreter.Interpreter;

public class PlusExpr extends Expr {
    public final Expr expr1, expr2;
    public PlusExpr(Expr e1, Expr e2, Location loc) {
	super(loc);
	expr1 = e1; 
	expr2 = e2;
    }
    public void print(PrintStream ps) {
	ps.print("(");
	expr1.print(ps);
	ps.print("+");
	expr2.print(ps);
	ps.print(")");
    }

    public String check(Map<String, String> table){
        if(!expr1.check(table).equals(expr2.check(table)))
            Interpreter.fatalError("different type vars cannot be added: ", Interpreter.EXIT_STATIC_CHECKING_ERROR);
        return expr1.check(table);
    }
}
