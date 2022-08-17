package yte.obs_demo_proje_v2.demo_obs_2.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import yte.obs_demo_proje_v2.demo_obs_2.exam.entity.Exam;

public interface ExamRepository extends JpaRepository<Exam, Long> {
}
