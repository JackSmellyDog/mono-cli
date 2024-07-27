package me.shaposhnik.monocli.mono.converter;

import lombok.RequiredArgsConstructor;
import me.shaposhnik.monocli.core.Account;
import me.shaposhnik.monocli.mono.dto.AccountDto;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
class AccountConverter implements ListConverter<AccountDto, Account> {
  private final Iso4217CurrencyConverter iso4217CurrencyConverter;

  @Override
  public Account convert(AccountDto source) {
    return Account.builder()
        .id(source.id())
        .sendId(source.sendId())
        .balance(source.balance())
        .creditLimit(source.creditLimit())
        .type(source.type())
        .currencyCode(iso4217CurrencyConverter.convert(source.currencyCode()))
        .cashbackType(source.cashbackType())
        .maskedPan(source.maskedPan())
        .iban(source.iban())
        .build();
  }
}
