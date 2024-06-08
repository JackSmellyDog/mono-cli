package me.shaposhnik.monocli.cli.view.impl;

import static me.shaposhnik.monocli.cli.view.ConsoleViewUtils.getColumnData;
import static me.shaposhnik.monocli.cli.view.ConsoleViewUtils.getFormatter;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.bretty.console.table.Table;
import java.util.List;
import me.shaposhnik.monocli.cli.view.AbstractCommandLineView;
import me.shaposhnik.monocli.cli.view.ColumnDataWithMaxLength;
import me.shaposhnik.monocli.mono.dto.Currency;
import me.shaposhnik.monocli.mono.dto.MonoApiResponse;

public class CurrenciesView extends AbstractCommandLineView<List<Currency>> {
  private static final String CURRENCY_CODE_A = "CURRENCY_CODE_A";
  private static final String CURRENCY_CODE_B = "CURRENCY_CODE_B";
  private static final String DATE = "DATE";
  private static final String RATE_SELL = "RATE SELL";
  private static final String RATE_BUY = "RATE BUY";
  private static final String RATE_CROSS = "RATE CROSS";

  public CurrenciesView(MonoApiResponse<List<Currency>> response, ObjectMapper objectMapper) {
    super(response, objectMapper);
  }

  @Override
  public String toViewable() {
    ColumnDataWithMaxLength columnCodeA = getColumnData(response.body(), Currency::currencyCodeA,
        CURRENCY_CODE_A.length());
    var builder = new Table.Builder(CURRENCY_CODE_A, columnCodeA.column(),
        getFormatter(columnCodeA.length()));

    ColumnDataWithMaxLength columnCodeB = getColumnData(response.body(), Currency::currencyCodeB,
        CURRENCY_CODE_B.length());
    builder.addColumn(CURRENCY_CODE_B, columnCodeB.column(),
        getFormatter(columnCodeB.length()));

    ColumnDataWithMaxLength columnRateSell = getColumnData(response.body(), Currency::rateSell,
        RATE_SELL.length());
    builder.addColumn(RATE_SELL, columnRateSell.column(),
        getFormatter(columnRateSell.length()));

    ColumnDataWithMaxLength columnRateBuy = getColumnData(response.body(), Currency::rateBuy,
        RATE_BUY.length());
    builder.addColumn(RATE_BUY, columnRateBuy.column(),
        getFormatter(columnRateBuy.length()));

    ColumnDataWithMaxLength columnRateCross = getColumnData(response.body(), Currency::rateCross,
        RATE_CROSS.length());
    builder.addColumn(RATE_CROSS, columnRateCross.column(),
        getFormatter(columnRateCross.length()));

    ColumnDataWithMaxLength columnDate = getColumnData(response.body(), Currency::date,
        DATE.length());
    builder.addColumn(DATE, columnDate.column(),
        getFormatter(columnDate.length()));
    return builder.build().toString();
  }
}
