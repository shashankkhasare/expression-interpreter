package com.expression.interpreters.core;

import java.util.ArrayList;
import java.util.List;
import com.expression.interpreters.App;
import com.expression.interpreters.enums.TokenType;

public class Scanner {
  private static final String UNKNOWN_CHAR = "Unexpected character.";

  private final String source;
  private final List<Token> tokens = new ArrayList<>();
  private int start = 0;
  private int current = 0;

  public Scanner(String source) {
    this.source = source;
  }

  public List<Token> scanTokens() {
    while (!isAtEnd()) {
      start = current;
      scanToken();
    }

    tokens.add(new Token(TokenType.EOF, "", null));
    return tokens;
  }

  private void scanToken() {
    char c = advance();
    switch (c) {
      case '(':
        addToken(TokenType.LEFT_PAREN);
        break;
      case ')':
        addToken(TokenType.RIGHT_PAREN);
        break;
      case '-':
        addToken(TokenType.MINUS);
        break;
      case '+':
        addToken(TokenType.PLUS);
        break;
      case '*':
        addToken(TokenType.STAR);
        break;
      case '!':
        if (match('=')) {
          addToken(TokenType.BANG_EQUAL);
        } else {
          App.error(UNKNOWN_CHAR);
        }
        break;
      case '=':
        if (match('=')) {
          addToken(TokenType.EQUAL_EQUAL);
        } else {
          App.error(UNKNOWN_CHAR);
        }
        break;
      case '<':
        addToken(match('=') ? TokenType.LESS_EQUAL : TokenType.LESS);
        break;
      case '>':
        addToken(match('=') ? TokenType.GREATER_EQUAL : TokenType.GREATER);
        break;
      case ' ':
      case '\r':
      case '\t':
      case '\n':
        break;
      default:
        if (isDigit(c)) {
          number();
        } else {
          App.error(UNKNOWN_CHAR);
        }
        break;
    }
  }

  private boolean isDigit(char c) {
    return c >= '0' && c <= '9';
  }

  private void number() {
    while (isDigit(peek()))
      advance();

    /* Look for a fractional part. */
    if (peek() == '.' && isDigit(peekNext())) {
      /* Consume the "." */
      advance();

      while (isDigit(peek())) {
        advance();
      }
    }
    addToken(TokenType.NUMBER, Double.parseDouble(source.substring(start, current)));
  }

  private char peek() {
    if (isAtEnd()) {
      return '\0';
    }

    return source.charAt(current);
  }

  private char peekNext() {
    if (current + 1 >= source.length()) {
      return '\0';
    }
    return source.charAt(current + 1);
  }

  private boolean match(char expected) {
    if (isAtEnd()) {
      return false;
    }

    if (source.charAt(current) != expected) {
      return false;
    }

    current++;
    return true;
  }

  private char advance() {
    return source.charAt(current++);
  }

  private void addToken(TokenType type) {
    addToken(type, null);
  }

  private void addToken(TokenType type, Object literal) {
    String text = source.substring(start, current);
    tokens.add(new Token(type, text, literal));
  }

  private boolean isAtEnd() {
    return current >= source.length();
  }
}
