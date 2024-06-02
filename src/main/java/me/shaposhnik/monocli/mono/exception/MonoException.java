package me.shaposhnik.monocli.mono.exception;

public class MonoException extends RuntimeException {
  public MonoException(Exception e) {
    super(e);
  }

  public MonoException(String s) {
    super(s);
  }
}
