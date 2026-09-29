import java.util.List;
import java.util.Scanner;

import evaluator.Evaluator;
import evaluator.EvaluatorError;
import lexer.Lexer;
import lexer.LexerError;
import lexer.Token;
import parser.Expr;
import parser.Parser;
import parser.ParserError;

public class Main {
    public static void main(String[] args) {
        try (Scanner scnr = new Scanner(System.in)) {
            System.out.println("Evaluator");
            System.out.println("Enter 'exit' to quit the program");
            
            while (true) {
                System.out.print("Equation: ");
                String equation = scnr.nextLine();
                
                if (equation.equals("exit")) {
                    break;
                }
                
                List<Token> tokens;

                try {
                    tokens = new Lexer(equation).lex();
                } catch (LexerError e) {
                    System.err.println("lexer error: " + e.getMessage());
                    continue;
                }

                Expr expr;

                try {
                    expr = new Parser(tokens).parse();
                    System.out.println("AST: " + expr.toString());
                } catch (ParserError e) {
                    System.err.println("parser error: " + e.getMessage());
                    continue;
                }

                try {
                    System.out.println(Evaluator.evaluate(expr));
                } catch (EvaluatorError e) {
                    System.err.println("evaluator error: " + e.getMessage());
                }
            }
        }
    }
}