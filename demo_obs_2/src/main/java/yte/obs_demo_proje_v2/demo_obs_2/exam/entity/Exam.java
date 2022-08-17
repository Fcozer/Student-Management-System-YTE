package yte.obs_demo_proje_v2.demo_obs_2.exam.entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import yte.obs_demo_proje_v2.demo_obs_2.lesson.entity.Lesson;
import yte.obs_demo_proje_v2.demo_obs_2.common.entity.BaseEntity;
import javax.persistence.*;
import java.time.LocalTime;


@Entity
@Getter
@Setter
@NoArgsConstructor
public class Exam extends BaseEntity{



        private String examClass;
        private LocalTime startTimeSlot;
        private LocalTime endTimeSlot;



        public Exam(String examClass, LocalTime startTimeSlot,LocalTime endTimeSlot, Long lessonId) {
            this.examClass = examClass;
            this.startTimeSlot =startTimeSlot;
            this.endTimeSlot=endTimeSlot;

            this.lesson = new Lesson();
            this.lesson.setId(lessonId);
        }

        public void update(Exam updatedExam) {

            this.examClass=updatedExam.examClass;
            this.startTimeSlot=updatedExam.startTimeSlot;
            this.endTimeSlot=updatedExam.endTimeSlot;

            this.lesson = updatedExam.lesson;
        }
        @ManyToOne(fetch = FetchType.LAZY)
        private Lesson lesson;


    }

