package me.shaposhnik.monocli.mono.converter;

import java.util.Collections;
import java.util.List;
import lombok.RequiredArgsConstructor;
import me.shaposhnik.monocli.core.Account;
import me.shaposhnik.monocli.mono.dto.AccountDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
class AccountConverter implements Converter<AccountDto, Account> {
  private final Iso4217CurrencyConverter iso4217CurrencyConverter;

  @Override
  public Account convert(AccountDto source) {
    return new Account(
        source.id(),
        source.sendId(),
        source.balance(),
        source.creditLimit(),
        source.type(),
        iso4217CurrencyConverter.convert(source.currencyCode()),
        source.cashbackType(),
        source.maskedPan(),
        source.iban()
    );
  }

  public List<Account> convert(List<AccountDto> sourceList) {
    if (sourceList == null) {
      return Collections.emptyList();
    }

    return sourceList.stream()
        .map(this::convert)
        .toList();
  }
}
