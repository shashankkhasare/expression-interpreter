package com.expression.interpreters;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import com.expression.interpreters.core.Scanner;
import com.expression.interpreters.core.Token;

public class App {
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
    }
  }

  private static void process(String source) {
    Scanner scanner = new Scanner(source);
    List<Token> tokens = scanner.scanTokens();

    /* Just print the tokens. */
    for (Token token : tokens) {
      System.out.println(token);
    }
  }

  public static void error(String message) {
    System.err.println("[Error] " + ": " + message);
  }
}
