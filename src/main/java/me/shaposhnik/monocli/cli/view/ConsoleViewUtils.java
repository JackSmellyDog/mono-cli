package me.shaposhnik.monocli.cli.view;

import io.bretty.console.table.Alignment;
import io.bretty.console.table.ColumnFormatter;
import java.util.List;
import java.util.function.Function;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ConsoleViewUtils {

  public static void printLine(String s) {
    System.out.println(s);
  }

  public static String makeRed(String text) {
    return Color.RED.colorize(text);
  }

  public static String makeGreen(String text) {
    return Color.GREEN.colorize(text);
  }

  public static <T, R> ColumnDataWithMaxLength getColumnData(List<T> list,
                                                             Function<T, R> getter,
                                                             int defaultLength) {
    String[] columnData = list.stream()
        .map(getter)
        .map(String::valueOf)
        .toArray(String[]::new);

    int maxLength = list.stream()
        .map(getter)
        .map(String::valueOf)
        .mapToInt(String::length)
        .max()
        .orElse(defaultLength);

    return new ColumnDataWithMaxLength(columnData, Math.max(maxLength, defaultLength));
  }

  public static ColumnFormatter<String> getFormatter(int length) {
    return ColumnFormatter.text(Alignment.LEFT, length);
  }

  enum Color {
    RED("\u001B[31m"),

    GREEN("\u001B[32m");

    private final String code;

    Color(String code) {
      this.code = code;
    }

    public String colorize(String text) {
      return "%s%s\u001B[0m".formatted(code, text);
    }
  }
}
