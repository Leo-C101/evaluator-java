package evaluator;

import parser.BinaryExpr;
import parser.Expr;
import parser.NumberExpr;

public class Evaluator {
    public static double evaluate(Expr expr) {
        return evalExpr(expr);
    }

    static double evalExpr(Expr expr) {
        if (expr instanceof BinaryExpr bin) {
            return evalBinary(bin);
        }
        else if (expr instanceof NumberExpr num) {
            return num.value;
        }
        throw new EvaluatorError("unknown expr type: " + expr.toString());
    }

    static double evalBinary(BinaryExpr bin) {
        double left = evalExpr(bin.left);
        double right = evalExpr(bin.right);
        
        if (bin.operator.equals("+")) {
            return left + right;
        }

        if (bin.operator.equals("-")) {
            return left - right;
        }

        if (bin.operator.equals("*")) {
            return left * right;
        }

        if (bin.operator.equals("/")) {
            if (right == 0.0) {
                throw new EvaluatorError("division by zero");
            }
            return left / right;
        }

        return 0.0f;
    }
}
