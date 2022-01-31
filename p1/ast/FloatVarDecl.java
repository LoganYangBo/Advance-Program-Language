package ast;
import java.io.PrintStream;
import java.util.Map;
import interpreter.Interpreter;


public class FloatVarDecl extends VarDecl {
    public FloatVarDecl(String i, Location loc) {
	super(i,loc);
    }
    public void print(PrintStream ps) {
	ps.print("float " + ident);
    }
    
    public String check(Map<String, String> table){
        if(table.containsKey(ident))  Interpreter.fatalError("var has been declared: " + ident, Interpreter.EXIT_STATIC_CHECKING_ERROR);
        table.put(ident, "FLOAT");
        return "FLOAT";
    }
}
