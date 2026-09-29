package parser;

public class NumberExpr extends Expr {
    public double value;

    public NumberExpr(double val) {
        value = val;
    }
    
    @Override 
    public String toString() {
        return String.valueOf(value);
    }
}
