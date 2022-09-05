package yte.obs_demo_proje_v2.demo_obs_2.security;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import yte.obs_demo_proje_v2.demo_obs_2.academician.entity.Academician;
import yte.obs_demo_proje_v2.demo_obs_2.academician.repository.AcademicianRepository;
import yte.obs_demo_proje_v2.demo_obs_2.assistant.entity.Assistant;
import yte.obs_demo_proje_v2.demo_obs_2.assistant.repository.AssistantRepository;
import yte.obs_demo_proje_v2.demo_obs_2.security.entity.Users;
import yte.obs_demo_proje_v2.demo_obs_2.student.entity.Student;
import yte.obs_demo_proje_v2.demo_obs_2.student.repository.StudentRepository;

@Component
public class AuthenticationService {


    private AcademicianRepository academicianRepository;

    private StudentRepository studentRepository;

    private AssistantRepository assistantRepository;


    public Academician academician() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof Users) {
            Users users = (Users) principal;
            return academicianRepository.findAcademicianById(users.getId()).orElseThrow(RuntimeException::new);
        }

        throw new RuntimeException();
    }

    public Student student() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof Users) {
            Users users = (Users) principal;
            return studentRepository.findStudentById(users.getId()).orElseThrow(RuntimeException::new);
        }

        throw new RuntimeException();
    }

    public Assistant assistant() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof Users) {
            Users users = (Users) principal;
            return assistantRepository.findAssistantById(users.getId()).orElseThrow(RuntimeException::new);
        }

        throw new RuntimeException();
    }

}
