package com.olivyou2.hufsattend.lib.Docker;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.olivyou2.hufsattend.lib.Docker.Dto.CreateContainerPayload;
import com.olivyou2.hufsattend.lib.Docker.Dto.CreateContainerResponse;
import com.olivyou2.hufsattend.lib.Http.HttpClient;
import lombok.Setter;
import org.yaml.snakeyaml.nodes.ScalarNode;
import org.yaml.snakeyaml.reader.StreamReader;

import javax.sound.sampled.AudioFormat;
import java.io.*;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class DockerClient {
    final private HttpClient httpClient;
    public DockerClient(String endpoint) {
        this.httpClient = new HttpClient();
        this.httpClient.setEndpoint(endpoint);
    }
    public List<DockerImage> getImages() throws IOException {

            String response
                    = this.httpClient.getRequest( "/images/json");

            ObjectMapper mapper = new ObjectMapper();
            return Arrays.stream(mapper.readValue(response, DockerImage[].class)).toList();

    }

    public List<DockerContainer> getContainers() throws IOException {
            String response
                    = this.httpClient.getRequest( "/containers/json?all=true");

            ObjectMapper mapper = new ObjectMapper();
            return Arrays.stream(mapper.readValue(response, DockerContainer[].class)).toList();
    }

    public String createContainer(String image, Map<String, String> environment) throws IOException {

        StringBuilder environBuilder = new StringBuilder();
        List<String> envs = new ArrayList<>();

        environment.forEach((k, v) -> envs.add(k + "=" + v));
        CreateContainerPayload payload = new CreateContainerPayload();

        payload.setEnv(envs);
        payload.setImage("haback");

        String response = this.httpClient.postRequest("/containers/create", payload);

        ObjectMapper mapper = new ObjectMapper();
        System.out.println(mapper.writeValueAsString(environment));
        CreateContainerResponse csr = mapper.readValue(response, CreateContainerResponse.class);

        return csr.Id;
    }

    public void startContainer(String Id) throws IOException {
        String response = this.httpClient.postRequest("/containers/" + Id + "/start", null);
    }

    public void killContainer(String Id) throws IOException {
        String response = this.httpClient.postRequest("/containers/" + Id + "/kill", null);
    }

    public void removeContainer(String Id) throws IOException {
        String response = this.httpClient.deleteRequest("/containers/" + Id, null);
    }

    public List<String> getContainerLog(String Id, String tail) throws IOException {
        String log = this.httpClient.getRequest("/containers/" + Id + "/logs?stdout=true&tail="+tail);
        List<String> logs = new LinkedList<>();
        ByteBuffer bb = ByteBuffer.wrap(log.getBytes());

        while (bb.hasRemaining()){
            bb.getInt();
            int size = bb.getInt() - 1;

            byte[] dest = new byte[size];
            bb.get(bb.position(), dest, 0, size);
            bb.position(bb.position() + size);
            logs.add(new String(dest, StandardCharsets.UTF_8));
        }

        return logs;
    }
}
