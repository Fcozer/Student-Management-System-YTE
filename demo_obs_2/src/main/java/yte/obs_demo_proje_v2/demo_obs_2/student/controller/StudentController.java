package yte.obs_demo_proje_v2.demo_obs_2.student.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import yte.obs_demo_proje_v2.demo_obs_2.common.response.MessageResponse;

import yte.obs_demo_proje_v2.demo_obs_2.security.entity.Authority;
import yte.obs_demo_proje_v2.demo_obs_2.security.entity.Users;
import yte.obs_demo_proje_v2.demo_obs_2.security.repository.AuthorityRepository;
import yte.obs_demo_proje_v2.demo_obs_2.security.repository.UserRepository;
import yte.obs_demo_proje_v2.demo_obs_2.student.controller.requests.AddStudentRequest;
import yte.obs_demo_proje_v2.demo_obs_2.student.controller.requests.UpdateStudentRequest;
import yte.obs_demo_proje_v2.demo_obs_2.student.controller.responses.StudentQueryModel;
import yte.obs_demo_proje_v2.demo_obs_2.student.service.StudentService;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/students")
@RequiredArgsConstructor
@Validated
public class StudentController {
    private final StudentService studentService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthorityRepository authorityRepository;

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public MessageResponse addStudent(@Valid @RequestBody AddStudentRequest addStudentRequest) {
        Users a = new Users();
        Authority student = authorityRepository.findByAuthority("STUDENT")
                .orElseThrow(() -> new EntityNotFoundException("Authority:STUDENT not found!"));
        a.setPassword(passwordEncoder.encode(addStudentRequest.password()));

        a.setUsername(addStudentRequest.username());

        a.setAuthorities(List.of(student));

        userRepository.save(a);
        return studentService.addStudent(addStudentRequest.toDomainEntity());
    }

    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
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
    @PreAuthorize("hasAuthority('ADMIN')")
    public MessageResponse deleteStudentById(@PathVariable @NotNull Long id) {
        return studentService.deleteStudentById(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public MessageResponse updateStudent(@Valid @RequestBody UpdateStudentRequest request, @PathVariable Long id) {
        return studentService.updateStudent(id, request.toDomainEntity());
    }
}
