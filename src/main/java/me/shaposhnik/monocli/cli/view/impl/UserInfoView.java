package me.shaposhnik.monocli.cli.view.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import me.shaposhnik.monocli.cli.view.AbstractCommandLineView;
import me.shaposhnik.monocli.mono.dto.ClientInfo;
import me.shaposhnik.monocli.mono.dto.MonoApiResponse;

public class UserInfoView extends AbstractCommandLineView<ClientInfo> {

  private final YAMLMapper yamlMapper;

  public UserInfoView(MonoApiResponse<ClientInfo> response,
                      ObjectMapper objectMapper,
                      YAMLMapper yamlMapper) {
    super(response, objectMapper);
    this.yamlMapper = yamlMapper;
  }

  @Override
  public String toViewable() {
    try {
      return yamlMapper.writeValueAsString(response.body());
    } catch (JsonProcessingException e) {
      throw new IllegalStateException(e);
    }
  }
}
