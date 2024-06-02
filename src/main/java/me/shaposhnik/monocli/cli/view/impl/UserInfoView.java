package me.shaposhnik.monocli.cli.view.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.shaposhnik.monocli.cli.view.AbstractCommandLineView;
import me.shaposhnik.monocli.mono.dto.ClientInfo;
import me.shaposhnik.monocli.mono.dto.MonoApiResponse;

public class UserInfoView extends AbstractCommandLineView<ClientInfo> {
  public UserInfoView(MonoApiResponse<ClientInfo> response,
                      ObjectMapper objectMapper) {
    super(response, objectMapper);
  }

  @Override
  public String toTable() {
    return null;
  }
}
