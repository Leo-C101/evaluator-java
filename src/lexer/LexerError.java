package lexer;
public class LexerError extends RuntimeException {
    public LexerError(String msg) {
        super(msg);
    }
}
