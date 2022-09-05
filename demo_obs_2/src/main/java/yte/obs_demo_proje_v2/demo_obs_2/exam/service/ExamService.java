package yte.obs_demo_proje_v2.demo_obs_2.exam.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import yte.obs_demo_proje_v2.demo_obs_2.lesson.entity.Lesson;
import yte.obs_demo_proje_v2.demo_obs_2.lesson.service.LessonService;
import yte.obs_demo_proje_v2.demo_obs_2.common.response.MessageResponse;
import yte.obs_demo_proje_v2.demo_obs_2.common.response.ResponseType;



import yte.obs_demo_proje_v2.demo_obs_2.exam.entity.Exam;
import yte.obs_demo_proje_v2.demo_obs_2.exam.repository.ExamRepository;


import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ExamService {

    private final ExamRepository ExamRepository;
    private final LessonService lessonService;

    public MessageResponse addExam(Exam exam) {


        ExamRepository.save(exam);

        return new MessageResponse(ResponseType.SUCCESS, "Exam has been added successfully");
    }

    public List<Exam> getAllExams() {
        return ExamRepository.findAll();
    }

    public Exam getById(Long id) {
        return ExamRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Exam not found"));
    }

    public MessageResponse deleteExamById(Long id) {
        ExamRepository.deleteById(id);
        return new MessageResponse(ResponseType.SUCCESS, "Exam has been delete successfully");

    }
    public MessageResponse updateExam(Long id, Exam updatedExam) {
        Exam Exam = ExamRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Exam not found"));



        Exam.update(updatedExam);

        ExamRepository.save(Exam);

        return new MessageResponse(ResponseType.SUCCESS, "Exam has been updated successfully");
    }
}
