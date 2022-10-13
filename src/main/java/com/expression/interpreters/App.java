package com.expression.interpreters;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import com.expression.interpreters.core.Expr;
import com.expression.interpreters.core.Interpreter;
import com.expression.interpreters.core.Parser;
import com.expression.interpreters.core.RuntimeError;
import com.expression.interpreters.core.Scanner;
import com.expression.interpreters.core.Token;
import com.expression.interpreters.enums.TokenType;

public class App {

  private static boolean hadError = false;

  private static final Interpreter interpreter = new Interpreter();

  public static void main(String[] args) throws IOException {
    /* Initialize reader. */
    InputStreamReader input = new InputStreamReader(System.in);
    BufferedReader reader = new BufferedReader(input);

    /* REPL. */
    for (;;) {
      System.out.print("> ");
      String line = reader.readLine();
      if (line == null) {
        break;
      }
      process(line);
      hadError = false;
    }
  }

  private static void process(String source) {
    Scanner scanner = new Scanner(source);
    List<Token> tokens = scanner.scanTokens();

    Parser parser = new Parser(tokens);
    Expr expression = parser.parse();

    /* Stop if there was a syntax error. */
    if (hadError) {
      return;
    }

    /* Do the interpretation here. */
    interpreter.interpret(expression);

  }

  public static void error(Token token, String message) {
    if (token == null || token.type == TokenType.EOF) {
      System.err.println("[Error] " + ": " + message);
    } else {
      System.err.println("[Error] " + ": " + token.lexeme + "': " + message);
    }
    hadError = true;
  }

  public static void runtimeError(RuntimeError error) {
    System.err.println(error.getMessage());
  }
}
