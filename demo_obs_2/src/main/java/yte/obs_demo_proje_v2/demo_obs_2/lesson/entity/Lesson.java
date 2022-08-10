package yte.obs_demo_proje_v2.demo_obs_2.lesson.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import yte.obs_demo_proje_v2.demo_obs_2.academician.entity.Academician;
import yte.obs_demo_proje_v2.demo_obs_2.common.entity.BaseEntity;
import yte.obs_demo_proje_v2.demo_obs_2.student.entity.Student;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@NoArgsConstructor
public class Lesson extends BaseEntity {


    private String lessonName;
    private LocalTime timeSlot;

    public Lesson(String lessonName, LocalTime timeSlot) {
        this.lessonName = lessonName;
        this.timeSlot = timeSlot;
    }

    public void update(Lesson updatedLesson) {

        this.lessonName=updatedLesson.lessonName;
        this.timeSlot=updatedLesson.timeSlot;
    }
    @ManyToMany(cascade=CascadeType.ALL)
    @JoinColumn(name = "student_id")
    private Set<Student> students= new HashSet<>();

    @ManyToOne(fetch = FetchType.EAGER,cascade=CascadeType.ALL)
    @JoinColumn(name="academician_id",referencedColumnName = "ID")
    private Academician academician;


}