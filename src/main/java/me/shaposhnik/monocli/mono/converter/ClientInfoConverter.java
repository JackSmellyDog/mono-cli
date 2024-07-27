package me.shaposhnik.monocli.mono.converter;

import lombok.RequiredArgsConstructor;
import me.shaposhnik.monocli.core.ClientInfo;
import me.shaposhnik.monocli.mono.dto.ClientInfoDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
class ClientInfoConverter implements Converter<ClientInfoDto, ClientInfo> {
  private final AccountConverter accountConverter;
  private final JarConverter jarConverter;

  @Override
  public ClientInfo convert(ClientInfoDto source) {
    return ClientInfo.builder()
        .clientId(source.clientId())
        .name(source.name())
        .webHookUrl(source.webHookUrl())
        .permissions(source.permissions())
        .accounts(accountConverter.convert(source.accounts()))
        .jars(jarConverter.convert(source.jars()))
        .build();
  }
}
