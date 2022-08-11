package yte.obs_demo_proje_v2.demo_obs_2.lesson.controller.request;


import yte.obs_demo_proje_v2.demo_obs_2.lesson.entity.Lesson;

import javax.validation.constraints.NotBlank;

import java.time.LocalTime;

public record AddLessonRequest(
        @NotBlank
        String lessonName,


        LocalTime timeSlot,


        Long academicianId
) {

    public Lesson toDomainEntity() {
        return new Lesson(lessonName,timeSlot,academicianId);
    }


}

