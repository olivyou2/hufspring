package com.olivyou2.hufsattend.lib.Hufs;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.olivyou2.hufsattend.entity.Reservation;
import com.olivyou2.hufsattend.lib.Docker.DockerClient;
import com.olivyou2.hufsattend.lib.Http.HttpClient;
import com.olivyou2.hufsattend.lib.Request.HufsAccount;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public class HufsClient {
    final private HttpClient httpClient;
    final private DockerClient dockerClient;

    private String fromCode;
    private String os_type;
    private String app_version;

    public HufsClient() {
        this.httpClient = new HttpClient();
        this.httpClient.setEndpoint("https://hufsplusweb2.hufs.ac.kr");

        this.dockerClient = new DockerClient("http://hattend.suplitter.com:2375");

        this.setFromCode("hufsPlusAPI");
        this.setOs_type("ios");
        this.setApp_version("app_version");
    }

    private String getSha512(String payload) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-512");

            messageDigest.update(payload.getBytes());

            return String.format("%0128x", new BigInteger(1, messageDigest.digest()));
        } catch (NoSuchAlgorithmException exception) {
            return "";
        }
    }

    public List<HufsLesson> getLessons(String id, String password) throws IOException {
        String hashedPassword = getSha512(password);

        String payload = "from_code=%s".formatted(fromCode) +
                "&os_type=%s".formatted(os_type) +
                "&app_version=%s".formatted(app_version) +
                "&id=%s".formatted(id) +
                "&pwd=%s".formatted(hashedPassword) +
                "&year=%d".formatted(2023) +
                "&semester=%d".formatted(3);


        String rawResponse = this.httpClient.postRequest("/quick_service/getLectureInfoListForStudentAU_V2", payload);

        ObjectMapper objectMapper = new ObjectMapper();
        HufsLessonResponse response = objectMapper.readValue(rawResponse, HufsLessonResponse.class);

        List<HufsLesson> unfilteredLessons = Arrays.stream(response.value).map(HufsLesson::fromLessonArray).toList();
        return unfilteredLessons.stream().distinct().toList();
    }

    public String createReservation(HufsAccount account, Reservation reservation) throws IOException {
        String containerId = this.dockerClient.createContainer(
                "haback",
                Map.of(
                        "ACCOUNT", account.getId(),
                        "PASSWORD", account.getPassword(),
                        "LSSN_CD", reservation.lssn_cd,
                        "period", reservation.period,
                        "YEAR", reservation.year,
                        "SEMESTER", reservation.semester,
                        "RESERVATE", reservation.reservation_start
                )
        );

        this.dockerClient.startContainer(containerId);
        return containerId;
    }

}
