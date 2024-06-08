package me.shaposhnik.monocli.cli.view.impl;

import static me.shaposhnik.monocli.cli.view.ConsoleViewUtils.getColumnData;
import static me.shaposhnik.monocli.cli.view.ConsoleViewUtils.getFormatter;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.bretty.console.table.Table;
import java.util.List;
import me.shaposhnik.monocli.cli.view.AbstractCommandLineView;
import me.shaposhnik.monocli.mono.dto.MonoApiResponse;
import me.shaposhnik.monocli.mono.dto.Transaction;

public class AccountStatementView extends AbstractCommandLineView<List<Transaction>> {
  private static final String ID = "ID";
  private static final String TIME = "TIME";
  private static final String DESCRIPTION = "DESCRIPTION";
  private static final String MCC = "MCC";
  private static final String ORIGINAL_MCC = "ORIGINAL MCC";
  private static final String HOLD = "HOLD";
  private static final String AMOUNT = "AMOUNT";
  private static final String OPERATION_AMOUNT = "OPERATION AMOUNT";
  private static final String CURRENCY = "CURRENCY";
  private static final String COMMISSION_RATE = "COMMISSION RATE";
  private static final String CASHBACK = "CASHBACK";
  private static final String BALANCE = "BALANCE";
  private static final String COMMENT = "COMMENT";
  private static final String RECEIPT_ID = "RECEIPT ID";
  private static final String INVOICE_ID = "INVOICE ID";
  private static final String COUNTER_EDRPOU = "COUNTER_EDRPOU";
  private static final String COUNTER_IBAN = "COUNTER IBAN";
  private static final String COUNTER_NAME = "COUNTER_NAME";
  
  public AccountStatementView(MonoApiResponse<List<Transaction>> response,
                              ObjectMapper objectMapper) {
    super(response, objectMapper);
  }

  @Override
  public String toViewable() {
    var columnId = getColumnData(response.body(), Transaction::id, ID.length());
    var builder = new Table.Builder(ID, columnId.column(), getFormatter(columnId.length()));

    var columnTime = getColumnData(response.body(), Transaction::time, TIME.length());
    builder.addColumn(TIME, columnTime.column(), getFormatter(columnTime.length()));

    var columnDescription = getColumnData(response.body(), Transaction::description,
        DESCRIPTION.length());
    builder.addColumn(DESCRIPTION, columnDescription.column(),
        getFormatter(columnDescription.length()));

    var columnMcc = getColumnData(response.body(), Transaction::mcc, MCC.length());
    builder.addColumn(MCC, columnMcc.column(), getFormatter(columnMcc.length()));

    var columnOriginalMcc = getColumnData(response.body(), Transaction::originalMcc,
        ORIGINAL_MCC.length());
    builder.addColumn(ORIGINAL_MCC, columnOriginalMcc.column(),
        getFormatter(columnOriginalMcc.length()));

    var columnHold = getColumnData(response.body(), Transaction::hold, HOLD.length());
    builder.addColumn(HOLD, columnHold.column(), getFormatter(columnHold.length()));

    var columnAmount = getColumnData(response.body(), Transaction::amount, AMOUNT.length());
    builder.addColumn(AMOUNT, columnAmount.column(), getFormatter(columnAmount.length()));

    var columnOperationAmount = getColumnData(response.body(), Transaction::operationAmount,
        OPERATION_AMOUNT.length());
    builder.addColumn(OPERATION_AMOUNT, columnOperationAmount.column(),
        getFormatter(columnOperationAmount.length()));

    var columnCurrency = getColumnData(response.body(), Transaction::currencyCode,
        CURRENCY.length());
    builder.addColumn(CURRENCY, columnCurrency.column(), getFormatter(columnCurrency.length()));

    var commissionRate = getColumnData(response.body(), Transaction::commissionRate,
        COMMISSION_RATE.length());
    builder.addColumn(COMMISSION_RATE, commissionRate.column(),
        getFormatter(commissionRate.length()));

    var cashbackAmount = getColumnData(response.body(), Transaction::cashbackAmount,
        CASHBACK.length());
    builder.addColumn(CASHBACK, cashbackAmount.column(), getFormatter(cashbackAmount.length()));

    var balance = getColumnData(response.body(), Transaction::balance,
        BALANCE.length());
    builder.addColumn(BALANCE, balance.column(), getFormatter(balance.length()));

    var comment = getColumnData(response.body(), Transaction::comment, COMMENT.length());
    builder.addColumn(COMMENT, comment.column(), getFormatter(comment.length()));

    var receiptId = getColumnData(response.body(), Transaction::receiptId, RECEIPT_ID.length());
    builder.addColumn(RECEIPT_ID, receiptId.column(), getFormatter(receiptId.length()));

    var invoiceId = getColumnData(response.body(), Transaction::invoiceId, INVOICE_ID.length());
    builder.addColumn(INVOICE_ID, invoiceId.column(), getFormatter(invoiceId.length()));

    var counterEdrpou = getColumnData(response.body(), Transaction::counterEdrpou,
        COUNTER_EDRPOU.length());
    builder.addColumn(COUNTER_EDRPOU, counterEdrpou.column(), getFormatter(counterEdrpou.length()));

    var counterIban = getColumnData(response.body(), Transaction::counterIban, COUNTER_IBAN.length());
    builder.addColumn(COUNTER_IBAN, counterIban.column(), getFormatter(counterIban.length()));

    var counterName = getColumnData(response.body(), Transaction::counterName, COUNTER_NAME.length());
    builder.addColumn(COUNTER_NAME, counterName.column(), getFormatter(counterName.length()));

    return builder.build().toString();
  }
}
