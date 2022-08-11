package yte.obs_demo_proje_v2.demo_obs_2.academician.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import yte.obs_demo_proje_v2.demo_obs_2.assistant.entity.Assistant;
import yte.obs_demo_proje_v2.demo_obs_2.common.entity.BaseEntity;
import yte.obs_demo_proje_v2.demo_obs_2.lesson.entity.Lesson;
import yte.obs_demo_proje_v2.demo_obs_2.student.entity.Student;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Academician extends BaseEntity {


    private String name;
    private String surname;
    private String username;
    private String password;

    public Academician(String name, String surname, String username, String password) {
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.password = password;
    }

    public void update(Academician updateAcademician) {
        this.name = updateAcademician.name;
        this.surname = updateAcademician.surname;
        this.password = updateAcademician.password;
    }

    @OneToMany(mappedBy = "academician")
    private Set<Lesson> lessons= new HashSet<>();

    @OneToMany(mappedBy = "academician",cascade=CascadeType.ALL)
    private Set<Assistant> assistants= new HashSet<>();


    @ManyToMany(cascade=CascadeType.ALL)
    @JoinColumn(name = "student_id")
    private Set<Student> students= new HashSet<>();
}
