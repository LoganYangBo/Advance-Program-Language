package ast;

import interpreter.Interpreter;

import java.io.PrintStream;
import java.util.*;

public class ReadFloatExpr extends Expr {
    public ReadFloatExpr(Location loc) {
        super(loc);
    }

    @Override
    public void check(ArrayList<Map<String, Type>> tables) {
        type = Type.FLOAT;
    }

    @Override
    public void evaluate(Map<String, Number> valueTable, Scanner s) {
        System.out.print("Read Float: ");
        try {
            value = s.nextDouble();
        } catch (Exception e) {
            Interpreter.fatalError("Input error: " + type , Interpreter.EXIT_FAILED_STDIN_READ);
        }
    }

    public void print(PrintStream ps) {
        ps.print("readfloat");
    }
}
