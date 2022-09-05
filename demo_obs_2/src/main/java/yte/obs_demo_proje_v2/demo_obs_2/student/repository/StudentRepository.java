package yte.obs_demo_proje_v2.demo_obs_2.student.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import yte.obs_demo_proje_v2.demo_obs_2.student.entity.Student;

import java.util.List;
import java.util.Optional;

public interface StudentRepository  extends JpaRepository<Student, Long> {
    Optional<Student> findStudentById(Long id);

    List<Student> findAll();
}
