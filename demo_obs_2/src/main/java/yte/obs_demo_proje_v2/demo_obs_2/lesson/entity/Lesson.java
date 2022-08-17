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
    private LocalTime startTimeSlot;
    private LocalTime endTimeSlot;



    public Lesson(String lessonName, LocalTime startTimeSlot,LocalTime endTimeSlot, Long academicianId) {
        this.lessonName = lessonName;
        this.startTimeSlot =startTimeSlot;
        this.endTimeSlot=endTimeSlot;

        this.academician = new Academician();
        this.academician.setId(academicianId);
    }

    public void update(Lesson updatedLesson) {

        this.lessonName=updatedLesson.lessonName;
        this.startTimeSlot=updatedLesson.startTimeSlot;
        this.endTimeSlot=updatedLesson.endTimeSlot;

        this.academician = updatedLesson.academician;
    }
    @ManyToMany(cascade=CascadeType.ALL)
    private Set<Student> students= new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    private Academician academician;

    @OneToMany(mappedBy = "lesson",cascade=CascadeType.ALL)
    private Set<Exam> exams= new HashSet<>();


}