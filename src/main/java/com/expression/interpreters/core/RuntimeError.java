package com.expression.interpreters.core;

public class RuntimeError extends RuntimeException {
  private static final long serialVersionUID = 5389701187336989183L;
  public final Token token;

  RuntimeError(Token token, String message) {
    super(message);
    this.token = token;
  }
}
