package ast;

import java.io.*;
import java.util.*;

abstract class ASTNode {
    public final Location loc;

    ASTNode(Location loc) {
        this.loc = loc;
    }

    public abstract void print(PrintStream ps);

    public String toString() {
        ByteArrayOutputStream b = new ByteArrayOutputStream();
        print(new PrintStream(b));
        return b.toString(java.nio.charset.StandardCharsets.UTF_8);
    }

    public abstract void check(ArrayList<Map<String, Type>> tables);
    public abstract void evaluate(Map<String, Number> valueTable, Scanner s);
}
