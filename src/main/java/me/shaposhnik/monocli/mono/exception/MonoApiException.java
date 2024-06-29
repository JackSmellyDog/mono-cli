package me.shaposhnik.monocli.mono.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ProblemDetail;

@Getter
@RequiredArgsConstructor
public class MonoApiException extends RuntimeException {
  private final transient ProblemDetail problemDetail;
}
