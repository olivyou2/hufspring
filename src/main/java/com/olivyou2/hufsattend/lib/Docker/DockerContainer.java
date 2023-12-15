package com.olivyou2.hufsattend.lib.Docker;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class DockerContainer {
    public String Id;
    public List<String> Names;
    public String Image;
    public String ImageID;
    public long Create;
    public String State;
    public String Status;
}
