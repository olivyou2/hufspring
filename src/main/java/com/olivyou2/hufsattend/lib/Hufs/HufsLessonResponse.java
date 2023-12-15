package com.olivyou2.hufsattend.lib.Hufs;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class HufsLessonResponse {
    public String status;
    public String issue_date;
    public String[][] value;
}
