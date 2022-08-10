package yte.obs_demo_proje_v2.demo_obs_2.lesson.controller.response;

import yte.obs_demo_proje_v2.demo_obs_2.lesson.entity.Lesson;



import java.time.LocalTime;

public record LessonQueryModel(
        
      String lessonName,
      LocalTime timeSlot
) {

    public LessonQueryModel(Lesson lesson) {
        this(

                lesson.getLessonName(),
                lesson.getTimeSlot()

        );
    }
}
