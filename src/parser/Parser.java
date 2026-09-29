package parser;

import java.util.List;
import lexer.Token;
import lexer.Token.TokenType;

public class Parser {
    List<Token> tokens;
    int position;
    
    public Parser(List<Token> tokens) {
        this.tokens = tokens;
        this.position = 0;
    }

    public Expr parse() throws ParserError {
        return parseExpression();
    }

    private Expr parseExpression() throws ParserError {
        return parseTerm();
    } 

    private Expr parseTerm() throws ParserError {
        Expr expr = parseFactor();

        while (true) {
            String op = peek().value;

            if (!op.equals("+") && !op.equals("-")) {
                break;
            }

            position++;
            expr = new BinaryExpr(expr, parseFactor(), op);
        }

        return expr;
    }
    
    private Expr parseFactor() throws ParserError {
        Expr expr = parsePrimary();

        while (true) {
            String op = peek().value;


            if (!op.equals("*") && !op.equals("/")) {
                break;
            }

            position++;
            expr = new BinaryExpr(expr, parsePrimary(), op);
        }

        return expr;
    }

    private Expr parsePrimary() throws ParserError {
        if (peek().type == TokenType.NUMBER) {
            NumberExpr expr = new NumberExpr(Double.valueOf(peek().value));
            position++;
            return expr;
        }

        if (peek().type == TokenType.LPAREN) {
            position++;
            Expr expr = parseExpression();
            if (peek().type != TokenType.RPAREN) {
                throw new ParserError("expected ')' to match '('");
            }
            position++;
            return expr;
        }

        throw new ParserError("unexpected token: " + peek().toString());
    }

    private Token peek() {
        return tokens.get(position);
    }
}
