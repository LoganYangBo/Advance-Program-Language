package ast;
import java.io.PrintStream;
import java.util.Map;
import interpreter.Interpreter;

public class AssignStmt extends Stmt {
    public final String ident;
    public final Expr expr;
    public AssignStmt(String i, Expr e, Location loc) {
	super(loc);
	ident = i;
	expr = e;
    }
    public void print(PrintStream ps) { 
	ps.print(ident + " = ");
	expr.print(ps);
	ps.print(";");
    }
    public String check(Map<String, String> globalTable){
        if(!globalTable.containsKey(ident)){
             Interpreter.fatalError("var need to be declaration : " + ident, Interpreter.EXIT_STATIC_CHECKING_ERROR);
        }else if(!globalTable.get(ident).equals(expr.check(globalTable))){
             Interpreter.fatalError("var dosen't fit the daclaration: " + ident, Interpreter.EXIT_STATIC_CHECKING_ERROR);
        }

        return globalTable.get(ident);
    }
}
