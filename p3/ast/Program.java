package ast;

import java.io.PrintStream;
import java.util.*;

public class Program extends ASTNode {
    public final UnitList unitList;

    public Program(UnitList ul, Location loc) {
        super(loc);
        unitList = ul;
    }

    public void print(PrintStream ps) {
        unitList.print(ps, "");
    }

    public void check(ArrayList<Map<String, Type>> tables) {
        Map<String, Type> temp = new HashMap<>();
        tables.add(temp);
        unitList.check(tables);
    }

    public void evaluate(Map<String, Number> valueTable, Scanner s) {
        unitList.evaluate(valueTable, s);
    }
}
