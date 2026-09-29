package parser;

public class BinaryExpr extends Expr {
    public Expr left;
    public Expr right;
    public String operator;

    public BinaryExpr(Expr left, Expr right, String op) {
        this.left = left;
        this.right = right;
        this.operator = op;
    }

    @Override 
    public String toString() {
        return String.format(
            "(%s %s %s)",
            left,
            operator,
            right
        );
    }
}
