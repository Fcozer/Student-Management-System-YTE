package yte.obs_demo_proje_v2.demo_obs_2.lesson.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import yte.obs_demo_proje_v2.demo_obs_2.academician.entity.Academician;
import yte.obs_demo_proje_v2.demo_obs_2.common.entity.BaseEntity;
import yte.obs_demo_proje_v2.demo_obs_2.exam.entity.Exam;
import yte.obs_demo_proje_v2.demo_obs_2.student.entity.Student;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Lesson extends BaseEntity {


    private String lessonName;
    private String timeSlot;




    public Lesson(String lessonName,String timeSlot, Long academicianId) {
        this.lessonName = lessonName;
        this.timeSlot=timeSlot;

        this.academician = new Academician();
        this.academician.setId(academicianId);
    }

    public void update(Lesson updatedLesson) {

        this.lessonName=updatedLesson.lessonName;
        this.timeSlot= updatedLesson.timeSlot;
        this.academician = updatedLesson.academician;
    }


    @ManyToOne(fetch = FetchType.LAZY)
    private Academician academician;




}