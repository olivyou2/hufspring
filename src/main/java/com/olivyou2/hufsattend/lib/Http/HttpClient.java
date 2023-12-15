package com.olivyou2.hufsattend.lib.Http;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.olivyou2.hufsattend.lib.Docker.DockerImage;
import lombok.Getter;
import lombok.Setter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class HttpClient{
    private String endpoint;

    public String getRequest(String uri) throws IOException {

        URL url = URI.create(this.endpoint + uri).toURL();
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        connection.setRequestMethod("GET");

        int responseCode = connection.getResponseCode();

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder stringBuffer = new StringBuilder();
        String inputLine;

        while ((inputLine = bufferedReader.readLine()) != null)  {
            stringBuffer.append(inputLine);
        }
        bufferedReader.close();
        return stringBuffer.toString();
    }
    public String postRequest(String uri, String payload) throws IOException {

        URL url = URI.create(this.endpoint + uri).toURL();
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        connection.setRequestMethod("POST");

        if (payload != null){
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            connection.setDoOutput(true);

            connection.getOutputStream().write(payload.getBytes());
        }

        try {
            int responseCode = connection.getResponseCode();

            return new String(connection.getInputStream().readAllBytes());
        }catch (IOException e){
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
            StringBuilder stringBuffer = new StringBuilder();
            String inputLine;

            while ((inputLine = bufferedReader.readLine()) != null) {
                stringBuffer.append(inputLine);
            }

            System.out.println(stringBuffer.toString());
        }

        return "";
    }
    public String postRequest(String uri, Object payload) throws IOException {

        URL url = URI.create(this.endpoint + uri).toURL();
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        ObjectMapper mapper = new ObjectMapper();

        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setDoOutput(true);

        mapper.writeValue(
                connection.getOutputStream(),
                payload
        );

        try {
            int responseCode = connection.getResponseCode();

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder stringBuffer = new StringBuilder();
            String inputLine;

            while ((inputLine = bufferedReader.readLine()) != null) {
                stringBuffer.append(inputLine);
            }
            bufferedReader.close();
            return stringBuffer.toString();
        }catch (IOException e){
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
            StringBuilder stringBuffer = new StringBuilder();
            String inputLine;

            while ((inputLine = bufferedReader.readLine()) != null) {
                stringBuffer.append(inputLine);
            }

            System.out.println(stringBuffer.toString());
        }

        return "";
    }
    public String deleteRequest(String uri, Object payload) throws IOException {

        URL url = URI.create(this.endpoint + uri).toURL();
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        ObjectMapper mapper = new ObjectMapper();

        connection.setRequestMethod("DELETE");

        try {
            int responseCode = connection.getResponseCode();

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder stringBuffer = new StringBuilder();
            String inputLine;

            while ((inputLine = bufferedReader.readLine()) != null) {
                stringBuffer.append(inputLine);
            }
            bufferedReader.close();
            return stringBuffer.toString();
        }catch (IOException e){
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
            StringBuilder stringBuffer = new StringBuilder();
            String inputLine;

            while ((inputLine = bufferedReader.readLine()) != null) {
                stringBuffer.append(inputLine);
            }

            System.out.println(stringBuffer.toString());
        }

        return "";
    }
}
