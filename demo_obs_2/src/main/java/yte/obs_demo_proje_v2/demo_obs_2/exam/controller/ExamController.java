package yte.obs_demo_proje_v2.demo_obs_2.exam.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import yte.obs_demo_proje_v2.demo_obs_2.common.entity.BaseEntity;
import yte.obs_demo_proje_v2.demo_obs_2.common.response.MessageResponse;

import yte.obs_demo_proje_v2.demo_obs_2.exam.controller.request.AddExamRequest;
import yte.obs_demo_proje_v2.demo_obs_2.exam.controller.request.UpdateExamRequest;
import yte.obs_demo_proje_v2.demo_obs_2.exam.controller.response.ExamQueryModel;
import yte.obs_demo_proje_v2.demo_obs_2.exam.service.ExamService;



import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/Exams")
@RequiredArgsConstructor
@Validated
public class ExamController extends BaseEntity {
   
    private final ExamService ExamService;


    @PostMapping
    public MessageResponse addExam(@Valid @RequestBody AddExamRequest addExamRequest) {
        return ExamService.addExam(addExamRequest.toDomainEntity());
    }

    @GetMapping
    public List<ExamQueryModel> getAllExams() {
        return ExamService.getAllExams()
                .stream()
                .map(Exam -> new ExamQueryModel(Exam))
                .toList();
    }


    @GetMapping("/{id}")

    public ExamQueryModel getById(@NotNull @PathVariable Long id) {
        return new ExamQueryModel(ExamService.getById(id));
    }

    @DeleteMapping("/{id}")
    public MessageResponse deleteExamById(@PathVariable @NotNull Long id) {
        return ExamService.deleteExamById(id);
    }

    @PutMapping("/{id}")
    public MessageResponse updateExam(@Valid @RequestBody UpdateExamRequest request, @PathVariable Long id) {
        return ExamService.updateExam(id, request.toDomainEntity());
    }
}


