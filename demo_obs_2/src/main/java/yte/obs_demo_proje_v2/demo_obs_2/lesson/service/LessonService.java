package yte.obs_demo_proje_v2.demo_obs_2.lesson.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import yte.obs_demo_proje_v2.demo_obs_2.common.response.MessageResponse;
import yte.obs_demo_proje_v2.demo_obs_2.common.response.ResponseType;

 

import yte.obs_demo_proje_v2.demo_obs_2.lesson.entity.Lesson;
import yte.obs_demo_proje_v2.demo_obs_2.lesson.repository.LessonRepository;


import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LessonService {

    private final LessonRepository LessonRepository;

    public MessageResponse addLesson(Lesson lesson) {
        LessonRepository.save(lesson);

        return new MessageResponse(ResponseType.SUCCESS, "Lesson has been added successfully");
    }

    public List<Lesson> getAllLessons() {
        return LessonRepository.findAll();
    }

    public Lesson getById(Long id) {
        return LessonRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Lesson not found"));
    }

    public MessageResponse deleteLessonById(Long id) {
        LessonRepository.deleteById(id);
        return new MessageResponse(ResponseType.SUCCESS, "Lesson has been delete successfully");

    }
    public MessageResponse updateLesson(Long id, Lesson updatedLesson) {
        Lesson Lesson = LessonRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Lesson not found"));

        Lesson.update(updatedLesson);

        LessonRepository.save(Lesson);

        return new MessageResponse(ResponseType.SUCCESS, "Lesson has been updated successfully");
    }
}
