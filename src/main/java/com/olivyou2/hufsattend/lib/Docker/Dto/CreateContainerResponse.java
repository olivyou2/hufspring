package com.olivyou2.hufsattend.lib.Docker.Dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateContainerResponse {
    public String Id;
}
