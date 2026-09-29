# Evaluator

Evaluator is a simple recursive descent parser and expression evaluator that takes in a string equation from the user and outputs the evaluated expression.

The input goes through lexical analysis where it is turned into meaningful tokens (number, operator, parentheses), parsing where it is turned into an AST (abstract syntax tree), and then evaluation where the AST is walked and is evaluated using mutual recursion.

## Running
Windows:
```powershell
New-Item -ItemType Directory -Force out
javac -d out -sourcepath src src/Main.java
java -cp out Main
```

Linux:
```sh
mkdir -p out
javac -d out -sourcepath src src/Main.java
java -cp out Main
```

## Example
```
Evaluator
Enter 'exit' to quit the program
Equation: 1 + 2 * (2 + 1)
AST: (1.0 + (2.0 * (2.0 + 1.0)))
Result: 7.0
```