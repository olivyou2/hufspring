package com.olivyou2.hufsattend.lib.Hufs;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@Builder
public class HufsLesson {
    public String lesson_id;
    public String subject;

    @Override
    public boolean equals(Object destObject){
        if (destObject.getClass() != this.getClass()){
            return false;
        }
        HufsLesson destLesson = (HufsLesson) destObject;

        return this.lesson_id.equals(destLesson.lesson_id);
    }

    @Override
    public int hashCode(){
        return Objects.hashCode(getLesson_id());
    }

    public static HufsLesson fromLessonArray(String[] arr){
        return HufsLesson.builder()
                .lesson_id(arr[1])
                .subject(arr[5]).build();
    }
}
