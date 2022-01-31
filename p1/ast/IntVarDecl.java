package ast;
import java.io.PrintStream;
import java.util.Map;
import interpreter.Interpreter;

public class IntVarDecl extends VarDecl {
    public IntVarDecl(String i, Location loc) {
	super(i,loc);
    }
    public void print(PrintStream ps) {
	ps.print("int " + ident);
    }

    public String check(Map<String, String> table){
        if(table.containsKey(ident)) Interpreter.fatalError("var has been declared: " + ident, Interpreter.EXIT_STATIC_CHECKING_ERROR);
        table.put(ident, "INT");
        return "INT";
    }
}
