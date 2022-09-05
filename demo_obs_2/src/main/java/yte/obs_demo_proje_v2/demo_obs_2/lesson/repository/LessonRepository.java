package yte.obs_demo_proje_v2.demo_obs_2.lesson.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import yte.obs_demo_proje_v2.demo_obs_2.assistant.entity.Assistant;
import yte.obs_demo_proje_v2.demo_obs_2.lesson.entity.Lesson;

import java.util.List;
import java.util.Optional;

public interface LessonRepository extends JpaRepository<Lesson, Long> {
    Optional<Lesson> findLessonById(Long id);

    List<Lesson> findAll();
}
