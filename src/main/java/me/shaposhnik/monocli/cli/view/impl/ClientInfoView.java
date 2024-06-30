package me.shaposhnik.monocli.cli.view.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import me.shaposhnik.monocli.cli.view.AbstractCommandLineView;
import me.shaposhnik.monocli.core.ClientInfo;
import me.shaposhnik.monocli.mono.MonoApiResponse;

public class ClientInfoView extends AbstractCommandLineView<ClientInfo> {

  private final YAMLMapper yamlMapper;

  public ClientInfoView(MonoApiResponse<ClientInfo> response,
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
