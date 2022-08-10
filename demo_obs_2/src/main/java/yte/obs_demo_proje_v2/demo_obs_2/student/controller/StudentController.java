package yte.obs_demo_proje_v2.demo_obs_2.student.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import yte.obs_demo_proje_v2.demo_obs_2.common.response.MessageResponse;
import yte.obs_demo_proje_v2.demo_obs_2.student.controller.requests.AddStudentRequest;
import yte.obs_demo_proje_v2.demo_obs_2.student.controller.requests.UpdateStudentRequest;
import yte.obs_demo_proje_v2.demo_obs_2.student.controller.responses.StudentQueryModel;
import yte.obs_demo_proje_v2.demo_obs_2.student.service.StudentService;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/students")
@RequiredArgsConstructor
@Validated
public class StudentController {
    private final StudentService studentService;


    @PostMapping
    public MessageResponse addStudent(@Valid @RequestBody AddStudentRequest addStudentRequest) {
        return studentService.addStudent(addStudentRequest.toDomainEntity());
    }

    @GetMapping
    public List<StudentQueryModel> getAllStudents() {
        return studentService.getAllStudents()
                .stream()
                .map(student -> new StudentQueryModel(student))
                .toList();
    }


    @GetMapping("/{id}")

    public StudentQueryModel getById(@NotNull @PathVariable Long id) {
        return new StudentQueryModel(studentService.getById(id));
    }

    @DeleteMapping("/{id}")
    public MessageResponse deleteStudentById(@PathVariable @NotNull Long id) {
        return studentService.deleteStudentById(id);
    }

    @PutMapping("/{id}")
    public MessageResponse updateStudent(@Valid @RequestBody UpdateStudentRequest request, @PathVariable Long id) {
        return studentService.updateStudent(id, request.toDomainEntity());
    }
}
