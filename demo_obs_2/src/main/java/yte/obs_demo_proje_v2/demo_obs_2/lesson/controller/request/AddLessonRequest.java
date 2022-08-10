package yte.obs_demo_proje_v2.demo_obs_2.lesson.controller.request;


import yte.obs_demo_proje_v2.demo_obs_2.lesson.entity.Lesson;

import javax.validation.constraints.NotBlank;

import java.time.LocalTime;

public record AddLessonRequest(
        @NotBlank
        String lessonName,


        @NotBlank
        LocalTime timeSlot
) {

    public Lesson toDomainEntity() {
        return new Lesson(lessonName,timeSlot);
    }


}
