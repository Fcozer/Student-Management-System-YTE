package yte.obs_demo_proje_v2.demo_obs_2.exam.controller.response;

import yte.obs_demo_proje_v2.demo_obs_2.exam.entity.Exam;


import java.time.LocalTime;

public record ExamQueryModel(
        Long id,
        String examClass,
        LocalTime startTimeSlot,
        LocalTime endTimeSlot

) {

    public ExamQueryModel(Exam exam) {
        this(
                exam.getId(),
                exam.getExamClass(),
                exam.getStartTimeSlot(),
                exam.getEndTimeSlot()


        );
    }
}
