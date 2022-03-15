package ast;

import interpreter.Interpreter;

import java.io.PrintStream;
import java.util.*;

public class ReadIntExpr extends Expr {
    public ReadIntExpr(Location loc) {
        super(loc);
    }

    @Override
    public void check(ArrayList<Map<String, Type>> tables) {
        type = Type.INT;
    }

    @Override
    public void evaluate(Map<String, Number> valueTable, Scanner s) {
        System.out.print("Read Int: ");
        try {
            value = s.nextLong();
        } catch (Exception e) {
            Interpreter.fatalError("Input error : " + type, Interpreter.EXIT_FAILED_STDIN_READ);
        }
    }

    public void print(PrintStream ps) {
        ps.print("readint");
    }
}
