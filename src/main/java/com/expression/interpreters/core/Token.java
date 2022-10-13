package com.expression.interpreters.core;

import com.expression.interpreters.enums.TokenType;

public class Token {
  final TokenType type;
  final String lexeme;
  final Object literal;

  Token(TokenType type, String lexeme, Object literal) {
    this.type = type;
    this.lexeme = lexeme;
    this.literal = literal;
  }

  public String toString() {
    return type + " " + lexeme + " " + literal;
  }
}
