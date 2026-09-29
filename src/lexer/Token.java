package lexer;
public class Token {
    public enum TokenType {
        NUMBER,
        PLUS,
        MINUS,
        STAR,
        SLASH,
        LPAREN,
        RPAREN,
        SEMICOLON,
        EOF
    }

    public TokenType type;
    public String value;

    public Token(TokenType type, String value) {
        this.type = type;
        this.value = value;
    }
    
    @Override 
    public String toString() {
        return String.format(
            "(%s%s)",
            type.toString(),
            value.isEmpty() ? ""
                            : ", " + value
        );
    }
}
