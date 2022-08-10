package yte.obs_demo_proje_v2.demo_obs_2.lesson.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import yte.obs_demo_proje_v2.demo_obs_2.lesson.entity.Lesson;

public interface LessonRepository extends JpaRepository<Lesson, Long> {
}
