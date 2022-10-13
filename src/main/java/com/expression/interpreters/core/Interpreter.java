package com.expression.interpreters.core;

import com.expression.interpreters.core.Expr.Binary;
import com.expression.interpreters.core.Expr.Grouping;
import com.expression.interpreters.core.Expr.Literal;
import com.expression.interpreters.core.Expr.Unary;

public class Interpreter implements Expr.Visitor<Object> {

  private Object evaluate(Expr expr) {
    return expr.accept(this);
  }

  public void interpret(Expr expression) {
    Object value = evaluate(expression);
    System.out.println(stringify(value));
  }

  private String stringify(Object object) {
    if (object == null) {
      return "nil";
    }

    if (object instanceof Double) {
      String text = object.toString();
      if (text.endsWith(".0")) {
        text = text.substring(0, text.length() - 2);
      }
      return text;
    }

    return object.toString();
  }

  @Override
  public Object visitBinaryExpr(Binary expr) {
    Object left = evaluate(expr.left);
    Object right = evaluate(expr.right);

    switch (expr.operator.type) {
      case GREATER:
        return (double) left > (double) right;
      case GREATER_EQUAL:
        return (double) left >= (double) right;
      case LESS:
        return (double) left < (double) right;
      case LESS_EQUAL:
        return (double) left <= (double) right;
      case MINUS:
        return (double) left - (double) right;
      case PLUS:
        return (double) left + (double) right;
      case SLASH:
        return (double) left / (double) right;
      case STAR:
        return (double) left * (double) right;
      case BANG_EQUAL:
        return !isEqual(left, right);
      case EQUAL_EQUAL:
        return isEqual(left, right);
      default:
        return null;
    }
  }

  private boolean isEqual(Object a, Object b) {
    if (a == null && b == null) {
      return true;
    }

    if (a == null) {
      return false;
    }

    return a.equals(b);
  }

  @Override
  public Object visitGroupingExpr(Grouping expr) {
    return evaluate(expr.expression);
  }

  @Override
  public Object visitLiteralExpr(Literal expr) {
    return expr.value;
  }

  @Override
  public Object visitUnaryExpr(Unary expr) {
    Object right = evaluate(expr.right);
    return -(double) right;
  }

}
