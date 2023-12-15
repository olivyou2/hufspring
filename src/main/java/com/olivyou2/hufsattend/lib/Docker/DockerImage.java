package com.olivyou2.hufsattend.lib.Docker;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class DockerImage {
    
    public int Containers;
    public int Created;
    public String Id;
    public String ParentId;
    public List<String> RepoDigests;
    public List<String> RepoTags;
    public int SharedSize;
    public int Size;
    public int VirtualSize;


    @Override
    public String toString(){
        return this.Id;
    }
}
