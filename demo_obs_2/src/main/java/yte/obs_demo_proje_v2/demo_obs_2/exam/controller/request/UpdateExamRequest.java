package yte.obs_demo_proje_v2.demo_obs_2.exam.controller.request;


import yte.obs_demo_proje_v2.demo_obs_2.exam.entity.Exam;
import yte.obs_demo_proje_v2.demo_obs_2.lesson.entity.Lesson;

import java.time.LocalTime;

public record UpdateExamRequest(
        String examClass,
        LocalTime startTimeSlot,
        LocalTime endTimeSlot,

        Long lessonId
) {
    public Exam toDomainEntity() {
        return new Exam(examClass,startTimeSlot,endTimeSlot, lessonId);
    }
}
