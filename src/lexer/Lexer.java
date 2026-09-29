package lexer;
import java.util.ArrayList;
import java.util.List;

public class Lexer {
    String equation;
    int position;
    List<Token> tokens;

    public Lexer(String eq) {
        equation = eq;
        position = 0;
        tokens = new ArrayList<>();
    }

    public List<Token> lex() throws LexerError {
        position = 0;
        tokens.clear();

        while (!atEnd()) {
            char current = equation.charAt(position);

            if (Character.isWhitespace(current)) {
                position++;
                continue;
            }

            if (Character.isDigit(current)) {
                lexNumber();
                continue;
            }

            Token token = switch (current) {
                case '+' -> new Token(Token.TokenType.PLUS, "+");
                case '-' -> new Token(Token.TokenType.MINUS, "-");
                case '*' -> new Token(Token.TokenType.STAR, "*");
                case '/' -> new Token(Token.TokenType.SLASH, "/");
                case '(' -> new Token(Token.TokenType.LPAREN, "(");
                case ')' -> new Token(Token.TokenType.RPAREN, ")");
                default -> throw new LexerError("unknown character: " + current);
            };

            tokens.add(token);
            position++;
        }
        
        tokens.add(new Token(Token.TokenType.EOF, ""));
        return tokens;
    }

    private void lexNumber() throws LexerError {
        String numberString = "";
        int numDots = 0;

        while (!atEnd() && (Character.isDigit(current()) || current() == '.')) {
            if (current() == '.') {
                numDots++;
            }
            numberString += current();
            position++;
        }

        if (numDots > 1) {
            throw new LexerError("improper number literal: " + numberString);
        }
        
        try {
            Double.valueOf(numberString);
        } catch (NumberFormatException e) {
            throw new LexerError("improper number literal: " + numberString);
        }
        
        tokens.add(new Token(Token.TokenType.NUMBER, numberString));
    }

    private char current() {
        return equation.charAt(position);
    }
    
    private boolean atEnd() {
        return position >= equation.length();
    }
}
