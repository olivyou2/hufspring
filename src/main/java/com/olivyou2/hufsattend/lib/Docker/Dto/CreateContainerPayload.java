package com.olivyou2.hufsattend.lib.Docker.Dto;

import lombok.Setter;

import java.util.List;

@Setter
public class CreateContainerPayload {
    public List<String> Env;
    public String Image;
    public Boolean AttachStdout = true;
}
